package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.List;

import static com.mygdx.game.Constants.GIRL_NAKED;

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

    //movement animation arrays
    private Animation<TextureRegion> currentAnimation;
    private Animation<TextureRegion> idleAnimation;
    private Animation<TextureRegion> walkAnimationDOWN;
    private Animation<TextureRegion> walkAnimationUP;
    private Animation<TextureRegion> walkAnimationLEFT;
    private Animation<TextureRegion> walkAnimationRIGHT;

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

    //movement animation arrays for socks
    private Animation<TextureRegion> currentAnimationSocks;
    private Animation<TextureRegion> idleAnimationSocks;
    private Animation<TextureRegion> walkAnimationDOWNSocks;
    private Animation<TextureRegion> walkAnimationUPSocks;
    private Animation<TextureRegion> walkAnimationLEFTSocks;
    private Animation<TextureRegion> walkAnimationRIGHTSocks;
    private TextureRegion currentFrameSocks;

    //movement animation arrays for underwear
    private Animation<TextureRegion> currentAnimationUnderwear;
    private Animation<TextureRegion> idleAnimationUnderwear;
    private Animation<TextureRegion> walkAnimationDOWNUnderwear;
    private Animation<TextureRegion> walkAnimationUPUnderwear;
    private Animation<TextureRegion> walkAnimationLEFTUnderwear;
    private Animation<TextureRegion> walkAnimationRIGHTUnderwear;
    private TextureRegion currentFrameUnderwear;

    //movement animation arrays for shirt
    private Animation<TextureRegion> currentAnimationShirt;

    private Animation<TextureRegion> idleAnimationShirt;
    private Animation<TextureRegion> walkAnimationDOWNShirt;
    private Animation<TextureRegion> walkAnimationUPShirt;
    private Animation<TextureRegion> walkAnimationLEFTShirt;
    private Animation<TextureRegion> walkAnimationRIGHTShirt;
    private TextureRegion currentFrameShirt;

    //movement animation arrays for pants
    private Animation<TextureRegion> currentAnimationPants;
    private Animation<TextureRegion> idleAnimationPants;
    private Animation<TextureRegion> walkAnimationDOWNPants;
    private Animation<TextureRegion> walkAnimationUPPants;
    private Animation<TextureRegion> walkAnimationLEFTPants;
    private Animation<TextureRegion> walkAnimationRIGHTPants;
    private TextureRegion currentFramePants;

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
        walkAnimationDOWN = animationUtil.makeAnimation(walkSheet, 0 );
        walkAnimationUP = animationUtil.makeAnimation(walkSheet,  3 );
        walkAnimationLEFT = animationUtil.makeAnimation(walkSheet, 1 );
        walkAnimationRIGHT = animationUtil.makeAnimation(walkSheet, 2 );


        //animation for the girl with underwear
        currentAnimationUnderwear = animationUtil.makeAnimation(underwearSheet, FRAME_COLS, FRAME_ROWS, 4, new int[]{0,2} );
        idleAnimationUnderwear = animationUtil.makeAnimation(underwearSheet, FRAME_COLS, FRAME_ROWS, 4, new int[]{0,2} );
        walkAnimationDOWNUnderwear = animationUtil.makeAnimation(underwearSheet, 0 );
        walkAnimationUPUnderwear = animationUtil.makeAnimation(underwearSheet, 3 );
        walkAnimationLEFTUnderwear = animationUtil.makeAnimation(underwearSheet, 1 );
        walkAnimationRIGHTUnderwear = animationUtil.makeAnimation(underwearSheet, 2 );
        //animation for the girl with socks
        currentAnimationSocks = animationUtil.makeAnimation(socksSheet, FRAME_COLS, FRAME_ROWS, 4, new int[]{0,2} );
        idleAnimationSocks = animationUtil.makeAnimation(socksSheet, FRAME_COLS, FRAME_ROWS, 4, new int[]{0,2} );
        walkAnimationDOWNSocks = animationUtil.makeAnimation(socksSheet,  0 );
        walkAnimationUPSocks = animationUtil.makeAnimation(socksSheet,  3 );
        walkAnimationLEFTSocks = animationUtil.makeAnimation(socksSheet,  1 );
        walkAnimationRIGHTSocks = animationUtil.makeAnimation(socksSheet,  2 );
        //animation for the girl with shirt
        currentAnimationShirt= animationUtil.makeAnimation(tshirtSheet, FRAME_COLS, FRAME_ROWS, 4, new int[]{0,2} );
        idleAnimationShirt = animationUtil.makeAnimation(tshirtSheet, FRAME_COLS, FRAME_ROWS, 4, new int[]{0,2} );
        walkAnimationDOWNShirt = animationUtil.makeAnimation(tshirtSheet,  0 );
        walkAnimationUPShirt = animationUtil.makeAnimation(tshirtSheet,  3 );
        walkAnimationLEFTShirt = animationUtil.makeAnimation(tshirtSheet,  1 );
        walkAnimationRIGHTShirt = animationUtil.makeAnimation(tshirtSheet,  2 );
        //animation for the girl with pants
        currentAnimationPants= animationUtil.makeAnimation(pantsSheet, FRAME_COLS, FRAME_ROWS, 4, new int[]{0,2} );
        idleAnimationPants = animationUtil.makeAnimation(pantsSheet, FRAME_COLS, FRAME_ROWS, 4, new int[]{0,2} );
        walkAnimationDOWNPants = animationUtil.makeAnimation(pantsSheet,  0 );
        walkAnimationUPPants = animationUtil.makeAnimation(pantsSheet,  3 );
        walkAnimationLEFTPants = animationUtil.makeAnimation(pantsSheet,  1 );
        walkAnimationRIGHTPants = animationUtil.makeAnimation(pantsSheet,  2 );


        // Instantiate a SpriteBatch for drawing and reset the elapsed animation time to 0
        spriteBatch = new SpriteBatch();
        stateTime = 0f;

    }

    public void setCurrentAnimation(Animation<TextureRegion> currentAnimation) {
        this.currentAnimation = currentAnimation;
    }

    public void setCurrentAnimationSocks(Animation<TextureRegion> currentAnimation) {
        this.currentAnimationSocks = currentAnimation;
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
        currentFrameSocks = getCurrentAnimationSocks().getKeyFrame(stateTime, true);
        currentFrameShirt = getCurrentAnimationShirt().getKeyFrame(stateTime, true);
        currentFramePants = getCurrentAnimationPants().getKeyFrame(stateTime, true);
        //go back to idle state after 2 sec
        if(stateTime > 3){
            stateTime = 0;
            setCurrentAnimation(getIdleAnimation());
            setCurrentAnimationUnderwear(getIdleAnimationUnderwear());
            setCurrentAnimationSocks(getIdleAnimationSocks());
            setCurrentAnimationShirt(getIdleAnimationShirt());
            setCurrentAnimationPants(getIdleAnimationPants());
        }
        spriteBatch.begin();
        spriteBatch.draw(currentFrame, getX(), getY()); //naked girl rendering
        spriteBatch.end();
    }

    public float getPlainX() { return x; }

    public float getX() { return x - (float)(Constants.tileSize*0.25); } //place the animation in the center of the tile

    public float getY() {return y;}

    /**
     * Update girl with clothes on
     * @param items the array(Libgdx specific) of items
     */
    public void updateSpriteBatch(Array<Item> items){
        spriteBatch.begin();

        for(Item item : items){
        if(item.isCollected() == true && item.getName().equals("underwear")) {
            // spriteBatch.draw(currentFrame, getX(), getY());
            spriteBatch.draw(currentFrameUnderwear, getX(), getY());
        }
        if(item.isCollected() == true && item.getName().equals("socks")){
            spriteBatch.draw(currentFrameSocks, getX(), getY());}
        if(item.isCollected() == true && item.getName().equals("tshirt")){
            spriteBatch.draw(currentFrameShirt, getX(), getY());}
        if(item.isCollected() == true && item.getName().equals("pants")){
            spriteBatch.draw(currentFramePants, getX(), getY());
        }}
        spriteBatch.end();
    }

    /**
     * Define how the player moves. the new positions in X and Y are the results of
     * the previous positions PLUS the steps taken
     * @param stepX the steps taken horizontally
     * @param stepY the steps taken vertically
     */
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
        socksSheet.dispose();
        pantsSheet.dispose();
        tshirtSheet.dispose();
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

    public Animation<TextureRegion> getCurrentAnimationSocks() {
        return currentAnimationSocks;
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

    public void setCurrentAnimationShirt(Animation<TextureRegion> currentAnimationShirt) {
        this.currentAnimationShirt = currentAnimationShirt;
    }
    public void setCurrentAnimationPants(Animation<TextureRegion> currentAnimationPants) {
        this.currentAnimationPants= currentAnimationPants;
    }


    public Animation<TextureRegion> getIdleAnimationSocks() {
        return idleAnimationSocks;
    }

    public Animation<TextureRegion> getWalkAnimationDOWNSocks() {
        return walkAnimationDOWNSocks;
    }

    public Animation<TextureRegion> getWalkAnimationUPSocks() {
        return walkAnimationUPSocks;
    }

    public Animation<TextureRegion> getWalkAnimationLEFTSocks() {
        return walkAnimationLEFTSocks;
    }

    public Animation<TextureRegion> getWalkAnimationRIGHTSocks() {
        return walkAnimationRIGHTSocks;
    }


    public Animation<TextureRegion> getIdleAnimationShirt() {
        return idleAnimationShirt;
    }

    public Animation<TextureRegion> getWalkAnimationDOWNShirt() {
        return walkAnimationDOWNShirt;
    }

    public Animation<TextureRegion> getWalkAnimationUPShirt() {
        return walkAnimationUPShirt;
    }

    public Animation<TextureRegion> getWalkAnimationLEFTShirt() {
        return walkAnimationLEFTShirt;
    }

    public Animation<TextureRegion> getWalkAnimationRIGHTShirt() {
        return walkAnimationRIGHTShirt;
    }

    public TextureRegion getCurrentFrameShirt() {
        return currentFrameShirt;
    }


    public TextureRegion getCurrentFrameSocks() {
        return currentFrameSocks;
    }

    public Animation<TextureRegion> getWalkAnimationUPPants() {
        return walkAnimationUPPants;
    }

    public Animation<TextureRegion> getWalkAnimationLEFTPants() {
        return walkAnimationLEFTPants;
    }

    public Animation<TextureRegion> getWalkAnimationRIGHTPants() {
        return walkAnimationRIGHTPants;
    }

    public TextureRegion getCurrentFramePants() {
        return currentFramePants;
    }


    public Animation<TextureRegion> getCurrentAnimationShirt() {
        return currentAnimationShirt;
    }

    public Animation<TextureRegion> getCurrentAnimationPants() {
        return currentAnimationPants;
    }

    public Animation<TextureRegion> getIdleAnimationPants() {
        return idleAnimationPants;
    }

    public Animation<TextureRegion> getWalkAnimationDOWNPants() {
        return walkAnimationDOWNPants;
    }


    public void makeAnimationList(Texture sheet){
        List<Animation<TextureRegion>> list = new ArrayList<Animation<TextureRegion>>();
        list.add(animationUtil.makeAnimation(sheet, FRAME_COLS, FRAME_ROWS, 4, new int[]{0,2} ));

        currentAnimation = animationUtil.makeAnimation(sheet, FRAME_COLS, FRAME_ROWS, 4, new int[]{0,2} );
        idleAnimation = animationUtil.makeAnimation(sheet, FRAME_COLS, FRAME_ROWS, 4, new int[]{0,2} );
        walkAnimationDOWN = animationUtil.makeAnimation(sheet, 0 );
        walkAnimationUP = animationUtil.makeAnimation(sheet,  3 );
        walkAnimationLEFT = animationUtil.makeAnimation(sheet, 1 );
        walkAnimationRIGHT = animationUtil.makeAnimation(sheet, 2 );
    }



}
