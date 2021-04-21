package com.llong.myappgym.Model;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.llong.myappgym.PreSenter.Interface.IWorkout;

import java.io.Serializable;

public class WorkOutModel  implements Serializable
{
    private String categories;
    private  String des;
    private String link;
    private String name;
    private String ngay;
    private String noidung;
    private String hinhanh;
    private String mota;
    private FirebaseFirestore db;
    private IWorkout callback;
    public  WorkOutModel(){

    }
    public  WorkOutModel(IWorkout callback){
        this.callback=callback;
        db=FirebaseFirestore.getInstance();
    }

    public WorkOutModel(String categories, String des, String link, String name, String ngay, String noidung,String hinhanh,String mota) {
        this.categories = categories;
        this.des = des;
        this.link = link;
        this.name = name;
        this.ngay = ngay;
        this.noidung = noidung;
        this.hinhanh=hinhanh;
        this.mota=mota;
    }

    public String getMota() {
        return mota;
    }

    public String getCategories() {
        return categories;
    }

    public String getDes() {
        return des;
    }

    public String getLink() {
        return link;
    }

    public String getName() {
        return name;
    }

    public String getNgay() {
        return ngay;
    }

    public String getNoidung() {
        return noidung;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public  void HandleReadDataWorkOut(String categories){
        db.collection("WorkOut")
                .whereEqualTo("categories",categories)
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot q : queryDocumentSnapshots){
                    WorkOutModel workOutModel=q.toObject(WorkOutModel.class);
                    callback.getDataWorkOut(
                      workOutModel.getCategories(),workOutModel.getDes(),
                      workOutModel.getLink(),workOutModel.getName(),workOutModel.getNgay(),
                      workOutModel.getNoidung(),workOutModel.getHinhanh(),
                            workOutModel.getMota()

                    );
                }
            }
        });
    }
}
