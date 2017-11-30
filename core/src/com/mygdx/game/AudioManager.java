package com.mygdx.game;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by danshima on 2017-11-30.
 */

public class AudioManager {
        public static final AudioManager instance = new AudioManager();
        private Music playingMusic;
        // singleton: prevent instantiation from other classes
        private AudioManager () { }
        public void play (Sound sound) {
            play(sound, 1);
        }
        public void play (Sound sound, float volume) {
            play(sound, volume, 1);
        }
        public void play (Sound sound, float volume, float pitch) {
            play(sound, volume, pitch, 0);
        }
        public void play (Sound sound, float volume, float pitch,
                          float pan) {
            if (!GamePreferences.instance.sound) return;
            sound.play(GamePreferences.instance.volSound * volume,
                    pitch, pan);
        } }
    public void play (Music music) {
        stopMusic();
        playingMusic = music;
        if (GamePreferences.instance.music) {
            music.setLooping(true);
            music.setVolume(GamePreferences.instance.volMusic);
            music.play();
        } }
    public void stopMusic () {
        if (playingMusic != null) playingMusic.stop();
    }
    public void onSettingsUpdated () {
        if (playingMusic == null) return;
        playingMusic.setVolume(GamePreferences.instance.volMusic);
        if (GamePreferences.instance.music) {
            if (!playingMusic.isPlaying()) playingMusic.play();
        } else {
            playingMusic.pause();
        }
    }
}
