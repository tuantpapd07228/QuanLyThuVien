package com.example.asmduanmau_pbc.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DataName = "LibDatabase";
    static final String ThuThu = "CREATE TABLE ThuThu (maTT TEXT PRIMARY KEY,hoTen   TEXT NOT NULL,matKhau TEXT NOT NULL )";
    static final String LoaiSach = "CREATE TABLE LoaiSach ( maLoai INTEGER PRIMARY KEY AUTOINCREMENT,tenLoai TEXT NOT NULL)";
    static final String ThanhVien = "CREATE TABLE ThanhVien ( maTV integer PRIMARY KEY autoincrement,hoTen TEXT NOT NULL,namSinh TEXT NOT NULL);";
    static final String Sach = "CREATE TABLE Sach (maSach  INTEGER PRIMARY KEY AUTOINCREMENT,tenSach TEXT NOT NULL,giaThue INTEGER NOT NULL," +
            "    maLoai  INTEGER REFERENCES LoaiSach (maloai) " +
            ");";
    static final String PhieuMuon = "CREATE TABLE PhieuMuon (" +
            "    maPM     INTEGER PRIMARY KEY AUTOINCREMENT," +
            "    maTT     TEXT    REFERENCES ThuThu (maTT)," +
            "    maTV     TEXT    REFERENCES ThanhVien (maTV)," +
            "    maSach   INTEGER REFERENCES Sach (maSach)," +
            "    tienThue INTEGER NOT NULL," +
            "    ngay     DATE    NOT NULL," +
            "    traSach  INTEGER NOT NULL" +
            ");";
    public DbHelper(@Nullable Context context) {
        super(context, DataName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE Orders (\n" +
                "    OrderID INTEGER PRIMARY KEY,\n" +
                "    CustomerID INTEGER,\n" +
                "    OrderDate DATE\n" +
                ");");
        sqLiteDatabase.execSQL(ThuThu);
        sqLiteDatabase.execSQL(ThanhVien);
        sqLiteDatabase.execSQL(LoaiSach);
        sqLiteDatabase.execSQL(Sach);
        sqLiteDatabase.execSQL(PhieuMuon);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if (i != i1){
            sqLiteDatabase.execSQL("drop table if exists ThuThu");
            sqLiteDatabase.execSQL("drop table if exists LoaiSach");
            sqLiteDatabase.execSQL("drop table if exists Sach");
            sqLiteDatabase.execSQL("drop table if exists ThanhVien");
            sqLiteDatabase.execSQL("drop table if exists PhieuMuon");
            onCreate(sqLiteDatabase);

        }
    }
}



//
////public class DbHelper extends SQLiteOpenHelper {
//    public DbHelper(@Nullable Context context) {
//        super(context,"QUANLYTHUVIEN",null, 1);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        //taoj bảng thu thu--------------------------------------------------------
//        String dbThuThu = "CREATE TABLE THUTHU(matt text primary key, hoten text, matkhau text)";
//        sqLiteDatabase.execSQL(dbThuThu);
//        //tạo bảng thành viên------------------------------------------------------------
//        String dbThanhVien = " CREATE TABLE THANHVIEN( matv integer primary key autoincrement, hoten text, namsinh text)";
//        sqLiteDatabase.execSQL(dbThanhVien);
//        //tạo bảng loại sách và sách----------------------------------------------------------------------------
//        String dbLoai = "CREATE TABLE lOAISACH( maloai integer primary key autoincrement, tenloai text)";
//        sqLiteDatabase.execSQL(dbLoai);
//
//        String dbSach = "CREATE TABLE SACH(masach integer primary key autoincrement, tensach text, giathue interger, maloai integer references LOAISACH(maloai))";
//        sqLiteDatabase.execSQL(dbSach);
//
//        //tạo bảng phiếu mượn///////////////////////////////////////////////
//        String dbPhieuMuon = "CREATE TABLE PHIEUMUON(mapm integer primary key autoincrement,matv integer references THANHVIEN(matv), matt text references THUTHU(matt),masach integer references SACH(masach), ngay text, trasach integer, tienthue integer)";
//        sqLiteDatabase.execSQL(dbPhieuMuon);
//        // maux data test
//
//        sqLiteDatabase.execSQL("INSERT INTO LOAISACH VALUES (1, 'Thiếu nhi'),(2,'Tình cảm'),(3, 'Giáo khoa')");
//        sqLiteDatabase.execSQL("INSERT INTO SACH VALUES (1, 'Hãy đợi đấy', 2500, 1), (2, 'Thằng cuội', 1000, 1), (3, 'Lập trình Android', 2000, 3)");
//        sqLiteDatabase.execSQL("INSERT INTO THUTHU VALUES ('thuthu01','Nguyễn Văn Anh','abc123'),('thuthu02','Trần Văn Hùng','123abc')");
//        sqLiteDatabase.execSQL("INSERT INTO THANHVIEN VALUES (1,'Cao Thu Trang','2000'),(2,'Trần Mỹ Kim','2000')");
//        //trả sách: 1: đã trả - 0: chưa trả
//        sqLiteDatabase.execSQL("INSERT INTO PHIEUMUON VALUES (1,1,'thuthu01', 1, '19/03/2022', 1, 2500),(2,1,'thuthu01', 3, '19/03/2022', 0, 2000),(3,2,'thuthu02', 1, '19/03/2022', 1, 2000)");
//
//
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//        if (i != i1){
//            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS THUTHU");
//            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS THANHVIEN");
//            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS LOAISACH");
//            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS SACH");
//            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS PHIEUMUON");
//            onCreate(sqLiteDatabase);
//        }
//    }
//}
//

