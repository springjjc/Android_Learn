package com.example.administrator.sms_monitor;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private SMSBroadcastReceiver mReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView)findViewById(R.id.tv);
        mReceiver=new SMSBroadcastReceiver();
        mReceiver.setOnReceiveMessageListener(new SMSBroadcastReceiver.MessageListener() {
            @Override
            public void OnReceived(String message) {
                textView.setText(message);
            }
        });
        IntentFilter intentFilter=new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        MainActivity.this.registerReceiver(mReceiver,intentFilter);
    }
}
