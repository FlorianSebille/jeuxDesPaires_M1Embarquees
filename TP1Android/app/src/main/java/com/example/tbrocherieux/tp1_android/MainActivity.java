package com.example.tbrocherieux.tp1_android;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private Button validateButton, goToButton;
    private TextInputLayout firstNameInput, lastNameInput;
    private Snackbar mySnackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.activity_main);

        this.goToButton = (Button)this.findViewById(R.id.goTo);
        this.validateButton = (Button)this.findViewById(R.id.button);
        this.firstNameInput = (TextInputLayout) this.findViewById(R.id.prenom);
        this.lastNameInput = (TextInputLayout) this.findViewById(R.id.nom);

        this.validateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MediaPlayer mPlayer = MediaPlayer.create(getBaseContext(), R.raw.son);
                mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mPlayer.start();

                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("prenom",firstNameInput.getEditText().getText().toString());
                intent.putExtra("nom",lastNameInput.getEditText().getText().toString());
                startActivity(intent);

                //Snackbar.make(findViewById(R.id.myCoordinatorLayout),  + "-" + ,Snackbar.LENGTH_SHORT).show();
            }
        });

        this.goToButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, Main3Activity.class);
                startActivity(intent);

            }
        });
    }


}
