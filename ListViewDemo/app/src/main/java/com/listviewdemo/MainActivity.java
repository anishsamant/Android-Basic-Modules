package com.listviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView myListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myListView=(ListView) findViewById(R.id.myListView);

        final ArrayList<String> players=new ArrayList<String>();

        String playerNames[]=Players.getPlayerNames();

        for(String playerName: playerNames){
            players.add(playerName);
        }

        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,R.layout.custom_listview_text,players);

        myListView.setAdapter(arrayAdapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "Hello, " + players.get(i), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
