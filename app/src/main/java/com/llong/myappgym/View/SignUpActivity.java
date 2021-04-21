package com.llong.myappgym.View;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.llong.myappgym.PreSenter.Interface.UserView;
import com.llong.myappgym.PreSenter.UserPreSenter;
import com.llong.myappgym.R;

public class SignUpActivity extends AppCompatActivity
implements  View.OnClickListener, UserView {
    private Toolbar toolbar;
    private EditText editemail,editpass,editrepeatpass;
    private Button btnsignup;
    private UserPreSenter userPreSenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        InitWiget();
        Init();
        HandleEvents();
    }

    private void HandleEvents() {
        btnsignup.setOnClickListener(this);
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
    }

    private void InitWiget() {
        btnsignup=findViewById(R.id.btnsignup);
        toolbar=findViewById(R.id.toolbar);
        editemail=findViewById(R.id.editemail);
        editpass=findViewById(R.id.edtipass);
        editrepeatpass=findViewById(R.id.editrepeatpassword);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnsignup:
                String Email=editemail.getText().toString().trim();
                String Pass=editpass.getText().toString().trim();
                String Pass1=editrepeatpass.getText().toString().trim();
                if(Pass.equals(Pass1)){
                    userPreSenter.HandleSignUp(Email,Pass);
                }else{
                    Toast.makeText(this, "Pass No Same", Toast.LENGTH_SHORT).show();
                }
                break;


        }
    }

    @Override
    public void OnValid() {
          Toast.makeText(this,"Error Valid Email",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnSucess() {
        Toast.makeText(this,"Sign Up Sucess",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnFail() {
        Toast.makeText(this,"Sign Up Fail",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnEmailSucess() {
        Toast.makeText(this,"Please Auth Email",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getDataHistory(String title, String ngay, String thoigian, String noidung) {

    }
}
