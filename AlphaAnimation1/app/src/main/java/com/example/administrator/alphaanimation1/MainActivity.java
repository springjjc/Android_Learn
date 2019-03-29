package com.example.administrator.alphaanimation1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private Button mButtonStart;
    private Button mButtonStop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=(ImageView)findViewById(R.id.image);
        imageView.setImageResource(R.drawable.android_photo);
        mButtonStart=(Button)findViewById(R.id.start);
        mButtonStop=(Button)findViewById(R.id.cancel);
        final AlphaAnimation animation =new AlphaAnimation(1,0);
        animation.setDuration(6000);
        animation.setRepeatCount(3);
        animation.setStartOffset(1000);
        mButtonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation1= AnimationUtils.loadAnimation(MainActivity.this,R.anim.alpha);
                imageView.startAnimation(animation1);
            }
        });
        mButtonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animation.cancel();
            }
        });
    }
}
