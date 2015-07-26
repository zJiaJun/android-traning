package com.zjiajun.firstapp.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.zjiajun.firstapp.R;
import com.zjiajun.firstapp.base.BaseActivity;
import com.zjiajun.firstapp.services.ForegroundService;
import com.zjiajun.firstapp.services.LongRunningService;
import com.zjiajun.firstapp.services.MyIntentService;
import com.zjiajun.firstapp.services.MyService;

public class ServiceActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "ServiceActivity";
    private Button btn_start_service,btn_stop_service,
            btn_bind_service,btn_unBind_service,
            btn_start_foreground_service,btn_start_intent_service;
    private MyService.MyBinder myBinder;
    private boolean isBound = false;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(TAG, "onServiceConnected ");
            myBinder = (MyService.MyBinder) service;
            myBinder.startDownload();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG, "onServiceDisconnected ");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btn_start_service.setOnClickListener(this);
        btn_stop_service.setOnClickListener(this);
        btn_bind_service.setOnClickListener(this);
        btn_unBind_service.setOnClickListener(this);
        btn_start_foreground_service.setOnClickListener(this);
        btn_start_intent_service.setOnClickListener(this);
        Intent intent = new Intent(this, LongRunningService.class);
        startService(intent);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, MyService.class);
        intent.putExtra("key","value");
        switch (v.getId()) {
            case R.id.btn_start_service :
                startService(intent);
                break;
            case R.id.btn_stop_service :
                stopService(intent);
                break;
            case R.id.btn_bind_service :
                isBound = bindService(intent, serviceConnection, BIND_AUTO_CREATE);
                break;
            case R.id.btn_unBind_service :
                if (isBound) { //safely unBind
                    unbindService(serviceConnection);
                    isBound = false;
                }
                break;
            case  R.id.btn_start_foreground_service :
                Intent fIntent = new Intent(this, ForegroundService.class);
                startService(fIntent);
                break;
            case R.id.btn_start_intent_service :
                Intent intentService = new Intent(this, MyIntentService.class);
                intentService.putExtra("key", "value");
                startService(intentService);
                break;
            default:
                break;
        }

    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_service);
    }

    @Override
    protected void initViews() {
        btn_start_service = (Button) findViewById(R.id.btn_start_service);
        btn_stop_service = (Button) findViewById(R.id.btn_stop_service);
        btn_bind_service = (Button) findViewById(R.id.btn_bind_service);
        btn_unBind_service = (Button) findViewById(R.id.btn_unBind_service);
        btn_start_foreground_service = (Button) findViewById(R.id.btn_start_foreground_service);
        btn_start_intent_service = (Button) findViewById(R.id.btn_start_intent_service);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_service, menu);
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
