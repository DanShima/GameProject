package com.mygdx.game;

import com.badlogic.gdx.Gdx;

/**
 * This class holds game (meta) info that is the same throughout the game.
 */

public class Constants {
    //tiledMap info
    public static final  int TILE_SIZE = 128; //tile in pixel
    public static int TILE_COUNT_WIDTH = 15; //numbers of tiles in width
    public static int TILE_COUNT_HEIGHT = 8; //numbers of tiles in height
    public static  final int MAP_WIDTH = TILE_SIZE * TILE_COUNT_WIDTH;
    public static  final int MAP_HEIGHT = TILE_SIZE * TILE_COUNT_HEIGHT;

    //number of points at the start of the game
    public static  int SCORE_START = 1000;

    //location of the sprite sheet for the player without clothes
    public static final String GIRL_NAKED = "pinkGirl_v02.png";
    //locations of the sprite sheets for itemsLevelZero
    public static final String UNDERWEAR = "underwear.png";
    public static final String TSHIRT = "tshirt.png";
    public static final String SOCKS = "socks.png";
    public static final String PANTS = "pants.png";
    public static final String APPLE = "apple1.png";
    //locations of the sprite sheets for monsters
    public static final String GAZETI = "gazeti.png";
    public static final String GOLEM = "golem.png";
    public static final String WASP = "wasp.png";
    public static final String MUSHROOM = "mossmushroom.png";
    public static final String PHREONI = "Phreoni.png";
    //UI SKIN for buttons and bars
    public static final String SKIN = new String("skin/freezing-ui.json");
    //to Display menu
    public static final int SCREEN_WIDTH =Gdx.graphics.getWidth();
    public static final int SCREEN_HEIGHT =Gdx.graphics.getHeight();
    public static final int CENTER_X = SCREEN_WIDTH /2;
    public static final int CENTER_Y = SCREEN_HEIGHT /2;
    public static final int COL_WIDTH = SCREEN_WIDTH /8;
    public static final int ROW_HEIGHT = SCREEN_HEIGHT /8;

    //time between frames in seconds
    public static final float FPS = 0.3f;
    //an array of all the LEVELS
    public static final String[] LEVELS = {"blocking_v1.tmx", "snow128_16x10_v02.tmx"};
    //current level map index from the array
    public static int CURRENT_LEVEL = 0;
}
