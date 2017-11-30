package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * just a placeholder class for monster. feel free to modify or change completely
 */

public class Monster implements ApplicationListener {
    private Texture texture; //The texture that will hold the sprite sheet.
    private SpriteBatch spriteBatch;
    private TextureRegion[] regions = new TextureRegion[4]; //if 4 monsters in one sheet
    private Animation<TextureRegion> walkAnimation;
    private TextureRegion[] idleFrames;
    private TextureRegion currentFrame;
    private float stateTime;
    private float timeTillIdle = 0;
    private float fps = 0.3f; //time between frames in seconds

    //yeti monster
    public Monster(){
        create();
    }
    //customize a monster
    public Monster(String pngFile, int rows, int columns, int specifyRow){

        texture = new Texture(Gdx.files.internal(pngFile));
        TextureRegion[][] tmp = TextureRegion.split(texture,
                texture.getWidth() / columns,
                texture.getHeight() / rows);
        idleFrames = new TextureRegion[columns];
        for (int i = 0; i < columns; i++) {
            idleFrames[i] = tmp[specifyRow][i];
        }
        walkAnimation = new Animation<TextureRegion>(fps, idleFrames);
        spriteBatch = new SpriteBatch();
        stateTime = 0f;

    }
    public void create(){
        texture = new Texture(Gdx.files.internal("yeti_v01.png"));

        // Create a 2D array of TextureRegions by splitting the sheet into separate frames
        TextureRegion[][] tmp = TextureRegion.split(texture,
                texture.getWidth() / 8,
                texture.getHeight() / 3); //8 columns and 3 rows
        //convert 2D array to normal array
        idleFrames = new TextureRegion[3];
        idleFrames[0] = tmp[0][4];
        idleFrames[1] = tmp[0][5];
        idleFrames[2] = tmp[0][6];
        walkAnimation = new Animation<TextureRegion>(fps, idleFrames);
        // Instantiate a SpriteBatch for drawing and reset the elapsed animation time to 0
        spriteBatch = new SpriteBatch();
        stateTime = 0f;
    }

    public void render() {
        stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time
        // Get current frame of animation for the current stateTime:
        currentFrame = walkAnimation.getKeyFrame(stateTime, true);
        //go back to idle state after 2 sec
        spriteBatch.begin();
        spriteBatch.draw(currentFrame, 782, 512); // Draw current frame at (0, 0)
        spriteBatch.end();
    }


    public void move(float stepX, float stepY){

    }
    @Override
    public void dispose() {
        spriteBatch.dispose();
        texture.dispose();
    }

    @Override
    public void resume(){}
    @Override
    public void pause(){}
    @Override
    public void resize(int x, int y){}

}
