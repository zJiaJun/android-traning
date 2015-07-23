package com.zjiajun.firstapp.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.zjiajun.firstapp.R;
import com.zjiajun.firstapp.adapter.MsgAdapter;
import com.zjiajun.firstapp.base.BaseActivity;
import com.zjiajun.firstapp.model.MsgItem;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends BaseActivity {

    private ListView lvMsg;
    private EditText etInput;
    private Button btnSend;

    private List<MsgItem> values = new ArrayList<>();
    private MsgAdapter adapter;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_chat);
    }

    @Override
    protected void initViews() {
        lvMsg = ((ListView) findViewById(R.id.msg_list_view));
        etInput = (EditText) findViewById(R.id.input_text);
        btnSend = (Button) findViewById(R.id.btn_send);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        values.add(new MsgItem("hello,在吗?", MsgItem.TYPE_RECEIVED));
        values.add(new MsgItem("I'm coding",MsgItem.TYPE_SENT));
        values.add(new MsgItem("So cool",MsgItem.TYPE_RECEIVED));

        adapter = new MsgAdapter(this,R.layout.msg_item,values);
        lvMsg.setAdapter(adapter);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sendMsg = etInput.getText().toString();
                if (!"".equals(sendMsg)) {
                    values.add(new MsgItem(sendMsg, MsgItem.TYPE_SENT));
                    adapter.notifyDataSetChanged();
                    lvMsg.setSelection(values.size());
                    etInput.setText("");
                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chat, menu);
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
