package com.example.administrator.viewpager;

import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager myViewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private List<View>  mDatas;
    private List<String> mTitles;
    private PagerTitleStrip mPagerTitleStrip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myViewPager=(ViewPager)findViewById(R.id.viewPager);
        initDatas();
        myViewPagerAdapter=new MyViewPagerAdapter(mDatas,mTitles);
        myViewPager.setAdapter(myViewPagerAdapter);
    }
    private void initDatas()
    {
        mTitles=new ArrayList<>();
        mTitles.add("第一页");
        mTitles.add("第二页");
        mTitles.add("第三页");
        mDatas=new ArrayList<>();
        View view1= LayoutInflater.from(this).inflate(R.layout.layout_page1,null);
        View view2=LayoutInflater.from(this).inflate(R.layout.layout_page2,null);
        View view3=LayoutInflater.from(this).inflate(R.layout.layout_page3,null);
        mDatas.add(view1);
        mDatas.add(view2);
        mDatas.add(view3);
    }
}
