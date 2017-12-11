package com.mygdx.game;

import com.badlogic.gdx.Gdx;

/**
 * This class creates the Gazeti Monster monster with specified starting position
 */

public class GazetiMonster extends Monster {

    public GazetiMonster() {
        super(Constants.GAZETI,4,3,1,3);
    }

    public float getXposMonster(){
        return XposMonster = 768;
    }
    public float getYposMonster(){
        return YposMonster = 256;
    }
}
