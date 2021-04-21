package com.llong.myappgym.PreSenter.Interface;

public interface UserView {
    void OnValid();

    void OnSucess();

    void OnFail();

    void OnEmailSucess();

    void getDataHistory(String title, String ngay, String thoigian, String noidung);
}
