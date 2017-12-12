package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Model class for the game object item, such as a piece of clothes or food
 */

public class Item {
    private Sprite sprite;
    private SpriteBatch spriteBatch;
    private Texture texture;
    private float x;
    private float y;
    private boolean collected;
    private String name;

    /**
     * Constructor to create ITEM
     */
     public Item(String name, String pngFile, float x, float y) {
         this.name = name;
         this.x=x;
         this.y=y;
         create(pngFile, x, y);
    }

    public String getName(){
         return name;
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

    public boolean getCollected(){
        return collected;
    }

    public boolean isCollected(){

        return collected ;
    }

    /**
     * this method returns the item's score that the player will receive when he collects it.
     * @return
     */
    public int giveScorePoint() {
        return 1000;
    }

    /**
     * Food items reward health points
     * @return
     */
    public int giveHealthPoint() {
        return 50;
    }

    public void dispose() {
        spriteBatch.dispose();
        texture.dispose();
    }
    //getters
    public float getPositionX() {
        return x;
    }
    public float getPositionY() {
        return y;
    }
}
