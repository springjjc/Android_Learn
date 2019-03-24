package com.example.administrator.customizedbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Administrator on 2019/3/23.
 */
public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("com.test.broadcast"))
        {
            String info=intent.getStringExtra("info");
            Toast.makeText(context,"receive:"+info,Toast.LENGTH_LONG).show();
        }
    }
}
