package com.zjiajun.firstapp.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by zhujiajun
 * 15/7/10 14:37
 */
public class MyOrderBroadcastReceiver2 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, intent.getStringExtra("key") + 2, Toast.LENGTH_LONG).show();
    }
}
