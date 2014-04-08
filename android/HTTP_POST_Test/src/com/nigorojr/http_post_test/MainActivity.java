package com.nigorojr.http_post_test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    public static String url = "http://nigorojr.com/naoki/cgi-bin/database_test.cgi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        findViewById(R.id.send).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText et1 = (EditText)findViewById(R.id.id);
                EditText et2 = (EditText)findViewById(R.id.name);
                EditText et3 = (EditText)findViewById(R.id.age);
                EditText et4 = (EditText)findViewById(R.id.sex);
                String[] array = new String[] {
                        et1.getText().toString(), et2.getText().toString(),
                        et3.getText().toString(), et4.getText().toString()
                };
                sendText(array);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void sendText(String[] text) {
        
        PostTextTask post = new PostTextTask();
        try {
            toastHttpResponce(post.execute(text).get());
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        catch (ExecutionException e) {
            e.printStackTrace();
        }
        
    }
    
    public class PostTextTask extends AsyncTask<String, Integer, Integer> {

        @Override
        protected Integer doInBackground(String... text) {
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(url);
            
            HttpResponse res = null;
            
            ArrayList<NameValuePair> list = new ArrayList<NameValuePair>();
            String[] what = {"id", "name", "age", "sex"};
            for (int i = 0; i < text.length; i++)
                list.add(new BasicNameValuePair(what[i], text[i]));

            try {
                post.setEntity(new UrlEncodedFormEntity(list, "utf-8"));
                res = client.execute(post);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            if (res == null)
                return null;

            return res.getStatusLine().getStatusCode();
        }
    }
    
    public void toastHttpResponce(int code) {
        String msg = "";
        switch (code) {
            case HttpStatus.SC_OK:
                msg = "Successfully sent!";
                break;
            case HttpStatus.SC_NOT_FOUND:
                msg = "404. Sounds familiar?";
                break;
            case HttpStatus.SC_INTERNAL_SERVER_ERROR:
                msg = "Something's wrong with the server!";
                break;
            default:
                msg = "0 (meaning it failed)";
        }
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }

}
