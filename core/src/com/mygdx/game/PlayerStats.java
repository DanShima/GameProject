package com.mygdx.game;

/**
 * Created by Giddy on 03/12/2017.
 *
 * Keep track of player's stats like health, steps moved, itemsLevelZero picked up, etc.
 */

public class PlayerStats {

    int stepsTaken=0;

    public void addSteps(int steps){
        stepsTaken += steps;
    };

    public void resetSteps(int steps){
        stepsTaken = 0;
    };


}
