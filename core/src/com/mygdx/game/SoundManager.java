package com.mygdx.game;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 * This class regulates and controls the play modes of sound effects and music
 */
public class SoundManager {
        public static final SoundManager newSoundManager = new SoundManager();
        private Music playAudio;

        private SoundManager() { }

        public void play (Sound sound) {
            play(sound, 100);
        }

        public void play (Sound sound, int volume) {
            //TODO create menu with checkboxes for sound and music options in a new class
            if (!GameSetting.newSetting.hasSoundOn) return; //if the user doesnt want sounds on in the menu option, no sounds will be played
            sound.play(GameSetting.newSetting.soundVolume * volume);
        }
    public void play (Music music) {
        stopMusic();
        playAudio = music;
        if (GameSetting.newSetting.hasMusicOn) {
            music.setLooping(true); //set the song to loop
            music.setVolume(GameSetting.newSetting.musicVolume);
            music.play();
        } }


    public void stopMusic () {
            if (playAudio != null)
                playAudio.stop();
    }

    //notify when the user has changed something in settings
    public void onSettingsUpdated () {
        if (playAudio == null) return;
        playAudio.setVolume(GameSetting.newSetting.musicVolume);
        if (GameSetting.newSetting.hasMusicOn) {
            if (!playAudio.isPlaying())
                playAudio.play();
        } else {
            playAudio.pause();
        }
    }

    
}
