package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.maps.tiled.TiledMap;

/**
 * This class deals with the game logic of the game. it handles collisions between game objects
 */

public class GameLogic extends InputAdapter {
    public LevelController levelController; //need everything on a level
    public int lives;
    public int score;
    public TiledMap map; //this class should hold map only
     public GameLogic(){
         create();
     }
     private void create(){
         Gdx.input.setInputProcessor(this);
         lives = Constants.LIVES;
         initializeLevel();
     }
     private void initializeLevel() {
         score = Constants.SCORE_START;
         //map = new TiledMap() //TODO create a class only to hold info for map
     }

     public void update() {}
     public boolean isGameOver() {
         return lives < 0;
     }

     public void checkCollisions() {
         for(Monster monster: levelController.monsters){
             double distance = levelController.animator.getX(); //TODO complete calculation
             if(distance < 0.10f){
                 playerCollideWithMonster(monster);
             }
         }
         for(Item item: levelController.items){
             float distance = 0; //not done yet
             if(distance < 0.15){
                 playerCollideWithItem(item);
             }
         }

     }

    private void playerCollideWithMonster(Monster monster){}

    private void playerCollideWithItem(Item item){
        item.setCollected(true);
        score += item.giveScorePoint();
        Gdx.app.log("GameLogic", "Check item collision");
    }


}
