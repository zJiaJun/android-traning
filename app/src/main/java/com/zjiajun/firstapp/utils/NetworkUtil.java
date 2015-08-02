package com.zjiajun.firstapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by zhujiajun
 * 15/8/2 12:40
 */
public class NetworkUtil {

    public static boolean isNetworkAvailable() {
        boolean flag = true;
        Context context = MyApplication.getContext();
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null || !networkInfo.isAvailable()) {
            Toast.makeText(context, "没有网络", Toast.LENGTH_SHORT).show();
            flag = false;
        }
        return flag;
    }
}
