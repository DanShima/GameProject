package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Giddy on 23/11/2017.
 */

public class Animator implements ApplicationListener {

    // Constant rows and columns of the sprite sheet
    private static final int FRAME_COLS = 3, FRAME_ROWS = 5;

    private Texture walkSheet; //sprite sheet for the player
    private SpriteBatch spriteBatch; //to draw on screen
    // A variable for tracking elapsed time for the animation (when the player moves)
    private float stateTime;

    //movement arrays
    private Animation<TextureRegion> walkAnimation;
   // private TextureRegion[] walkFrames;


    private TextureRegion currentFrame;

    private float x; //current x position
    private float y; //current y position
    private float oldX = 0; //old X position
    private float oldY = 0; //old Y position

    boolean updateAnimationStateTime =false; // keep track of when to update Bob state time

    @Override
    public void create() {
        // Load the sprite sheet as a Texture
        walkSheet = new Texture(Gdx.files.internal("spritebase_hero.png"));

        // Create a 2D array of TextureRegions by splitting the sheet into separate frames
        TextureRegion[][] tmp = TextureRegion.split(walkSheet,
                walkSheet.getWidth() / FRAME_COLS,
                walkSheet.getHeight() / FRAME_ROWS);
        //convert 2D array to normal array
        TextureRegion[] walkFrames = new TextureRegion[FRAME_COLS];
        for (int i = 0; i < FRAME_COLS; i++) {
            walkFrames[i] = tmp[0][i];}


        /*//TODO put the movements in a switch statement.
        walkFramesDOWN = new TextureRegion[FRAME_COLS];
        walkFramesUP = new TextureRegion[FRAME_COLS];
        walkFramesLEFT = new TextureRegion[FRAME_COLS];
        walkFramesRIGHT = new TextureRegion[FRAME_COLS];
        //We only want to animate the three images in the first row
        //GO DOWN & IDLE
        for (int i = 0; i < FRAME_COLS; i++) {
            walkFramesDOWN[i] = tmp[0][i];
        }
        //GO LEFT
        for (int i = 0; i < FRAME_COLS; i++) {
            walkFramesLEFT[i] = tmp[1][i];
        }
        //GO RIGHT
        for (int i = 0; i < FRAME_COLS; i++) {
            walkFramesRIGHT[i] = tmp[2][i];
        }
        //GO UP
        for (int i = 0; i < FRAME_COLS; i++) {
            walkFramesUP[i] = tmp[3][i];
        }*/

        walkAnimation = new Animation<TextureRegion>(0.5f, walkFrames);

        //walkAnimationDOWN = new Animation<TextureRegion>(0.5f, walkFrames);
        //walkAnimationUP = new Animation<TextureRegion>(0.5f, walkFrames);
        //walkAnimationLEFT = new Animation<TextureRegion>(0.5f, walkFrames);
        //walkAnimationRIGHT = new Animation<TextureRegion>(0.5f, walkFrames);

        // Instantiate a SpriteBatch for drawing and reset the elapsed animation time to 0
        spriteBatch = new SpriteBatch();
        stateTime = 0f;

    }
    // Initialize the Animation with the frame interval and array of frames
    public void initializeAnimation(TextureRegion[] walkFrames){}

    public float getStateTime() {
        return stateTime;
    }

    public TextureRegion getCurrentFrame() {
        return currentFrame;
    }


    public void setCurrentFrame(TextureRegion currentFrame) {
        this.currentFrame = currentFrame;
    }

    public Animation<TextureRegion> getWalkAnimation() {
        return walkAnimation;
    }

    @Override
    public void render() {

        stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time
        // Get current frame of animation for the current stateTime:
        // this method takes an elapsed time parameter and returns the appropriate image for that time. it loops through a series of images and do it again
        //TODO put the movements in a separate method
        currentFrame = walkAnimation.getKeyFrame(stateTime, true);

        spriteBatch.begin();
        spriteBatch.draw(currentFrame, getX(), getY()); // Draw current frame at (0, 0)
        spriteBatch.end();
    }



    public float getX() { return x - (float)(TiledTest.tileSize*0.25); }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void move(float stepX, float stepY){
        x = stepX + oldX;
        y = stepY + oldY;
        oldX = x;
        oldY = y;
    }

    public float getOldX() {
        return oldX;
    }
    public float getOldY() {
        return oldY;
    }
    public void draw(float x, float y){}

    @Override
    public void dispose() {
        spriteBatch.dispose();
        walkSheet.dispose();
    }
    @Override
    public void resume(){
        updateAnimationStateTime = true;
    }
    @Override
    public void pause(){}
    @Override
    public void resize(int x, int y){}
}
