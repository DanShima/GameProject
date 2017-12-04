package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * This class holds game (meta) info that is the same throughout the game.
 */

public class Constants {
    // Visible game world TODO Change this
    public static final String skin = new String ( "skin/freezing-ui.json" );
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
    public static final String LEVEL_ONE = "prototype_map_128_15x8_v04.tmx";
    // location of tilemap file for map level 2 snow
    public static final String LEVEL_TWO = "snow128_16x10_v02.tmx";
    // location of the sprite sheet for the player without clothes
    public static final String GIRL_NAKED = "pinkGirl_v02.png";
    // items
    public static final String UNDERWEAR = "underwear.png";
    public static final String TSHIRT = "tshirt.png";
    public static final String SOCKS = "socks.png";
    //Monster
    public static final String MONSTER1 = "gazeti_3.png";

}
