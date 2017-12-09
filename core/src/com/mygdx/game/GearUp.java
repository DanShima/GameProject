package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.PlayerLogin;

/**
 * This class shows the player login page
 */

public class GearUp extends Game {
    public static int WIDTH;
    public static int HEIGHT;

    @Override
    public void create() {
        this.setScreen(new PlayerLogin());


        WIDTH = Gdx.graphics.getWidth();
        HEIGHT = Gdx.graphics.getHeight();

       // setScreen( new Splash(this) );
    }
    @Override
    public void resize(int width, int height) {
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
