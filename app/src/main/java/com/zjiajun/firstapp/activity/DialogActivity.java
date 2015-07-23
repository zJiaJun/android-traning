package com.zjiajun.firstapp.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.zjiajun.firstapp.R;
import com.zjiajun.firstapp.base.BaseActivity;

public class DialogActivity extends BaseActivity {

    private static final String TAG = "DialogActivity";


    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_dialog);
    }

    @Override
    protected void initViews() {}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "DialogActivity onCreate");
        super.onCreate(savedInstanceState);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.hide();
        }
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "DialogActivity onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG,"DialogActivity onDestroy");
        super.onDestroy();
    }


    @Override
    protected void onPause() {
        Log.d(TAG,"DialogActivity onPause");
        super.onPause();
    }


    @Override
    protected void onResume() {
        Log.d(TAG,"DialogActivity onResume");
        super.onResume();
    }

    @Override
    protected void onStart() {
        Log.d(TAG,"DialogActivity onStart");
        super.onStart();
    }

    @Override
    protected void onRestart() {
        Log.d(TAG, "DialogActivity onRestart");
        super.onRestart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dialog, menu);
        return false;
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
