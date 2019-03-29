package com.example.administrator.floatmenu;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{
    private ImageView mImageView1,mImageView2,mImageView3,mImageViewMore;
    private boolean mIsSelected=false;
    private List<View> mViews=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

    }
    private void initViews()
    {
        mImageView1=(ImageView)findViewById(R.id.img1);
        mImageView2=(ImageView)findViewById(R.id.img2);
        mImageView3=(ImageView)findViewById(R.id.img3);
        mImageViewMore=(ImageView)findViewById(R.id.img_more);
        mImageViewMore.setOnClickListener(this);
        mImageView1.setOnClickListener(this);
        mImageView2.setOnClickListener(this);
        mImageView3.setOnClickListener(this);
        mViews.add(mImageView1);
        mViews.add(mImageView2);
        mViews.add(mImageView3);
    }
    private void finishAnimator()
    {
        mIsSelected=false;
        ObjectAnimator animator =ObjectAnimator.ofFloat(mImageViewMore,"rotation",0F,360F).setDuration(1000);
        animator.setInterpolator(new BounceInterpolator());
        animator.start();
        for(int i=0;i<3;i++)
        {
            ObjectAnimator.ofFloat(mViews.get(i),"translationY",-200*(i+1),0f).setDuration(1000).start();
        }
    }
    private void startAnimator()
    {
        mIsSelected=true;
        ObjectAnimator animator =ObjectAnimator.ofFloat(mImageViewMore,"rotation",0F,360F).setDuration(1000);
        animator.setInterpolator(new BounceInterpolator());
        animator.start();
        for(int i=0;i<3;i++)
        {
            ObjectAnimator.ofFloat(mViews.get(i),"translationY",0f,-200*(i+1)).setDuration(1000).start();
        }
    }
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.img_more:
                if(mIsSelected)
                    finishAnimator();
                else
                    startAnimator();
                break;
            case R.id.img1:
                Toast.makeText(MainActivity.this,"imag1",Toast.LENGTH_LONG).show();
                break;
            case R.id.img2:
                Toast.makeText(MainActivity.this,"imag2",Toast.LENGTH_LONG).show();
                break;
            case R.id.img3:
                Toast.makeText(MainActivity.this,"imag3",Toast.LENGTH_LONG).show();
                break;
        }
    }

}
