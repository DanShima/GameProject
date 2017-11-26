package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

/**
 * This class renders the tile map made with Tiled and shows it on the screen
 * When you run the code you should see your map.  Pressing the arrow keys will scroll around the map ( and show bright red when you’ve moved beyond the extents of your map ) .
 * Pressing 0 or 1 will toggle the visibility of each of the two layers in your map.
 * TODO make the map fixed
 */

public class TiledTest extends ApplicationAdapter implements InputProcessor {
    private int tileSize = 128; //tile in pixel
    private int tileCountW = 15; //numbers of tiles in width
    private int tileCountH = 8; //numbers of tiles in height
    //calculate the game world dimensions
    private final int mapWidth = tileSize * tileCountW ;
    private final int mapHeight = tileSize * tileCountH ;
    MapLayer layer ;



    private Texture img;
    private TiledMap tiledMap;
    private OrthographicCamera camera;
    private TiledMapRenderer tiledMapRenderer;


    private Animator girl; //animated player
    private SpriteBatch sb;
    private Texture texture;
    private Sprite sprite; //static player

    @Override
    public void create () {
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();

        //set up an OrthographicCamera, set it to the dimensions of the screen and update() it.
        camera = new OrthographicCamera();
        //camera.setToOrtho(false,mapWidth,mapHeight);
        //camera.translate ( 128 ,512 );
        //camera.zoom=3/2f;
        camera.setToOrtho(false,width,height);
        camera.translate ( 128 ,128 );
        camera.update();
        //load map and create a renderer passing in our tiled map
        tiledMap = new TmxMapLoader().load("blocking_v1.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        Gdx.input.setInputProcessor(this);


        //set up the static player
        sb = new SpriteBatch();
        //link the sprite image to the sprite
        texture = new Texture(Gdx.files.internal("general-single.png"));
        sprite = new Sprite(texture);
        //set the initial starting position of the player
        sprite.setPosition(512,512);

        //set up the animated player
        girl = new Animator();
        girl.create();

    }

    @Override
    public void render () {
        //set the background color to black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //update the camera (move using arrow keys)
        // pass it in to the TiledMapRenderer with setView() and finally render() the map.
        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        //draw the sprite on the map
        sb.begin();
        sprite.draw(sb);
        sb.end();

        girl.render();

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    /**
     * Navigating around the map is simply a matter of moving around the camera. Move in 128px per tile size.
     * move left/right/up/down by one tile each time an arrow key is pressed
     * Pressing 0 or 1 key toggle on or off a layer. the TileMap layers are accessed using the getLayers().get() function
     * @param keycode
     * @return
     */
    public boolean keyUp(int keycode) {
        if(keycode == Input.Keys.LEFT)
            collisionL();
        // sprite.translate(-128,0);
        if(keycode == Input.Keys.RIGHT)
            collisionR();
        // sprite.translate(128,0);
        if(keycode == Input.Keys.UP)
            collisionU();
        // sprite.translate(0,128);
        if(keycode == Input.Keys.DOWN)
            collisionD();
        // sprite.translate(0,-128);
        if(keycode == Input.Keys.NUM_1)
            tiledMap.getLayers().get(0).setVisible(!tiledMap.getLayers().get(0).isVisible());
        if(keycode == Input.Keys.NUM_2)
            tiledMap.getLayers().get(1).setVisible(!tiledMap.getLayers().get(1).isVisible());
        return false;
    }
    public void collisionL(){
        TiledMapTileLayer Blockedlayer = (TiledMapTileLayer)tiledMap.getLayers().get("Tile Layer 1");
        float oldX = sprite.getX () , oldY = sprite.getY ();
        float tileWidth= Blockedlayer.getTileWidth ();
        float tileHeight= Blockedlayer.getTileHeight ();
        boolean CollisionX = false , CollisionY=false;
        if (
                CollisionX = Blockedlayer.getCell((int) (oldX / tileWidth), (int) (oldY / tileHeight) + 1)
                        .getTile().getProperties().containsKey("blocked"))
            sprite.translate(0, 0);
        else sprite.translate(-128, 0);
    }

    public void collisionR(){
        TiledMapTileLayer Blockedlayer = (TiledMapTileLayer)tiledMap.getLayers().get("Tile Layer 1");
        float oldX = sprite.getX () , oldY = sprite.getY ();
        float tileWidth= Blockedlayer.getTileWidth ();
        float tileHeight= Blockedlayer.getTileHeight ();
        boolean CollisionX = false , CollisionY=false;

        if (
                CollisionX = Blockedlayer.getCell((int) (oldX / tileWidth)+2 , (int) (oldY / tileHeight)+1)
                        .getTile().getProperties().containsKey("blocked"))
            sprite.translate(0, 0);
        else sprite.translate(+128, 0);
    }

    public void collisionU(){

        TiledMapTileLayer Blockedlayer = (TiledMapTileLayer)tiledMap.getLayers().get("Tile Layer 1");
        float oldX = sprite.getX () , oldY = sprite.getY ();
        float tileWidth= Blockedlayer.getTileWidth ();
        float tileHeight= Blockedlayer.getTileHeight ();
        boolean CollisionX = false , CollisionY=false;

        if (
                CollisionX = Blockedlayer.getCell((int) (oldX / tileWidth)+1 , (int) (oldY / tileHeight)+2)
                        .getTile().getProperties().containsKey("blocked"))
            sprite.translate(0, 0);
        else sprite.translate(0, +128);
    }
    public void collisionD(){
        TiledMapTileLayer Blockedlayer = (TiledMapTileLayer)tiledMap.getLayers().get("Tile Layer 1");
        float oldX = sprite.getX () , oldY = sprite.getY ();
        float tileWidth= Blockedlayer.getTileWidth ();
        float tileHeight= Blockedlayer.getTileHeight ();
        boolean CollisionX = false , CollisionY=false;

        if (
                CollisionY = Blockedlayer.getCell((int) (oldX / tileWidth)+1 , (int) (oldY / tileHeight))
                        .getTile().getProperties().containsKey("blocked"))
            sprite.translate(0, 0);
        else sprite.translate(0, -128);

    }

    @Override
    public boolean keyTyped(char character) {return false;}

    /** Called when the user touches the screen
     *
     * */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if (Gdx.input.isTouched(pointer)) {
            sprite.setPosition(tileSize/2,tileSize/2);
            //float positionX = (float) Math.floor(screenX - (screenX % 128));
           // float positionY = 1080 - (float) Math.floor(screenY - (screenY % 128)) - 128;
            sprite.translate(128,0);} //TODO he only moves to the right...
            //sprite.setPosition(positionX, positionY);
        return true;
    }



    /** Called when the user lifts their finger from the screen
    **/
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {return false;}

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    public boolean moveOnTouch(){
    return false;
    }
}
