package com.example.administrator.gridview;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private GridView mGridView;
    private GridAdapter mGridAdapter;
    private boolean isShowDelete;
    private List<Animal> mDatas=new ArrayList<Animal>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE); //隐藏标题
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGridView=(GridView)findViewById(R.id.gv);
        initDatas();
        mGridAdapter=new GridAdapter(this,mDatas);
        mGridView.setAdapter(mGridAdapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position!=mDatas.size()) {
                    String name = mDatas.get(position).getAnimal();
                    ImageView imageView = new ImageView(MainActivity.this);
                    ViewGroup.LayoutParams layoutParams=imageView.getLayoutParams();
                    imageView.setScaleType(ImageView.ScaleType.CENTER);
                    imageView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    imageView.setImageResource(mDatas.get(position).getImgId());
                    Dialog dialog = new AlertDialog.Builder(MainActivity.this)
                            .setIcon(android.R.drawable.ic_btn_speak_now)
                            .setTitle("选择的动物是 " + name)
                            .setView(imageView)
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }).create();
                    dialog.show();
                }
                else
                {
                    Animal animalAdd=new Animal("蛇",R.drawable.snake);
                    mDatas.add(animalAdd);
                    mGridAdapter.notifyDataSetChanged();
                }
            }
        });
        mGridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if(isShowDelete)
                {
                    isShowDelete =false;
                    mGridAdapter.setmIsShowDelete(isShowDelete);
                }
                else
                {
                    isShowDelete=true;
                    mGridAdapter.setmIsShowDelete(isShowDelete);
                }
                return false;
            }
        });

    }
    private void initDatas()
    {
        Animal animal0=new Animal("马",R.drawable.horse);
        Animal animal1=new Animal("牛",R.drawable.cow);
        mDatas.add(animal0);
        mDatas.add(animal1);
    }
}
