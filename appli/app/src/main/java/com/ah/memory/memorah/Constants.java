package com.ah.memory.memorah;

import java.util.concurrent.ThreadLocalRandom;

public final class Constants {
    public static final String PREF_NAME = "HighScore";
    public static final String EASY_HIGH_KEY = "easy_high";
    public static final String MEDIUM_HIGH_KEY = "medium_high";
    public static final String HARD_HIGH_KEY = "hard_high";
    public static final int LEVEL_EASY = 0;
    public static final int LEVEL_MEDIUM = 1;
    public static final int LEVEL_HARD = 2;
    public static final int HARD_NO_OF_CARDS = 16;
    public static final int MEDIUM_NO_OF_CARDS = 12;
    public static final int EASY_NO_OF_CARDS = 8;
    public static final long EASY_TIME = 22000;
    public static final long MEDIUM_TIME = 27000;
    public static final long HARD_TIME = 32000;
    public static final int TIMER_INTERVAL = 1000;
    public static final String PREFS = "user_prefs";
    public static final String PREFS_SOUNDS = "user_prefs_sounds";
    public static final String PREFS_MUSIC = "user_prefs_music";
    public static final int CARDS[] = {
            R.drawable.card1,
            R.drawable.card2,
            R.drawable.card3,
            R.drawable.card4,
            R.drawable.card5,
            R.drawable.card6,
            R.drawable.card7,
            R.drawable.card8,
            R.drawable.card1,
            R.drawable.card2,
            R.drawable.card3,
            R.drawable.card4,
            R.drawable.card5,
            R.drawable.card6,
            R.drawable.card7,
            R.drawable.card8,
            R.drawable.card1,
            R.drawable.card2,
            R.drawable.card3,
            R.drawable.card4,
            R.drawable.card5,
            R.drawable.card6,
            R.drawable.card7,
            R.drawable.card8,
            R.drawable.card1,
            R.drawable.card2,
            R.drawable.card3,
            R.drawable.card4,
            R.drawable.card5,
            R.drawable.card6,
            R.drawable.card7,
            R.drawable.card8,
            R.drawable.card1,
            R.drawable.card2,
            R.drawable.card3,
            R.drawable.card4,
            R.drawable.card5,
            R.drawable.card6,
            R.drawable.card7,
            R.drawable.card8,
            R.drawable.card1,
            R.drawable.card2,
            R.drawable.card3,
            R.drawable.card4,
            R.drawable.card5,
            R.drawable.card6,
            R.drawable.card7,
            R.drawable.card8,
            R.drawable.card1,
            R.drawable.card2,
            R.drawable.card3,
            R.drawable.card4,
            R.drawable.card5,
            R.drawable.card6,
            R.drawable.card7,
            R.drawable.card8,
            R.drawable.card1,
            R.drawable.card2,
            R.drawable.card3,
            R.drawable.card4,
            R.drawable.card5,
            R.drawable.card6,
            R.drawable.card7,
            R.drawable.card8,
            R.drawable.card1,
            R.drawable.card2,
            R.drawable.card3,
            R.drawable.card4,
            R.drawable.card5,
            R.drawable.card6,
            R.drawable.card7,
            R.drawable.card8,
            R.drawable.card1,
            R.drawable.card2,
            R.drawable.card3,
            R.drawable.card4,
            R.drawable.card5,
            R.drawable.card6,
            R.drawable.card7,
            R.drawable.card8,
            R.drawable.card1,
            R.drawable.card2,
            R.drawable.card3,
            R.drawable.card4,
            R.drawable.card5,
            R.drawable.card6,
            R.drawable.card7,
            R.drawable.card8,
            R.drawable.card1,
            R.drawable.card2,
            R.drawable.card3,
            R.drawable.card4
    };

    public static int[] pickUpRandomCards(int number){
        int[] temp = new int[number];
        int[] res = new int[number*2];


        for(int i = 0; i < number; i++){
            temp[i]=CARDS[ThreadLocalRandom.current().nextInt(0, 100 + 1)];
        }

        for(int i= 0; i < number; i++){
            res[i] = temp[i];
            res[i+number] = temp[i];
        }

        return res;
    }
}
