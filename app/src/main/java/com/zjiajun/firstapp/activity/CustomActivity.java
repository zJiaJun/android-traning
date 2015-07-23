package com.zjiajun.firstapp.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.zjiajun.firstapp.R;
import com.zjiajun.firstapp.adapter.CustomAdapter;
import com.zjiajun.firstapp.base.BaseActivity;
import com.zjiajun.firstapp.model.CustomItem;

import java.util.ArrayList;
import java.util.List;

public class CustomActivity extends BaseActivity {

    private ListView listView;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_custom);
    }

    @Override
    protected void initViews() {
        listView = (ListView) findViewById(R.id.custom_list_view);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        List<CustomItem> values = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            values.add(new CustomItem("This is " + i + "Line", android.R.drawable.ic_menu_report_image));
        }
        CustomAdapter adapter = new CustomAdapter(this, R.layout.custom_item, values);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_custom, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
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
