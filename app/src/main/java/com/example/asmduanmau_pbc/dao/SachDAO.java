package com.example.asmduanmau_pbc.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asmduanmau_pbc.database.DbHelper;
import com.example.asmduanmau_pbc.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class SachDAO {
    private static final String TABLENAME = "Sach";
    private SQLiteDatabase db;

    public SachDAO(Context context) {
        DbHelper helper = new DbHelper(context);
        db = helper.getWritableDatabase();
    }
//    private int maSach;
//    private String tenSach;
//    private int giaThue;
//    private int maLoai;
    public int insert (Sach sach){
        ContentValues val = new ContentValues();
        val.put("tenSach" , sach.getTenSach());
        val.put("giaThue" , sach.getGiaThue());
        val.put("maLoai" , sach.getMaLoai());

        return (int) db.insert(TABLENAME, null, val);

    }

    public int update(Sach sach){
        ContentValues val = new ContentValues();
        val.put("tenSach" , sach.getTenSach());
        val.put("giaThue" , sach.getGiaThue());
        val.put("maLoai" , sach.getMaLoai());
        return db.update(TABLENAME, val, "maSach =?", new String[]{String.valueOf(sach.getMaSach())});
    }
    public int delete (String id){
        return db.delete(TABLENAME, "maSach = ?", new String[]{id});
    }
    public List<Sach> getData(String sql, String...selectArgs){
        Cursor c = db.rawQuery(sql, selectArgs);
        List<Sach> list = new ArrayList<>();
        while(c.moveToNext()){
            Sach sach = new Sach(
                    c.getInt(0),
                    c.getString(1),
                    c.getInt(2),
                    c.getInt(3)
            );
            list.add(sach);
        }
        c.close();
        return list;
    }

    public List<Sach> getALL (){
        return getData("select * from sach");
    }
    public Sach getById(String id){
        return getData("select * from sach where masach = ?", id).get(0);
    }

}
