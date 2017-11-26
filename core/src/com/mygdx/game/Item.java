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

    public void create(String pngFile, float x, float y) {
        spriteBatch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal(pngFile));
        sprite = new Sprite(texture);
        //set the initial starting position of the player
        //set the player in the middle of the tile
        sprite.setPosition(x, y);
    }

    public void render() {
        spriteBatch.begin();
        sprite.draw(spriteBatch);
        spriteBatch.end();
    }

    //this maybe should also be in tiledtest class
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
