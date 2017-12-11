package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.math.MathUtils;

/**
 * This class is used in the menu screen as settings. it regulates sound volume
 */

public class GameSetting {
        //the class creates its own object
        public static final GameSetting newSetting = new GameSetting();

        private boolean hasSoundOn;
        private boolean hasMusicOn;
        private float soundVolume;
        private float musicVolume;

        private Preferences preference;

        private GameSetting () {
            //get access to a preference file made by Libgdx
            preference = Gdx.app.getPreferences("settingss.pref");
        }

    /**
     *  fetch values stored in the preferences files
     *  define limits for the audio volumes between 0 (minimum volume) and 1 (maximum volume)
     *  if no value is found, the default of 0.5 is returned
     */
     public void load () {
            hasSoundOn = preference.getBoolean("sound effect", true);
            hasMusicOn = preference.getBoolean("background music", true);
            soundVolume = MathUtils.clamp(preference.getFloat("sound volume", 0.5f), 0.0f, 1.0f);
            musicVolume = MathUtils.clamp(preference.getFloat("music volume", 0.5f), 0.0f, 1.0f);
     }

    /**
     *  save the values of audio volume and status into the hashmap in the file
     */
     public void save () {
            preference.putBoolean("sound effect", hasSoundOn);
            preference.putBoolean("background music", hasMusicOn);
            preference.putFloat("sound volume", soundVolume);
            preference.putFloat("music volume", musicVolume);
            preference.flush(); //this is called to write the changed data into the file
     }

    //getters and setters
    public boolean isHasSoundOn() {
        return hasSoundOn;
    }
    public void setHasSoundOn(boolean hasSoundOn) {
        this.hasSoundOn = hasSoundOn;
    }
    public boolean isHasMusicOn() {
        return hasMusicOn;
    }
    public void setHasMusicOn(boolean hasMusicOn) {
        this.hasMusicOn = hasMusicOn;
    }
    public float getSoundVolume() {
        return soundVolume;
    }
    public void setSoundVolume(float soundVolume) {
        this.soundVolume = soundVolume;
    }
    public float getMusicVolume() {
        return musicVolume;
    }
    public void setMusicVolume(float musicVolume) {
        this.musicVolume = musicVolume;
    }
}

