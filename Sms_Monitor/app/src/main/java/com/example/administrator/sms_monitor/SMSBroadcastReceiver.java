package com.example.administrator.sms_monitor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import java.util.Objects;
import java.util.zip.CheckedOutputStream;

/**
 * Created by Administrator on 2019/3/23.
 */
public class SMSBroadcastReceiver extends BroadcastReceiver {
    private MessageListener mMessageListener;
    public SMSBroadcastReceiver() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        Object [] pdus=( Objects [] )bundle.get("pdus");
        String format=bundle.getString("format");
        for(Object pdu:pdus)
        {
            SmsMessage smsMessage=SmsMessage.createFromPdu((byte[])pdu,format);
            String content=smsMessage.getMessageBody();
            mMessageListener.OnReceived(content);
        }
    }
    public interface MessageListener
    {
        void OnReceived(String message);
    }
    public void setOnReceiveMessageListener(MessageListener messageListener)
    {
        mMessageListener=messageListener;
    }
}
