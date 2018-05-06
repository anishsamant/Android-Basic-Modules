package com.jsondemo;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ANISH on 06-05-2018.
 */

public class DownloadJsonContent  extends AsyncTask<String, Void, String>{

    @Override
    protected String doInBackground(String... urls) {
        String result="";
        URL url= null;
        try {
            url = new URL(urls[0]);
            HttpURLConnection conn=(HttpURLConnection)url.openConnection();
            InputStream in = conn.getInputStream();
            InputStreamReader rd=new InputStreamReader(in);
            int data=rd.read();
            while(data!=-1)
            {
                char curr=(char) data;
                result+=curr;
                data=rd.read();
            }
            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

//    protected void onPostExecute(String result)
//    {
//        Log.i("Result",result);
//        JSONObject jsonObject= null;
//        try {
//            jsonObject = new JSONObject(result);
//            String weatherInfo=jsonObject.getString("weather");
//            Log.i("String",weatherInfo);
//            JSONArray jsonArray=new JSONArray(weatherInfo);
//            for(int i=0;i<jsonArray.length();i++)
//            {
//                JSONObject jsonPart=jsonArray.getJSONObject(i);
//                String main=jsonPart.getString("main");
//                String description=jsonPart.getString("description");
//                Log.i("Main",main);
//                Log.i("Description",description);
//
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//    }
}
