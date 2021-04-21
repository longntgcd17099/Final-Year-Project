package com.llong.myappgym.Model;

import android.util.Log;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.*;
import com.llong.myappgym.PreSenter.Interface.IExcersie;

import java.io.Serializable;

public class ExcersieModel implements Serializable {
    private String categories;
    private String hinhanh;
    private String link;
    private String title;
    private String noidung;
    private FirebaseFirestore db;

    private DocumentReference documentReference;
    private IExcersie callback;

    public ExcersieModel() {

    }

    public ExcersieModel(IExcersie callback){
        this.callback=callback;
        db=FirebaseFirestore.getInstance();
    }

    public ExcersieModel(String hinhanh, String link, String title, String noidung,String categories) {
        this.hinhanh = hinhanh;
        this.link = link;
        this.title = title;
        this.noidung = noidung;
        this.categories=categories;
    }


    public void HandleReadDataExcersie(String Key){
        db.collection("Exsercise").
                whereEqualTo("categories",Key).
                get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                   for(QueryDocumentSnapshot q : queryDocumentSnapshots){
                       ExcersieModel excersieModel=q.toObject(ExcersieModel.class);
                       callback.getDataExcersie(excersieModel.getHinhanh(),
                               excersieModel.getLink(),excersieModel.getTitle(), excersieModel.getNoidung(),excersieModel.getCategories());
                   }
            }
        });

    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }
}
