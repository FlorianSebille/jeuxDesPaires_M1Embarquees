package com.ah.memory.memorah;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.ah.memory.memorah.R;
import com.ah.memory.memorah.Start;

public class GameActivity extends AppCompatActivity {

    private int difficulty;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent myIntent = getIntent(); // gets the previously created intent
        difficulty = myIntent.getIntExtra("difficulty",0); // will return "FirstKeyValue"

        System.out.println("Diff :" +  difficulty);

        switch (difficulty){
            case 0: fragmentTransaction(new EasyLevel());break;
            case 1: fragmentTransaction(new MediumLevel());break;
            case 2: fragmentTransaction(new HardLevel());break;
        }

    }

    private void fragmentTransaction(Fragment f){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.layoutFragment, f);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
