package com.zjiajun.firstapp.activity;

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

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.zjiajun.firstapp.R;
import com.zjiajun.firstapp.base.BaseActivity;
import com.zjiajun.firstapp.utils.HttpUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkActivity extends BaseActivity {

    private static final String TAG = "NetworkActivity";

    private static final String WEB_SITE = "http://www.google.com";
    private Button btn_send_request,btn_json_response;
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
                case R.id.btn_json_response :
                    parseJsonWithGson();
                    break;
                default:
                    break;
            }
        }
    };

    private void parseJsonWithGson() {
        Log.i(TAG, "parseJsonWithGson,ThreadId " + Thread.currentThread().getId());
        /*
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "parseJsonWithGson---Run,ThreadId " + Thread.currentThread().getId());
                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    //just for test
                    String jsonUrl = "https://api.weibo.com/2/statuses/public_timeline.json";
                    HttpResponse response = httpClient.execute(new HttpGet(jsonUrl));
                    final WeiBoError weiBoError = new Gson().fromJson(
                            EntityUtils.toString(response.getEntity(), "UTF-8"), WeiBoError.class);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.i(TAG, "parseJsonWithGson---runOnUiThread,ThreadId " + Thread.currentThread().getId());
                            tv_response.setText(weiBoError.toString());
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        */

        //just for test
        String jsonUrl = "https://api.weibo.com/2/statuses/public_timeline.json";
        AsyncTaskCompat.executeParallel(new JsonAsyncTask(),jsonUrl);
//        HttpUtil.sendHttpGetRequest(jsonUrl);//responseCode 400
//        WeiBoError weiBoError = new Gson().fromJson(content, WeiBoError.class);
//        tv_response.setText(weiBoError.toString());
    }
    class JsonAsyncTask extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... params) {
            Log.i(TAG, "doInBackground,ThreadId " + Thread.currentThread().getId());
            return HttpUtil.sendHttpGetRequest(params[0]);
        }

        @Override
        protected void onPostExecute(String response) {
            Log.i(TAG, "onPostExecute,ThreadId " + Thread.currentThread().getId());
            super.onPostExecute(response);
            WeiBoError weiBoError = new Gson().fromJson(response, WeiBoError.class);
            tv_response.setText(weiBoError.toString());
        }
    }

    class WeiBoError {
        private String error;
        @SerializedName("error_code")
        private Integer errorCode;
        private String request;

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

        public Integer getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(Integer errorCode) {
            this.errorCode = errorCode;
        }

        public String getRequest() {
            return request;
        }

        public void setRequest(String request) {
            this.request = request;
        }

        @Override
        public String toString() {
            return "WeiBoError{" +
                    "error='" + error + '\'' +
                    ", errorCode=" + errorCode +
                    ", request='" + request + '\'' +
                    '}';
        }
    }

    private void sendRequestWithHttpClient() {
        Log.i(TAG, "sendRequestWithHttpClient,ThreadId " + Thread.currentThread().getId());
        /*
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
        */

//        String content = HttpUtil.sendHttpGetRequest(WEB_SITE);
//        tv_response.setText(content);

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
        btn_json_response.setOnClickListener(mListener);
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_network);
    }

    @Override
    protected void initViews() {
        btn_send_request = (Button) findViewById(R.id.btn_send_request);
        btn_json_response = (Button) findViewById(R.id.btn_json_response);
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
