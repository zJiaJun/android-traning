package com.zjiajun.firstapp.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by zhujiajun
 * 15/7/11 19:00
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        ActivityManager.addActivity(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.removeActivity(this);
    }

    private void init() {
        setContentView();
        initViews();
    }

    protected abstract void setContentView();

    protected abstract void initViews();









}
