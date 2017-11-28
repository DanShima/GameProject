package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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
    public static final int SCORE_POINTS = 20;
    // number of lives at the start of the game
    public static final int LIVES_START = 3;
    // location of tilemap file for map level 1
    public static final String LEVEL_ONE = "prototype_map_128_15x8_v04.tmx";
    // location of tilemap file for map level 2
    public static final String LEVEL_TWO = "prototype_map_128_15x8_v04.tmx";
    // location of the sprite sheet for the player without clothes
    public static final String GIRL_NAKED = "pinkGirl_v01a.png";
}
