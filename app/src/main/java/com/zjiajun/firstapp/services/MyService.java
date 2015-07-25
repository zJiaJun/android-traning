package com.zjiajun.firstapp.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    private static final String TAG = "MyService";

    public MyService() {
    }

    private MyBinder myBinder = new MyBinder();

    public class MyBinder extends Binder {

        public void startDownload() {
            Log.i(TAG, "startDownload ");
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind " + intent.getStringExtra("key"));
        return myBinder;
    }

    @Override
    public void onRebind(Intent intent) {
        Log.i(TAG, "onRebind ");
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind ");
        return super.onUnbind(intent);
    }

    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate ");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand : flags: " + flags + " ,startId: " + startId + ",intentExtra: " + intent.getStringExtra("key"));
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy ");
        super.onDestroy();
    }
}
