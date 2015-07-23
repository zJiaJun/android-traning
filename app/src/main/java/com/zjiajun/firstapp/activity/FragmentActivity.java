package com.zjiajun.firstapp.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


import com.zjiajun.firstapp.R;
import com.zjiajun.firstapp.base.BaseActivity;
import com.zjiajun.firstapp.fragment.AnotherBottomFragment;

public class FragmentActivity extends BaseActivity {

    private Button btn_replace_fragment;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_fragment);
    }

    @Override
    protected void initViews() {
        btn_replace_fragment = (Button) findViewById(R.id.btn_replace_fragment);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }

        btn_replace_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnotherBottomFragment anotherBottomFragment = new AnotherBottomFragment();
                android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.bottom_frame_layout, anotherBottomFragment);
                transaction.addToBackStack(null);
                transaction.commit();
                /*
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.bottom_frame_layout,anotherBottomFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                */

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fragment, menu);
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
