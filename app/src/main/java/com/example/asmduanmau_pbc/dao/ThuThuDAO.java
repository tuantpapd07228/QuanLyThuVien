package com.example.asmduanmau_pbc.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asmduanmau_pbc.database.DbHelper;
import com.example.asmduanmau_pbc.model.ThuThu;

import java.util.ArrayList;
import java.util.List;

public class ThuThuDAO {
    private static final String TABLENAME = "ThuThu";
    private SQLiteDatabase db;

    public ThuThuDAO(Context context) {
        DbHelper helper = new DbHelper(context);
        db =  helper.getWritableDatabase();
    }

    public int insert (ThuThu thuThu){
        ContentValues val = new ContentValues();
        val.put("maTT" , thuThu.getMaTT());
        val.put("hoTen" , thuThu.getHoTen());
        val.put("matKhau" , thuThu.getMatKhau());
        return (int) db.insert(TABLENAME, null, val);
    }
    public int update(ThuThu thuThu){
        ContentValues val = new ContentValues();
        val.put("hoTen" , thuThu.getHoTen());
        val.put("matKhau" , thuThu.getMatKhau());
        return db.update(TABLENAME, val, "maTT =?", new String[]{String.valueOf(thuThu.getMaTT())});
    }
    public int delete (String id){
        return db.delete(TABLENAME, "maTT = ?", new String[]{id});
    }
    public List<ThuThu> getData(String sql, String...selectArgs){
        Cursor c = db.rawQuery(sql, selectArgs);
        List<ThuThu> list = new ArrayList<>();
        while(c.moveToNext()){
            ThuThu thuthu = new ThuThu(
                    c.getString(0),
                    c.getString(1),
                    c.getString(2)
            );
            list.add(thuthu);
        }
        c.close();
        return list;
    }

    public List<ThuThu> getALL (){
        return getData("select * from thuthu");
    }
    public ThuThu getById(String id){
        return getData("select * from thuthu where matt = ?", id).get(0);
    }
    public ThuThu checkLogin(String id , String pw){
        String sql = "select * from thuthu where matt = ? and matKhau = ?";
        ThuThu thuThu = getData(sql, id, pw).get(0);
        return thuThu;
    }
}
