package com.llong.myappgym.PreSenter;

import com.llong.myappgym.Model.UserModel;
import com.llong.myappgym.PreSenter.Interface.IUserModel;
import com.llong.myappgym.PreSenter.Interface.UserView;

public class UserPreSenter  implements IUserModel {
    private UserModel userModel;
    private UserView callback;
    public UserPreSenter(UserView callback){
        this.callback=callback;
        userModel=new UserModel(this);
    }
    public  void HandleSignUp(String Email,String Pass){
        userModel.HandleSignUp(Email,Pass);
    }
    public  void HandleLogin(String Email,String Pass){
        userModel.HandleLogin(Email,Pass);
    }
    public  void HandleInsertUser(String title,String noidung,String thoigan,String ngay,String Chude){
        userModel.SetData(title,noidung,thoigan,ngay,Chude);
    }
    public  void HandlegetDataHistory(String KEY){
        userModel.HandleReadDataHistoryExcers(KEY);
    }
    @Override
    public void OnValid() {
        callback.OnValid();
    }

    @Override
    public void OnSucess() {
      callback.OnSucess();
    }

    @Override
    public void OnFail() {
      callback.OnFail();
    }

    @Override
    public void OnSendEmailSucess() {
       callback.OnEmailSucess();
    }

    @Override
    public void getDataHistory(String title, String ngay, String thoigian, String noidung) {
        callback.getDataHistory(title,ngay,thoigian,noidung);
    }
}
