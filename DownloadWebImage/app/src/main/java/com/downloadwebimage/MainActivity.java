package com.downloadwebimage;

import android.graphics.Bitmap;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    ImageView mImage;
    Button downloadImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImage=(ImageView) findViewById(R.id.downloadedImage);
        downloadImage=(Button) findViewById(R.id.downloadButton);


        downloadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap result;
                DownloadImage di=new DownloadImage();
                try {
                    result=di.execute("https://wallpaperbrowse.com/media/images/3848765-wallpaper-images-download.jpg").get();
                    mImage.setImageBitmap(result);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
