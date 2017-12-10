package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Timer;

/**
 * This class shows the player login page
 */

public class GearUp extends Game {
    public static int WIDTH;
    public static int HEIGHT;
    private static long SPLASH_MINIMUM_MILLIS = 3000L;

    public GearUp() { super(); }

    @Override
    public void create() {
        this.setScreen(new PlayerLogin());
        this.setScreen(new PlayerLogin());

        WIDTH = Gdx.graphics.getWidth();
        HEIGHT = Gdx.graphics.getHeight();

        setScreen( new Splash(this) );

        final long splash_start_time = System.currentTimeMillis();

        new Thread(new Runnable() {
            @Override
            public void run() {

                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {

                        long splash_elapsed_time = System.currentTimeMillis() - splash_start_time;
                        if (splash_elapsed_time < GearUp.SPLASH_MINIMUM_MILLIS)
                        {
                            Timer.schedule(
                                    new Timer.Task() {
                                        @Override
                                        public void run() {
                                            GearUp.this.setScreen(new PlayerLogin());
                                        }
                                    }, (float)(GearUp.SPLASH_MINIMUM_MILLIS - splash_elapsed_time) / 1000f);
                        } else
                        {
                            GearUp.this.setScreen(new PlayerLogin());
                        }
                    }
                });
            }
        }).start();

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
