package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static com.mygdx.game.Constants.FPS;

/**
 * just a placeholder class for monster. feel free to modify or change completely
 */

public class Monster  {
    
    private Texture texture; //The texture that will hold the sprite sheet.
    private Texture mummySheet;
    private SpriteBatch spriteBatch;
    private TextureRegion[] regions = new TextureRegion[4]; //if 4 monsters in one sheet
    private Animation<TextureRegion> walkAnimation;
    private TextureRegion[] idleFrames;
    private TextureRegion currentFrame;
    private float stateTime;
    protected final static int tileSize=128;
    protected final static int merginTop=55;
    private AnimationUtil animationUtil;
    private int turnOrder;
    protected float monsterPositionX;
    protected float monsterPositionY;
    protected boolean initialMonsterPos=false;

    //Yeti monster
    public Monster(){

    }

    //customize a monster
    public Monster(String pngFile, int rows, int columns, int specifyRow, int turnOrder){
        texture = new Texture(Gdx.files.internal(pngFile));
        TextureRegion[][] tmp = TextureRegion.split(texture,
                texture.getWidth() / columns,
                texture.getHeight() / rows);
        idleFrames = new TextureRegion[columns];
        for (int i = 0; i < columns; i++) {
            idleFrames[i] = tmp[specifyRow][i];
        }
        walkAnimation = new Animation<TextureRegion>(FPS, idleFrames);
        spriteBatch = new SpriteBatch();
        stateTime = 0f;

        this.turnOrder = turnOrder;
    }



    public void render(float positionX, float positionY) {
        // setting the received monster position from tiletest  for the monster
        stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time
        // Get current frame of animation for the current stateTime:
        currentFrame = walkAnimation.getKeyFrame(stateTime, true);
        //go back to idle state after 2 sec
        spriteBatch.begin();

        if (!(initialMonsterPos)){
            spriteBatch.draw(currentFrame, positionX, positionY); // Draw current monster  at (position x, position Y)
            Gdx.app.log("inside render condition " + positionX," innnnn" + positionY);
            monsterPositionX= positionX;
            monsterPositionY= positionY;
            initialMonsterPos=true;

        }else
        {

            move();

            spriteBatch.draw(currentFrame, monsterPositionX, monsterPositionY); // Update the monster place
            Gdx.app.log("AAAAAAAAAAAAAAAAA" + monsterPositionX," AAAAAAAAAAAAAAAAA" + monsterPositionY);

        }



        spriteBatch.end();

    }



/*
    // return Y position for the Monster

    public float getMonsterY(){
        return monsterPositionY;
    }

    // Return X position for the Monster

    public float getMonsterX(){
        return monsterPositionX;
    }*/




    // convert X monster position To simplified X
    public int ScreenPosYtoSimplified(float monsterPositionY){
        float temporary = (monsterPositionY-(float) merginTop)/(float) tileSize;

        return (int) Math.floor( Math.max(0.0,temporary));
    }
    // convert Y monster position To simplified Y
    public int ScreenPosXtoSimplified(float monsterPositionX){ //convert screen X position to simplified X
        return (int) Math.floor( Math.max(0,monsterPositionX/(float) tileSize));
    }



    public void move(){





        }


    public void dispose() {
        spriteBatch.dispose();
        texture.dispose();
    }

}
