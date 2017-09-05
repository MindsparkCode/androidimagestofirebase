package com.a2l.cheatoye;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends Activity {

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.ProgressBar01);
        progressBar.setVisibility(View.GONE);
    }

    public void onStart() {
        super.onStart();

        // reset the bar to the default value of 0
        progressBar.setProgress(0);
        // create a thread for updating the progress bar
        Thread background = new Thread(new Runnable() {
            public void run() {
                try {
                    // wait 1000ms between each update
                    Thread.sleep(2000);

                    startActivity(new Intent(getApplicationContext(), Home.class));
                    finish();
                } catch (Throwable t) {
                }
            }
        });
        background.start();
    }

    public void onStop() {
        super.onStop();
    }
}
