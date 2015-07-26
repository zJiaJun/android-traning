package com.zjiajun.firstapp.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by zhujiajun
 * 15/7/26 09:47
 */
public class MyIntentService extends IntentService {

    private static final String TAG = "MyIntentService";

    public MyIntentService() {
        super("threadName88");
    }

    /**
     * 开启新线程执行,执行后stopSelf
     * @param intent Intent
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(TAG, "onHandleIntent," + "ThreadId: " + Thread.currentThread().getId() + ",ThreadName: " + Thread.currentThread().getName());
        Log.i(TAG, "onHandleIntent: " + intent.getStringExtra("key"));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy ");
    }
}
