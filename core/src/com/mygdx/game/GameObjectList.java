package com.mygdx.game;

import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.List;

import static com.mygdx.game.Constants.APPLE;
import static com.mygdx.game.Constants.PANTS;
import static com.mygdx.game.Constants.SOCKS;
import static com.mygdx.game.Constants.TSHIRT;
import static com.mygdx.game.Constants.UNDERWEAR;

/**
 * This class creates lists of game objects: items and monsters
 *
 */

public class GameObjectList {
    Array<Item> items;
    List<Monster> monsters;


    public GameObjectList(){
          items = new Array<Item>();
     //   monsters = new ArrayList<Monster>();
        addItem();
      // addMonster();
      //  hasItem = false;
    }

    public void renderItems() {
        for(Item item : items){
            item.render();
        }
    }

    public void addItem() {
        items.add(new Item("underwear", UNDERWEAR, 256,256));
        items.add(new Item("tshirt", TSHIRT,1280, 384));
        items.add(new Item("socks", SOCKS,1280, 896));
        items.add(new Item("pants", PANTS, 637, 256));
        items.add(new Item("apple", APPLE, 384, 512));
    }

   /* public void addMonster(){
       monsters.add(new HydraMonster());
       monsters.add(new GazetiMonster());

    }*/


    public void hasItemOn(boolean collected){
        //hasItem = collected;
        if(collected){

        }
    }

    public Array<Item> getItems() {
        return items;
    }
    public Item getSpecificItem(int index){
        return items.get(index);
    }


}
