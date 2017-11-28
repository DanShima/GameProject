package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller class for managing the interaction between player, items and monsters.
 * Some stuff in TiledTest needs to be moved here
 */

public class GameplayController {
    Animator animator;
    List<Item> items;
    List<Monster> monsters;
    boolean hasItem;
    //int score;

    public GameplayController(){
        animator = new Animator();
        items = new ArrayList<Item>();
        monsters = new ArrayList<Monster>();
        hasItem = false;
    }

    public void addItem() {
        items.add(new Item("underwear.png", 256, 256));
        items.add(new Item("tshirt.png", 512, 1024));
        items.add(new Item("socks", 512, 512));
    }

    public void addMonster(){

    }

    public void hasItemOn(boolean collected){
        hasItem = collected;
        if(collected){

        }
    }

}
