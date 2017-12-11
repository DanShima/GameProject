package com.mygdx.game;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

/**
 * This class contains the map made with Tiled for the game.
 */

public class Map {
    private  TiledMap tiledMap;
    private  TiledMapRenderer tiledMapRenderer;

    /**
     * the tmx file is loaded and rendered into a tiledMap
     */
    public void create() {
       //load map and create a renderer passing in our tiled map
        tiledMap = new TmxMapLoader().load(Constants.LEVELS[Constants.CURRENT_LEVEL]);
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
    }

    public void setTiledMap(TiledMap tiledMap) {
        this.tiledMap = tiledMap;
    }

    public void setTiledMapRenderer(TiledMapRenderer tiledMapRenderer) {
        this.tiledMapRenderer = tiledMapRenderer;
    }

    public TiledMap getTiledMap() {
        return tiledMap;
    }

    public TiledMapRenderer getTiledMapRenderer() {
        return tiledMapRenderer;
    }

    public void dispose(){
        tiledMap.dispose();
    }

}
