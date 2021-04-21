package com.llong.myappgym.View.Practice;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.airbnb.lottie.LottieAnimationView;
import com.llong.myappgym.Model.ExcersieModel;
import com.llong.myappgym.PreSenter.Interface.UserView;
import com.llong.myappgym.PreSenter.UserPreSenter;
import com.llong.myappgym.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class ContentExcersiActivity  extends AppCompatActivity
implements View.OnClickListener, UserView {
    private Toolbar toolbar;
    private LottieAnimationView lottieAnimationView;
    private Intent intent;
    private TextView txtnoidung;
    private Button btnbatdau,btnstop;
    private int checked=0,gio=0;
    private TextView txtgio;
    private CountDownTimer countDownTimer;
    private UserPreSenter userPreSenter;
    private ExcersieModel excersieModel;
    private Handler handler;
    private TextView txtcalo;
    private float calo=0;
    private long weight=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_excersie);
        InitWidget();
        Init();
    }

    private void Init() {
        userPreSenter=new UserPreSenter(this);
        intent=getIntent();
         excersieModel= (ExcersieModel) intent.getSerializableExtra("Excersie");



        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(excersieModel.getTitle());
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        txtnoidung.setText(Html.fromHtml(excersieModel.getNoidung()));
        lottieAnimationView.setAnimationFromUrl(excersieModel.getLink());
        lottieAnimationView.playAnimation();
        lottieAnimationView.loop(true);
        lottieAnimationView.setSpeed(1.2f);
        btnbatdau.setOnClickListener(this);
        btnstop.setOnClickListener(this);
        btnstop.setEnabled(false);

    }

    private void InitWidget() {
        btnstop=findViewById(R.id.btnstop);
        toolbar=findViewById(R.id.toolbar);
        lottieAnimationView=findViewById(R.id.lottievideo);
        txtnoidung=findViewById(R.id.txtnoidung);
        btnbatdau=findViewById(R.id.btnbatdau);
        txtgio=findViewById(R.id.txtthoigian);
        txtcalo=findViewById(R.id.txtcalo);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnbatdau:
                DiaLogInputWeight();break;
            case R.id.btnstop: Practice(1);break;
        }
    }

    private void DiaLogInputWeight() {

        final Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.dialog_input);
               if(weight>0){
                 Practice(0);
               }else{
                   dialog.show();
               }

        Button btnenter=dialog.findViewById(R.id.btnenter);
        final EditText editweight=dialog.findViewById(R.id.editweight);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

         btnenter.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if(editweight.length()>0){
                     try{
                          weight=Long.parseLong(editweight.getText().toString().trim());
                              if(weight>0){
                                  dialog.cancel();
                                  Practice(0);
                              }else{
                                  Toast.makeText(ContentExcersiActivity.this, "Not ", Toast.LENGTH_SHORT).show();
                              }


                     }catch (Exception e){
                         Toast.makeText(ContentExcersiActivity.this, "Input Number", Toast.LENGTH_SHORT).show();
                     }
                 }
             }
         });
    }


    private void Practice(int checked) {
        handler=new Handler();

        if(checked==0){


            btnbatdau.setEnabled(false);
            btnstop.setEnabled(true);
            countDownTimer=new CountDownTimer(1800000,1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    gio+=1000;
                    //1000s= -> 1000/60*1000;
                    calo =  (float) (TimeUnit.MILLISECONDS.toMinutes(gio) *(8*3.5*weight)/2002);
                    txtcalo.setText(String.format("%.1f",calo)+" Calo ");
                    long giay= TimeUnit.MILLISECONDS.toSeconds(gio)
                            - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(gio));
                    long phut = TimeUnit.MILLISECONDS.toMinutes(gio)
                            - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(gio));
                    txtgio.setText(String.format("%2d:%2d",phut,giay));

                }

                @Override
                public void onFinish() {


                }
            }.start();

        }
       else  if(checked==1){
           btnstop.setEnabled(false);
           btnbatdau.setEnabled(true);
                if(countDownTimer!=null){
                    countDownTimer.onFinish();
                    countDownTimer.cancel();
                }

            String timer=txtgio.getText().toString().trim();
            Calendar calendar=Calendar.getInstance();
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy");
            userPreSenter.HandleInsertUser(excersieModel.getCategories(),excersieModel.getTitle(),timer,simpleDateFormat.format(calendar.getTime()),"Excersie");
        }



    }

    @Override
    public void OnValid() {

    }

    @Override
    public void OnSucess() {
        Toast.makeText(this, "Đã Lưu Lịch Sử", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnFail() {

    }

    @Override
    public void OnEmailSucess() {


    }

    @Override
    public void getDataHistory(String title, String ngay, String thoigian, String noidung) {

    }
}
