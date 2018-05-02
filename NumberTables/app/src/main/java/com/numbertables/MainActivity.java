package com.numbertables;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SeekBar numberSlider;
    ListView numberTable;

    public void generateTable(int number)
    {
        ArrayList<Integer> multiples= new ArrayList<Integer>();
        for(int i=1;i<=10;i++)
        {
            multiples.add(i*number);
        }

        ArrayAdapter arrayAdapter=new ArrayAdapter(this,R.layout.listview_textstyle,multiples);

        numberTable=(ListView) findViewById(R.id.numberTable);
        numberTable.setAdapter(arrayAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberSlider=(SeekBar) findViewById(R.id.numberSlider);

        numberSlider.setMax(20);
        numberSlider.setProgress(10);

        generateTable(10);

        numberSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                int min=1;
                int number;
                if(progress<min)
                {
                    number=min;
                    numberSlider.setProgress(min);
                }
                else
                {
                    number=progress;
                }
                generateTable(number);
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
