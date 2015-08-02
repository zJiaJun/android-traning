package com.zjiajun.firstapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by zhujiajun
 * 15/7/9 17:40
 */
public class BootCompleteReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"启动啦啦啦~~~",Toast.LENGTH_LONG).show();
    }
}
