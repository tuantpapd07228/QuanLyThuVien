package com.example.asmduanmau_pbc.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asmduanmau_pbc.database.DbHelper;
import com.example.asmduanmau_pbc.model.LoaiSach;
import com.example.asmduanmau_pbc.model.ThuThu;

import java.util.ArrayList;
import java.util.List;

public class LoaiSachDAO {
    private static final String TABLENAME = "LoaiSach";
    private SQLiteDatabase db;

    public LoaiSachDAO(Context context) {
        DbHelper helper = new DbHelper(context);
        db = helper.getWritableDatabase();
    }
    public int insert (LoaiSach loaiSach){
        ContentValues val = new ContentValues();
        val.put("tenLoai" , loaiSach.getTenLoai());
        return (int) db.insert(TABLENAME, null, val);
    }
    public int update(LoaiSach loaiSach){
        ContentValues val = new ContentValues();
        val.put("tenLoai" , loaiSach.getTenLoai());
        return db.update(TABLENAME, val, "maLoai =?", new String[]{String.valueOf(loaiSach.getMaLoai())});
    }
    public int delete (String id){
        return db.delete(TABLENAME, "maLoai = ?", new String[]{id});
    }
    public List<LoaiSach> getData(String sql, String...selectArgs){
        Cursor c = db.rawQuery(sql, selectArgs);
        List<LoaiSach> list = new ArrayList<>();
        while(c.moveToNext()){
            LoaiSach loaiSach = new LoaiSach(
                    c.getInt(0),
                    c.getString(1)
            );
            list.add(loaiSach);
        }
        c.close();
        return list;
    }

    public List<LoaiSach> getALL (){
        return getData("select * from loaisach");
    }
    public LoaiSach getById(String id){
        return getData("select * from loaisach where maloai = ?", id).get(0);
    }

}
