package com.ah.memory.memorah;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    private Button btnPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_menu);

        this.btnPlay = (Button)this.findViewById(R.id.buttonPlay);

        this.btnPlay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){

                Intent intent = new Intent(MenuActivity.this, PlayActivity.class);
                startActivity(intent);

            }
        });
    }


}
