package com.mygdx.game;

import com.badlogic.gdx.utils.Array;

import java.util.List;

import static com.mygdx.game.Constants.APPLE;
import static com.mygdx.game.Constants.PANTS;
import static com.mygdx.game.Constants.SOCKS;
import static com.mygdx.game.Constants.TSHIRT;
import static com.mygdx.game.Constants.UNDERWEAR;

/**
 * This class creates lists of game objects: itemsLevelZero and monsters
 *
 */

public class GameObjectList {
    Array<Item> itemsLevelZero;
    Array<Item> itemsLevelOne;
    List<Monster> monsters;


    public GameObjectList(){
          itemsLevelZero = new Array<Item>();
     //   monsters = new ArrayList<Monster>();
        addItemLevelZero();
      // addMonster();
      //  hasItem = false;
    }

    public void renderItems() {
        for(Item item : itemsLevelZero){
            item.render();
        }
    }

    public void addItemLevelZero() {
        itemsLevelZero.add(new Item("underwear", UNDERWEAR, 256,256));
        itemsLevelZero.add(new Item("tshirt", TSHIRT,1280, 384));
        itemsLevelZero.add(new Item("socks", SOCKS,1280, 896));
        itemsLevelZero.add(new Item("pants", PANTS, 637, 256));
        itemsLevelZero.add(new Item("apple", APPLE, 384, 512));
    }

   /* public void addMonster(){
       monsters.add(new MushRoomMonster());
       monsters.add(new GazetiMonster());

    }*/


    public void hasItemOn(boolean collected){
        //hasItem = collected;
        if(collected){

        }
    }

    public Array<Item> getItemsLevelZero() {
        return itemsLevelZero;
    }
    public Item getSpecificItem(int index){
        return itemsLevelZero.get(index);
    }


}
