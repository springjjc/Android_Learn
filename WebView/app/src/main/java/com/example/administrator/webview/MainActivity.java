package com.example.administrator.webview;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity {
    private EditText mEditText;
    private Button mButton,mButtonLeft,mButtonRight,mButtonUp;
    private WebView mWebView;
    private ProgressBar mProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mEditText.setText("https://www.cnblogs.com/yongdaimi/p/6170982.html");
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebView.loadUrl(mEditText.getText().toString());
            }
        });
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                mProgressBar.setProgress(newProgress);
            }
        });
        mWebView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                float webViewHeight = mWebView.getContentHeight() * mWebView.getScaleY();
                float nowHeight = mWebView.getHeight() + mWebView.getScrollY();
                if (nowHeight == webViewHeight) {
                    mButtonUp.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "已经处于底端", Toast.LENGTH_LONG).show();
                } else if (mWebView.getScrollY() == 0) {
                    Toast.makeText(MainActivity.this, "已经处于顶端", Toast.LENGTH_LONG).show();
                } else {
                    mButtonUp.setVisibility(View.GONE);
                }
            }
        });
        mWebView.loadUrl("https://www.cnblogs.com/yongdaimi/p/6170982.html");
        int loc=getLoc();
        mWebView.scrollTo(0,loc);
    }

    private void initView()
    {
        mEditText=(EditText)findViewById(R.id.edit_web);
        mButton=(Button)findViewById(R.id.btn_load);
        mWebView=(WebView)findViewById(R.id.webview);
        mProgressBar=(ProgressBar)findViewById(R.id.progressBar);
        mButtonUp=(Button)findViewById(R.id.btn_up);
    }
    public void reload(View view)
    {
        mWebView.reload();
    }
    public void right(View view)
    {
        mWebView.goForward();
    }
    public void left(View view)
    {
        mWebView.goBack();
    }
    public void up(View view)
    {
        mButtonUp.setVisibility(View.GONE);
        mWebView.scrollTo(0, 0);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK&&mWebView.canGoBack())
        {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    private void saveLoc()
    {
        SharedPreferences sharedPreferences=getSharedPreferences("loc",Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("scrolly",mWebView.getScrollY());
        editor.commit();
    }
    private int getLoc()
    {
        SharedPreferences sharedPreferences=getSharedPreferences("loc",Activity.MODE_PRIVATE);
        int loc=sharedPreferences.getInt("scrolly",0);
        return loc;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveLoc();
        Log.d("MainActivity","onDestroy");
    }
}
