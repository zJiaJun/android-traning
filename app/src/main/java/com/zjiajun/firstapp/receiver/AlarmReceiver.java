package com.zjiajun.firstapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.zjiajun.firstapp.services.LongRunningService;

/**
 * Created by zhujiajun
 * 15/7/26 12:10
 */
public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent serviceIntent = new Intent(context, LongRunningService.class);
        context.startService(serviceIntent);
    }
}
