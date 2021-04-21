package com.llong.myappgym;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.llong.myappgym.View.LoginActivity;
import com.llong.myappgym.View.SignUpActivity;

public class MainActivity extends AppCompatActivity
implements View.OnClickListener {
  private Button btnlogin,btnsignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitWidget();
        HandleEvents();
    }

    private void HandleEvents() {
        btnlogin.setOnClickListener(this);
        btnsignUp.setOnClickListener(this);
    }

    private void InitWidget() {
        btnlogin=findViewById(R.id.btnlogin);
        btnsignUp=findViewById(R.id.btnsignup);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnlogin:
                startActivity(new Intent(this, LoginActivity.class));break;
            case R.id.btnsignup:
                startActivity(new Intent(this, SignUpActivity.class));break;
        }
    }
}