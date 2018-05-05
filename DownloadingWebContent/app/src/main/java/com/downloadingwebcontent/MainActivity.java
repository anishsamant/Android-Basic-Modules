package com.downloadingwebcontent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    TextView contents;
    Button downloadContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contents=(TextView) findViewById(R.id.contents);
        downloadContent=(Button) findViewById(R.id.downloadButton);

        downloadContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DownloadContent dc=new DownloadContent();
                String res=null;
                try {
                    res=dc.execute("https://www.linkedin.com/").get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                contents.setText(res);
                Log.i("Content",res);
            }
        });


    }
}
