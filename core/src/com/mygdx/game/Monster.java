package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
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

    private AnimationUtil animationUtil;
    private int turnOrder;

    //Yeti monster
    public Monster(){
        createYeti();
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

    public void createYeti(){
        texture = new Texture(Gdx.files.internal("yeti_v01.png"));
        animationUtil = new AnimationUtil();
        walkAnimation = animationUtil.makeAnimation(texture, 8, 3, 0, new int[]{4, 5, 6});
        // Instantiate a SpriteBatch for drawing and reset the elapsed animation time to 0
        spriteBatch = new SpriteBatch();
        stateTime = 0f;
        turnOrder = 1;
    }

    public void render(float positionX, float positionY) {
        stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time
        // Get current frame of animation for the current stateTime:
        currentFrame = walkAnimation.getKeyFrame(stateTime, true);
        //go back to idle state after 2 sec
        spriteBatch.begin();
        spriteBatch.draw(currentFrame, positionX, positionY); // Draw current frame at (0, 0)
        spriteBatch.end();
    }


    public void move(float stepX, float stepY){

    }

    public void dispose() {
        spriteBatch.dispose();
        texture.dispose();
    }

}
