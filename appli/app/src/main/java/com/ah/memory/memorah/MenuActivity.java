package com.ah.memory.memorah;

import android.content.Intent;
import android.media.MediaPlayer;
import android.content.SharedPreferences;
import android. content. Context;
import android.widget.Toast;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MenuActivity extends AppCompatActivity {

    private SharedPreferences sharedPref;
    private static MediaPlayer mediaPlayer; // instance pour la musique
    private Button btnPlay;
    private Button btnCollection;
    private Switch switchMusic;
    private Switch switchSounds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_menu);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.musicbackground);
        mediaPlayer.setLooping(true);

        this.btnPlay = (Button)this.findViewById(R.id.buttonPlay);
        this.btnCollection = (Button)this.findViewById(R.id.buttonCollection);
        this.switchMusic = (Switch)this.findViewById(R.id.switch2);
        this.switchSounds = (Switch)this.findViewById(R.id.switch1);

        sharedPref = getApplicationContext().getSharedPreferences(Constants.PREFS,Context.MODE_PRIVATE);

        if (sharedPref.contains(Constants.PREFS_SOUNDS) && sharedPref.contains(Constants.PREFS_MUSIC)) {
            boolean sounds = sharedPref.getBoolean(Constants.PREFS_SOUNDS, false);
            boolean music = sharedPref.getBoolean(Constants.PREFS_MUSIC, false);

            this.switchMusic.setChecked(music);
            this.switchSounds.setChecked(sounds);

        } else {
            savePreferences();
        }

        this.btnPlay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){
                Intent intent = new Intent(MenuActivity.this, PlayActivity.class);
                startActivity(intent);

            }
        });
        this.btnPlay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, Leaderboard.class);
                startActivity(intent);
            }
        });



        switchMusic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    MainActivity.musicOn();
                else MainActivity.musicOff();

                savePreferences();
            }
        });

        switchSounds.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // A FAIRE EN FONTION DES SONS QUI SERONT RAJOUTES
                //if(isChecked)
                    //MainActivity.musicOn();
                //else MainActivity.musicOff();
                savePreferences();
            }
        });
    }

    private void savePreferences(){
        sharedPref.edit()
                .putBoolean(Constants.PREFS_SOUNDS, switchSounds.isChecked())
                .putBoolean(Constants.PREFS_MUSIC, switchMusic.isChecked())
                .apply();

        Toast.makeText(this, "Sauvegardé, relancez l'application pour voir le résultat", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MainActivity.musicOff();
    }
}
