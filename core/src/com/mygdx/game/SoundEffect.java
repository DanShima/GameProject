package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Disposable;

/**
 * This class contains audio sound and music files
 */

public class SoundEffect implements Disposable, AssetErrorListener{
    public static final SoundEffect instance = new SoundEffect();
    private AssetManager manager;
    public GameSound sounds;
    public GameMusic backgroundMusic;

    private SoundEffect(){} //singleton pattern. cannot be used to create objects in other classes
    public class GameSound {
        public final Sound collect; //played when the player picks up an item
        public final Sound loseLife;
        public final Sound monsterGroan;
        public final Sound gainHP; //gain some extra health points after picking up a health potion.

        public GameSound(AssetManager manager) {
            collect = manager.get("cloth-inventory.wav", Sound.class);
            loseLife = manager.get("wscream_2.wav", Sound.class);
            monsterGroan = manager.get("mnstr5.wav", Sound.class);
            gainHP = manager.get("bottle.wav", Sound.class);
        }
    }

    public class GameMusic{
            public final Music backgroundMusic1;
            public GameMusic(AssetManager manager){
                backgroundMusic1 = manager.get("backgroundmusic.mp3", Music.class);
            }

        }

        public void create(AssetManager manager){
            this.manager = manager;
            manager.setErrorListener(this);

            manager.load("cloth-inventory.wav", Sound.class);
            manager.load("wscream_2.wav", Sound.class);
            manager.load("mnstr5.wav", Sound.class);
            manager.load("bottle.wav", Sound.class);

            manager.load("backgroundmusic.mp3", Music.class);
            manager.finishLoading();
            sounds = new GameSound(manager);
            backgroundMusic = new GameMusic(manager);
        }



    //SoundEffect music = Gdx.audio.newMusic(Gdx.files.internal("backgroundmusic.mp3"));

    public void setVolume(float volume){}
    public void play(){}
    public void pause(){}
    public void stop(){}
    @Override
    public void dispose () {
        manager.dispose();

    }

    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        Gdx.app.error("AUDIO", "Couldn't load audio file '" + asset.fileName + "'", throwable);
    }
}
