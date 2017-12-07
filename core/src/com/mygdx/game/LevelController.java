package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

import static com.mygdx.game.Constants.APPLE;
import static com.mygdx.game.Constants.PANTS;
import static com.mygdx.game.Constants.SOCKS;
import static com.mygdx.game.Constants.TSHIRT;
import static com.mygdx.game.Constants.UNDERWEAR;

/**
 * This class is the container for levels
 * Some stuff in TiledTest needs to be moved here
 */

public class LevelController {
    Player animator;



    List<Item> items;
    List<Monster> monsters;
    boolean hasItem;
    //int score;
    //TODO add map
    public LevelController(){
        animator = new Player();
        items = new ArrayList<Item>();
        monsters = new ArrayList<Monster>();
        addItem();
        hasItem = false;
    }

    public void render() {
        animator.render();
        for(Item item : items){
            item.render();
        }
        for(Monster monster: monsters){
            //monster.render();
        }

        //map should be rendered here too
    }

    public void addItem() {
        items.add(new Item("underwear", UNDERWEAR, 256,256));
        items.add(new Item("tshirt", TSHIRT,1280, 384));
        items.add(new Item("socks", SOCKS,1280, 896));
        items.add(new Item("pants", PANTS, 637, 256));
        items.add(new Item("apple", APPLE, 384, 512));
    }

    public void addMonster(){
       // monsters.add(new Monster("yeti03.png", 512, 256));
    }

    public void hasItemOn(boolean collected){
        hasItem = collected;
        if(collected){

        }
    }

    public List<Item> getItems() {
        return items;
    }

}
