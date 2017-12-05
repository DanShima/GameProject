package com.mygdx.game;

import com.badlogic.gdx.Game;

/**
 * Created by Shashidhar on 05-12-2017.
 */

public class GearUp extends Game {
    @Override
    public void create() {
        this.setScreen(new MenuScreen());
    }
}
