package com.nigorojr.cmupdaterestore;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.doIt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executeCommands();
            }
        });
    }
    
    public void executeCommands() {
        try {
            Process p = Runtime.getRuntime().exec("su");
            DataOutputStream out = new DataOutputStream(p.getOutputStream());
            DataInputStream in = new DataInputStream(p.getInputStream());
            
            out.writeBytes("sh /mnt/sdcard/MyBackups/restore.sh");
            out.flush();
            
            out.writeBytes("exit" + "\n");
            out.flush();
            p.waitFor();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
