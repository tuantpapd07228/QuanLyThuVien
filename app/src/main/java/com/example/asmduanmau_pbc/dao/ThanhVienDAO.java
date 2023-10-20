package com.example.asmduanmau_pbc.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.asmduanmau_pbc.database.DbHelper;
import com.example.asmduanmau_pbc.model.ThanhVien;

import java.util.ArrayList;
import java.util.List;

public class ThanhVienDAO {
    private static final String TABLENAME = "ThanhVien";
    SQLiteDatabase db;
    DbHelper dbHelper;

    public ThanhVienDAO(Context context) {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public int insert (ThanhVien thanhVien){
        ContentValues val = new ContentValues();
        val.put("hoTen", thanhVien.getHoTen());
        val.put("namSinh", thanhVien.getNamSinh());
        if ((db.insert(TABLENAME , null, val)) > 0){
//            Log.d("ghithanhcong", "insert tahnhcong");
            return 1;
        }
        return  -1;
    }
    public int update (ThanhVien thanhVien){
        db = dbHelper.getWritableDatabase();
        ContentValues val = new ContentValues();
        val.put("hoTen", thanhVien.getHoTen());
        val.put("namSinh", thanhVien.getNamSinh());
        return db.update(TABLENAME , val, "maTV=?", new String[]{String.valueOf(thanhVien.getMaTV())});
    }
    public int delete (String id){
        return db.delete(TABLENAME , "maTV = ?", new String[]{id} );
    }
    @SuppressLint("Range")
    public List<ThanhVien> getData(String sql, String...selecttionArgs){
//        db = dbHelper.getReadableDatabase();
        List<ThanhVien> list = new ArrayList<>();
//        Cursor c = db.rawQuery(sql, selecttionArgs);
////        Cursor c = db.query(TABLENAME , null, null,null,null,null,null);
//        Log.d("addthanhcong1111111" , "thanhcong "+ c.getCount());
//        while(c.moveToNext()){
//            ThanhVien thanhVien = new ThanhVien(
//                    Integer.parseInt(c.getString(c.getColumnIndex("maTV"))),
//                    c.getString(c.getColumnIndex("hoTen")),
//                    c.getString(c.getColumnIndex("namSinh"))
//            );
//            list.add(thanhVien);
//            Log.d("addthanhcong" , "thanhcong"+list.size());
//        }
//        c.close();
            SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery(sql,selecttionArgs);
            if (cursor.getCount() !=0){
                cursor.moveToFirst();
                do {

                    list.add(new ThanhVien(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));
                }while (cursor.moveToNext());
            }
        return list;
    }
    public List<ThanhVien> getALL(){
        String sql = "select * from thanhvien";
        return getData(sql);
    }
    public ThanhVien getThanhVienByID (String id){
        String sql = "select * from thanhvien where maTV=?";
        return getData(sql, id).get(0);
    }

}
