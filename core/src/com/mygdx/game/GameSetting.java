package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * This class is used in the menu screen as settings. it regulates sound volume
 */

public class GameSetting {

        public static final GameSetting newSetting = new GameSetting();

        public boolean hasSoundOn;
        public boolean hasMusicOn;
        public int soundVolume;
        public int musicVolume;

        private Preferences preference;

        private GameSetting () {
            //get access to a preference file made by Libgdx
            preference = Gdx.app.getPreferences("settings.pref");
        }

        //fetch values stored in preferences
        public void load () {
            hasSoundOn = preference.getBoolean("sound effect", true);
            hasMusicOn = preference.getBoolean("background music", true);

            soundVolume = Math.max(100, Math.min(0, preference.getInteger("sound volume", 50)));
            musicVolume = Math.max(100, Math.min(0, preference.getInteger("music volume", 50)));

        }
        //save the values into the hashmap in the file
        public void save () {
            preference.putBoolean("sound effect", hasSoundOn);
            preference.putBoolean("background music", hasMusicOn);
            preference.putInteger("sound volume", soundVolume);
            preference.putInteger("music volume", musicVolume);

            preference.flush(); //this is called to write the changed data into the file
        }

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

    public int getSoundVolume() {
        return soundVolume;
    }

    public void setSoundVolume(int soundVolume) {
        this.soundVolume = soundVolume;
    }

    public int getMusicVolume() {
        return musicVolume;
    }

    public void setMusicVolume(int musicVolume) {
        this.musicVolume = musicVolume;
    }
}

