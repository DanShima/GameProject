package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import static com.mygdx.game.Constants.LEVEL_TWO;

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
    float tileWidth;
    float tileHeight;
    float oldX , oldY;

    private final int mapWidth = tileSize * tileCountW;
    private final int mapHeight = tileSize * tileCountH;
    private int NumberOfMovedTiles=2;

    private TiledMap tiledMap;
    private OrthographicCamera camera;
    private TiledMapRenderer tiledMapRenderer;
    private TiledMapTileLayer Blockedlayer;
    private TiledMapTileLayer terrain;


    private Item underwear;
    private Player girl; //animated player
    private Monster yeti;


    int oneStepHorizontaly ;
    int twoStepsHorizontally;
    int oneStepVertically ;
    int twoStepsvertically ;

    private TiledMapTileLayer.Cell ground;
    private TiledMapTileLayer.Cell obstacles;


    @Override
    public void create () {
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();

        //set up an OrthographicCamera, set it to the dimensions of the screen and update() it.
        camera = new OrthographicCamera();
        camera.setToOrtho(false,width,height);
        camera.translate ( 128 ,128 );
        camera.update();
        //load map and create a renderer passing in our tiled map

        tiledMap = new TmxMapLoader().load(LEVEL_TWO);
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        tiledMapRenderer.setView(camera);
        Gdx.input.setInputProcessor(this);

        girl = new Player();
        girl.create();

        underwear = new Item("socks.png", 768, 768);
        //underwear.checkCollision();
        SoundEffect.instance.create(new AssetManager()); //load audio
        GameSetting.instance.load(); //load audio settings
        SoundManager.instance.play(SoundEffect.instance.backgroundMusic.backgroundMusic1); //play background music
        //SoundEffect music = Gdx.audio.newMusic(Gdx.files.internal("backgroundmusic.mp3"));

       //yeti = new Monster();
       yeti = new Monster("gazeti_3.png", 4, 3, 1);


    }

    @Override
    public void render () {
        //set the background color to black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // pass it in to the TiledMapRenderer with setView() and finally render() the map.
        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        girl.render();
        underwear.render();
        yeti.render();

    }

    @Override
    public boolean keyDown(int keycode) {return false;}

    /**
     * Navigating around the map is simply a matter of moving around the camera. Move in 128px per tile size.
     * move left/right/up/down by one tile each time an arrow key is pressed
     * Pressing 0 or 1 key toggle on or off a layer. the TileMap layers are accessed using the getLayers().get() function
     * @param keycode
     * @return
     */
  

     public boolean keyUp(int keycode) {

            if (keycode == Input.Keys.LEFT){// one step left
                collisionL();
               // girl.setWalkAnimation(girl.getWalkAnimationLEFT());
                //girl.move(-oneStepHorizontaly, 0);
                }
            if (keycode == Input.Keys.A)    {   // 2 steps left

                girl.setWalkAnimation(girl.getWalkAnimationLEFT());
                girl.move(-twoStepsHorizontally, 0);
                }
            if (keycode == Input.Keys.RIGHT)   {// one step right
                collisionR ();
                //girl.setWalkAnimation(girl.getWalkAnimationRIGHT());
                //girl.move(oneStepHorizontaly, 0);
            }
            if (keycode == Input.Keys.D)  {       // two steps step right
                girl.setWalkAnimation(girl.getWalkAnimationRIGHT());
                girl.move(twoStepsHorizontally, 0);}

            if (keycode == Input.Keys.UP)    {        // one step up
                collisionU ();
                //girl.setWalkAnimation(girl.getWalkAnimationUP());
                //girl.move(0, oneStepVertically);
            }

            if (keycode == Input.Keys.W)  {          // 2 steps up
                girl.setWalkAnimation(girl.getWalkAnimationUP());
                girl.move(0, twoStepsvertically); }

            if (keycode == Input.Keys.DOWN)    {     // one step down
                collisionD ();
               // girl.setWalkAnimation(girl.getWalkAnimationDOWN());
                //girl.move(0, -oneStepVertically);
                //
                }

            if (keycode == Input.Keys.S)    {      // 2 steps down
                girl.setWalkAnimation(girl.getWalkAnimationDOWN());
                girl.move(0, -twoStepsvertically);}

            if (keycode == Input.Keys.NUM_1)
                tiledMap.getLayers().get(0).setVisible(!tiledMap.getLayers().get(0).isVisible());
            if (keycode == Input.Keys.NUM_2)
                tiledMap.getLayers().get(1).setVisible(!tiledMap.getLayers().get(1).isVisible());

            return false;
    }


    /**
      *check the collision on the left side. if the Properties is blocked the character will stay on the old x, y
     */
    public void collisionL(){
        GetProperties();
        girl.resetTimeTillIdle();
        ground = Blockedlayer.getCell((int) (oldX / tileWidth), (int) (oldY / tileHeight) + 1);
        obstacles = terrain.getCell((int) (oldX / tileWidth), (int) (oldY / tileHeight) + 1);
        if((checkFirstLayer(ground))||checkSecondLayer(obstacles))
                         girl.move ( 0,0 );
                             else {girl.setWalkAnimation(girl.getWalkAnimationLEFT());
                                    girl.move(-oneStepHorizontaly, 0);}
    }


    /**
     *  collision for the right side
     */
    public void collisionR(){
        GetProperties();
        girl.resetTimeTillIdle();
        ground = Blockedlayer.getCell((int) (oldX / tileWidth)+2 , (int) (oldY / tileHeight)+1);
        obstacles = terrain.getCell((int) (oldX / tileWidth)+2, (int) (oldY / tileHeight) + 1);
        if ((checkFirstLayer(ground))||checkSecondLayer(obstacles))
            girl.move ( 0,0 );
        else {girl.setWalkAnimation(girl.getWalkAnimationRIGHT ());
            girl.move(+oneStepHorizontaly, 0);}
    }


    public void collisionU(){
        GetProperties();
        girl.resetTimeTillIdle(); //go back to idle state
        ground = Blockedlayer.getCell((int) (oldX / tileWidth)+1 , (int) (oldY / tileHeight)+2);
        obstacles = terrain.getCell((int) (oldX / tileWidth)+1, (int) (oldY / tileHeight) +2);
        if((checkFirstLayer(ground))||checkSecondLayer(obstacles)){
            girl.move ( 0,0 );}
        else {girl.setWalkAnimation(girl.getWalkAnimationUP ());
            girl.move(0, +oneStepVertically);}
    }
    /**
     *  collision for the downward
     */
    public void collisionD(){
        GetProperties();
        girl.resetTimeTillIdle();
        ground = Blockedlayer.getCell((int) (oldX / tileWidth)+1 , (int) (oldY / tileHeight));
        obstacles = terrain.getCell((int) (oldX / tileWidth)+1, (int) (oldY / tileHeight));
        if ((checkFirstLayer(ground))||checkSecondLayer(obstacles))
            girl.move ( 0,0 );
            else {girl.setWalkAnimation(girl.getWalkAnimationDOWN());
            girl.move(0, -oneStepVertically);}
    }

    /**
     * assign the values of the tiles Properties
     */
    public void GetProperties(){

         Blockedlayer = (TiledMapTileLayer)tiledMap.getLayers().get("background");
         terrain = (TiledMapTileLayer)tiledMap.getLayers().get("terrain");

         oldX = girl.getOldX () ;
         oldY = girl.getOldY ();
         tileWidth= Blockedlayer.getTileWidth ();
         tileHeight= Blockedlayer.getTileHeight ();

         oneStepHorizontaly = mapWidth / tileCountW;
         twoStepsHorizontally = mapWidth / tileCountW * NumberOfMovedTiles;
         oneStepVertically = mapHeight / tileCountH;
         twoStepsvertically = mapHeight / tileCountH * NumberOfMovedTiles;
    }

    /**
     *  This method checks whether a tile for the second layer contains the property "blocked"
     */
    public boolean checkSecondLayer(TiledMapTileLayer.Cell obstacle){
        if(obstacle != null) { //if it is not an empty cell
            return obstacle.getTile().getProperties().containsKey("blocked");}
        return false;     //else do nothing
    }

    /**
     * This method checks whether a tile from the first layer contains the property "blocked"
     * @param ground the first tile layer
     * @return false if it is an empty cell
     */

    public boolean checkFirstLayer(TiledMapTileLayer.Cell ground){
        if(ground != null) { //if it is not an empty cell
            return ground.getTile().getProperties().containsKey("blocked");}
        return false;     //else do nothing
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
        Gdx.app.log("move", "." + screenY);
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
