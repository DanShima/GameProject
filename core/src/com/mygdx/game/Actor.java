package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Giddy on 25/11/2017.
 */

public abstract class Actor {
    private Sprite sprite;
    private SpriteBatch spriteBatch;
    private Texture texture;
    private float positionX;
    private float positionY;
    public abstract void move();
    public abstract void render(SpriteBatch batch);
}
