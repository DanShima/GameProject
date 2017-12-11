package com.mygdx.game;

/**
 * Created by Giddy on 10/12/2017.
 */

public class MovementPreviewTarget {

    //private boolean isGoodGoal;
    private int posSimpleX;
    private int posSimpleY;
    //private int posX;
    //private int posY;

    public int getPosSimpleX() {
        return posSimpleX;
    }

    public int getPosSimpleY() {
        return posSimpleY;
    }

    MovementPreviewTarget(int simplePosX, int simplePosY){
        posSimpleX = simplePosX;
        posSimpleY = simplePosY;
        //
    }


}
