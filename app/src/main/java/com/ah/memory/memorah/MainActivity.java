package com.ah.memory.memorah;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    private VideoView accueil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        accueil = findViewById(R.id.accueil);

        String uriPath = "android.resource://"+getPackageName()+"/"+R.raw.denis;
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
    }
}
