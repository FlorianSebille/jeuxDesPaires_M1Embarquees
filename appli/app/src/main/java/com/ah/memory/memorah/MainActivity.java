package com.ah.memory.memorah;

import android.media.MediaPlayer;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    private VideoView accueil;
    private static  int TIME_OUT = 5000; // time to lunch the another activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        accueil = findViewById(R.id.accueil);

        String uriPath = "android.resource://"+getPackageName()+"/"+R.raw.newdenis;
        Uri uri = Uri.parse(uriPath);
        accueil.setVideoURI(uri);
        accueil.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
        accueil.requestFocus();
        accueil.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                //transaction.replace(R.id.layoutFragment, new Start());
                //transaction.commit();

                Intent nextActivity = new Intent(MainActivity.this, MenuActivity.class);
                startActivity(nextActivity);
                finish();
            }
        }, TIME_OUT);
    }
}
