package com.ah.memory.memorah;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ah.memory.memorah.Adapters.EasyLevelAdapter;
import com.ah.memory.memorah.Adapters.HardLevelAdapter;
import com.ah.memory.memorah.Adapters.MediumLevelAdapter;
import com.wajahatkarim3.easyflipview.EasyFlipView;

import java.util.ArrayList;
import java.util.Random;


public class Level extends Fragment {

    private RecyclerView LevelRecyclerView;
    public ArrayList<Integer> cards;
    public int CARDS[];
    EasyFlipView flippedCard;
    public long RemainingTime;
    public boolean isPaused, isCancelled;
    Bundle b;
    private SharedPreferences pref;
    int pos, count, bestScore;

    private int levelLayout;
    private int levelNumber;
    private String levelKey;
    private int levelCardNumber;
    private long levelTimer;


    public Level() {
        // Required empty public constructor
    }

    private void initializeLevel(Bundle bundle){
        this.levelNumber = bundle.getInt("level_number");
        this.levelKey = bundle.getString("level_key");
        this.levelCardNumber = bundle.getInt("level_card_number");
        this.levelTimer = bundle.getLong("level_timer");

        this.CARDS = Constants.pickUpRandomCards(this.levelCardNumber);

        System.out.println(this.levelNumber);
        System.out.println(this.levelKey);
        System.out.println(this.levelCardNumber);
        System.out.println(this.levelTimer);

    }

    public void shuffle(int cards[], int n){
        Random random = new Random();

        for (int i=0;i<n;i++){
            int r = random.nextInt(n-i);

            int temp = cards[r];
            cards[r] = cards[i];
            cards[i] = temp;
        }
    }

    public void fragmentTransaction(Bundle b){
        final FragmentTransaction transaction = getFragmentManager().beginTransaction();
        final Result r= new Result();
        r.setArguments(b);
        transaction.replace(R.id.layoutFragment, r);
        transaction.commit();
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        initializeLevel(getArguments());


        final View rootView =  inflater.inflate(R.layout.fragment_level, container, false);

        LevelRecyclerView = rootView.findViewById(R.id.levelview);
        b=new Bundle();
        b.putInt("level",this.levelNumber);
        pref = getActivity().getSharedPreferences(Constants.PREF_NAME,0);
        bestScore = pref.getInt(this.levelKey, (int) (this.levelTimer/Constants.TIMER_INTERVAL));

        ((TextView) rootView.findViewById(R.id.best)).append(bestScore+"");

        RecyclerView.LayoutManager lm = new GridLayoutManager(getContext(),3, LinearLayoutManager.VERTICAL,false);
        LevelRecyclerView.setLayoutManager(lm);

        cards = new ArrayList<>();
        // TODO: card shuffle here

        shuffle(CARDS,this.levelCardNumber);
        shuffle(CARDS,this.levelCardNumber);   // double shuffle
        for (int card : CARDS){
            cards.add(card);
        }

        switch (this.levelNumber){
            case 1:
                LevelRecyclerView.setAdapter(new EasyLevelAdapter(cards));
                break;
            case 2:
                LevelRecyclerView.setAdapter(new MediumLevelAdapter(cards));
                break;
            case 3:
                LevelRecyclerView.setAdapter(new HardLevelAdapter(cards));
                break;
        }

        isPaused = false;
        isCancelled = false;

         new CountDownTimer(this.levelTimer,Constants.TIMER_INTERVAL){
            @Override
            public void onTick(long millisUntilFinished) {
                if (isPaused || isCancelled){
                    cancel();
                }
                else {
                    ((TextView) rootView.findViewById(R.id.levelcounter)).setText("Time : " + millisUntilFinished / Constants.TIMER_INTERVAL);
                    RemainingTime = millisUntilFinished;
                    if (count == levelCardNumber) {
                        b.putString("Data", "win");
                        long time = (levelTimer - millisUntilFinished)/ Constants.TIMER_INTERVAL;
                        b.putInt("Time", (int) time);
                        cancel();
                        this.onFinish();
                    }
                }
            }

            @Override
            public void onFinish() {
                if (count < levelCardNumber) {
                    b.putString("Data", "lost");
                    b.putInt("Time", (int) (levelTimer/Constants.TIMER_INTERVAL));
                }
                fragmentTransaction(b);
            }
        }.start();


        rootView.setFocusableInTouchMode(true);
        rootView.requestFocus();
        rootView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP){
                    isPaused = true;
                    AlertDialog.Builder pause = new AlertDialog.Builder(getContext());
                    pause.setTitle("Game paused");
                    pause.setMessage("Do you want to quit ?");
                    pause.setCancelable(false);
                    pause.setPositiveButton("Resume", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            isPaused = false;
                            new CountDownTimer(RemainingTime,Constants.TIMER_INTERVAL){
                                int time;
                                @Override
                                public void onTick(long millisUntilFinished) {
                                    if (isPaused || isCancelled){
                                        cancel();
                                    }
                                    else {
                                        ((TextView) rootView.findViewById(R.id.levelcounter)).setText("Time : " + millisUntilFinished / Constants.TIMER_INTERVAL);
                                        RemainingTime = millisUntilFinished;
                                        if (count == levelCardNumber) {
                                            b.putString("Data", "win");
                                            time = (int) ((levelTimer - millisUntilFinished)/ Constants.TIMER_INTERVAL);
                                            b.putInt("Time", time);
                                            cancel();
                                            this.onFinish();
                                        }
                                    }
                                }

                                @Override
                                public void onFinish() {
                                    if (count < levelCardNumber) {
                                        b.putString("Data", "lost");
                                        b.putInt("Time", (int) (levelTimer/Constants.TIMER_INTERVAL));
                                    }
                                    fragmentTransaction(b);
                                }
                            }.start();
                        }
                    });
                    pause.setNegativeButton("Quit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            isCancelled = true;
                            getActivity().finish();
                        }
                    });
                    pause.show();
                    return true;
                }
                return false;
            }
        });

        LevelRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            @Override
            public boolean onInterceptTouchEvent(final RecyclerView rv, MotionEvent e) {
                final View child = rv.findChildViewUnder(e.getX(),e.getY());
                if (child != null){

                    final int position = rv.getChildAdapterPosition(child);

                    if (flippedCard == null){
                        flippedCard = (EasyFlipView) child;
                        pos = position;
                    }

                    else{
                        if (pos == position){
                            flippedCard=null;
                        }
                        else{
                            if (cards.get(pos).equals(cards.get(position))){
                                ((EasyFlipView) child).setOnFlipListener(new EasyFlipView.OnFlipAnimationListener() {
                                    @Override
                                    public void onViewFlipCompleted(EasyFlipView easyFlipView, EasyFlipView.FlipState newCurrentSide) {
                                        for (int i = 0; i < LevelRecyclerView.getChildCount(); i++) {
                                            EasyFlipView child1 = (EasyFlipView) LevelRecyclerView.getChildAt(i);
                                            child1.setEnabled(false);
                                        }
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                flippedCard.setVisibility(View.GONE);
                                                child.setVisibility(View.GONE);
                                                child.setEnabled(false);
                                                flippedCard.setEnabled(false);
                                                flippedCard=null;
                                                count+=2;

                                                for (int i = 0; i < LevelRecyclerView.getChildCount(); i++) {
                                                    EasyFlipView child1 = (EasyFlipView) LevelRecyclerView.getChildAt(i);
                                                    child1.setEnabled(true);
                                                }
                                                ((EasyFlipView) child).setOnFlipListener(null);
                                            }
                                        },250);
                                    }
                                });
                            }
                            else {
                                ((EasyFlipView) child).setOnFlipListener(new EasyFlipView.OnFlipAnimationListener() {
                                    @Override
                                    public void onViewFlipCompleted(EasyFlipView easyFlipView, EasyFlipView.FlipState newCurrentSide) {
                                        for (int i = 0; i < LevelRecyclerView.getChildCount(); i++) {
                                            EasyFlipView child1 = (EasyFlipView) LevelRecyclerView.getChildAt(i);
                                            child1.setEnabled(false);
                                        }
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                flippedCard.flipTheView();
                                                ((EasyFlipView) child).flipTheView();
                                                flippedCard = null;
                                                ((EasyFlipView) child).setOnFlipListener(null);

                                                for (int i = 0; i < LevelRecyclerView.getChildCount(); i++) {
                                                    EasyFlipView child1 = (EasyFlipView) LevelRecyclerView.getChildAt(i);
                                                    child1.setEnabled(true);
                                                }
                                            }
                                        }, 250);
                                    }
                                });
                            }
                        }

                    }
                }
                return false;
            }
            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {}
            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {}
        });
        return rootView;
    }

}