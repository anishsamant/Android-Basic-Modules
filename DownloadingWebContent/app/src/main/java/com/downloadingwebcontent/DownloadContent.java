package com.downloadingwebcontent;

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

public class DownloadContent extends AsyncTask<String,Void,String> {


    @Override
    protected String doInBackground(String... urls) {
        String result="";
        try {
            URL url=new URL(urls[0]);
            HttpURLConnection conn=(HttpURLConnection)url.openConnection();
            InputStream in=conn.getInputStream();
            InputStreamReader rd=new InputStreamReader(in);
            int data;
            data=rd.read();
            while(data!=-1)
            {
                char current = (char) data;
                result+=current;
                data=rd.read();
            }
            return result;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Failed";
    }
}
