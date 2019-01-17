package com.example.tbrocherieux.tp1_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    private Button returnButton;
    private TextView bonjour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.activity_main2);

        this.returnButton = (Button)this.findViewById(R.id.retour);
        this.bonjour = (TextView)this.findViewById(R.id.bonjour);

        Intent myIntent = getIntent(); // gets the previously created intent
        String prenom = myIntent.getStringExtra("prenom"); // will return "FirstKeyValue"
        String nom= myIntent.getStringExtra("nom");

        this.bonjour.setText("Bonjour " + prenom + " " + nom );

        this.returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }

        });

    }
}