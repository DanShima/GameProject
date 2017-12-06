package com.mygdx.game;

/**
 * Created by Giddy on 03/12/2017.
 *
 * Keep track of player's stats like health, steps moved, items picked up, etc.
 */

public class PlayerStats {

    private String  name;
   // private Player player;
    private int score;
    private int health;
    private static int maxScore=75;



    int stepsTaken=0;
    public PlayerStats(String name){
        this.name=name;
        score=75;
        health=100;



    }
    public void setScore(int score){
        this.score=score;
        if(score > maxScore)
            maxScore=score;

    }
    public void setHealth(int health){
        this.health=health;

    }



    public void addSteps(int steps){
        stepsTaken += steps;


    };

    public void resetSteps(int steps){
        stepsTaken = 0;
    };


}
