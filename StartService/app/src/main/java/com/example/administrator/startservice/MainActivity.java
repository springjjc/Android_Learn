package com.example.administrator.startservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private Intent mIntentStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIntentStart=new Intent(this,MyServiceStart.class);
    }
    public void startService(View view)
    {
        startService(mIntentStart);
    }
    public  void stopService(View view)
    {
        stopService(mIntentStart);
    }
}
