package com.llong.myappgym.Model;

public class HistoryModel {
    private String title;
    private String ngay;
    private String noidung;
    private String thoigian;

    public HistoryModel() {

    }

    public HistoryModel(String title, String ngay, String noidung, String thoigian) {
        this.title = title;
        this.ngay = ngay;
        this.noidung = noidung;
        this.thoigian = thoigian;
    }

    public String getNoidung() {
        return noidung;
    }

    public String getNgay() {
        return ngay;
    }

    public String getThoigian() {
        return thoigian;
    }

    public String getTitle() {
        return title;
    }
}
