package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static com.mygdx.game.Constants.GIRL_NAKED;

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
    private float fps = 0.3f; //time between frames in seconds

    //movement animation arrays
    private Animation<TextureRegion> walkAnimation;
    private Animation<TextureRegion> walkAnimationDOWN;
    private Animation<TextureRegion> walkAnimationUP;
    private Animation<TextureRegion> walkAnimationLEFT;
    private Animation<TextureRegion> walkAnimationRIGHT;
   //arrays for holding the frames
    private TextureRegion[] walkFramesDOWN;
    private TextureRegion[] walkFramesUP;
    private TextureRegion[] walkFramesLEFT;
    private TextureRegion[] walkFramesRIGHT;
    private TextureRegion currentFrame;

    private float x; //current x position
    private float y; //current y position
    private float oldX = 0; //old X position
    private float oldY = 0; //old Y position

    boolean updateAnimationStateTime =false; // keep track of when to update Bob state time

    @Override
    public void create() {
        // Load the sprite sheet as a Texture
        walkSheet = new Texture(Gdx.files.internal(GIRL_NAKED));
        // Create a 2D array of TextureRegions by splitting the sheet into separate frames
        TextureRegion[][] tmp = TextureRegion.split(walkSheet,
                walkSheet.getWidth() / FRAME_COLS,
                walkSheet.getHeight() / FRAME_ROWS);
        //convert 2D array to normal array
        TextureRegion[] walkFrames = new TextureRegion[FRAME_COLS];
        for (int i = 0; i < FRAME_COLS; i++) {
          walkFrames[i] = tmp[0][i];}
        //TODO put the movements in a switch statement.
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
        }

        walkAnimation = new Animation<TextureRegion>(fps, walkFrames);
        walkAnimationDOWN = new Animation<TextureRegion>(fps, walkFramesDOWN);
        walkAnimationUP = new Animation<TextureRegion>(fps, walkFramesUP);
        walkAnimationLEFT = new Animation<TextureRegion>(fps, walkFramesLEFT);
        walkAnimationRIGHT = new Animation<TextureRegion>(fps, walkFramesRIGHT);
        // Instantiate a SpriteBatch for drawing and reset the elapsed animation time to 0
        spriteBatch = new SpriteBatch();
        stateTime = 0f;

    }

    public void setWalkAnimation(Animation<TextureRegion> walkAnimation) {
        this.walkAnimation = walkAnimation;
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
        currentFrame = getWalkAnimation().getKeyFrame(stateTime, true);

        spriteBatch.begin();
        spriteBatch.draw(currentFrame, getX(), getY()); // Draw current frame at (0, 0)
        spriteBatch.end();
    }

    public float getX() { return x - (float)(TiledTest.tileSize*0.25); } //place the animation in the center of the tile

    public float getY() {return y;}

    public void move(float stepX, float stepY){
        x = stepX + oldX;
        y = stepY + oldY;
        oldX = x;
        oldY = y;
    }

    public float getOldX() {return oldX;}
    public float getOldY() {return oldY;}

    @Override
    public void dispose() {
        spriteBatch.dispose();
        walkSheet.dispose();
    }
    @Override
    public void resume(){updateAnimationStateTime = true;}
    @Override
    public void pause(){}
    @Override
    public void resize(int x, int y){}

    public Animation<TextureRegion> getWalkAnimationDOWN() {
        return walkAnimationDOWN;
    }

    public Animation<TextureRegion> getWalkAnimationUP() {
        return walkAnimationUP;
    }

    public Animation<TextureRegion> getWalkAnimationLEFT() {
        return walkAnimationLEFT;
    }

    public Animation<TextureRegion> getWalkAnimationRIGHT() {
        return walkAnimationRIGHT;
    }

    public TextureRegion[] getWalkFramesDOWN() {
        return walkFramesDOWN;
    }

    public TextureRegion[] getWalkFramesUP() {
        return walkFramesUP;
    }

    public TextureRegion[] getWalkFramesLEFT() {
        return walkFramesLEFT;
    }

    public TextureRegion[] getWalkFramesRIGHT() {
        return walkFramesRIGHT;
    }
}
