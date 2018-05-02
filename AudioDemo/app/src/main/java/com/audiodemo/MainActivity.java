package com.audiodemo;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    AppCompatButton mPlay;
    AppCompatButton mPause;
    SeekBar volumeController;
    SeekBar lengthController;

    MediaPlayer mediaPlayer;
    AudioManager audioManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPlay=(AppCompatButton) findViewById(R.id.play);
        mPause=(AppCompatButton) findViewById(R.id.pause);
        volumeController=(SeekBar) findViewById(R.id.volumeSeekBar);
        lengthController=(SeekBar) findViewById(R.id.lengthSeekBar);

        mediaPlayer=MediaPlayer.create(this,R.raw.funnyvoice);
        audioManager=(AudioManager)getSystemService(Context.AUDIO_SERVICE);

        int maxVolume=audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currVolume=audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        volumeController.setMax(maxVolume);
        volumeController.setProgress(currVolume);
        lengthController.setMax(mediaPlayer.getDuration());

        mPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
            }
        });

        mPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.pause();
            }
        });

        volumeController.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //Log.i("Progress Info:",Integer.toString(progress));
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                lengthController.setProgress(mediaPlayer.getCurrentPosition());
            }
        },0,1000);

        lengthController.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //Log.i("Scrubber Info:",Integer.toString(progress));
                mediaPlayer.seekTo(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
