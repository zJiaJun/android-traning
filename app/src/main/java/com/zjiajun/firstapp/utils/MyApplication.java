package com.zjiajun.firstapp.utils;

import android.app.Application;
import android.content.Context;

/**
 * Created by zhujiajun
 * 15/8/2 12:11
 */
public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }

}
