package com.llong.myappgym.PreSenter;

import com.llong.myappgym.Model.ExcersieModel;
import com.llong.myappgym.PreSenter.Interface.ExcersieView;
import com.llong.myappgym.PreSenter.Interface.IExcersie;

public class ExcersiePreSenter  implements IExcersie {
    private ExcersieView callback;
    private ExcersieModel excersieModel;
    public ExcersiePreSenter(ExcersieView callback){
        this.callback=callback;
        excersieModel=new ExcersieModel( this);
    }
    public  void HandleReadDataExcerise(String Key){
        excersieModel.HandleReadDataExcersie(Key);
    }


    @Override
    public void getDataExcersie(String hinhanh, String link, String title, String noidung,String categories) {
       callback.getDataExcerise(hinhanh,link,title, noidung,categories);
    }
}
