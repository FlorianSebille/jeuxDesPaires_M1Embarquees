package com.ah.memory.memorah;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.ah.memory.memorah.Adapters.CollectionAdapter;

import java.util.ArrayList;

public class CollectionActivity extends AppCompatActivity {

    private RecyclerView CollectionRecyclerView;
    public ArrayList<Integer> cards = new ArrayList<>();

    private SharedPreferences pref;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);

        CollectionRecyclerView = findViewById(R.id.levelview);

        RecyclerView.LayoutManager lm = new GridLayoutManager(getBaseContext(),3, LinearLayoutManager.VERTICAL,false);
        CollectionRecyclerView.setLayoutManager(lm);

        pref = getBaseContext().getSharedPreferences(Constants.PREFS, Context.MODE_PRIVATE);

        if (pref.contains(Constants.PREFS_COLLECTION_LEN_PREFIX)) {
            int count = pref.getInt(Constants.PREFS_COLLECTION_LEN_PREFIX, 0);

            System.out.println("TOTAL" + count);
            for (int i = 0; i < count; i++)
                cards.add(pref.getInt(Constants.PREFS_COLLECTION_VAL_PREFIX + i, i));
        }

        CollectionRecyclerView.setAdapter(new CollectionAdapter(cards));


    }
}
