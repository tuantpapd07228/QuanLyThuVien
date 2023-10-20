package com.example.asmduanmau_pbc.model;

import java.util.Date;

public class PhieuMuon {
    private int maPM;
    private String maTT;
    private int maTV;
    private int maSach;
    private Date ngay;
    private int tienThue;
    private int stattus;



    public PhieuMuon(int maPM, String maTT, int maTV, int maSach, Date ngay, int tienThue, int traSach) {
        this.maPM = maPM;
        this.maTT = maTT;
        this.maTV = maTV;
        this.maSach = maSach;
        this.ngay = ngay;
        this.tienThue = tienThue;
        this.stattus = traSach;
    }

    public PhieuMuon() {

    }

    public int getMaPM() {
        return maPM;
    }

    public void setMaPM(int maPM) {
        this.maPM = maPM;
    }

    public String getMaTT() {
        return maTT;
    }

    public void setMaTT(String maTT) {
        this.maTT = maTT;
    }

    public int getMaTV() {
        return maTV;
    }

    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public int getTienThue() {
        return tienThue;
    }

    public void setTienThue(int tienThue) {
        this.tienThue = tienThue;
    }

    public int getStattus() {
        return stattus;
    }

    public void setStattus(int stattus) {
        this.stattus = stattus;
    }

    @Override
    public String toString() {
        return "PhieuMuon{" +
                "maPM=" + maPM +
                ", maTT='" + maTT + '\'' +
                ", maTV=" + maTV +
                ", maSach=" + maSach +
                ", ngay=" + ngay +
                ", tienThue=" + tienThue +
                ", traSach=" + stattus +
                '}';
    }
}
