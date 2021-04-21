package com.llong.myappgym.Sever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.util.Log;

public class Alarm extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        intent=new Intent(context,Notifications.class);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            context.startForegroundService(intent);
        }else{
            context.startService(intent);
        }
    }
}
