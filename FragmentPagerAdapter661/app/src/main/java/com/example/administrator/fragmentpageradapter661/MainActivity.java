package com.example.administrator.fragmentpageradapter661;

import android.support.v4.app.Fragment;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {
    private ViewPager mViewPager;
    private PagerTabStrip mPagerTabStrip;
    private List<Fragment> mFragments;
    private List<String> mTitles;
    private MyFragmentViewPagerAdapter myFragmentViewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager=(ViewPager)findViewById(R.id.viewPager);
        mPagerTabStrip=(PagerTabStrip)findViewById(R.id.pagerTabStrip);
        mPagerTabStrip.setDrawFullUnderline(false);
        mPagerTabStrip.setTabIndicatorColor(Color.WHITE);
        mPagerTabStrip.setTextColor(Color.WHITE);
        mPagerTabStrip.setBackgroundResource(android.R.drawable.alert_dark_frame);
        initDatas();
        myFragmentViewPagerAdapter=new MyFragmentViewPagerAdapter(getSupportFragmentManager(),mFragments,mTitles);
        mViewPager.setAdapter(myFragmentViewPagerAdapter);
    }
    private void initDatas()
    {
        mFragments=new ArrayList<>();
        mTitles=new ArrayList<>();
        mFragments.add(new View1_Fragment());
        mFragments.add(new View2_Fragment());
        mFragments.add(new View3_Fragment());
        mTitles.add("first page");
        mTitles.add("second page");
        mTitles.add("third page");
    }
}
