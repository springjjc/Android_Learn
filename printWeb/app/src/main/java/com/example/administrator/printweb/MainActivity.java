package com.example.administrator.printweb;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {
    private static final String URLSTRING ="http://gank.io/api/data/%E7%A6%8F%E5%88%A9/10/1";
    private HttpURLConnection mHttpURLConnection;
    private TextView mTextView;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);
            Log.d("MainActivity", "handleMessage()");
            mTextView.setText((String) msg.obj);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView=(TextView)findViewById(R.id.tv);

    }
    private String getConnectionContent()
    {

        InputStream inputStream =null;
        try {
            URL url=new URL(URLSTRING);
            mHttpURLConnection=(HttpURLConnection)url.openConnection();
            mHttpURLConnection.setConnectTimeout(5000);
            mHttpURLConnection.setReadTimeout(30000);
            mHttpURLConnection.setDoInput(true);
            mHttpURLConnection.setDoOutput(false);
            mHttpURLConnection.setUseCaches(false);
            mHttpURLConnection.setRequestMethod("GET");
            mHttpURLConnection.connect();
            inputStream =mHttpURLConnection.getInputStream();
            return convertStreamToString(inputStream);
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
            return "";
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return "";
        }
        finally {
            if(inputStream!=null)
            {
                try{
                    inputStream.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            if(mHttpURLConnection!=null){
                mHttpURLConnection.disconnect();
            }
        }
    }
    private String convertStreamToString(InputStream is) throws IOException
    {
        BufferedReader reader =new BufferedReader(new InputStreamReader((is)));
        StringBuffer sb=new StringBuffer();
        String line;
        while((line=reader.readLine())!=null)
        {
            sb.append(line+"\n");
        }
        String response=sb.toString();
        if(reader!=null)
            reader.close();
        return response;
    }
    public void get(View view)
    {
        Log.d("MainActivity","start get");
        new Thread(new Runnable() {
            @Override
            public void run() {
                String response=getConnectionContent();
                Log.d("reponse",response);
                Message message=new Message();
                message.obj=response;
                handler.sendMessage(message);
            }
        }).start();
    }
}
