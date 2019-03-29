package com.example.administrator.handler;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


//子线程中创建的handler，接收消息需要起始和末端分别调用Looper.prepare,Looper.loop
public class MainActivity extends AppCompatActivity {
    private TextView mTextView;
    private int secondLeft=10;
    private Button mButton;
    private int count=0;
    private MyRunnable myRunnable;
    private final Handler mHandler=new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what)
            {
                case -1:
                    mTextView.setText("接收到空消息");
                    break;
                case 0:
                    mTextView.setText("接收到WHAT为0的消息"+msg.getData().getString("key"));
                    break;
                case 1:
                    secondLeft--;
                    mButton.setText(""+secondLeft);
                    if(secondLeft>0)
                    {
                        Message message=mHandler.obtainMessage(1);
                        mHandler.sendMessageDelayed(message,1000);
                    }
                    else
                    {
                        mButton.setText("倒计时结束");
                        secondLeft=10;
                    }
                    break;
                case 2:
                    mTextView.setText("接收到延迟的消息"+msg.getData().getString("key"));
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView=(TextView)findViewById(R.id.tv);
        mButton=(Button)findViewById(R.id.delay_btn);

    }
    public void sendWhat0(View view)
    {
        Message message=Message.obtain();
        message.what=0;
        Bundle bundle=new Bundle();
        bundle.putString("key", "this is what 0");
        message.setData(bundle);
        mHandler.sendMessage(message);
    }
    public void sendWhat1(View view)
    {
        Message message=Message.obtain();
        message.what=1;
        Bundle bundle=new Bundle();
        bundle.putString("key","this is what 1");
        message.setData(bundle);
        mHandler.sendMessage(message);
    }
    public void sendDelay(View view)
    {
        if(secondLeft!=10)
            return;
        Message message=mHandler.obtainMessage(1);
        mHandler.sendMessageDelayed(message, 1000);
    }
    public void sendEmpty(View view)
    {
        mHandler.sendEmptyMessage(-1);
    }
    public void postDelay(View view)
    {
        count=0;
        myRunnable=new MyRunnable();
        mHandler.postDelayed(myRunnable, 2000);
    }
    public void stopCycle(View view)
    {
        mHandler.removeCallbacks(myRunnable);
    }
    private class MyRunnable implements Runnable
    {
        @Override
        public void run() {
            count++;
            Log.d("MainActivity","run:"+count);
            mHandler.postDelayed(myRunnable,2000);
        }
    }
}
