package com.nigorojr.twier;

import twitter4j.TwitterException;
import twitter4j.auth.OAuthAuthorization;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationContext;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
    
    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button b = (Button)findViewById(R.id.button1);
        b.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                executeOauth();
            }
        });
    }
    
    public void executeOauth() {
        RequestToken _req = null;
        OAuthAuthorization _oauth = null;
        // Read configuration of Twitter4j
        Configuration conf = ConfigurationContext.getInstance();

        
        // Create OAuth authorization object
        _oauth = new OAuthAuthorization(conf);
        // Set consumerKey and consumerSecret to object
        // These 2 Strings should not be seen by others
        _oauth.setOAuthConsumer("", "");
        
        try {
            _req = _oauth.getOAuthRequestToken("Callback://CallBackActivity");
        }
        catch (TwitterException e) {
            e.printStackTrace();
        }
        
        String _uri;
        _uri = _req.getAuthorizationURL();
        startActivityForResult(new Intent(Intent.ACTION_VIEW, Uri.parse(_uri)), 0);
    }
}