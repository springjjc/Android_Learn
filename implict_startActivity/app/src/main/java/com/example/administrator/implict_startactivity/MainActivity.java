package com.example.administrator.implict_startactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mButton;

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn:
                Intent intent=new Intent();
                intent.setAction("com.example.AnotherActivity");
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton=(Button)findViewById(R.id.btn);
        mButton.setOnClickListener(this);

    }
}
