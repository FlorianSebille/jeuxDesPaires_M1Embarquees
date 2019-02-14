package com.ah.memory.memorah;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MenuActivity extends AppCompatActivity {

    private static MediaPlayer mediaPlayer; // instance pour la musique
    private Button btnPlay;
    private Switch switchMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_menu);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.musicbackground);
        mediaPlayer.setLooping(true);

        this.btnPlay = (Button)this.findViewById(R.id.buttonPlay);
        this.switchMusic = (Switch)this.findViewById(R.id.switch2);
        this.switchMusic = (Switch)this.findViewById(R.id.switch2);

        this.btnPlay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){
                Intent intent = new Intent(MenuActivity.this, PlayActivity.class);
                startActivity(intent);

            }
        });

        switchMusic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked && !MainActivity.getMediaPlayer().isPlaying())
                    mediaPlayer.start();

                else  if(!isChecked)
                    if (mediaPlayer.isPlaying())
                        mediaPlayer.stop();
                    else MainActivity.getMediaPlayer().stop();

            }
        });
    }


}
