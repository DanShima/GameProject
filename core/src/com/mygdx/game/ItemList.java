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

public class ItemList {
    Array<Item> itemsLevelZero;
    Array<Item> itemsLevelOne;

    public ItemList(){
          itemsLevelZero = new Array<Item>();
          itemsLevelOne = new Array<Item>();
          addItemLevelZero();
        addItemLevelOne();
    }

    public void renderItemsLevelZero() {
        for(Item item : itemsLevelZero){
            item.render();
        }
    }
    public void renderItemsLevelOne() {
        for(Item item : itemsLevelOne){
            item.render();
        }
    }
    public void addItemLevelZero() {
        itemsLevelZero.add(new Item("underwear", UNDERWEAR, 256,256));
        itemsLevelZero.add(new Item("tshirt", TSHIRT,1280, 384));
        itemsLevelZero.add(new Item("apple", APPLE, 384, 512));
    }

    public void addItemLevelOne() {
        itemsLevelOne.add(new Item("socks", SOCKS,1280, 896));
        itemsLevelOne.add(new Item("pants", PANTS, 637, 256));
        itemsLevelOne.add(new Item("apple", APPLE, 384, 512));
    }

    public Array<Item> getItemsLevelZero() {
        return itemsLevelZero;
    }
    public Array<Item> getItemsLevelOne() {
        return itemsLevelOne;
    }
    public Item getSpecificItemLevelZero(int index){
        return itemsLevelZero.get(index);
    }
    public Item getSpecificItemLevelOne(int index){
        return itemsLevelOne.get(index);
    }


}
