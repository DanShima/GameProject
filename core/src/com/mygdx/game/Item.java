package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static com.mygdx.game.TiledTest.tileSize;

/**
 * Created by Giddy on 26/11/2017.
 */

public class Item {
    private Sprite sprite;
    private SpriteBatch spriteBatch;
    private Texture texture;
    private float positionX;
    private float positionY;
    private boolean collected;

    /**
     * Constructor to create ITEM
     */
     public Item(String pngFile, float x, float y) {
        create(pngFile, x, y);
    }

    /**
     *  To create item at specific location
     */
    public void create(String pngFile, float x, float y) {
        spriteBatch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal(pngFile));
        sprite = new Sprite(texture);
        sprite.setPosition(x, y);
        collected = false;
        }

    /**
     * Render the item to the screen. If the object is collected then it won't be rendered.
     * */
    public void render() {
        //if item is collected by the player then it becomes invisible
        if(!collected) {
            spriteBatch.begin();
            sprite.draw(spriteBatch);
            spriteBatch.end();
        }
    }
    /**
     * Set the boolean collected is true if player grabs item
     * @param collected
     */
    public void setCollected(boolean collected) {
        this.collected = collected;
    }






    /**
     * this method returns the item's score that the player will receive when he collects it.
     * @return
     */
    public int giveScorePoint() {
        return 20;
    }

    //this maybe should be in tiledtest class
    public void dispose() {
        spriteBatch.dispose();
        texture.dispose();
    }
    public boolean isCollected() {
        return collected;
    }

    public float getPositionX() {
        return positionX;
    }

    public void setPositionX(float positionX) {
        this.positionX = positionX;
    }

    public float getPositionY() {
        return positionY;
    }

    public void setPositionY(float positionY) {
        this.positionY = positionY;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }
    public Texture getTexture() {
        return texture;
    }


}
