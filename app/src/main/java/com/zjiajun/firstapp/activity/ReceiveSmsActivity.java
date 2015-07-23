package com.zjiajun.firstapp.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.zjiajun.firstapp.R;
import com.zjiajun.firstapp.base.BaseActivity;

public class ReceiveSmsActivity extends BaseActivity {

    private TextView tv_sms_from;
    private TextView tv_sms_content;

    private SmsReceiver smsReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IntentFilter intentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        smsReceiver = new SmsReceiver();
        registerReceiver(smsReceiver,intentFilter);
    }

    private class SmsReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle extras = intent.getExtras();
            Object[] pduses = (Object[]) extras.get("pdus");
            SmsMessage [] smsMessages = new SmsMessage[pduses.length];
            for (int i = 0;i < smsMessages.length;i++) {
                smsMessages[i] = SmsMessage.createFromPdu((byte[]) pduses[i]);
            }
            String address = smsMessages[0].getOriginatingAddress();
            String content = "";
            for (SmsMessage smsMessage : smsMessages) {
                content += smsMessage.getMessageBody();
            }
            tv_sms_from.setText(address);
            tv_sms_content.setText(content);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(smsReceiver);
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_receive_sms);
    }

    @Override
    protected void initViews() {
        tv_sms_from = (TextView) findViewById(R.id.tv_sms_from);
        tv_sms_content = (TextView) findViewById(R.id.tv_sms_content);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_receive_sms, menu);
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
