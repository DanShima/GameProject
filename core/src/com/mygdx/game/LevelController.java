package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

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
        hasItem = false;
    }

    public void render() {
        animator.render();
        for(Item item : items){
            item.render();
        }
        for(Monster monster: monsters){
            monster.render();
        }

        //map should be rendered here too
    }

    public void addItem() {
        items.add(new Item("underwear.png", 256, 256));
        items.add(new Item("tshirt.png", 512, 1024));
        items.add(new Item("socks", 512, 512));
    }

    public void addMonster(){
       // monsters.add(new Monster("yeti03.png", 512, 256));
    }

    public void hasItemOn(boolean collected){
        hasItem = collected;
        if(collected){

        }
    }



}
