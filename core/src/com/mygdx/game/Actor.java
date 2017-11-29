package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * A super class for monster and player and maybe also items?
 */
public abstract class Actor {
    private Sprite sprite;
    private SpriteBatch spriteBatch; //to draw on screen
    private Texture texture; //sprite sheet
    private float positionX;
    private float positionY;
    public abstract void move(float x, float y);
    public abstract void render();
}
