package com.example.administrator.valueanimator;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class MainActivity extends Activity {
    private TextView mTextView;
    private boolean show=false;
    private WebView mWebView;
    private int height=0;
    private String TAG="MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.tv);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                height = mTextView.getHeight();
                Log.d(TAG, "textView height:" + height);
            }
        }).start();
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performAnim();
            }
        });
        initWebView();
    }

    private void initWebView()
    {
        mWebView = (WebView) findViewById(R.id.wv);
        WebSettings webSettings=mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setLoadWithOverviewMode(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });

        mWebView.loadUrl("http://www.baidu.com");
    }
    private void performAnim()
    {
        show=!show;
        ValueAnimator valueAnimator;
        if(show)
        {
            valueAnimator=ValueAnimator.ofInt(height,40);
            mTextView.setText("单击展开");
        }
        else
        {
            valueAnimator=ValueAnimator.ofInt(40, height);
            mTextView.setText("单击收缩");
        }
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int heightTemp=(Integer)animation.getAnimatedValue();
                Log.d(TAG,"updata height:"+heightTemp);
                mTextView.getLayoutParams().height=heightTemp;
                mTextView.requestLayout();
            }
        });
        //valueAnimator.setInterpolator(new BounceInterpolator());
        valueAnimator.setDuration(2000);
        valueAnimator.start();
    }
}
