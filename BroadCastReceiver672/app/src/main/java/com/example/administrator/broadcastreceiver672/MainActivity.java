package com.example.administrator.broadcastreceiver672;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    private Button mButton;
    private BatteryInfoBroadCastReceiver mReceive;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton=(Button)findViewById(R.id.btn);
        mButton.setOnClickListener(new onClickListenerImpl());
    }

    private class onClickListenerImpl implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            mReceive=new BatteryInfoBroadCastReceiver();
            IntentFilter filter=new IntentFilter(Intent.ACTION_BATTERY_CHANGED); //过滤广播消息
            MainActivity.this.registerReceiver(mReceive,filter);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MainActivity.this.unregisterReceiver(mReceive);
    }
}
