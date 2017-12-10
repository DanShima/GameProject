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
    protected float YposMonster;
    protected float XposMonster;
    protected boolean initialMonsterPos=false;
    private  Controller playerPos;

    private int simpleMonsterX;
    private int simpleMonsterY;


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
            XposMonster=positionX;
            YposMonster=positionY;
            spriteBatch.draw(currentFrame, XposMonster, YposMonster); // Draw current monster  at (position x, position Y)

            initialMonsterPos=true;

        }else
        {

            spriteBatch.draw(currentFrame, XposMonster, YposMonster); // Update the monster place

        }

        spriteBatch.end();

    }

    public Texture getTexture() {
        return texture;
    }

    public int getSimpleMonsterX() {
        return simpleMonsterX;
    }

    public int getSimpleMonsterY() {
        return simpleMonsterY;
    }



    public void move(int playerPositionX, int playerPositionY){

        simpleMonsterY = (int) Math.floor( Math.max(0,(YposMonster-(float) merginTop)/(float) tileSize));
        simpleMonsterX= (int) Math.floor( Math.max(0,XposMonster/(float) tileSize));

        int diffBetweenX= playerPositionX-simpleMonsterX;

        int diffBetweenY=playerPositionY-simpleMonsterY;

        if ((Math.signum((int)diffBetweenX) == -1)){
           if (simpleMonsterX!=playerPositionX ){

                XposMonster-=tileSize;
            if ((Math.signum((int)diffBetweenY) == -1)){


                  if (simpleMonsterY!=playerPositionY ){

                YposMonster-=tileSize;

                  }
            }
        } }else if ((Math.signum((int)diffBetweenX) == 1)){
            if (simpleMonsterX!=playerPositionX){
                XposMonster+=tileSize;
                if ((Math.signum((int)diffBetweenY) == 1)){

                     if (simpleMonsterY!=playerPositionY  ){
                    YposMonster+=tileSize;

                     }

                }

            }

            }









        }

    public void dispose() {
        spriteBatch.dispose();
        texture.dispose();
    }

}
