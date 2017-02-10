package com.gtu.edu.tr.mustafa.akilli.score;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class HighScoreHandler {

    public static Preferences pref;

    /**
     * Daha once high score var mi kontrol eder
     * High score varsa load eder
     */
    public static void load(){
        pref = Gdx.app.getPreferences("Oyun");

        //HighScore yoksa HighScore diye bir alan olustur ve 0 ata
        if(!pref.contains("HighScore")){
            pref.putLong("HighScore",0);
        }

    }

    public static void setHighScore(long highScore){
        pref.putLong("HighScore",highScore);
        pref.flush();//kaydetmek gibi
    }

    public static long getHighScore(){
        return pref.getLong("HighScore");
    }


}

