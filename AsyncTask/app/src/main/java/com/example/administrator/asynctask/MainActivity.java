package com.example.administrator.asynctask;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private Button mButtonDownload;
    private ProgressBar mProgressBar;
    private ImageView mImageView;
    private LoadImage mLoadImage;
    private static final String IMAGE_URL="https://cdn.pixabay.com/photo/2018/05/27/18/19/animal-3434123_960_720.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    private void initView()
    {
        mButtonDownload=(Button)findViewById(R.id.btnDownload);
        mProgressBar=(ProgressBar)findViewById(R.id.progressBar);
        mButtonDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoadImage=new LoadImage();
                mLoadImage.execute(IMAGE_URL);
            }
        });
        mProgressBar.setVisibility(View.INVISIBLE);
        mImageView=(ImageView)findViewById(R.id.image);
    }
    private class LoadImage extends AsyncTask<String,Integer,Bitmap>
    {
        @Override
        protected void onPreExecute() {
            mProgressBar.setVisibility(View.VISIBLE);
            mProgressBar.setProgress(0);
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            String imageUrl=params[0];
            try {
                URL url;
                HttpURLConnection httpURLConnection=null;
                InputStream inputStream=null;
                OutputStream outputStream=null;
                String fileName="load_image";
                try {
                    url=new URL(imageUrl);
                    httpURLConnection=(HttpURLConnection)url.openConnection();
                    httpURLConnection.setConnectTimeout(5000);
                    inputStream=httpURLConnection.getInputStream();
                    outputStream=openFileOutput(fileName, Context.MODE_PRIVATE);
                    long total=httpURLConnection.getContentLength();
                    byte []data=new byte[1024];
                    int length;
                    long current=0;
                    while((length=inputStream.read(data))!=-1) {
                        outputStream.write(data, 0, length);
                        current += length;
                        int progress = (int) ((float) current / total * 100);
                        Thread.sleep(1000);
                        publishProgress(progress);
                    }
                }
                finally {
                    if(httpURLConnection!=null)
                        httpURLConnection.disconnect();
                    if(inputStream!=null)
                        inputStream.close();
                    if(outputStream!=null)
                        outputStream.close();

                }
                return BitmapFactory.decodeFile(getFileStreamPath(fileName).getAbsolutePath());
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if(bitmap!=null)
                mImageView.setImageBitmap(bitmap);
            mProgressBar.setVisibility(View.INVISIBLE);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            mProgressBar.setProgress(values[0]);
            super.onProgressUpdate(values);
        }
    }
}
