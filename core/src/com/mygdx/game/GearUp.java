package com.mygdx.game;

import com.badlogic.gdx.Game;

/**
 * This class shows the player login page
 */

public class GearUp extends Game {

    @Override
    public void create() {
        this.setScreen(new PlayerLogin());
    }
}
