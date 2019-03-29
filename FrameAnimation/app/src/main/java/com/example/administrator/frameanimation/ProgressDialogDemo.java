package com.example.administrator.frameanimation;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 2019/3/25.
 */
public class ProgressDialogDemo extends ProgressDialog {
    private AnimationDrawable mAnimation;
    private ImageView mImageView;
    private String mLoadingTip;
    private TextView mLoadingTv;
    private int mResid;

    public ProgressDialogDemo(Context context, String mLoadingTip, int mResid) {
        super(context);
        this.mLoadingTip = mLoadingTip;
        this.mResid = mResid;
        setCanceledOnTouchOutside(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();

    }
    private void initData()
    {
        mImageView.setBackgroundResource(mResid);
        mAnimation=(AnimationDrawable)mImageView.getBackground();
        mImageView.post(new Runnable() {
            @Override
            public void run() {
                mAnimation.start();
            }
        });
        mLoadingTv.setText(mLoadingTip);
    }
    private void initView()
    {

        setContentView(R.layout.dialog);
        mLoadingTv=(TextView)findViewById(R.id.loadingTv);
        mImageView=(ImageView)findViewById(R.id.loadingIv);
    }
}
