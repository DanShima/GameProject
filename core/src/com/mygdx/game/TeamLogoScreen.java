package com.mygdx.game;

/**
 * Created by Pintu on 12/8/2017.
 * fading is added
 */

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * This class shows the team logo before the login screen
 */
public class TeamLogoScreen implements Screen
{
    private Texture logo;
    private SpriteBatch spriteBatch;
    private LoginScreen game;
    private Sprite sprite;


    public TeamLogoScreen(LoginScreen game )
    {
        this.game = game;
    }

    @Override
    public void show()
    {
        logo = new Texture(Gdx.files.internal("teamlogo.png"));
        spriteBatch = new SpriteBatch();
        sprite = new Sprite(logo);
    }

    @Override
    public void render(float delta)
    {
        GL20 gl = Gdx.graphics.getGL20();
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        spriteBatch.draw(sprite, 0, 0, LoginScreen.WIDTH, LoginScreen.HEIGHT);
        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {
    }
    @Override
    public void hide() {
    }
    @Override
    public void pause() {
    }
    @Override
    public void resume() {
    }
    @Override
    public void dispose() {
        sprite.getTexture().dispose();
        spriteBatch.dispose();
    }
}