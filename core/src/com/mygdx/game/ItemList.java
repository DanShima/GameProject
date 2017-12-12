package com.mygdx.game;

import com.badlogic.gdx.utils.Array;
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
    private Array<Item> itemsLevelZero;
    private Array<Item> itemsLevelOne;

    public ItemList(){
          itemsLevelZero = new Array<Item>();
          itemsLevelOne = new Array<Item>();
          addItemLevelZero();
          addItemLevelOne();
    }

    /**
     * Draw items from level zero map to the screen
     */
    public void renderItemsLevelZero() {
        for(Item item : itemsLevelZero){
            item.render();
        }
    }
    /**
     * Draw items from level zero map to the screen
     */
    public void renderItemsLevelOne() {
        for(Item item : itemsLevelOne){
            item.render();
        }
    }

    /**
     * Add items to the array containing items per level
     */
    public void addItemLevelZero() {
        itemsLevelZero.add(new Item("underwear", UNDERWEAR, 256,256));
        itemsLevelZero.add(new Item("tshirt", TSHIRT,1280, 384));
        itemsLevelZero.add(new Item("apple", APPLE, 384, 512));
    }
    /**
     * Add items to the array containing items per level
     */
    public void addItemLevelOne() {
        itemsLevelOne.add(new Item("socks", SOCKS,1280, 896));
        itemsLevelOne.add(new Item("pants", PANTS, 640, 256));
        itemsLevelOne.add(new Item("apple", APPLE, 384, 512));
    }

    /**
     * Return the array of items
     * @return
     */
    public Array<Item> getItemsLevelZero() {
        return itemsLevelZero;
    }
    public Array<Item> getItemsLevelOne() {
        return itemsLevelOne;
    }

    /**
     * get a specific item from an array
     * @param index the index of the item in the array
     * @return the item selected
     */
    public Item getSpecificItemLevelZero(int index){
        return itemsLevelZero.get(index);
    }
    public Item getSpecificItemLevelOne(int index){
        return itemsLevelOne.get(index);
    }

}
