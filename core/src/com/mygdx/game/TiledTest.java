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
    private final int mapWidth = tileSize * tileCountW;
    private final int mapHeight = tileSize * tileCountH;
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
        camera.setToOrtho(false,width,height);
        camera.update();
        //load map and create a renderer passing in our tiled map
        tiledMap = new TmxMapLoader().load("prototype_map_128_15x8_v04.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        Gdx.input.setInputProcessor(this);

        //set up the static player
        sb = new SpriteBatch();
        //link the sprite image to the sprite
        texture = new Texture(Gdx.files.internal("general-single.png"));
        sprite = new Sprite(texture);
        //set the initial starting position of the player
        sprite.setPosition(0,0);

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

    @Override
    public boolean keyUp(int keycode) {

        if(keycode == Input.Keys.LEFT)
            collision();
           // sprite.translate(-128,0);
        if(keycode == Input.Keys.RIGHT)
            sprite.translate(128,0);
        if(keycode == Input.Keys.UP)
            sprite.translate(0,128);
        if(keycode == Input.Keys.DOWN)
            sprite.translate(0,-128);
        if(keycode == Input.Keys.NUM_1)
            tiledMap.getLayers().get(0).setVisible(!tiledMap.getLayers().get(0).isVisible());
        if(keycode == Input.Keys.NUM_2)
            tiledMap.getLayers().get(1).setVisible(!tiledMap.getLayers().get(1).isVisible());
        return false;
    }
    public void collision(){
        TiledMapTileLayer Blockedlayer = (TiledMapTileLayer)tiledMap.getLayers().get("blocked");
        float oldX = sprite.getX () , oldY = sprite.getY ()
                ,tileWidth= Blockedlayer.getTileWidth (), tileHight= Blockedlayer.getTileHeight ();
        boolean CollisoinX = false , CollisionY=false;
        if(
       CollisoinX = Blockedlayer.getCell ( (int)(oldX / tileWidth ), (int) (oldY+ sprite.getHeight() / tileHight) )
        .getTile ().getProperties ().containsKey ( "blocked" ))
        sprite.translate ( 0,0 );
       else sprite.translate ( -128,0 );



        }



    @Override
    public boolean keyTyped(char character) {

        return false;
    }

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
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        return false;
    }

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
