package com.zjiajun.firstapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by zhujiajun
 * 15/7/10 14:37
 */
public class MyOrderBroadcastReceiver1 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, intent.getStringExtra("key") + 1, Toast.LENGTH_LONG).show();
//        abortBroadcast();
    }
}
