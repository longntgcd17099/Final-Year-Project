package com.llong.myappgym.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.llong.myappgym.R;

public class ForgetPassWordActivity extends AppCompatActivity
implements View.OnClickListener {
  private Button btnsubmit;
  private Toolbar toolbar;
  private FirebaseUser firebaseUser;
  private FirebaseAuth firebaseAuth;
  private EditText editemail;
  private String Email_Vali="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpass);
        InitWidget();
        Init();
        HandleEvents();
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
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();
    }

    private void HandleEvents() {
        btnsubmit.setOnClickListener(this);

    }

    private void InitWidget() {
        toolbar=findViewById(R.id.toolbar);
        btnsubmit=findViewById(R.id.btnsubmit);
        editemail=findViewById(R.id.editemail);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnsubmit:
                String Email=editemail.getText().toString().trim();
                if(Email.matches(Email_Vali)&& Email.length()>0){
                    firebaseAuth.sendPasswordResetEmail(Email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(ForgetPassWordActivity.this,"Plase go to Email Reset Pass",Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(ForgetPassWordActivity.this, "Email Error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else{
                    Toast.makeText(ForgetPassWordActivity.this,"Email Not Valid",Toast.LENGTH_SHORT).show();
                }

        }
    }
}