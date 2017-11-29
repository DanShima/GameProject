package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * just a placeholder class for monster. feel free to modify or change completely
 */

public class Monster {
    private Texture texture; //The texture that will hold the sprite sheet.
    private SpriteBatch spriteBatch;
    private TextureRegion[] regions = new TextureRegion[4]; //if 4 monsters in one sheet

    public Monster(Texture spriteSheet, float x, float y){
        texture = spriteSheet;


    }
    public void create(){
        texture = new Texture(Gdx.files.internal("spritebase_hero.png"));
        spriteBatch = new SpriteBatch();
        //Cuts out a region from the texture starting at (0,0) having both the width and height 64 pixels.
        regions[0] = new TextureRegion(texture, 0, 0, 64, 64);
        regions[1] = new TextureRegion(texture, 0.5f, 0f, 1f, 0.5f);
        regions[2] = new TextureRegion(texture, 0, 63, 64, 64);
        regions[3] = new TextureRegion(texture, 0.5f, 0.5f, 1f, 1f);
    }

    public void render() {
        spriteBatch.begin();
        //draws the original texture scaled to 64 pixels wide and high at the bottom left corner.
        spriteBatch.draw(texture, 0, 0, 64, 64);
        for (int i = 0; i < regions.length; i++) {
            //draws each region at 75 pixels distance from each other horizontally.
            spriteBatch.draw(regions[i], 75 * (i + 1), 100);
        }
        spriteBatch.end();
    }
}
