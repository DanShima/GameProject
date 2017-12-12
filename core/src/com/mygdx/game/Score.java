package com.mygdx.game;

/**
 *
 * the score system
 * every move will reduce health for player and every collision with monster
 * every item will add score to the player
 */

public class Score {
    private int score = Constants.SCORE_START;
    private float health;
    private Player player;
    private int maxScore ;
    private HUD hud;

    public Score( HUD hud) {
        this.health = hud.getHealth();
        this.hud = hud;
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getMaxScore() {
        if (hud.getHealth() > 800){
            maxScore = score + 2000;
        return maxScore;}
        if (hud.getHealth()==100){
            maxScore = score + 3000;
        return maxScore;}
        else return score;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }
    public int StartScore(){
        return score=Constants.SCORE_START;
    }
}

