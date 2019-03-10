package com.ah.memory.memorah;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Intent;

import com.wajahatkarim3.easyflipview.EasyFlipView;

import org.jetbrains.annotations.NotNull;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;


public class Result extends Fragment {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private int bestEasyScore , bestHardScore;

    private EasyFlipView square1_flip;
    private EasyFlipView square2_flip;
    private EasyFlipView square3_flip;
    private EasyFlipView square4_flip;

    public Result() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_result, container, false);
        KonfettiView konfettiView = rootView.findViewById(R.id.viewKonfetti);

        pref = getActivity().getSharedPreferences("HighScore",0);
        editor= pref.edit();

        bestEasyScore = pref.getInt(Constants.EASY_HIGH_KEY,22);
        bestHardScore = pref.getInt(Constants.HARD_HIGH_KEY,32);

        Bundle b=getArguments();

        square1_flip = rootView.findViewById(R.id.square1_flip);
        square2_flip = rootView.findViewById(R.id.square2_flip);
        square3_flip = rootView.findViewById(R.id.square3_flip);
        square4_flip = rootView.findViewById(R.id.square4_flip);

        //hideEndScreenCard();

        if (b.getString("Data").equals("win")){
            konfettiView.build()
                    .addColors(Color.parseColor("#fce18a"), Color.parseColor("#ff726d"), Color.parseColor("#b48def"), Color.parseColor("#f4306d"))
                    .setDirection(0.0,359.0)
                    .setSpeed(4f,7f)
                    .setFadeOutEnabled(true)
                    .setTimeToLive(2000)
                    .addSizes(new Size(12,2f), new Size(16, 6f))
                    .addShapes(Shape.CIRCLE,Shape.RECT)
                    .setPosition(-50f,
                            konfettiView.getWidth() + 500f, -50f, -50f)
                    .stream(400,5000L);

            if (Integer.valueOf(b.get("level").toString()) == Constants.LEVEL_EASY){
                if (Integer.valueOf(b.get("Time").toString()) < bestEasyScore){
                    editor.putInt(Constants.EASY_HIGH_KEY, Integer.valueOf(b.get("Time").toString())).apply();
                    ((TextView) rootView.findViewById(R.id.newhigh)).setText("New High Score!");
                }
            }
            else if (Integer.valueOf(b.get("level").toString()) == Constants.LEVEL_HARD){
                if (Integer.valueOf(b.get("Time").toString()) < bestHardScore){
                    editor.putInt(Constants.HARD_HIGH_KEY, Integer.valueOf(b.get("Time").toString())).apply();
                    ((TextView) rootView.findViewById(R.id.newhigh)).setText("New High Score!");
                }
            }

            ((TextView)rootView.findViewById(R.id.square1_text)).setText("Y");
            ((TextView)rootView.findViewById(R.id.square2_text)).setText("A");
            ((TextView)rootView.findViewById(R.id.square3_text)).setText("Y");
            ((TextView)rootView.findViewById(R.id.square4_text)).setText("!");
            animateEndScreen(true);
            ((TextView)rootView.findViewById(R.id.desc1)).setText(getString(R.string.resultWinText1));
            ((TextView)rootView.findViewById(R.id.desc2)).setText(getString(R.string.resultWinText2));
            ((TextView)rootView.findViewById(R.id.time)).setText(getString(R.string.resultTime) + b.get("Time").toString());


        }
        else{


            ((TextView)rootView.findViewById(R.id.square1_text)).setText("N");
            ((TextView)rootView.findViewById(R.id.square2_text)).setText("O");
            ((TextView)rootView.findViewById(R.id.square3_text)).setText("P");
            ((TextView)rootView.findViewById(R.id.square4_text)).setText("E");
            animateEndScreen(false);
            ((TextView) rootView.findViewById(R.id.desc1)).setText(getString(R.string.resultLostText1));
            ((TextView) rootView.findViewById(R.id.desc2)).setText(getString(R.string.resultLostText1));
            ((TextView) rootView.findViewById(R.id.time)).setText(getString(R.string.resultTime)+b.get("Time").toString());
        }


        return rootView;
    }

    private void animateEndScreen(final boolean win){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                square1_flip.flipTheView();
            }
        },500);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                square2_flip.flipTheView();
            }
        },1000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                square3_flip.flipTheView();
            }
        },1500);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                square4_flip.flipTheView();
                if(win){
                    new SoundPlayer(getContext()).playSound("win.mp3");
                }else {
                    new SoundPlayer(getContext()).playSound("lost.mp3");

                }
            }
        },2000);

    }
}
