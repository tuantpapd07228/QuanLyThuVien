package com.example.asmduanmau_pbc.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.asmduanmau_pbc.database.DbHelper;
import com.example.asmduanmau_pbc.model.LoaiSach;
import com.example.asmduanmau_pbc.model.PhieuMuon;
import com.example.asmduanmau_pbc.model.Sach;
import com.example.asmduanmau_pbc.model.ThanhVien;
import com.example.asmduanmau_pbc.model.ThuThu;
import com.example.asmduanmau_pbc.model.Top;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DemoDao {
    private SQLiteDatabase db;
    PhieuMuonDAO dao;
//    Context context;
    public DemoDao(Context context) {
        DbHelper helper =  new DbHelper(context);
        db = helper.getWritableDatabase();
        dao = new PhieuMuonDAO(context);
    }
    public void testthanhvien(){
//        maPM     INTEGER PRIMARY KEY AUTOINCREMENT," +
//        "    maTT     TEXT    REFERENCES ThuThu (maTT)," +
//                "    maTV     TEXT    REFERENCES ThanhVien (maTV)," +
//                "    maSach   INTEGER REFERENCES Sach (maSach)," +
//                "    tienThue INTEGER NOT NULL," +
//                "    ngay     DATE    NOT NULL," +
//                "    traSach  INTEGER NOT NULL"
//        this.maPM = maPM;
//        this.maTT = maTT;
//        this.maTV = maTV;
//        this.maSach = maSach;
//        this.ngay = ngay;
//        this.tienThue = tienThue;
//        this.traSach = traSach;
//        Date date = new Date();
//        Log.d("phieumuon" , date+"");
//        PhieuMuon pm = new PhieuMuon(0, "tt01", 0, 0, date, 15000, 123 );
        Top top = new Top();
        try{
//            dao.insert(pm);
            Log.d("phieumuon" , dao.getDataTop().get(0).toString());

        }catch (Exception e){
            Log.e("phieumuonerr", e.getMessage());
        }
    }
}
