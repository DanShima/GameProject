package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Giddy on 22/11/2017.
 */

public class Player {
    private Animation moving; //animation object that animate the player
    private Texture movingSpriteSheet; //sprite sheet for the player
    private TextureRegion currentFrame; //current animation frame
    private float stateTime; //keep track of the time when the player moves
    //number of images(frames) that we use for the animation: spritebase_hero.png
    private int numberOfFrames = 14;
    private float timeBetweenFrames = 0.14f; //the time gap between two frames of animation in seconds

    /**
     * This method initialize the fields
     * @param width
     * @param height
     * @param movingSpriteSheet
     */

    public void start(float width, float height, Texture movingSpriteSheet){
        this.movingSpriteSheet = movingSpriteSheet;
        //split the sheet into separate frames
        TextureRegion[][] tmp = TextureRegion.split(movingSpriteSheet,movingSpriteSheet.getWidth()/numberOfFrames,
                movingSpriteSheet.getHeight());
        //convert 2D array to normal array
        TextureRegion[] movingFrames = tmp[0];
        //create a new animation sequence with the walk frames and time period of specified seconds
        moving = new Animation(timeBetweenFrames, movingFrames);
    }
}
