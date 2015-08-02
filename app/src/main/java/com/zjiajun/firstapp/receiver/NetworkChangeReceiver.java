package com.zjiajun.firstapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class NetworkChangeReceiver extends BroadcastReceiver {

    public NetworkChangeReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        String toastStr;
        if (networkInfo != null && networkInfo.isAvailable()) {
            toastStr = "Network is available";
        } else {
            toastStr = "Network is unavailable";
        }
        Toast.makeText(context,toastStr,Toast.LENGTH_LONG).show();


    }
}
