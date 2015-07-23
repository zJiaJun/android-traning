package com.zjiajun.firstapp.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zjiajun.firstapp.R;
import com.zjiajun.firstapp.base.BaseActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SaveActivity extends BaseActivity implements View.OnClickListener {

    private Button btn_file_save;
    private Button btn_file_load;

    private Button btn_shared_preferences_save;
    private Button btn_shared_preferences_load;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_file_save :
                FileOutputStream fileOutputStream;
                BufferedWriter bufferedWriter = null;
                try {
                    fileOutputStream = openFileOutput("data", Context.MODE_PRIVATE);
                    bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
                    bufferedWriter.write("This is save data");
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (bufferedWriter != null) {
                            bufferedWriter.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.btn_file_load :
                FileInputStream fileInputStream;
                BufferedReader bufferedReader = null;
                StringBuilder content = new StringBuilder();
                try {
                    fileInputStream = openFileInput("data");
                    bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        content.append(line);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                Toast.makeText(this,content,Toast.LENGTH_LONG).show();
                break;
            case R.id.btn_shared_preferences_save :
                SharedPreferences sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putString("name","zJiaJun");
                edit.putInt("age",27);
                edit.putFloat("fuck",128.12f);
//                edit.commit();
                edit.apply();
                break;
            case R.id.btn_shared_preferences_load :
                SharedPreferences data = getSharedPreferences("data", Context.MODE_PRIVATE);
                String name = data.getString("name","");
                int age = data.getInt("age", 0);
                float fuck = data.getFloat("fuck", 0);
                Toast.makeText(this,name + "_" + age + "_" + fuck,Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btn_file_save.setOnClickListener(this);
        btn_file_load.setOnClickListener(this);
        btn_shared_preferences_save.setOnClickListener(this);
        btn_shared_preferences_load.setOnClickListener(this);
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_save);
    }

    @Override
    protected void initViews() {
        btn_file_save = ((Button) findViewById(R.id.btn_file_save));
        btn_file_load = (Button) findViewById(R.id.btn_file_load);
        btn_shared_preferences_save = (Button) findViewById(R.id.btn_shared_preferences_save);
        btn_shared_preferences_load = (Button) findViewById(R.id.btn_shared_preferences_load);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_save, menu);
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
