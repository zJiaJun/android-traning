package com.zjiajun.firstapp.activity;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.zjiajun.firstapp.R;
import com.zjiajun.firstapp.base.BaseActivity;
import com.zjiajun.firstapp.model.MainItem;
import com.zjiajun.firstapp.model.PersonParcelable;
import com.zjiajun.firstapp.model.PersonSerializable;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    private ListView listView;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initViews() {
        listView = ((ListView) findViewById(R.id.listView));
    }

    int sendBroadcastCount = 0;
    int sendStickyBroadcastCount = 0;

    private List<MainItem> getMainItems() {
        List<MainItem> values = new ArrayList<>();
        values.add(new MainItem("启动到第二个活动并传参", SecondActivity.class));
        values.add(new MainItem("通过startActivityForResult启动", SecondActivity.class));
        values.add(new MainItem("启动对话框活动", DialogActivity.class));
        values.add(new MainItem("启动Alert和ProgressDialog活动", AlertAndProgressDialogActivity.class));
        values.add(new MainItem("启动自定义的列表活动",CustomActivity.class));
        values.add(new MainItem("启动聊天活动", ChatActivity.class));
        values.add(new MainItem("启动碎片活动", FragmentActivity.class));
        values.add(new MainItem("启动广播活动", BroadcastActivity.class));
        values.add(new MainItem("启动测试ListView活动", MultipleItemsListActivity.class));
        values.add(new MainItem("用sendBroadcast发送广播", "com.zjiajun.firstapp.MY_STICKY_BROADCAST"));
        values.add(new MainItem("使用sendStickyBroadcast发送广播","com.zjiajun.firstapp.MY_STICKY_BROADCAST"));
        values.add(new MainItem("使用广播强制下线用法",LoginActivity.class));
        values.add(new MainItem("持久化技术", SaveActivity.class));
        values.add(new MainItem("DB持久化", DbActivity.class));
        values.add(new MainItem("通知栏活动",NotifyActivity.class));
        values.add(new MainItem("接收短信活动",ReceiveSmsActivity.class));
        values.add(new MainItem("选择照片活动",ChoosePicActivity.class));
        values.add(new MainItem("媒体播放活动",MediaActivity.class));
        values.add(new MainItem("子线程活动",ThreadActivity.class));
        values.add(new MainItem("服务活动",ServiceActivity.class));
        values.add(new MainItem("网络活动",NetworkActivity.class));
        values.add(new MainItem("位置活动",LocationActivity.class));
        return values;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "MainActivity onCreate" + this.toString());
        Log.d(TAG, getResources().getDisplayMetrics().xdpi + "");
        Log.d(TAG, getResources().getDisplayMetrics().ydpi + "");
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            Log.d(TAG, savedInstanceState.getString("bundleData"));
        }

        ((NotificationManager) getSystemService(NOTIFICATION_SERVICE)).cancel(100);
        List<MainItem> values = getMainItems();

        ArrayAdapter<MainItem> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,values);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MainItem item = (MainItem) parent.getAdapter().getItem(position);
                CharSequence text = ((TextView) view).getText();
                Object itemObject = item.getObject();
                if (itemObject != null) {
                    if (itemObject instanceof Class<?>) {
                        Intent intent = new Intent(MainActivity.this, (Class<?>) itemObject);
                        if ("启动到第二个活动并传参".equals(text)) {
                            intent.putExtra("extraKey", "上个活动数据");
                            PersonSerializable personSerializable = new PersonSerializable();
                            personSerializable.setName("9leg.com");
                            personSerializable.setAge(27);
                            intent.putExtra("serKey", personSerializable);
                            PersonParcelable personParcelable = new PersonParcelable("leon",37);
                            intent.putExtra("parKey",personParcelable);
                            startActivity(intent);
                        } else if ("通过startActivityForResult启动".equals(text)) {
                            startActivityForResult(intent, 1);
                        } else {
                            startActivity(intent);
                        }
                    }
                    if (itemObject instanceof String) {
                        if ("使用sendBroadcast发送广播".equals(text)) {
                            Intent intent = new Intent(itemObject.toString());
                            intent.putExtra("key", "sendBroadcast");
                            sendBroadcastCount++;
                            intent.putExtra("count", sendBroadcastCount);
                            sendBroadcast(intent);
                        } else if ("使用sendStickyBroadcast发送广播".equals(text)) {
                            Intent intent = new Intent(itemObject.toString());
                            intent.putExtra("key", "sendStickyBroadcast");
                            sendStickyBroadcastCount++;
                            intent.putExtra("count", sendStickyBroadcastCount);
                            sendStickyBroadcast(intent);
                        }
                    }
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (RESULT_OK == resultCode) {
                    String dataReturn = data.getStringExtra("dataReturn");
                    Log.d(TAG,dataReturn);
                }
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "MainActivity onSaveInstanceState");
        super.onSaveInstanceState(outState);
        outState.putString("bundleData", "100");
    }

    @Override
    protected void onStop() {
        Log.d(TAG,"MainActivity onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG,"MainActivity onDestroy");
        super.onDestroy();
    }


    @Override
    protected void onPause() {
        Log.d(TAG,"MainActivity onPause");
        super.onPause();
    }


    @Override
    protected void onResume() {
        Log.d(TAG,"MainActivity onResume");
        super.onResume();
    }

    @Override
    protected void onStart() {
        Log.d(TAG,"MainActivity onStart");
        super.onStart();
    }

    @Override
    protected void onRestart() {
        Log.d(TAG,"MainActivity onRestart");
        super.onRestart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
