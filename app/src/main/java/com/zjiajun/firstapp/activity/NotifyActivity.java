package com.zjiajun.firstapp.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.zjiajun.firstapp.R;
import com.zjiajun.firstapp.base.BaseActivity;

public class NotifyActivity extends BaseActivity {

    private Button btn_send_notify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btn_send_notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                /* API 11被废弃 不建议使用
                Notification notification = new Notification(R.mipmap.ic_launcher, "tickerText", System.currentTimeMillis());
                notification.setLatestEventInfo(NotifyActivity.this, "标题啊", "这是通知内容尼玛",
                        PendingIntent.getActivity(NotifyActivity.this, 0, new Intent(NotifyActivity.this, MainActivity.class), 0));

                notificationManager.notify(100, notification);
                */
                Notification.Builder builder = new Notification.Builder(NotifyActivity.this);
                builder.setSmallIcon(android.R.drawable.stat_notify_sdcard)
                        .setTicker("小标题吖吖")
                        .setContentTitle("这是标题啊").setContentText("这是通知内容尼玛")
                        .setContentIntent(PendingIntent.getActivity(NotifyActivity.this, 0,
                                new Intent(NotifyActivity.this, MainActivity.class), 0));
                Notification notification1 = builder.build();
                notificationManager.notify(100,notification1);
            }
        });

    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_notify);
    }

    @Override
    protected void initViews() {
        btn_send_notify = (Button) findViewById(R.id.btn_send_notify);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_notify, menu);
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
