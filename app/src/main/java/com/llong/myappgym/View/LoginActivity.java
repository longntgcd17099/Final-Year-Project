package com.llong.myappgym.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.firebase.auth.FirebaseAuth;
import com.llong.myappgym.PreSenter.Interface.UserView;
import com.llong.myappgym.PreSenter.UserPreSenter;
import com.llong.myappgym.R;

public class LoginActivity  extends AppCompatActivity
implements  View.OnClickListener, UserView {
    private Button btnlogin;
    private Toolbar toolbar;
    private EditText editemail,editpass;
    private UserPreSenter userPreSenter;
    private TextView txtforgetpassword;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        InitWidget();
        Init();
        HandlerEvents();
    }

    private void HandlerEvents() {
        btnlogin.setOnClickListener(this);
        txtforgetpassword.setOnClickListener(this);
    }

    private void Init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        userPreSenter=new UserPreSenter(this);
        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null){
            startActivity(new Intent(this,HomeActivity.class));
        }
    }

    private void InitWidget() {
        btnlogin=findViewById(R.id.btnlogin);
        toolbar=findViewById(R.id.toolbar);
        editemail=findViewById(R.id.editemail);
        editpass=findViewById(R.id.edtipass);
        txtforgetpassword=findViewById(R.id.txtforgetpass);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnlogin:
                String Email=editemail.getText().toString().trim();
                String Pass=editpass.getText().toString().trim();
                userPreSenter.HandleLogin(Email,Pass);break;
            case R.id.txtforgetpass:
               startActivity(new Intent(this, ForgetPassWordActivity.class));break;

        }
    }

    @Override
    public void OnValid() {
        Toast.makeText(this,"Email Valid Error",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnSucess() {
        startActivity(new Intent(this,HomeActivity.class));

    }

    @Override
    public void OnFail() {
        Toast.makeText(this,"Login Fail",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnEmailSucess() {
        Toast.makeText(this,"Plese Auth Gmail",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getDataHistory(String title, String ngay, String thoigian, String noidung) {

    }



    @Override
    protected void onRestart() {
        super.onRestart();
        if(firebaseAuth.getCurrentUser().isEmailVerified()){
            startActivity(new Intent(this,HomeActivity.class));
        }
    }
}
