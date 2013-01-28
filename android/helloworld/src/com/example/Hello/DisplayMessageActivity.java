package com.example.Hello;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayMessageActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the message from the intent
        Intent intent = getIntent();
        String message = intent.getStringExtra(HelloWorld.EXTRA_MESSAGE);

        // Create the text view
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(message);

        setContentView(textView);
        //setContentView(R.layout.activity_display_message);

        /* // Make sure we're running on Honeycomb or higher to use ActionBar APIs */
        /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) { */
        /*     // Show the Up button in the action bar. */
        /*     getActionBar().setDisplayHomeAsUpEnabled(true); */
        /* } */
    }

/*     @Override */
/*     public boolean onOptionsItemSelected(MenuItem item) { */
/*         switch (item.getItemID()) { */
/*             case android.R.id.home: */
/*                 NavUtils.navigateUpFromSameTask(this); */
/*                 return true; */
/*         } */
/*         return super.onOptionsItemSelected(item); */
/*     } */
}
