package com.llong.myappgym.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import com.google.firebase.auth.FirebaseAuth;
import com.llong.myappgym.R;

public class ProFileActivity extends AppCompatActivity
 implements  View.OnClickListener{
    private Toolbar toolbar;
    private EditText editsex,editname;
    private TextView txtemai;
    private Button btnsave;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private CardView c2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        InitWidget();
        Init();
        HandleEvents();
    }

    private void HandleEvents() {
        btnsave.setOnClickListener(this);
        c2.setOnClickListener(this);
    }

    private void Init() {
        sharedPreferences=getSharedPreferences(FirebaseAuth.getInstance().getCurrentUser().getUid(),MODE_PRIVATE);
        editor=sharedPreferences.edit();
        String name=sharedPreferences.getString("Name","");
        String Sex=sharedPreferences.getString("Sex","");
        editname.setText(name);
        editsex.setText(Sex);
        txtemai.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void InitWidget() {
        toolbar=findViewById(R.id.toolbar);
        btnsave=findViewById(R.id.btnsave);
        txtemai=findViewById(R.id.txtemai);
        editsex=findViewById(R.id.editsex);
        editname=findViewById(R.id.editname);
        c2=findViewById(R.id.c2);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnsave:
                String name=editname.getText().toString().trim();
                String sex=editsex.getText().toString().trim();
                editor.putString("Name",name);
                editor.putString("Sex",sex);
                editor.commit();
                Toast.makeText(this,"Save Sucess",Toast.LENGTH_SHORT).show();
                break;
            case R.id.c2:
                startActivity(new Intent(this,ChangePassActivity.class));break;


        }
    }
}
