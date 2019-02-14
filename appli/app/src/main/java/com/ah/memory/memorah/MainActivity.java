package com.ah.memory.memorah;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.VideoView;



public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPref;
    private static MediaPlayer mediaPlayer; // instance pour la musique
    private VideoView accueil; // instance pour la video
    private static  int TIME_OUT = 5000; // time to lunch the another activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.musicbackground);
        mediaPlayer.setLooping(true);

        sharedPref = getApplicationContext().getSharedPreferences(Constants.PREFS,Context.MODE_PRIVATE);

        if (sharedPref.contains(Constants.PREFS_SOUNDS) && sharedPref.contains(Constants.PREFS_MUSIC)) {
            boolean sounds = sharedPref.getBoolean(Constants.PREFS_SOUNDS, false);
            boolean music = sharedPref.getBoolean(Constants.PREFS_MUSIC, false);

            if(music)
                mediaPlayer.start();

            if(sounds){}// a faire quand on rajoute du son

        } else
            mediaPlayer.start();


        accueil = findViewById(R.id.accueil);
        String uriPath = "android.resource://" + getPackageName() + "/" + R.raw.newdenis;
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
                Intent nextActivity = new Intent(MainActivity.this, MenuActivity.class);
                startActivity(nextActivity);
                finish();
            }
        }, TIME_OUT);
    }

    public static void musicOn(){
        mediaPlayer.start();
    }

    public static void musicOff(){
        mediaPlayer.pause();
    }

}
