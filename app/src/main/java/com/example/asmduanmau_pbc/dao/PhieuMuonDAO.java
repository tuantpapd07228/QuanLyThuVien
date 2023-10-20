package com.example.asmduanmau_pbc.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.asmduanmau_pbc.database.DbHelper;
import com.example.asmduanmau_pbc.model.PhieuMuon;
import com.example.asmduanmau_pbc.model.Sach;
import com.example.asmduanmau_pbc.model.Top;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class PhieuMuonDAO {
    private SQLiteDatabase db;
    private Context context;
    private static final String TABLENAME = "PhieuMuon";
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public PhieuMuonDAO(Context context) {
        this.context = context;
        DbHelper helper = new DbHelper(context);
        db= helper.getWritableDatabase();
    }

    public int insert (PhieuMuon phieuMuon){
        ContentValues val = new ContentValues();
        val.put("maTT" , phieuMuon.getMaTT());
        val.put("maSach" , phieuMuon.getMaSach());
        val.put("ngay" ,sdf.format( phieuMuon.getNgay()));
        val.put("tienThue" , phieuMuon.getTienThue());
        val.put("maTV" , phieuMuon.getMaTV());
        val.put("traSach" , phieuMuon.getStattus());
        return (int) db.insert(TABLENAME, null, val);

    }

    public int update(PhieuMuon phieuMuon){
        ContentValues val = new ContentValues();
        val.put("maTT" , phieuMuon.getMaTT());
        val.put("maSach" , phieuMuon.getMaSach());
        val.put("maTV" , phieuMuon.getMaTV());
        val.put("ngay" ,sdf.format( phieuMuon.getNgay()));
        val.put("tienThue" , phieuMuon.getTienThue());
        val.put("traSach" , phieuMuon.getStattus());
        return db.update(TABLENAME, val, "maPM=?", new String[]{String.valueOf(phieuMuon.getMaPM())});
    }
    public int delete (String id){
        return db.delete(TABLENAME, "maPM=?", new String[]{id});
    }
    //    private int maPM;
//    private String maTT;
//    private int maTV;
//    private int maSach;
//    private Date ngay;
//    private int tienThue;
//    private int traSach;
    public List<PhieuMuon> getData(String sql, String...selectArgs){
        Cursor c = db.rawQuery(sql, selectArgs);
        List<PhieuMuon> list = new ArrayList<>();
        while(c.moveToNext()){
            PhieuMuon phieuMuon = null;
            try {
                phieuMuon = new PhieuMuon(
                        c.getInt(0),
                        c.getString(1),
                        c.getInt(2),
                        c.getInt(3),
                        sdf.parse(c.getString(5)),
                        c.getInt(4),
                        c.getInt(6)
                );

            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            list.add(phieuMuon);
        }
        c.close();
        return list;
    }

    public List<PhieuMuon> getALL (){
        return getData("select * from " +TABLENAME);
    }
    public PhieuMuon getById(String id){
        return getData("select * from phieumuon where maPM = ?", id).get(0);
    }
    @SuppressLint("Range")
    public List<Top> getDataTop(){
        String sqltop = "select masach, count(masach) as soluong from phieumuon group by masach order by soluong desc limit 10";
        Cursor c = db.rawQuery(sqltop, null);
        SachDAO dao = new SachDAO(context);
        List<Top> list = new ArrayList<>();
        while(c.moveToNext()){
            Top top = new Top();
           Sach sachDAO = dao.getById(c.getString(c.getColumnIndex("maSach")));
           top.setTenSach(sachDAO.getTenSach());
           top.setSoLuong(Integer.parseInt(c.getString(c.getColumnIndex("soluong"))));
            list.add(top);
        }
        c.close();
        return list;
    }
    @SuppressLint("Range")
    public int getDoanhThu(String tungay, String denngay) {
        int doanhthu = 0;
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
            SimpleDateFormat sqlDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Date fromDate = inputFormat.parse(tungay);
            Date toDate = inputFormat.parse(denngay);

            String sqlFromDate = sqlDateFormat.format(fromDate);
            String sqlToDate = sqlDateFormat.format(toDate);

            String sql ;
            Cursor c;

            if (tungay.equals(denngay)) {
                sql = "SELECT sum(tienThue) FROM phieumuon WHERE ngay = ?";
                c = db.rawQuery(sql, new String[]{sqlFromDate});
            } else {
                sql = "SELECT sum(tienThue) FROM phieumuon WHERE ngay BETWEEN ? AND ?";
                c = db.rawQuery(sql, new String[]{sqlFromDate, sqlToDate});
            }

            if (c.moveToFirst()) {
                doanhthu = c.getInt(0);
            }
            c.close();
        } catch (Exception e) {
            Log.e("atuan err 123", e.getMessage());
        }
        return doanhthu;
    }

}
