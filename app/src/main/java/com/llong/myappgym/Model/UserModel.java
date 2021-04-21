package com.llong.myappgym.Model;

import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.llong.myappgym.PreSenter.Interface.IUserModel;

import javax.security.auth.callback.Callback;

public class UserModel {
    private String title;
    private String noidung;
    private String thoigian,ngay;
    private IUserModel callback;
    private FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore db;
    private HistoryModel historyModel;

    private String Email_Vali="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    public UserModel(IUserModel callback){
        this.callback=callback;
        historyModel=new HistoryModel();
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();
        db=FirebaseFirestore.getInstance();


    }

    public String getTitle() {
        return title;
    }

    public String getNoidung() {
        return noidung;
    }

    public String getThoigian() {
        return thoigian;
    }

    public String getNgay() {
        return ngay;
    }

    public UserModel(String title, String noidung, String thoigian, String ngay) {
        this.title=title;
        this.noidung=noidung;
        this.thoigian=thoigian;
        this.ngay=ngay;
    }

    public  void HandleSignUp(final String Email, String Pass){
              if(Email.matches(Email_Vali) && Email.length()>0){

                  if(Pass.length()>0){
                      firebaseAuth.createUserWithEmailAndPassword(Email,Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                          @Override
                          public void onComplete(@NonNull Task<AuthResult> task) {
                              if(task.isSuccessful()){
                                  callback.OnSucess();
                                  firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                      @Override
                                      public void onComplete(@NonNull Task<Void> task) {
                                          if(task.isSuccessful()){
                                              callback.OnSendEmailSucess();
                                          }
                                      }
                                  });
                              }else{
                                  callback.OnFail();
                              }
                          }
                      });
                  }
              }else{
                  callback.OnValid();
              }
    }
    public  void HandleLogin(String Email,String Pass){
        if(Email.matches(Email_Vali) && Email.length()>0){

            if(Pass.length()>0){
                firebaseAuth.signInWithEmailAndPassword(Email,Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            if(firebaseAuth.getCurrentUser()!=null){
                                if(firebaseAuth.getCurrentUser().isEmailVerified()){
                                    callback.OnSucess();
                                }

                            }else{
                                SendEmail();

                            }

                        }else{
                            callback.OnFail();
                        }
                    }
                });
            }
        }else{
            callback.OnValid();
        }

    }

    private void SendEmail() {
        firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    callback.OnSendEmailSucess();
                }
            }
        });
    }
    public  void SetData(String title,String noidung,String thoigian,String ngay,String Chude){
        UserModel userModel=new UserModel(title,noidung,thoigian,ngay);
        db.collection("User")
        .document(Chude)
        .collection(firebaseUser.getProviderId())
        .add(userModel).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                if(task.isSuccessful()){
                    callback.OnSucess();
                }else{
                    callback.OnFail();
                }
            }
        }) ;


    }
    public  void HandleReadDataHistoryExcers(String KEY){

        db.collection("User")
                .document(KEY)
                .collection(firebaseUser.getProviderId())
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot q:queryDocumentSnapshots){
                     HistoryModel historyModel =q.toObject(HistoryModel.class);
                      callback.getDataHistory(historyModel.getTitle(),
                              historyModel.getNgay(),historyModel.getThoigian(),historyModel.getNoidung());
                }

            }
        });

    }




}
