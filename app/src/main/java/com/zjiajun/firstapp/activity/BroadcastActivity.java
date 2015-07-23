package com.zjiajun.firstapp.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.zjiajun.firstapp.R;
import com.zjiajun.firstapp.base.BaseActivity;
import com.zjiajun.firstapp.broadcast.MyStickyBroadcastReceiver;
import com.zjiajun.firstapp.broadcast.NetworkChangeReceiver;

public class BroadcastActivity extends BaseActivity {

    private NetworkChangeReceiver networkChangeReceiver;

    private Button btn_register_receiver,btn_send_broadcast,btn_send_order_broadcast;

    private MyStickyBroadcastReceiver myStickyBroadcastReceiver;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_broadcast);
    }

    @Override
    protected void initViews() {
        btn_register_receiver = (Button) findViewById(R.id.btn_register_receiver);
        btn_send_broadcast = (Button) findViewById(R.id.btn_send_broadcast);
        btn_send_order_broadcast = (Button) findViewById(R.id.btn_send_order_broadcast);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btn_register_receiver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
                networkChangeReceiver = new NetworkChangeReceiver();
                registerReceiver(networkChangeReceiver, intentFilter);//代码中注册广播,动态注册
            }
        });

        btn_send_broadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.zjiajun.firstapp.MY_BROADCAST");
                intent.putExtra("key","接收标准/无序广播啦啦啦!!!");
                sendBroadcast(intent);//发送标准/无序广播
            }
        });

        btn_send_order_broadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.zjiajun.firstapp.MY_ORDERED_BROADCAST");
                intent.putExtra("key", "接收有序广播啦啦啦,可以被截断!!!");
                sendOrderedBroadcast(intent, null);//发送有序广播
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter("com.zjiajun.firstapp.MY_STICKY_BROADCAST");
        myStickyBroadcastReceiver = new MyStickyBroadcastReceiver();
        registerReceiver(myStickyBroadcastReceiver,intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (networkChangeReceiver != null) {
            unregisterReceiver(networkChangeReceiver);
        }
        if (myStickyBroadcastReceiver != null) {
            unregisterReceiver(myStickyBroadcastReceiver);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_broadcast, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
