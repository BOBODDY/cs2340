package com.so.sofinances.ui;

import com.so.sofinances.R;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
 
public class SplashActivity extends Activity {
 
	private MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
              
            // Using handler with postDelayed called runnable run method
  
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, WelcomeActivity.class);
                startActivity(i);
  
                // close this activity
                finish();
            }
        }, 2*1000); // wait for 2 seconds
        mp = MediaPlayer.create(getBaseContext(), R.raw.lion_roar); /*Gets your 
        soundfile from res/raw/sound.ogg */
        mp.start(); //Starts your sound
    }
     
    @Override
    protected void onDestroy() {
         
        super.onDestroy();
         
    }
}