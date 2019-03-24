package com.example.administrator.implict_startactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Administrator on 2019/3/22.
 */
public class ThirdActivity extends Activity {
    private String TAG ="ThirdActivity";
    private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_layout);
        mTextView=(TextView)findViewById(R.id.tv);
        Intent intent=getIntent();
        String info=intent.getStringExtra("info");
        mTextView.setText("Receive MainActivity:"+info);
    }
    public void back(View view)
    {
        getIntent().putExtra("infoback","thirdActivity message");
        setResult(RESULT_OK, getIntent());
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "thirdActivity stop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "thirdActivity Pause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "thirdActivity destroy");
    }
}
