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
    private Animator animator;

    public Item(String pngFile, float x, float y) {
        create(pngFile, x, y);
    }

    public void create(String pngFile, float x, float y) {
        spriteBatch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal(pngFile));
        sprite = new Sprite(texture);
        //set the initial starting position of the player
        //set the player in the middle of the tile
        sprite.setPosition(x, y);
        collected = false;
    }

    /**
     * render the item to the screen. if the object is collected then it won't be rendered.
     * */
    public void render() {
        if(collected) return; //if it is collected by the player then it becomes invisible
        spriteBatch.begin();
        sprite.draw(spriteBatch);
        spriteBatch.end();
    }


    /**
     * check if player is on the same position as the item. If it is, then the item is collected by the player
     * @return
     */
    /*public boolean checkCollision(){
        if(getPositionY() == animator.getY() && getPositionX() == animator.getX()){
            collected = true;}
            return false;
    }*/

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
