package com.example.administrator.switchactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Administrator on 2019/3/21.
 */
public class SecActivity extends Activity {

    private String TAG ="SecActivity";
    private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sec_activity);
        mTextView=(TextView)findViewById(R.id.tv);
        Intent intent=getIntent();
        String info=intent.getStringExtra("info");
        mTextView.setText("Receive MainActivity:"+info);
    }
    public void back(View view)
    {
        getIntent().putExtra("infoback","secActivity message");
        setResult(RESULT_OK, getIntent());
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "SecActivity stop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "SecActivity Pause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "SecActivity destroy");
    }
}
