package com.animationdemo;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatButton mAlpha=(AppCompatButton) findViewById(R.id.alpha);
        AppCompatButton mRotate=(AppCompatButton) findViewById(R.id.rotate);
        AppCompatButton mSet=(AppCompatButton) findViewById(R.id.set);
        AppCompatButton mScale=(AppCompatButton) findViewById(R.id.scale);
        AppCompatButton mTranslate=(AppCompatButton) findViewById(R.id.translate);

        final ImageView mCenterImage=(ImageView) findViewById(R.id.centerImage);

        mAlpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCenterImage.animate().alpha(0f).setDuration(2000).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        if (mCenterImage.getDrawable().getConstantState() == getResources().getDrawable( R.drawable.avengers).getConstantState())
                        {
                            mCenterImage.setImageResource(R.drawable.justiceleague);
                        }
                        else
                        {
                            mCenterImage.setImageResource(R.drawable.avengers);
                        }

                        mCenterImage.animate().alpha(1f);
                    }
                });

            }
        });

        mRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCenterImage.animate().rotationBy(360f).setDuration(2000);
            }
        });

        mScale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCenterImage.animate().scaleX(0.5f).scaleY(0.5f).setDuration(2000).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        mCenterImage.animate().scaleX(1f).scaleY(1f);
                    }
                });

            }
        });

        mTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCenterImage.animate().translationXBy(-200).setDuration(2000).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        mCenterImage.animate().translationX(0);
                    }
                });
            }
        });

        mSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCenterImage.animate()
                        .rotationBy(360f)
                        .translationXBy(200)
                        .scaleX(1.5f)
                        .scaleY(1.5f)
                        .alpha(0f)
                        .setDuration(3000).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        mCenterImage.animate()
                                .rotationBy(-360f)
                                .translationX(0)
                                .scaleX(1f)
                                .scaleY(1f)
                                .alpha(1f);
                    }
                });
            }
        });
    }
}
