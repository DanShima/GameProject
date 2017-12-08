package com.mygdx.game;

/**
 * Created by Pintu on 12/8/2017.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Splash implements Screen
{
    private Texture logo;
    private SpriteBatch spriteBatch;
    private  GearUp game;

    public Splash( GearUp game )
    {
        this.game = game;
    }

    @Override
    public void show()
    {
        logo = new Texture(Gdx.files.internal("golem.png"));
        spriteBatch = new SpriteBatch();
    }

    @Override
    public void render(float delta)
    {
        handleInput();

        GL20 gl = Gdx.graphics.getGL20();
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();
        spriteBatch.draw(logo, 0, 0, GearUp.WIDTH, GearUp.HEIGHT);
        spriteBatch.end();
    }

    private void handleInput()
    {
        if(Gdx.input.justTouched())
        {
            game.setScreen(new PlayerLogin());
        }
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
    }
}