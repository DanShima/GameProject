package com.mygdx.game;

/**
 * Created by Shashidhar on 09-12-2017.
 */

public interface firebase {
    //Method for login validation
    public boolean onclicklogin(String name,String password);
    //Method for new user registration
    public boolean onclicknewuser(String name,String password);
    //Method to set High SCore to User
    public void setHighScore(int Maxscore, String name);
    //Method to get High Score of User
    public  int getHighScore(String name);
}
