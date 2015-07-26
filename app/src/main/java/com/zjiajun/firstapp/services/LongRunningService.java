package com.zjiajun.firstapp.services;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import com.zjiajun.firstapp.broadcast.AlarmReceiver;

import java.util.Date;

/**
 * Created by zhujiajun
 * 15/7/26 12:01
 * 后台执行定时任务
 */
public class LongRunningService extends Service {

    private static final String TAG = "LongRunningService";

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, final int startId) {
        Log.i(TAG, "onStartCommand ");
        new Thread(new Runnable() {
            @Override
            public void run() {
                //具体的逻辑操作
                Log.i(TAG, "run at" + new Date().toString() + " startId is: " + startId);
            }
        }).start();
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        long triggerAtTime = SystemClock.elapsedRealtime() + 10 * 1000;
        Intent broadIntent = new Intent(this,AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, broadIntent, 0);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerAtTime,pendingIntent);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy ");
        super.onDestroy();
    }
}
