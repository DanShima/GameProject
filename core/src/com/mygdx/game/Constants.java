package com.mygdx.game;

import com.badlogic.gdx.Gdx;

/**
 * This class holds game (meta) info that is the same throughout the game.
 */

public class Constants {
    //tiledMap info
    public static final  int tileSize = 128; //tile in pixel
    public static int tileCountW = 15; //numbers of tiles in width
    public static int tileCountH = 8; //numbers of tiles in height
    public static  final int  mapWidth = tileSize * tileCountW;
    public static  final int  mapHeight = tileSize * tileCountH;

    // number of points at the start of the game
    public static final int SCORE_START = 0;
    // number of lives at the start of the game
    public static final int LIVES = 4;

    // location of the sprite sheet for the player without clothes
    public static final String GIRL_NAKED = "pinkGirl_v02.png";
    //locations of the sprite sheets for items
    public static final String UNDERWEAR = "underwear.png";
    public static final String TSHIRT = "tshirt.png";
    public static final String SOCKS = "socks.png";
    public static final String PANTS = "pants.png";
    public static final String APPLE = "apple1.png";
    //locations of the sprite sheets for monsters
    public static final String GAZETI = "gazeti.png";
    public static final String HYDRA = "hydra.png";
    public static final String MUMMY = "mummy.png";
    public static final String GOLEM = "golem.png";
    public static final String ORC = "orcwarrior.png";
    public static final String WASP = "wasp.png";
    public static final String WINDGHOST = "windghost.png";
    public static final String MUSHROOM = "mossmushroom.png";
    //UI skin for buttons and bars
    public static final String skin = new String("skin/freezing-ui.json");
    //to Display menu
    public static final int screenWidth=Gdx.graphics.getWidth();
    public static final int mScreenHeight=Gdx.graphics.getHeight();
    public static final int centerX=screenWidth/2;
    public static final int centerY=mScreenHeight/2;
    public static final int colWidth=screenWidth/8;
    public static final int rowHeight=mScreenHeight/8;

    //time between frames in seconds
    public static final float FPS = 0.3f;

    // location of tilemap file for map level 1 desert
    public static final String LEVEL_ONE = "blocking_v1.tmx";
    // location of tilemap file for map level 2 snow
    public static final String LEVEL_TWO = "snow128_16x10_v02.tmx";
    //an array of all the LEVELS
    public static final String[] LEVELS = {"blocking_v1.tmx", "snow128_16x10_v02.tmx"};
    //current level map index from the array
    public static int currentLevel = 0;
}
