package com.example.administrator.objectanimator;

import android.animation.AnimatorInflater;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ImageView mImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView=(ImageView)findViewById(R.id.imageview);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"已点击",Toast.LENGTH_LONG).show();
            }
        });
    }
    public void translate(View view)
    {
        ObjectAnimator.ofFloat(mImageView,"translationX",0,200).setDuration(1000).start();
//        TranslateAnimation translateAnimation=new TranslateAnimation(0,200,0,0); // translate by Animation
//        translateAnimation.setDuration(2000);
//        translateAnimation.setFillAfter(true);
//        mImageView.startAnimation(translateAnimation);
    }
    public void scale(View view)
    {
        ObjectAnimator.ofFloat(mImageView,"scaleX",1,2.0f).setDuration(1000).start();
        ObjectAnimator.ofFloat(mImageView,"scaleY",1,2.0f).setDuration(1000).start();
    }
    public void rotate(View view)
    {
        ObjectAnimator.ofFloat(mImageView,"rotationX",0,360).setDuration(3000).start();
    }
    public void alpha(View view)
    {
        ObjectAnimator.ofFloat(mImageView,"alpha",1,0.5f).setDuration(1000).start();
    }
    public void ReadAnimatorOfXml(View view)
    {
        ObjectAnimator objectAnimator=(ObjectAnimator) AnimatorInflater.loadAnimator(MainActivity.this,R.animator.background);
        objectAnimator.setEvaluator(new ArgbEvaluator());
        objectAnimator.setTarget(mImageView);
        objectAnimator.start();
    }
}
