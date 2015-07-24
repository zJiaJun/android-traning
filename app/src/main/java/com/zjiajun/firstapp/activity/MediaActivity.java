package com.zjiajun.firstapp.activity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import com.zjiajun.firstapp.R;
import com.zjiajun.firstapp.base.BaseActivity;

import java.io.File;
import java.io.IOException;

public class MediaActivity extends BaseActivity implements View.OnClickListener {

    private Button btn_play,btn_pause,btn_stop;
    private VideoView videoView;
    private Button btn_play_movie,btn_pause_movie,btn_replay_movie;

    private MediaPlayer mediaPlayer = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btn_play.setOnClickListener(this);
        btn_pause.setOnClickListener(this);
        btn_stop.setOnClickListener(this);
        initMediaPlayerForAudio();
        btn_play_movie.setOnClickListener(this);
        btn_pause_movie.setOnClickListener(this);
        btn_replay_movie.setOnClickListener(this);
        initMediaPlayerForVideo();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        if (videoView != null) {
            videoView.suspend();
        }
    }

    private void initMediaPlayerForAudio() {
        File file = new File(Environment.getExternalStorageDirectory(),"Don't Cry.mp3");
        try {
            mediaPlayer.setDataSource(file.getPath());
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initMediaPlayerForVideo() {
        File file = new File(Environment.getExternalStorageDirectory(),"DiorsMan.mp4");
        videoView.setVideoPath(file.getPath());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_play_mp3 :
                if (!mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                    Toast.makeText(MediaActivity.this, mediaPlayer.getDuration() + "", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btn_pause_mp3 :
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                }
                break;
            case R.id.btn_stop_mp3 :
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.reset();
                    initMediaPlayerForAudio();
                }
                break;
            case R.id.btn_play_movie :
                if (!videoView.isPlaying()) {
                    videoView.start();
                }
                break;
            case R.id.btn_pause_movie :
                if (videoView.isPlaying()) {
                    videoView.pause();
                }
                break;
            case R.id.btn_replay_movie :
                if (videoView.isPlaying()) {
                    videoView.resume();
                }
            default:
                break;
        }
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_media);
    }

    @Override
    protected void initViews() {
        btn_play = (Button) findViewById(R.id.btn_play_mp3);
        btn_pause = (Button) findViewById(R.id.btn_pause_mp3);
        btn_stop = (Button) findViewById(R.id.btn_stop_mp3);
        videoView = (VideoView) findViewById(R.id.videoView);
        btn_play_movie = (Button) findViewById(R.id.btn_play_movie);
        btn_pause_movie = (Button) findViewById(R.id.btn_pause_movie);
        btn_replay_movie = (Button) findViewById(R.id.btn_replay_movie);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_media, menu);
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
