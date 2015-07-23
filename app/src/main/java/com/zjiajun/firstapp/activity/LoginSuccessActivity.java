package com.zjiajun.firstapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.zjiajun.firstapp.R;
import com.zjiajun.firstapp.base.BaseActivity;

public class LoginSuccessActivity extends BaseActivity {

    public static void actionStart(Context context) {
        Intent intent = new Intent(context,LoginSuccessActivity.class);
        context.startActivity(intent);
    }

    private Button btn_force_offline;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_login_success);
    }

    @Override
    protected void initViews() {
        btn_force_offline = (Button) findViewById(R.id.btn_force_offline);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btn_force_offline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.zjiajun.first.app.FORCE_OFFLINE");
                sendBroadcast(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login_success, menu);
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
