package com.mygdx.game;

import com.badlogic.gdx.Gdx;

/**
 * This class holds game (meta) info that is the same throughout the game.
 */

public class Constants {
    // Visible game world TODO Change this

    public static final float VIEWPORT_WIDTH = 0f;
    public static final float VIEWPORT_HEIGHT = 0f;

    // Progress bar width and height TODO Change this
    public static final float VIEWPORT_GUI_WIDTH = 0f;
    public static final float VIEWPORT_GUI_HEIGHT = 0f;

    // number of points at the start of the game
    public static final int SCORE_START = 0;
    // number of lives at the start of the game
    public static final int LIVES = 4;
    // location of tilemap file for map level 1 desert
    public static final String LEVEL_ONE = "blocking_v1.tmx";
    // location of tilemap file for map level 2 snow
    public static final String LEVEL_TWO = "snow128_16x10_v02.tmx";
    // location of the sprite sheet for the player without clothes
    public static final String GIRL_NAKED = "pinkGirl_v02.png";
    // items
    public static final String UNDERWEAR = "underwear.png";
    public static final String TSHIRT = "tshirt.png";
    public static final String SOCKS = "socks.png";
    //Monster Gazeti
    public static final String MONSTER1 = "gazeti_3.png";
    public static final String MONSTER2 = "hydra_3.png";
    //Skin
    public static final String skin=new String("skin/freezing-ui.json");
    //To Display menu
    public static final int screenWidth=Gdx.graphics.getWidth();
    public static final int screenHeight=Gdx.graphics.getHeight();
    public static final int centerX=screenWidth/2;
    public static final int centerY=screenHeight/2;
    public static final int colWidth=screenWidth/8;
    public static final int rowHeight=screenHeight/8;


    public static final float FPS = 0.3f; //time between frames in seconds
    //an array of all the levels
    public static final String[] levels = {"blocking_v1.tmx", "snow128_16x10_v02.tmx"};
}
