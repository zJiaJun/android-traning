package com.zjiajun.firstapp.activity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zjiajun.firstapp.R;
import com.zjiajun.firstapp.base.BaseActivity;
import com.zjiajun.firstapp.db.MyDataBaseHelper;

public class DbActivity extends BaseActivity implements View.OnClickListener {


    private Button btn_create_table,btn_upgrade_table,
            btn_query_table,btn_update_table,btn_delete_table,btn_insert_table;


    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_db);
    }

    @Override
    protected void initViews() {
        btn_create_table = (Button) findViewById(R.id.btn_create_table);
        btn_upgrade_table = (Button) findViewById(R.id.btn_upgrade_table);
        btn_query_table = (Button) findViewById(R.id.btn_query_table);
        btn_update_table = (Button) findViewById(R.id.btn_update_table);
        btn_delete_table = (Button) findViewById(R.id.btn_delete_table);
        btn_insert_table = (Button) findViewById(R.id.btn_insert_table);
    }

    // first run dbVersion is 1
    private MyDataBaseHelper dataBaseHelper = new MyDataBaseHelper(this,"BookStore.db",null,1);

    @Override
    public void onClick(View v) {
        dataBaseHelper = new MyDataBaseHelper(this,"BookStore.db",null,2);
        switch (v.getId()) {
            case R.id.btn_create_table :
                dataBaseHelper.getReadableDatabase();
                break;
            case R.id.btn_upgrade_table :
                dataBaseHelper.getReadableDatabase();
                break;
            case R.id.btn_query_table :
                SQLiteDatabase queryDb = dataBaseHelper.getReadableDatabase();
                Cursor cursor = queryDb.query("book", new String[]{"id", "author", "price", "pages"},
                        "name = ?", new String[]{"Crazy Android"}, null, null, "price desc");
                if (cursor.moveToFirst()) {
                    do {
                        int id = cursor.getInt(0);
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        Double price = cursor.getDouble(2);
                        String pages = cursor.getString(3);
                        Toast.makeText(this,id + "_" + author + "_" + price + "_" + pages,Toast.LENGTH_LONG).show();
                    } while (cursor.moveToNext());
                }
                cursor.close();
                break;
            case R.id.btn_update_table :
                SQLiteDatabase updateDb = dataBaseHelper.getReadableDatabase();
                ContentValues updateValues = new ContentValues();
                updateValues.put("price",999);
                updateValues.put("pages",99);
                updateDb.update("book",updateValues,"name = 'Crazy Android' and pages >= 50 and price >= 30",null);
                break;
            case R.id.btn_delete_table :
                SQLiteDatabase deleteDb = dataBaseHelper.getReadableDatabase();
                deleteDb.delete("category","category_code = ?",new String[]{"100"});
                break;
            case R.id.btn_insert_table :
                SQLiteDatabase insertDb = dataBaseHelper.getReadableDatabase();
                insertDb.beginTransaction();
                try {
                    ContentValues insertValues = new ContentValues();
                    insertValues.put("author", "guolin");
                    insertValues.put("price", 99.9);
                    insertValues.put("pages", 555);
                    insertValues.put("name", "第一行代码Android");
                    insertDb.insert("book", null, insertValues);
                    insertValues.clear();
                    insertValues.put("author", "zhujiajun");
                    insertValues.put("price", 69.9);
                    insertValues.put("pages", 123);
                    insertValues.put("name", "Crazy Android");
                    insertDb.insert("book", null, insertValues);
                    insertValues.clear();
                    if (true)
                        throw new Exception();
                    insertValues.put("category_name", "Android");
                    insertValues.put("category_code", 100);
                    insertDb.insert("category", null, insertValues);
                    insertDb.setTransactionSuccessful();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    insertDb.endTransaction();
                }
                break;
            default:
                break;

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btn_create_table.setOnClickListener(this);
        btn_upgrade_table.setOnClickListener(this);
        btn_query_table.setOnClickListener(this);
        btn_update_table.setOnClickListener(this);
        btn_delete_table.setOnClickListener(this);
        btn_insert_table.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_db, menu);
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
