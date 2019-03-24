package com.example.administrator.customizedbroadcast;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private MyBroadcastReceiver myBroadcastReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myBroadcastReceiver=new MyBroadcastReceiver();
        IntentFilter intentFilter=new IntentFilter("com.test.broadcast");
        MainActivity.this.registerReceiver(myBroadcastReceiver,intentFilter);
    }
    public void send(View view)
    {
        Intent intent=new Intent("com.test.broadcast");
        intent.putExtra("info","broadcast:hello world");
        sendBroadcast(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MainActivity.this.unregisterReceiver(myBroadcastReceiver);
    }
}
