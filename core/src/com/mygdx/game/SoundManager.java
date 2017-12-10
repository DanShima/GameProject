package com.mygdx.game;

import com.badlogic.gdx.Gdx;
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
            play(sound, 1.0f);
        }

        public void play (Sound sound, float volume) {
            if (!GameSetting.newSetting.isHasSoundOn()) return; //if the user doesnt want sounds on in the menu option, no sounds will be played
            sound.play(GameSetting.newSetting.getSoundVolume() * volume);
            Gdx.app.log("SOUND","SOUND PLAY");
        }
        public void play (Music music) {
        stopMusic();
        playAudio = music;
        if (GameSetting.newSetting.isHasMusicOn()) {
            music.setLooping(true); //set the song to loop
            music.setVolume(GameSetting.newSetting.getMusicVolume());
            music.play();
        } }


        public void stopMusic () {
            if (playAudio != null)
                playAudio.stop();
        }

        /**
         *notify when the user has changed something in settings
         */
         public void onSettingsUpdated () {
        if (playAudio == null) return;
        playAudio.setVolume(GameSetting.newSetting.getMusicVolume());
        if (GameSetting.newSetting.isHasMusicOn()) {
            if (!playAudio.isPlaying())
                playAudio.play();
        } else {
            playAudio.pause();
        }
        }

    
}
