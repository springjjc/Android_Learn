package com.example.administrator.frameanimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.os.Handler;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private ProgressDialogDemo dialogDemo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void test(View view)
    {
        dialogDemo=new ProgressDialogDemo(this,"正在加载中",R.drawable.frame_animation);
        dialogDemo.show();
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dialogDemo.dismiss();
            }
        },3000);
    }
}
