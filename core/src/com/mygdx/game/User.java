package com.mygdx.game;

/**
 * Created by tmp-sda-1124 on 11/24/17.
 */

/**
 * User detail for Login
 */
public class User {
    private String userId;
    private String userName;
    private String userpassword;
    private int HighScore;
    private int Score;

    //Constructors

    public User(){
    }

    public User(String userId, String userName, String userpassword, int highScore, int score) {
        this.userId = userId;
        this.userName = userName;
        this.userpassword = userpassword;
        HighScore = highScore;
        Score = score;
    }

    public User(String userId, String userName, String userpassword) {
        this.userId=userId;
        this.userName = userName;
        this.userpassword = userpassword;
        this.HighScore=0;
        this.Score=0;
    }

    //Getter methods

    public String getUserName() {
        return userName;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public String getUserId() {
        return userId;
    }

    public int getHighScore() { return HighScore; }

    public int getScore() {
        return Score;
    }

    public void setHighScore(int highScore) {
        HighScore = highScore;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

}
