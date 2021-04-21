package com.llong.myappgym.View;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.llong.myappgym.R;
import com.llong.myappgym.Sever.Alarm;
import com.llong.myappgym.View.FragMent.FragMent_Excersie;
import com.llong.myappgym.View.FragMent.FragMent_WorkOut;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Set;

public class HomeActivity  extends AppCompatActivity
implements  NavigationView.OnNavigationItemSelectedListener{
    private NavigationView navigationView;
    private Fragment fragment;
    private ActionBarDrawerToggle toggle;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        InitWidget();
        Init();
        HandleEvents();
    }

    private void HandleEvents() {
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void Init() {
        fragment=new FragMent_Excersie();
        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,fragment).commit();
        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.Open,R.string.Close);
        toggle.syncState();

    }

    private void InitWidget() {
        navigationView=findViewById(R.id.navigationview);
        drawerLayout=findViewById(R.id.drawablelayout);
        toolbar=findViewById(R.id.toolbar);


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        fragment=null;
        switch (menuItem.getItemId()){
            case R.id.excerises: fragment=new FragMent_Excersie();break;
            case R.id.workout: fragment=new FragMent_WorkOut();break;
            case R.id.profile: startActivity(new Intent(this,ProFileActivity.class));break;
            case R.id.logout: FirebaseAuth.getInstance().signOut();finish();break;
            case R.id.history: startActivity(new Intent(this,HistoryActivity.class));break;
            case R.id.settings: DialogTimer();break;

        }
        if(fragment!=null){
            getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,fragment).commit();
        }

        return true;
    }
    private void DialogTimer() {
        final Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.dialog_timer);
        dialog.show();
        TimePicker timePicker=dialog.findViewById(R.id.timepicker);
        final TextView txttimer=dialog.findViewById(R.id.txttimer);
        Button btnhengio=dialog.findViewById(R.id.btnhengio);
        final Calendar calendar=Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int gio=calendar.get(Calendar.HOUR);
        int phut=calendar.get(Calendar.MINUTE);
        timePicker.setIs24HourView(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            timePicker.setHour(gio);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            timePicker.setMinute(phut);
        }
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                calendar.set(0,0,0,hourOfDay,minute);

                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("HH:mm");
                txttimer.setText(simpleDateFormat.format(calendar.getTime()));
            }
        });
        btnhengio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this, Alarm.class);

                AlarmManager alarmManager= (AlarmManager) getSystemService(ALARM_SERVICE);
                PendingIntent pendingIntent=PendingIntent.getBroadcast(HomeActivity.this,0,
                        intent,0);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),
                        1000,pendingIntent  );
                Toast.makeText(HomeActivity.this, "Đã Hẹn giờ", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
}
