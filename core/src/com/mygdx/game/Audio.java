package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by danshima on 2017-11-30.
 */

public class Audio {
    public GameSound sounds;
    public GameSound.GameMusic backgroundMusic;
    public class GameSound
    {
        public final Sound collect;
        public final Sound loseLife;
        public final Sound monsterGroan;
        public final Sound gainHP; //gain some extra health points after picking up a health potion.
        public GameSound(TiledTest tt){
            collect = tt.get("cloth-inventory.wav", Sound.class);
            loseLife = tt.get("wscream_2.wav", Sound.class);
            monsterGroan = tt.get("mnstr5.wav", Sound.class);
            gainHP = tt.get("bottle.wav", Sound.class);
        }

        public class GameMusic{
            public final Music backgroundMusic1;
            public GameMusic(TiledTest tt){
                backgroundMusic1 = tt.get("backgroundmusic.mp3", Sound.class);
            }

        }
    }


    Audio music = Gdx.audio.newMusic(Gdx.files.internal("backgroundmusic.mp3"));

    public void setVolume(float volume){}
    public void play(){}
    public void pause(){}
    public void stop(){}
    @Override
    public void dispose() {
        music.dispose(); //free allocated memory

    }
}
