package com.sharedpreferencesandobjectserializationdemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText enterName;
    ListView listView;
    Button addName;
    ArrayList<String> names;
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enterName = (EditText) findViewById(R.id.enterName);
        addName = (Button) findViewById(R.id.addButton);
        listView = (ListView) findViewById(R.id.namesList);

        names = new ArrayList<>();

        addName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name="";
                name = enterName.getText().toString();
                if (!TextUtils.isEmpty(name))
                {
                    enterName.setText("");

                    InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(enterName.getWindowToken(), 0);

                    names.add(name);

                    SharedPreferences sp = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
                    SharedPreferences.Editor ed = sp.edit();
                    try
                    {
                        ed.putString("names", ObjectSerializer.serialize(names));
                        ed.commit();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    enterName.setError("This field cannot be empty");
                }
            }
        });

        SharedPreferences sp = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);

        try
        {
            names.clear();
            names = (ArrayList<String>) ObjectSerializer.deserialize(sp.getString("names", ObjectSerializer.serialize(new ArrayList<String>())));
        } catch (IOException e) {
            e.printStackTrace();
        }

        arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, names);
        listView.setAdapter(arrayAdapter);

    }
}
