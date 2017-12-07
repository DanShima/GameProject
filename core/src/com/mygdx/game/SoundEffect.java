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

    public static final SoundEffect newSoundEffect = new SoundEffect();
    private AssetManager manager;
    public GameSound sounds;
    public GameMusic backgroundMusic;

    private SoundEffect(){} //singleton pattern. cannot be used to create objects in other classes

    /**
     * An inner class containing sound effects
     */
    public class GameSound {
        public final Sound collect; //played when the player picks up an item
        public final Sound loseLife;
        public final Sound monsterGroan;
        public final Sound gainHP; //gain some extra health points after picking up a health potion.

        public GameSound(AssetManager manager) {
            collect = manager.get("cloth-inventory.wav", Sound.class);
            loseLife = manager.get("life_lost.wav", Sound.class);
            monsterGroan = manager.get("monstergroan.wav", Sound.class);
            gainHP = manager.get("bottle.wav", Sound.class);
        }
    }

    /**
     * An inner class containing background music
     */
    public class GameMusic{
            public final Music musicDesertMap;
            public final Music musicSnowMap;
            public final Music musicStartMenu;
            public GameMusic(AssetManager manager){
                musicDesertMap = manager.get("desert.mp3", Music.class);
                musicSnowMap = manager.get("snowsong.mp3", Music.class);
                musicStartMenu = manager.get("start_menu_music.mp3", Music.class);
            }

        }


        public void create(AssetManager manager){
            this.manager = manager;
            manager.setErrorListener(this);

            manager.load("cloth-inventory.wav", Sound.class);
            manager.load("life_lost.wav", Sound.class);
            manager.load("monstergroan.wav", Sound.class);
            manager.load("bottle.wav", Sound.class);

            manager.load("desert.mp3", Music.class);
            manager.load("snowsong.mp3", Music.class);
            manager.load("start_menu_music.mp3", Music.class);
            manager.finishLoading();
            sounds = new GameSound(manager);
            backgroundMusic = new GameMusic(manager);
        }

    public void setVolume(float volume){}
    public void play(){}
    public void pause(){}
    public void stop(){}
    @Override
    public void dispose () {
        manager.dispose(); //free allocated memory by getting rid of the sound manager after it's no longer used
    }

    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        Gdx.app.error("AUDIO", "Couldn't load audio file '" + asset.fileName + "'", throwable);
    }
}
