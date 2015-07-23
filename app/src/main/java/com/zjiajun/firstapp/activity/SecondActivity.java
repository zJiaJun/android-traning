package com.zjiajun.firstapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zjiajun.firstapp.R;
import com.zjiajun.firstapp.base.BaseActivity;

public class SecondActivity extends BaseActivity {

    private static final String TAG = "SecondActivity";

    private EditText editText;
    private Button btnParams;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_second);
    }

    @Override
    protected void initViews() {
        editText = (EditText) findViewById(R.id.editText);
        btnParams = (Button) findViewById(R.id.btn_params);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "SecondActivity onCreate");
        super.onCreate(savedInstanceState);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.hide();
        }
        String value = getIntent().getStringExtra("extraKey");//获取上个Activity的参数
        editText.setText(value);
        
        btnParams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("dataReturn","来自之前的活动的数据");
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "SecondActivity onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "SecondActivity onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "SecondActivity onPause");
        super.onPause();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "SecondActivity onResume");
        super.onResume();
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "SecondActivity onStart");
        super.onStart();
    }

    @Override
    protected void onRestart() {
        Log.d(TAG, "SecondActivity onRestart");
        super.onRestart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second, menu);
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
