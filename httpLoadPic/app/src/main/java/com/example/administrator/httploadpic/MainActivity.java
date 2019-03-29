package com.example.administrator.httploadpic;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.os.Handler;

public class MainActivity extends AppCompatActivity {
    private Button mButton,mButtonSave;
    private ImageView mImageView;
    private HttpURLConnection mHttpURLConnection;
    private static final String URLSTRING="https://cdn.pixabay.com/photo/2018/05/27/18/19/animal-3434123_960_720.jpg";
    private Handler mHandler =new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bitmap bitmap=(Bitmap)msg.obj;
            mImageView.setImageBitmap(bitmap);
        }
    };
    private Bitmap mBitmap=null;
    private final static String SAVE_PATH= Environment.getExternalStorageDirectory()+"/download_test/";
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView=(ImageView)findViewById(R.id.iv);
        mButton=(Button)findViewById(R.id.btn);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mBitmap=getConnectionContent();
                        Message message=new Message();
                        message.obj=mBitmap;
                        mHandler.sendMessage(message);
                    }
                }).start();
            }
        });
        mButtonSave=(Button)findViewById(R.id.btn_save);
        mButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveFile("test.jpg");
            }
        });
        if(shouldAskPermission())
            askPermissions();
    }
    private void SaveFile(String fileName)
    {
        File dirFile=new File(SAVE_PATH);
        if(!dirFile.exists())
            dirFile.mkdir();
        File file=new File(SAVE_PATH+fileName);
        try
        {
            BufferedOutputStream bufferedOutputStream=new BufferedOutputStream(new FileOutputStream(file));
            mBitmap.compress(Bitmap.CompressFormat.JPEG,80,bufferedOutputStream);
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            Log.d("MainActivity","success of Saving picture");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
    protected boolean shouldAskPermission()
    {
        return Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP_MR1;
    }
    @TargetApi(23)
    protected void askPermissions()
    {
        String []permissions={"android.permission.READ_EXTERNAL_STORAGE","android.permission.WRITE_EXTERNAL_STORAGE"};
        int requestCode=200;
        requestPermissions(permissions,requestCode);
    }
    private Bitmap getConnectionContent()
    {
        InputStream inputStream=null;
        Bitmap bitmap=null;
        try
        {
            URL url=new URL(URLSTRING);
            mHttpURLConnection=(HttpURLConnection)url.openConnection();
            mHttpURLConnection.setConnectTimeout(5000);
            mHttpURLConnection.setReadTimeout(5000);
            mHttpURLConnection.setRequestMethod("GET");
            inputStream =mHttpURLConnection.getInputStream();
            bitmap= BitmapFactory.decodeStream(inputStream);
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally {
            if(inputStream!=null)
                try
                {
                    inputStream.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            if(mHttpURLConnection!=null)
                mHttpURLConnection.disconnect();
        }
        if(bitmap==null)
            Log.d("MainActivity:","bitmap is null");
        return  bitmap;
    }
}
