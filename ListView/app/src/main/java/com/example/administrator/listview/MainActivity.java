package com.example.administrator.listview;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;


import java.util.ArrayList;
import java.util.List;
import android.os.Handler;

public class MainActivity extends AppCompatActivity implements UpAddListView.IUpAddListener {

    private UpAddListView mUpAddListView;
    private List datas = new ArrayList<String>();
    private ArrayAdapter mArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initDatas();
    }

    private void initViews() {
        mUpAddListView = (UpAddListView) findViewById(R.id.lv);
        mUpAddListView.setInterface(this);
        mArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, datas);
        mUpAddListView.setAdapter(mArrayAdapter);
    }

    private void initDatas() {
        for (int i = 0; i < 10; i++) {
            datas.add("测试数据" + i);
        }
    }

    private void addMoreDatas() {
        for (int i = 0; i < 3; i++) {
            datas.add("新数据" + i);
        }
    }

    @Override
    public void OnAdd() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                addMoreDatas();
                mArrayAdapter.notifyDataSetChanged();
                mUpAddListView.addComleted();
            }
        }
                , 2000);
    }
}
