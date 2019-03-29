package com.example.administrator.sharedpreference;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText mEditTextUserName;
    private EditText mEditTextPassword;
    private String mUserName,mPassword;
    SharedPreferences.Editor mEditor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditTextUserName=(EditText)findViewById(R.id.et_username);
        mEditTextPassword=(EditText)findViewById(R.id.et_password);
        SharedPreferences sharedPreferences =getSharedPreferences("userinfo",MODE_PRIVATE);
        mEditor=sharedPreferences.edit();
    }
    public void commit(View view)
    {
        mUserName=mEditTextUserName.getText().toString();
        mPassword=mEditTextPassword.getText().toString();
        mEditor.putString("userName",mUserName);
        mEditor.putString("password",mPassword);
        mEditor.commit();
    }
}
