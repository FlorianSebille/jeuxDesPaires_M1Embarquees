package com.ah.memory.memorah.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

import com.ah.memory.memorah.Constants;
import com.ah.memory.memorah.R;

/**
 * Created by yuweichen on 16/5/3.
 */
public class DataDemoView extends LinearLayout{
    private ListView listview;
    private SeekBar seekBar;
    private TextView seekBarChosenDifficulty;

    public DataDemoView(Context context, int position, int[] scores) {
        super(context);
        initView(context, position, scores);
    }

    private void initView(Context context, int position, int[] scores) {
        View view = inflate(context, R.layout.view_list,this);
        listview = (ListView) view.findViewById(R.id.listview);
        seekBar = (SeekBar) view.findViewById(R.id.seekBarDifficulty);
        seekBarChosenDifficulty = (TextView) view.findViewById(R.id.seekBarDisplayText);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                context,
                android.R.layout.simple_expandable_list_item_1,
                getData(position, scores).subList(0,5));
        listview.setAdapter(adapter);

        ViewCompat.setNestedScrollingEnabled(listview, true);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarChosenDifficulty.setText(getDifficultyString(progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    private String getDifficultyString(int difficulty){
        switch (difficulty){
            case 0: return getContext().getString(R.string.difficultyEasy);
            case 2: return getContext().getString(R.string.difficultyHard);
            default: return getContext().getString(R.string.difficultyMedium);
        }
    }

    public int getDifficulty(){
        return this.seekBar.getProgress();
    }

    private ArrayList<String> getData(int position, int[] scores)
    {

        ArrayList<String> list = new ArrayList<>();

        if(scores[0] == 0)
            list.add("1)");
        else list.add("1)    " + scores[0]);

        if(scores[1] == 0)
            list.add("2)");
        else list.add("2)    " + scores[1]);

        if(scores[2] == 0)
            list.add("3)");
        else list.add("3)    " + scores[2]);

        if(scores[3] == 0)
            list.add("4)");
        else list.add("4)    " + scores[3]);

        if(scores[4] == 0)
            list.add("5)");
        else list.add("5)    " + scores[4]);

        return list;
    }


}