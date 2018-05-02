package com.gridlayoutdemo;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    public void buttonPressed(View view)
    {
        int id=view.getId();
        String myIds="";

        myIds=view.getResources().getResourceEntryName(id);

        int resourceId=getResources().getIdentifier(myIds,"raw",getPackageName());

        MediaPlayer mediaPlayer=MediaPlayer.create(this,resourceId);
        mediaPlayer.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
