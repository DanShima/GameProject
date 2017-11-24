package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Giddy on 22/11/2017.
 */

public class Player {
    public Sprite character;



    private Animation moving; //animation object that animate the player
    private Texture movingSpriteSheet; //sprite sheet for the player
    private TextureRegion currentFrame; //current animation frame
    private float stateTime = 0f; //keep track of the time when the player moves
    boolean updateAnimationStateTime =false; // keep track of when to update Bob state time
    //number of images(frames) that we use for the animation: spritebase_hero.png
    private int numberOfFrames = 14;
    private float timeBetweenFrames = 0.14f; //the time gap between two frames of animation in seconds




    public void render(SpriteBatch batch) {
        character.draw(batch);
    }


    // this method sets the initial position of the player character
    public void setPosition(float x, float y){
        character.setPosition(x, y);
    }

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

        character = new Sprite();
        //set the size of the bob
        character.setSize((movingSpriteSheet.getWidth()/numberOfFrames)*width,movingSpriteSheet.getHeight()*width);
        // set the position of the bob to center
        setPosition(width/2f, height/2f);

        // set the animation to loop
        moving.setPlayMode(Animation.PlayMode.LOOP);

        // Elapsed time
        stateTime += Gdx.graphics.getDeltaTime();
        // get initial frame
       // currentFrame = moving.getKeyFrame(stateTime);
    }
}
