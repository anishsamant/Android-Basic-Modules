package game.com.animationapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView mImage;
    Button mAlpha,mScale,mSet,mRotate,mTranslate;
    Animation mAnimAlpha,mAnimScale,mAnimTranslate,mAnimRotate,mAnimSet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImage=(ImageView)findViewById(R.id.image);
        mAlpha=(Button)findViewById(R.id.alphabutton);
        mScale=(Button)findViewById(R.id.scalebutton);
        mSet=(Button)findViewById(R.id.setbutton);
        mRotate=(Button)findViewById(R.id.rotatebutton);
        mTranslate=(Button)findViewById(R.id.translatebutton);

        mAnimAlpha= AnimationUtils.loadAnimation(this,R.anim.alpha_animation);
        mAnimScale= AnimationUtils.loadAnimation(this,R.anim.scale_animation);
        mAnimRotate= AnimationUtils.loadAnimation(this,R.anim.rotate_animation);
        mAnimTranslate= AnimationUtils.loadAnimation(this,R.anim.translate_animation);
        mAnimSet=AnimationUtils.loadAnimation(this,R.anim.set_animation);

        mAlpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mImage.startAnimation(mAnimAlpha);
            }
        });

        mScale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mImage.startAnimation(mAnimScale);
            }
        });

        mSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mImage.startAnimation(mAnimSet);
            }
        });

        mRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mImage.startAnimation(mAnimRotate);
            }
        });

        mTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mImage.startAnimation(mAnimTranslate);
            }
        });
    }
}
