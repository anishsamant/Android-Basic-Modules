package com.videodemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VideoView mVideo=(VideoView) findViewById(R.id.videoView);

        mVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.demovideo);

        MediaController mediaController=new MediaController(this);

        mediaController.setAnchorView(mVideo);
        mVideo.setMediaController(mediaController);
        mVideo.start();
    }
}
