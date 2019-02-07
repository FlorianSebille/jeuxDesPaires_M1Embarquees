package com.ah.memory.memorah;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MenuActivity extends AppCompatActivity {

    private Button btnPlay;
    private Switch switchMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_menu);

        this.btnPlay = (Button)this.findViewById(R.id.buttonPlay);
        this.switchMusic = (Switch)this.findViewById(R.id.switch2);

        this.btnPlay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){
                System.out.println("test swith music ==> " + this.switchMusic.getSplitTrack());
                Intent intent = new Intent(MenuActivity.this, PlayActivity.class);
                startActivity(intent);

            }
        });

        this.switchMusic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            
        });
    }


}
