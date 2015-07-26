package com.zjiajun.firstapp.services;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.zjiajun.firstapp.activity.MainActivity;

/**
 * Created by zhujiajun
 * 15/7/26 08:56
 */
public class ForegroundService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Notification.Builder builder = new Notification.Builder(this);
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);
        builder.setContentTitle("This is content title").setContentText("This is content text")
                .setContentIntent(pendingIntent);
        startForeground(100,builder.build());

    }
}
