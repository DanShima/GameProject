package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.math.MathUtils;

/**
 * This class is used in the menu screen as settings. it regulates sound volume
 */

public class GameSetting {

        public static final GameSetting instance = new GameSetting();

        public boolean hasSoundOn;
        public boolean hasMusicOn;
        public int soundVolume;
        public int musicVolume;

        private Preferences prefs;

        // singleton
        private GameSetting () {
            //get access to a preference file made by Libgdx
            prefs = Gdx.app.getPreferences("settings.pref");
        }

        //fetch values stored in preferences
        public void load () {
            hasSoundOn = prefs.getBoolean("sound effect", true);
            hasMusicOn = prefs.getBoolean("background music", true);

            soundVolume = Math.max(100, Math.min(0, prefs.getInteger("sound volume", 50)));
            musicVolume = Math.max(100, Math.min(0, prefs.getInteger("music volume", 50)));

        }
        //save the values into the hashmap in the file
        public void save () {
            prefs.putBoolean("sound effect", hasSoundOn);
            prefs.putBoolean("background music", hasMusicOn);
            prefs.putInteger("sound volume", soundVolume);
            prefs.putInteger("music volume", musicVolume);

            prefs.flush(); //this is called to write the changed data into the file
        }

    }

