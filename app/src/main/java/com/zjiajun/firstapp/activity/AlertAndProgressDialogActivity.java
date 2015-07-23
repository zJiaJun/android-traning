package com.zjiajun.firstapp.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zjiajun.firstapp.R;
import com.zjiajun.firstapp.base.BaseActivity;

public class AlertAndProgressDialogActivity extends BaseActivity implements View.OnClickListener {


    private Button btn_alert_dialog,btn_progress_dialog;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_alert_and_progress_dialog);
    }

    @Override
    protected void initViews() {
        btn_alert_dialog = (Button) findViewById(R.id.btn_alert_dialog);
        btn_progress_dialog = (Button) findViewById(R.id.btn_progress_dialog);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_alert_dialog :
                buildAlertDialog();
                break;
            case R.id.btn_progress_dialog :
                buildProgressDialog();
                break;
            default:
                break;
        }
    }

    private void buildProgressDialog() {
        final ProgressDialog progressDialog = new ProgressDialog(AlertAndProgressDialogActivity.this);
        progressDialog.setTitle("This is Progress Dialog");
        progressDialog.setMessage("Something is running,Please wait 5s");
        progressDialog.setCancelable(false);
        progressDialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (progressDialog != null) {
                    progressDialog.dismiss();
                }
            }
        }).start();
    }

    private void buildAlertDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Ths is Alert Dialog");
        dialog.setMessage("Something important");
        dialog.setCancelable(true);
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(AlertAndProgressDialogActivity.this, "you click OK", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(AlertAndProgressDialogActivity.this, "you click Cancel", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.setNeutralButton("Neutral", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(AlertAndProgressDialogActivity.this, "you click Neutral", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btn_alert_dialog.setOnClickListener(this);
        btn_progress_dialog.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_alert_and_progress_dialog, menu);
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
