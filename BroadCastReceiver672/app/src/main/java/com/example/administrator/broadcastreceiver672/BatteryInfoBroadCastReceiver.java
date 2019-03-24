package com.example.administrator.broadcastreceiver672;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

/**
 * Created by Administrator on 2019/3/22.
 */
public class BatteryInfoBroadCastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction()))
        {
            int level=intent.getIntExtra("level",0);
            int scale=intent.getIntExtra("scale",0);
//            int voltage=intent.getIntExtra("voltage",0);
//            int temperature=intent.getIntExtra("temprature",0);
//            String technology=intent.getStringExtra("technology");
            Dialog dialog=new AlertDialog.Builder(context)
                    .setTitle("电池电量")
                    .setMessage("电池电量为：" +
                            String.valueOf(level*100/scale)+"%\n")
                    .setNegativeButton("关闭", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).create();
            dialog.show();
        }
    }
}
