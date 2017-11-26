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
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

/**
 * This class renders the tile map made with Tiled and shows it on the screen
 * When you run the code you should see your map.  Pressing the arrow keys will scroll around the map ( and show bright red when you’ve moved beyond the extents of your map ) .
 * Pressing 0 or 1 will toggle the visibility of each of the two layers in your map.
 * Event handling is done using the observer pattern. InputProcessor, a listener interface, is implemented
 */

public class TiledTest extends ApplicationAdapter implements InputProcessor {
    public static final  int tileSize = 128; //tile in pixel
    private int tileCountW = 15; //numbers of tiles in width
    private int tileCountH = 8; //numbers of tiles in height

    //calculate the game world dimensions
    private final int mapWidth = tileSize * tileCountW;
    private final int mapHeight = tileSize * tileCountH;
    private static final int PNGwidth=42;
    private static final int PNGheight= 48;
    private int NumberOfMovedTiles=2;

    private TiledMap tiledMap;
    private OrthographicCamera camera;
    private TiledMapRenderer tiledMapRenderer;

    private Item underwear;
    private float posX, posY;

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
        tiledMapRenderer.setView(camera);
        Gdx.input.setInputProcessor(this);

        //set up the static player
        //TODO static player can be deleted once the animated player is working perfectly
        sb = new SpriteBatch();
        //link the sprite image to the sprite
        texture = new Texture(Gdx.files.internal("general-single.png"));
        sprite = new Sprite(texture);
        //set the initial starting position of the player
        //set the player in the middle of the tile
        sprite.setPosition((float) ((tileSize*0.5)-(PNGwidth*0.5)), (float) ((tileSize*0.5)-(PNGheight*0.5)));



        underwear = new Item();
        //posX = width/2 - underwear.getPositionX()/2;
       // posY = height/2 - underwear.getPositionY()/2;
        underwear.create("underwear.png", 768, 768);


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
       underwear.render();
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
    // testing
    @Override
    public boolean keyUp(int keycode) {
        int oneStepHorizontaly = mapWidth / tileCountW;
        int twoStepsHorizontally = mapWidth / tileCountW * NumberOfMovedTiles;
        int oneStepVertically = mapHeight / tileCountH;
        int twoStepsvertically = mapHeight / tileCountH * NumberOfMovedTiles;
            if (keycode == Input.Keys.LEFT){    // one step left
                girl.setWalkAnimation(girl.getWalkAnimationLEFT());
                girl.move(-oneStepHorizontaly, 0);}
            if (keycode == Input.Keys.A)    {   // 2 steps left
                girl.setWalkAnimation(girl.getWalkAnimationLEFT());
                girl.move(-twoStepsHorizontally, 0);}
            if (keycode == Input.Keys.RIGHT)   {      // one step right
                girl.setWalkAnimation(girl.getWalkAnimationRIGHT());
                girl.move(oneStepHorizontaly, 0);}
            if (keycode == Input.Keys.D)  {       // two steps step right
                girl.setWalkAnimation(girl.getWalkAnimationRIGHT());
                girl.move(twoStepsHorizontally, 0);}

            if (keycode == Input.Keys.UP)    {        // one step up
                girl.setWalkAnimation(girl.getWalkAnimationUP());
                girl.move(0, oneStepVertically);}

            if (keycode == Input.Keys.W)  {          // 2 steps up
                girl.setWalkAnimation(girl.getWalkAnimationUP());
                girl.move(0, twoStepsvertically); }

            if (keycode == Input.Keys.DOWN)    {     // one step down
                girl.setWalkAnimation(girl.getWalkAnimationDOWN());
                girl.move(0, -oneStepVertically); }

            if (keycode == Input.Keys.S)    {      // 2 steps down
                girl.setWalkAnimation(girl.getWalkAnimationDOWN());
                girl.move(0, -twoStepsvertically);}

            if (keycode == Input.Keys.NUM_1)
                tiledMap.getLayers().get(0).setVisible(!tiledMap.getLayers().get(0).isVisible());
            if (keycode == Input.Keys.NUM_2)
                tiledMap.getLayers().get(1).setVisible(!tiledMap.getLayers().get(1).isVisible());

            return false;
    }

    @Override
    public boolean keyTyped(char character) {return false;}

    /**
     * Called when the user touches the screen
     *
     * */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        int oneStepHorizontaly = mapWidth / tileCountW;
        int twoStepsHorizontally = mapWidth / tileCountW * NumberOfMovedTiles;
        int oneStepVertically = mapHeight / tileCountH;
        int twoStepsvertically = mapHeight / tileCountH * NumberOfMovedTiles;
        //TODO complete this
        if (Gdx.input.isTouched(pointer) && screenX>girl.getOldX()+200) {
            girl.move(oneStepHorizontaly, 0); //move right
        } else if (Gdx.input.isTouched(pointer) && screenX<girl.getOldX()-200) {
            girl.move(-oneStepHorizontaly, 0); //move left
        } else if(Gdx.input.isTouched(pointer) && screenY>girl.getOldY()){
            girl.move(0, oneStepVertically); //move up
        } else if(Gdx.input.isTouched(pointer) ){//&& screenY<girl.getOldY()
            girl.move(0, -oneStepVertically); //move down
        } else {
            return true;
        }
        return false;
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

    public int getTileCountW() {
        return tileCountW;
    }

    public int getTileCountH() {
        return tileCountH;
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public int getNumberOfMovedTiles() {
        return NumberOfMovedTiles;
    }

    public static int getTileSize() {
        return tileSize;
    }
}
