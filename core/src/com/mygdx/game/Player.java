package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ObjectMap;

import static com.mygdx.game.Constants.FPS;
import static com.mygdx.game.Constants.GIRL_NAKED;
import static com.mygdx.game.Constants.UNDERWEAR;

/**
 * Created by Giddy on 23/11/2017.
 */

public class Player implements ApplicationListener {


    // Constant rows and columns of the sprite sheet
    private static final int FRAME_COLS = 3, FRAME_ROWS = 5;

    private Texture walkSheet; //sprite sheet for the player
    private SpriteBatch spriteBatch; //to draw on screen
    // A variable for tracking elapsed time for the animation (when the player moves)
    private float stateTime;
    private float timeTillIdle = 0;
   // private float fps = 0.3f; //time between frames in seconds

    //movement animation arrays
    private Animation<TextureRegion> currentAnimation;
    private Animation<TextureRegion> idleAnimation;
    private Animation<TextureRegion> walkAnimationDOWN;
    private Animation<TextureRegion> walkAnimationUP;
    private Animation<TextureRegion> walkAnimationLEFT;
    private Animation<TextureRegion> walkAnimationRIGHT;
   
    private TextureRegion[] idleFrames;
    private TextureRegion[] walkFramesDOWN;
    private TextureRegion[] walkFramesUP;
    private TextureRegion[] walkFramesLEFT;
    private TextureRegion[] walkFramesRIGHT;
    private TextureRegion currentFrame;

    private float x = 0; //current x position
    private float y = 0; //current y position
    private float oldX = 0; //old X position
    private float oldY = 0; //old Y position

    boolean updateAnimationStateTime =false;

    //underwear sprite sheet
    private Texture underwearSheet;
    private Texture socksSheet;
    private Texture tshirtSheet;
    private Texture pantsSheet;

    private Item item;
    TiledTest tiledTest;

    //movement animation arrays
    private Animation<TextureRegion> currentAnimationUnderwear;
    private Animation<TextureRegion> idleAnimationUnderwear;
    private Animation<TextureRegion> walkAnimationDOWNUnderwear;
    private Animation<TextureRegion> walkAnimationUPUnderwear;
    private Animation<TextureRegion> walkAnimationLEFTUnderwear;
    private Animation<TextureRegion> walkAnimationRIGHTUnderwear;
    private TextureRegion currentFrameUnderwear;


    public enum DIRECTION{
        UP, DOWN, LEFT, RIGHT, IDLE
    }

    public enum PLAYERSTATE{
        UNDERWEAR, PANTS, SHIRT, SOCKS
    }

    public DIRECTION direction;
    public PLAYERSTATE playerstate;

    private AnimationUtil animationUtil;

    @Override
    public void create() {
        // Load the sprite sheet as a Texture
        walkSheet = new Texture(Gdx.files.internal(GIRL_NAKED));
        underwearSheet = new Texture(Gdx.files.internal("pinkGirl_v02_underwear.png"));
        socksSheet = new Texture(Gdx.files.internal("pinkGirl_v02_socks.png"));
        tshirtSheet = new Texture(Gdx.files.internal("pinkGirl_v02_shirt.png"));
        pantsSheet = new Texture(Gdx.files.internal("pinkGirl_v02_pants.png"));

        animationUtil = new AnimationUtil();
        //animation for the girl without clothes
        currentAnimation = animationUtil.makeAnimation(walkSheet, FRAME_COLS, FRAME_ROWS, 4, new int[]{0,2} );
        idleAnimation = animationUtil.makeAnimation(walkSheet, FRAME_COLS, FRAME_ROWS, 4, new int[]{0,2} );
        walkAnimationDOWN = animationUtil.makeAnimation(walkSheet, FRAME_COLS, FRAME_ROWS, 0 );
        walkAnimationUP = animationUtil.makeAnimation(walkSheet, FRAME_COLS, FRAME_ROWS, 3 );
        walkAnimationLEFT = animationUtil.makeAnimation(walkSheet, FRAME_COLS, FRAME_ROWS, 1 );
        walkAnimationRIGHT = animationUtil.makeAnimation(walkSheet, FRAME_COLS, FRAME_ROWS, 2 );
        //animation for the girl with underwear
        currentAnimationUnderwear = animationUtil.makeAnimation(underwearSheet, 3, 5, 4, new int[]{0,2} );
        idleAnimationUnderwear = animationUtil.makeAnimation(underwearSheet, 3, 5, 4, new int[]{0,2} );
        walkAnimationDOWNUnderwear = animationUtil.makeAnimation(underwearSheet, 3, 5, 0 );
        walkAnimationUPUnderwear = animationUtil.makeAnimation(underwearSheet, 3, 5, 3 );
        walkAnimationLEFTUnderwear = animationUtil.makeAnimation(underwearSheet, 3, 5, 1 );
        walkAnimationRIGHTUnderwear = animationUtil.makeAnimation(underwearSheet, 3, 5, 2 );


        // Instantiate a SpriteBatch for drawing and reset the elapsed animation time to 0
        spriteBatch = new SpriteBatch();
        stateTime = 0f;
        item = new Item(UNDERWEAR, 256,256);

    }

    public void setCurrentAnimation(Animation<TextureRegion> currentAnimation) {
        this.currentAnimation = currentAnimation;
    }

    public Animation<TextureRegion> getCurrentAnimation() {
        return currentAnimation;
    }

    public void resetTimeTillIdle() {
        timeTillIdle = 0;
    }

    public TextureRegion getCurrentFrameUnderwear() {
        return currentFrameUnderwear;
    }

    @Override
    public void render() {

        stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time
        timeTillIdle += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time
        // Get current frame of animation for the current stateTime:
        // this method takes an elapsed time parameter and returns the appropriate image for that time. it loops through a series of images and do it again
        currentFrame = getCurrentAnimation().getKeyFrame(stateTime, true);
        currentFrameUnderwear = getCurrentAnimationUnderwear().getKeyFrame(stateTime, true);
        //go back to idle state after 2 sec
        if(stateTime > 2){
            stateTime = 0;
            setCurrentAnimation(getIdleAnimation());
            setCurrentAnimationUnderwear(getIdleAnimationUnderwear());
        }
        spriteBatch.begin();
        spriteBatch.draw(currentFrame, getX(), getY()); //naked girl rendering
        spriteBatch.end();
    }

    public float getPlainX() { return x; }

    public float getX() { return x - (float)(TiledTest.tileSize*0.25); } //place the animation in the center of the tile

    public float getY() {return y;}

    /**
     * Update girl with clothes on
     * @param item
     */
    public void updateSpriteBatch(Item item){
        stateTime += Gdx.graphics.getDeltaTime();
        currentFrameUnderwear = getCurrentAnimationUnderwear().getKeyFrame(stateTime, true);

        spriteBatch.begin();
        //spriteBatch.flush();
        if(item.isCollected() == true){
            // spriteBatch.draw(currentFrame, getX(), getY());
            spriteBatch.draw(currentFrameUnderwear, getX(), getY());}
        spriteBatch.end();
    }

    /**
     * update girl with clothes on
     * @param textureRegion
     */
    public void updateSpriteBatch(TextureRegion textureRegion){
        //stateTime += Gdx.graphics.getDeltaTime();
        //currentFrameUnderwear = getCurrentAnimationUnderwear().getKeyFrame(stateTime, true);

        spriteBatch.begin();
         // spriteBatch.draw(currentFrame, getX(), getY());
            spriteBatch.draw(textureRegion, getX(), getY());
        spriteBatch.end();
    }


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
        underwearSheet.dispose();
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

    public Animation<TextureRegion> getIdleAnimation() {
        return idleAnimation;
    }
    public Animation<TextureRegion> getCurrentAnimationUnderwear() {
        return currentAnimationUnderwear;
    }

    public Animation<TextureRegion> getIdleAnimationUnderwear() {
        return idleAnimationUnderwear;
    }

    public Animation<TextureRegion> getWalkAnimationDOWNUnderwear() {
        return walkAnimationDOWNUnderwear;
    }

    public Animation<TextureRegion> getWalkAnimationUPUnderwear() {
        return walkAnimationUPUnderwear;
    }

    public Animation<TextureRegion> getWalkAnimationLEFTUnderwear() {
        return walkAnimationLEFTUnderwear;
    }

    public Animation<TextureRegion> getWalkAnimationRIGHTUnderwear() {
        return walkAnimationRIGHTUnderwear;
    }
    public void setCurrentAnimationUnderwear(Animation<TextureRegion> currentAnimationUnderwear) {
        this.currentAnimationUnderwear = currentAnimationUnderwear;
    }






}
