package com.jsondemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    TextView resultPart;
    TextView myString;
    TextView mainANdDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultPart=(TextView) findViewById(R.id.resultSpace);
        myString=(TextView) findViewById(R.id.stringSpace);
        mainANdDescription=(TextView) findViewById(R.id.mainAndDescriptionSpace);
        String result=null;

        DownloadJsonContent obj=new DownloadJsonContent();
        try {
            result=obj.execute("http://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        resultPart.setText(result);
        JSONObject jsonObject= null;
        try {
            jsonObject = new JSONObject(result);
            String weatherInfo=jsonObject.getString("weather");
            myString.setText(weatherInfo);
            JSONArray jsonArray=new JSONArray(weatherInfo);
            for(int i=0;i<jsonArray.length();i++)
            {
                JSONObject jsonPart=jsonArray.getJSONObject(i);
                String main=jsonPart.getString("main");
                String description=jsonPart.getString("description");
                mainANdDescription.setText("Main: "+main+"\n"+"Description: "+description);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
