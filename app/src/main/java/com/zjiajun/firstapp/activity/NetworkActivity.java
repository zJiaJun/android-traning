package com.zjiajun.firstapp.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zjiajun.firstapp.R;
import com.zjiajun.firstapp.base.BaseActivity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkActivity extends BaseActivity {

    private static final String TAG = "NetworkActivity";

    private static final String WEB_SITE = "http://www.google.com";
    private Button btn_send_request;
    private TextView tv_response;
    private View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.i(TAG, "onClick ThreadId " + Thread.currentThread().getId());
            switch (v.getId()) {
                case R.id.btn_send_request :
                    /**
                    网络请求操作需要在新线程中操作,否则会异常
                    Caused by: android.os.NetworkOnMainThreadException
                    然而更新UI又需要在主线程中运行,就是UI Thread
                     这个问题的根本解决方法是,线程通信问题
                     在Android中,可以利用Handler,Message,MessageQueue,Loop
                     AsyncTask利用线程池技术
                     runOnUiThread是封装好的,使用方便,其底层还是使用Handler,Message这些
                     */
                    sendRequestWithHttpURLConnection();
                    sendRequestWithHttpClient();
                    break;
                default:
                    break;
            }
        }
    };

    private void sendRequestWithHttpClient() {
        Log.i(TAG, "sendRequestWithHttpClient,ThreadId " + Thread.currentThread().getId());
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "sendRequestWithHttpClient---Run,ThreadId " + Thread.currentThread().getId());
                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpGet httpGet = new HttpGet(WEB_SITE);
                    HttpResponse response = httpClient.execute(httpGet);
                    if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                        HttpEntity entity = response.getEntity();
                        final String content = EntityUtils.toString(entity, "UTF-8");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.i(TAG, "httpClient run,ThreadId " + Thread.currentThread().getId());
                                tv_response.setText(content);
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private void sendRequestWithHttpURLConnection() {
        Log.i(TAG, "sendRequestWithHttpURLConnection,ThreadId " + Thread.currentThread().getId());
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "sendRequestWithHttpURLConnection---Run,ThreadId " + Thread.currentThread().getId());
                try {
                    URL url = new URL(WEB_SITE);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream inputStream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    final StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.i(TAG, "httpURLConnection run,ThreadId " + Thread.currentThread().getId());
                            tv_response.setText(stringBuilder.toString());
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate,ThreadId " + Thread.currentThread().getId());
        btn_send_request.setOnClickListener(mListener);
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_network);
    }

    @Override
    protected void initViews() {
        btn_send_request = (Button) findViewById(R.id.btn_send_request);
        tv_response = (TextView) findViewById(R.id.tv_response);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_network, menu);
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