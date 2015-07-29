package com.zjiajun.firstapp.utils;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by zhujiajun
 * 15/7/29 15:49
 */
public class HttpUtil {

    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = CPU_COUNT + 1;
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(CORE_POOL_SIZE);

    public static String sendHttpGetRequest(final String url){
        System.out.println("HttpUtil,ThreadId: " + Thread.currentThread().getId());
        String responseStr = null;
        Future<String> future = EXECUTOR_SERVICE.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("HttpUtil---call,ThreadId: " + Thread.currentThread().getId());
                String content = null;
                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpGet httpGet = new HttpGet(url);
                    HttpResponse response = httpClient.execute(httpGet);
                    content = EntityUtils.toString(response.getEntity(), "UTF-8");
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                }
                return content;
            }
        });
        try {
            responseStr = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return responseStr;
    }
}
