package com.llong.myappgym.Sever;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.llong.myappgym.R;
import com.llong.myappgym.View.HomeActivity;

public class Notifications  extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        CreateNotificatioNChanel();
        Intent intent1=new Intent(this, HomeActivity.class);
        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0
                ,intent1,PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification =new NotificationCompat.Builder(this,"ChanelID")
                .setContentTitle("Thông Báo")
                .setContentText("Hãy vào Luyện tập ")
                .setSmallIcon(R.drawable.clock)
                .setContentIntent(pendingIntent).build();


        startForeground(1,notification);
        return  START_NOT_STICKY;
    }
    private void CreateNotificatioNChanel() {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel notificationChannel=new NotificationChannel("ChanelID","FourGround",
                    NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager=getSystemService(NotificationManager.class);
            manager.createNotificationChannel(notificationChannel);

        }
    }
}
