package com.ah.memory.memorah;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    private int difficulty;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Bundle bundle = new Bundle();
        Fragment level = new Level();

        Intent myIntent = getIntent(); // gets the previously created intent
        difficulty = myIntent.getIntExtra("difficulty",0); // will return "FirstKeyValue"

        System.out.println("Diff :" +  difficulty);

        switch (difficulty){
            case 0:
                bundle.putInt("level_number",Constants.LEVEL_EASY);
                bundle.putString("level_key",Constants.EASY_HIGH_KEY);
                bundle.putInt("level_card_number",Constants.EASY_NO_OF_CARDS);
                bundle.putLong("level_timer",Constants.EASY_TIME);
                level.setArguments(bundle);
                fragmentTransaction(level);
                break;
            case 1:
                bundle.putInt("level_number",Constants.LEVEL_MEDIUM);
                bundle.putString("level_key",Constants.MEDIUM_HIGH_KEY);
                bundle.putInt("level_card_number",Constants.MEDIUM_NO_OF_CARDS);
                bundle.putLong("level_timer",Constants.MEDIUM_TIME);
                level.setArguments(bundle);
                fragmentTransaction(level);
                break;

            case 2:
                bundle.putInt("level_number",Constants.LEVEL_HARD);
                bundle.putString("level_key",Constants.HARD_HIGH_KEY);
                bundle.putInt("level_card_number",Constants.HARD_NO_OF_CARDS);
                bundle.putLong("level_timer",Constants.HARD_TIME);
                level.setArguments(bundle);
                fragmentTransaction(level);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void fragmentTransaction(Fragment f){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.layoutFragment, f);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
