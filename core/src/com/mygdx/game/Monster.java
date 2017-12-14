package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Stack;

import static com.mygdx.game.Constants.FPS;

/**
 * Superclass monster
 */

public class Monster  {
    
    private Texture texture; //The texture that will hold the sprite sheet.
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
    private Stack<Float> steps ;
    private int simpleMonsterX;
    private int simpleMonsterY;
    private boolean backStep;

    private int monsterDamage=30;

    //create a monster
    public Monster(String pngFile, int rows, int columns, int specifyRow, int turnOrder , int startPosX, int startPosY){
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
        steps = new Stack<Float>();
        this.turnOrder = turnOrder;
        backStep=false;
        XposMonster = startPosX;
        YposMonster = startPosY;
    }

    /**
     * draw an animated monster to the screen
     */
    public void render() {
        // setting the received monster position from tiletest for the monster
        stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time
        // Get current frame of animation for the current stateTime:
        currentFrame = walkAnimation.getKeyFrame(stateTime, true);
        //go back to idle state after 2 sec
        spriteBatch.begin();

        if (!(initialMonsterPos)){
            XposMonster=getXposMonster();
            YposMonster=getYposMonster();
            spriteBatch.draw(currentFrame, XposMonster, YposMonster);
            initialMonsterPos=true;

        }else
        {
            spriteBatch.draw(currentFrame, XposMonster, YposMonster); // Update the monster place
        }
        spriteBatch.end();

    }


    public boolean move2(int playerPositionX, int playerPositionY){

        simpleMonsterY = SimpleMonsterYPosition();
        simpleMonsterX = SimpleMonsterXPosition();

        int diffBetweenX = playerPositionX-simpleMonsterX;
        int diffBetweenY = playerPositionY-simpleMonsterY;

        if(diffBetweenX!=0 && diffBetweenY!=0) { //prevent diagonal movement and make it horizontal
            YposMonster += Math.signum(diffBetweenY) * tileSize;
        }else{
            // this would be movement horizontal vertical and diagonal
            XposMonster += Math.signum(diffBetweenX) * tileSize;
            YposMonster += Math.signum(diffBetweenY) * tileSize;
        }

        // hit check
        if( playerPositionX-SimpleMonsterXPosition() == 0 && playerPositionY-SimpleMonsterYPosition() == 0 ){
            return true;
        }else {
            return false;
        }
    }


    public float getYposMonster() {
        return YposMonster;
    }
    public float getXposMonster() {
        return XposMonster;
    }

    /**
     * translate monster position in X to a simplified version
     * @return the integer position
     */
    public int SimpleMonsterXPosition(){
        return (int) Math.floor(
                Math.max(
                        0,
                        XposMonster/(float) tileSize
                )
        );
    }
    /**
     * translate monster position in Y to a simplified version
     * @return the integer position
     */
    public int SimpleMonsterYPosition(){

        return (int) Math.floor(
                Math.max(
                        0,
                        YposMonster/(float) tileSize
                )
        );
    }

    /**
     * Define how the monster moves
     * @param playerPositionX
     * @param playerPositionY
     * @return true if can move
     */
    public boolean move(int playerPositionX, int playerPositionY){
        simpleMonsterY = SimpleMonsterYPosition();
        simpleMonsterX = SimpleMonsterXPosition();

        int diffBetweenX = playerPositionX-simpleMonsterX;
        int diffBetweenY = playerPositionY-simpleMonsterY;

        if(diffBetweenX!=0 && diffBetweenY!=0) {
            //prevent diagonal movement and make it horizontal
            XposMonster += Math.signum(diffBetweenX) * tileSize;
        }else{
            // this would be movement horizontal vertical and diagonal
            XposMonster += Math.signum(diffBetweenX) * tileSize;
            YposMonster += Math.signum(diffBetweenY) * tileSize;
        }

        // hit check
        if( playerPositionX-SimpleMonsterXPosition() == 0 && playerPositionY-SimpleMonsterYPosition() == 0 ){
            return true;
        }else {
            return false;
        }
    }


    public int getMonsterDamage() {
        return monsterDamage;
    }

    public void dispose() {
        spriteBatch.dispose();
        texture.dispose();
    }
}
