package com.llong.myappgym.PreSenter.Interface;

public interface IUserModel {
    void OnValid();

    void OnSucess();

    void OnFail();

    void OnSendEmailSucess();

    void getDataHistory(String title, String ngay, String thoigian, String noidung);
}
