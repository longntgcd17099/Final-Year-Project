package com.llong.myappgym.View;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.llong.myappgym.R;

public class ChangePassActivity  extends AppCompatActivity
 implements View.OnClickListener {
    private Toolbar toolbar;
    private EditText editpass,editrepeatpass;
    private Button btnsave;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepass);
        InitWidget();
        Init();
        HandleEvents();
    }

    private void HandleEvents() {
        btnsave.setOnClickListener(this);

    }

    private void Init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Change Pass");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();


    }

    private void InitWidget() {
        toolbar=findViewById(R.id.toolbar);
        editpass=findViewById(R.id.editpass);
        editrepeatpass=findViewById(R.id.editpassrepeat);
        btnsave=findViewById(R.id.btnsave);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnsave:
                String Pass=editpass.getText().toString().trim();
                String RPass=editrepeatpass.getText().toString().trim();
                if(Pass.length()>0 || RPass.length()>0){
                    if(Pass.equals(RPass)){
                        firebaseUser.updatePassword(Pass);
                        Toast.makeText(this,"Change Pass Sucess",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(this, "Not Same Pass", Toast.LENGTH_SHORT).show();
                    }
                }else{
                   Toast.makeText(this,"Not Empty Pass",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
