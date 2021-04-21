package com.llong.myappgym.PreSenter;

import com.llong.myappgym.Model.WorkOutModel;
import com.llong.myappgym.PreSenter.Interface.IWorkOutView;
import com.llong.myappgym.PreSenter.Interface.IWorkout;

public class WorkOutPreSenter  implements IWorkout {
    private WorkOutModel workOutModel;
    private IWorkOutView callback;

    public  WorkOutPreSenter(IWorkOutView callback){
        this.callback=callback;
        workOutModel=new WorkOutModel(this);
    }
    public  void  HandleReadDataWorkOut(String categories){
        workOutModel.HandleReadDataWorkOut(categories);
    }

    @Override
    public void getDataWorkOut(String categories, String des, String link, String name, String ngay, String noidung,String hinhanh,String mota) {
          callback.getDataWorkOut(categories,des,link,name,ngay,noidung,hinhanh,mota);
    }
}
