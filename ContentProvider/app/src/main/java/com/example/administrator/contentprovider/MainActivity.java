package com.example.administrator.contentprovider;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private ListView mListView;
    ContentResolver mContentResolver;
    private List<String> mDatas=new ArrayList<>();
    Cursor mCursor=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView=(ListView)findViewById(R.id.lv);
        mContentResolver=getContentResolver();
    }
    public void btnQuery(View view)
    {
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED)
        {
            if(Build.VERSION.SDK_INT>=23)
            {
                ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.READ_CONTACTS},123);
            }
        }
        else
        {
            requestDatas();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==123&&ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS)==PackageManager.PERMISSION_GRANTED)
        {
            requestDatas();
        }
    }

    private void requestDatas()
    {
        mDatas.clear();
        mCursor=mContentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
        if(mCursor!=null)
        {
            while(mCursor.moveToNext())
            {
                int id=mCursor.getInt(mCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone._ID));
                String disPlayName=mCursor.getString(mCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String number=mCursor.getString(mCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                mDatas.add(id+"-姓名："+disPlayName+"-电话："+number);
                mCursor.close();
                mListView.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,mDatas));
            }
        }
    }
}
