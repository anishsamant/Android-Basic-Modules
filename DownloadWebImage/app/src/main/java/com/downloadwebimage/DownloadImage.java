package com.downloadwebimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ANISH on 05-05-2018.
 */

public class DownloadImage extends AsyncTask<String, Void, Bitmap> {


    @Override
    protected Bitmap doInBackground(String... urls) {
        Bitmap result=null;
        try {
            URL url=new URL(urls[0]);
            HttpURLConnection conn=(HttpURLConnection)url.openConnection();
            conn.connect();
            InputStream in=conn.getInputStream();
            result= BitmapFactory.decodeStream(in);
            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
