package com.zjiajun.firstapp.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.zjiajun.firstapp.R;
import com.zjiajun.firstapp.base.BaseActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ChoosePicActivity extends BaseActivity implements View.OnClickListener {

    private Button btn_take_photo,btn_choose_phot;
    private ImageView iv_photo;
    private Uri imageUri;

    private static final int TAKE_PHOTO = 1;
    private static final int CROP_PHOTO = 2;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_choose_pic);
    }

    @Override
    protected void initViews() {
        btn_take_photo = (Button) findViewById(R.id.btn_take_photo);
        btn_choose_phot = (Button) findViewById(R.id.btn_choose_photo);
        iv_photo = (ImageView) findViewById(R.id.iv_photo);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_take_photo :
                File outputImage = new File(Environment.getExternalStorageDirectory(),"outputImage.jpg");
                if (outputImage.exists()) {
                    outputImage.delete();
                }
                try {
                    outputImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imageUri = Uri.fromFile(outputImage);
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                startActivityForResult(intent,TAKE_PHOTO);
                break;
            case R.id.btn_choose_photo :
                File outputImage2 = new File(Environment.getExternalStorageDirectory(),"outputImage2.jpg");
                if (outputImage2.exists()) {
                    outputImage2.delete();
                }
                try {
                    outputImage2.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imageUri = Uri.fromFile(outputImage2);
                Intent intent2 = new Intent("android.intent.action.GET_CONTENT");
                intent2.setType("image/*");
                intent2.putExtra("crop", true);
                intent2.putExtra("scala", true);
                intent2.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                startActivityForResult(intent2,CROP_PHOTO);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TAKE_PHOTO :
                if (RESULT_OK == resultCode) {
                    Intent intent = new Intent("com.android.camera.action.CROP");
                    intent.setDataAndType(imageUri,"image/*");
                    intent.putExtra("scala", true);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                    startActivityForResult(intent,CROP_PHOTO);
                }
                break;
            case CROP_PHOTO :
                if (RESULT_OK == resultCode) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        iv_photo.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btn_take_photo.setOnClickListener(this);
        btn_choose_phot.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_choose_pic, menu);
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
