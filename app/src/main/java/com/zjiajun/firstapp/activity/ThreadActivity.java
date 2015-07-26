package com.zjiajun.firstapp.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.os.AsyncTaskCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zjiajun.firstapp.R;
import com.zjiajun.firstapp.base.BaseActivity;

public class ThreadActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "ThreadActivity";

    private static final int UPDATE_TEXT = 1;
    private Button btn_update_view;
    private TextView tv_thread;

    private Handler handler = new Handler();//主线程中创建的handler

    /**
     * 1.构造参数构造Callback,执行handlerMessage方法
     * 2.覆写Handler中的handleMessage方法
     * 执行逻辑查看 Handler中的dispatchMessage方法
     * 不建议使用,建议使用post方法,直接从Runnable构造出Message对象
     * dispatchMessage方法会先判断message对象的callback是否为空,不为空执行callback.run,就是Runnable的run方法
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_TEXT :
                    tv_thread.setText("Update text1");
                    break;
                default:
                    break;
            }
            return true;
        }
    }) {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_TEXT :
                   tv_thread.setText("Update text2");
                    break;
                default:
                    break;
            }
        }
    }; */

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update_view :
                new Thread(new Runnable() {
                    @Override
                    public void run() {
//                        Message message = Message.obtain();
//                        message.what = UPDATE_TEXT;
//                        handler.sendMessage(message);
                        Log.i(TAG, "run " + Thread.currentThread().getId());//run 236
                        /** 自己构造handler使用post方法
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Log.i(TAG, "Update text " + Thread.currentThread().getId());//Update text 1
                                tv_thread.setText("Update text");
                            }
                        });*/
                        //使用父类Activity的handler,如果不是当前线程就调用post方法,是当前线程就立即执行,推荐此方法
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.i(TAG, "Update text " + Thread.currentThread().getId());//Update text 1
                                tv_thread.setText("Update text");
                            }
                        });
                    }
                }).start();
                break;
            default:
                break;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /** 错误 不能在子线程中更新view 只能在主线程中更新
        btn_update_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        tv_thread.setText("Update text");
                    }
                }).start();
            }
        });
        */
        Log.i(TAG, "onCreate,ThreadId is : " + Thread.currentThread().getId());//onCreate 1
        btn_update_view.setOnClickListener(this);
        Intent tempIntent = new Intent();//just for test
        tempIntent.putExtra("key", 100);
//        new MyAsyncTask().execute("AsyncTask", tempIntent);
        AsyncTaskCompat.executeParallel(new MyAsyncTask(),"AsyncTask", tempIntent);//better

    }

    private class MyAsyncTask extends AsyncTask<Object,Integer,Boolean> {
        /* Run on UI thread */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.i(TAG, "onPreExecute,ThreadId is : " + Thread.currentThread().getId());
        }

        /* Run on new thread */
        @Override
        protected Boolean doInBackground(Object... params) {
            /* do something */
            String param1 = (String) params[0];
            Intent param2 = (Intent) params[1];
            Log.i(TAG, "doInBackground,params :" + param1 + "," + param2 + " ThreadId is: " + Thread.currentThread().getId());
            publishProgress(param2.getIntExtra("key", 0));
            Log.i(TAG, "doInBackground,publishProgress");
            return true;
        }

        /* Run on UI thread */
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Log.i(TAG, "onProgressUpdate, values: " + values[0] + " ThreadId is: " + Thread.currentThread().getId());
        }

        /* Run on UI thread */
        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            Log.i(TAG, "onPostExecute,return Value: " + aBoolean + " ThreadId is: " + Thread.currentThread().getId());
        }


    }


    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_thread);
    }

    @Override
    protected void initViews() {
        btn_update_view = (Button) findViewById(R.id.btn_update_view);
        tv_thread = (TextView) findViewById(R.id.tv_thread);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_thread, menu);
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
