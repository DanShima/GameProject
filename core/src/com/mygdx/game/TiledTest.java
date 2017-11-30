package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
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
    public static final  double tileSize = 128; //tile in pixel
    private double tileCountW = 16; //numbers of tiles in width
    private double tileCountH = 8; //numbers of tiles in height

    //calculate the game world dimensions
    float tileWidth;
    float tileHeight;
    double oldX , oldY;
    boolean CollisionX, CollisionY ,CollisionX1;

    private final double mapWidth = tileSize * tileCountW;
    private final double mapHeight = tileSize * tileCountH;
    private int NumberOfMovedTiles=2;

    private TiledMap tiledMap;
    private OrthographicCamera camera;
    private TiledMapRenderer tiledMapRenderer;
    private TiledMapTileLayer Blockedlayer;
    private TiledMapTileLayer terrain;


    private Item underwear;
    private float posX, posY;

    private Player girl; //animated player
    private SpriteBatch batch;


    double oneStepHorizontaly ;
    double twoStepsHorizontally;
    double oneStepVertically ;
    double twoStepsvertically ;
    private double playerposition=0;
    private double Screenposition=0;
    private double xplayerposition=0;
    private double xScreenposition=0;
    private double tempx=1;
    private double tempy=1;
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
        underwear = new Item("underwear.png", 768, 768);

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

    public void toIdle(){

    }
    /**
      *check the collision on the left side. if the Properties is blocked the character will stay on the old x, y
     */
    public void collisionL(){
        GetProperties();
        girl.resetTimeTillIdle();
        if(CollisionX = Blockedlayer.getCell((int) (oldX / tileWidth), (int) (oldY / tileHeight) + 1)
        .getTile().getProperties().containsKey("blocked")) //|| (CollisionX1 = terrain.getCell((int) (oldX / tileWidth), (int) (oldY / tileHeight) + 1)
               // .getTile().getProperties().containsKey("blocked")))

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
        if (CollisionX = Blockedlayer.getCell((int) (oldX / tileWidth)+2 , (int) (oldY / tileHeight)+1)
                        .getTile().getProperties().containsKey("blocked"))
            girl.move ( 0,0 );
        else {girl.setWalkAnimation(girl.getWalkAnimationRIGHT ());
            girl.move(+oneStepHorizontaly, 0);}
    }
    /**
     *  collision for the upward
     */
    public void collisionU(){

        GetProperties();
        girl.resetTimeTillIdle(); //go back to idle state
        if (CollisionY = Blockedlayer.getCell((int) (oldX / tileWidth)+1 , (int) (oldY / tileHeight)+2)
                        .getTile().getProperties().containsKey("blocked"))
            girl.move ( 0,0 );
        else {girl.setWalkAnimation(girl.getWalkAnimationUP ());
            girl.move(0, +oneStepVertically);}

    }
    /**
     *  collision for the downward
     */
    public void collisionD(){
        GetProperties();
        girl.resetTimeTillIdle();
        if (CollisionY = Blockedlayer.getCell((int) (oldX / tileWidth)+1 , (int) (oldY / tileHeight))
                        .getTile().getProperties().containsKey("blocked"))
            girl.move ( 0,0 );
            else {girl.setWalkAnimation(girl.getWalkAnimationDOWN());
            girl.move(0, -oneStepVertically);}
            //girl.setWalkAnimation(girl.getWalkAnimationDOWN ());
    }

    /**
     * assign the values of the tiles Properties
     */
    public void GetProperties(){


         Blockedlayer = (TiledMapTileLayer)tiledMap.getLayers().get("background");
         //terrain = (TiledMapTileLayer)tiledMap.getLayers().get("terrain");


         oldX = girl.getOldX () ;
         oldY = girl.getOldY ();
         tileWidth= Blockedlayer.getTileWidth ();
         tileHeight= Blockedlayer.getTileHeight ();
         CollisionX = false;
         CollisionY = false;
         oneStepHorizontaly = mapWidth / tileCountW;
         twoStepsHorizontally = mapWidth / tileCountW * NumberOfMovedTiles;
         oneStepVertically = mapHeight / tileCountH;
         twoStepsvertically = mapHeight / tileCountH * NumberOfMovedTiles;
    }

    @Override
    public boolean keyTyped(char character) {return false;}



    public void screenPosition(){



    }

    /**
     * Called when the user touches the screen
     *
     * */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
         oneStepHorizontaly = mapWidth / tileCountW;
         twoStepsHorizontally = mapWidth / tileCountW * NumberOfMovedTiles;
         oneStepVertically = mapHeight / tileCountH;
         twoStepsvertically = mapHeight / tileCountH * NumberOfMovedTiles;


         // up movment on touch

        if (Gdx.input.isTouched(pointer) ) {

            double diff = 55;

            if ((screenY>=0                          && (screenY <(mapHeight-diff)/tileCountH))){
                Screenposition=8;
            }else
            if (screenY  >= ((mapHeight-diff)/tileCountH)   && (screenY <((mapHeight-diff)/(tileCountH/2)))) {
                Screenposition=7;
            }else
            if (screenY  >= ((mapHeight-diff)/tileCountH/2) && (screenY <((mapHeight-diff)/(tileCountH/3)))){
                Screenposition=6;
            }else
            if (screenY  >= ((mapHeight-diff)/tileCountH/3) && (screenY <((mapHeight-diff)/(tileCountH/4)))){

                Screenposition=5;
            }else
            if (screenY  >= ((mapHeight-diff)/tileCountH/4) && (screenY <((mapHeight-diff)/(tileCountH/5)))){
                Screenposition=4;
            }else
            if (screenY  >= ((mapHeight-diff)/tileCountH/5) && (screenY <((mapHeight-diff)/(tileCountH/6)))){

                Screenposition=3;
            }else
            if (screenY  >= ((mapHeight-diff)/tileCountH/6) && (screenY <((mapHeight-diff)/(tileCountH/7)))){
                Gdx.app.log("positino 5 ", String.valueOf((mapHeight-diff)/tileCountH/3) +" + "+ (mapHeight-diff)/(tileCountH/4));
                Screenposition=2;
            }else
            {

                Screenposition=1;
            }

            if ((girl.getY())>=0 && (girl.getY()) <mapHeight/tileCountH) playerposition=1;
            if ((girl.getY())>=mapHeight/tileCountH     && ((girl.getY()) <  mapHeight/(tileCountH/2)))    playerposition=2;
            if ((girl.getY())>=mapHeight/(tileCountH/2) && ((girl.getY()) <mapHeight/(tileCountH/3)))    playerposition=3;
            if ((girl.getY())>=mapHeight/(tileCountH/3) && ((girl.getY()) <mapHeight/(tileCountH/4)))   playerposition=4;
            if ((girl.getY())>=mapHeight/(tileCountH/4) && ((girl.getY()) <mapHeight/(tileCountH/5)))   playerposition=5;
            if ((girl.getY())>=mapHeight/(tileCountH/5) && ((girl.getY()) <mapHeight/(tileCountH/6)))   playerposition=6;
            if ((girl.getY())>=mapHeight/(tileCountH/6) && ((girl.getY())<mapHeight/(tileCountH/7)))   playerposition=7;
            if  ((girl.getY())>=mapHeight/(tileCountH/7) && ((girl.getY()) <mapHeight/(tileCountH/8)))   playerposition=8;






            if (Screenposition > playerposition ) {

                Gdx.app.log(">>>>>player position inside   ", String.valueOf(playerposition));
                Gdx.app.log(">>>>>  Temp y ", String.valueOf(tempy));

                Gdx.app.log(">>>>>   x  player position    ", String.valueOf(xplayerposition));
                Gdx.app.log(">>>>>   x temp  position            ", String.valueOf(tempx));


                girl.move(0, oneStepVertically);


            }

        }
             if (Gdx.input.isTouched(pointer) )
             {
            if (Screenposition<playerposition )
                         {
                             Gdx.app.log(">>>>>player position inside   ", String.valueOf(playerposition));
                             Gdx.app.log(">>>>>Screeen positoin  insidde", String.valueOf(Screenposition));

                             Gdx.app.log(">>>>>   x  player position    ", String.valueOf(xplayerposition));
                             Gdx.app.log(">>>>>   y position            ", String.valueOf(xScreenposition));

                             girl.move(0, -oneStepVertically); //move Down



                         }

            }


            // move right
          if(Gdx.input.isTouched(pointer) ){ // move right

              if (screenX>=0   && (screenX <(mapWidth-tileSize)/tileCountW)){
                  xScreenposition=1;
              }else
              if (screenX  >= (mapWidth-tileSize)/tileCountW   && (screenX <((mapWidth-tileSize)/(tileCountW/2)))) {
                  xScreenposition=2;
              }else
              if (screenX  >= (mapWidth-tileSize)/(tileCountW/2) && (screenX <((mapWidth-tileSize)/(tileCountW/3)))){
                  xScreenposition=3;
              }else
              if (screenX  >= (mapWidth-tileSize)/(tileCountW/3) && (screenX <((mapWidth-tileSize)/(tileCountW/4)))){

                  xScreenposition=4;
              }else
              if (screenX  >= (mapWidth-tileSize)/(tileCountW/4) && (screenX <((mapWidth-tileSize)/(tileCountW/5)))){
                  xScreenposition=5;
              }else
              if (screenX  >= (mapWidth-tileSize)/(tileCountW/5) && (screenX <((mapWidth-tileSize)/(tileCountW/6)))){

                  xScreenposition=6;
              }else
              if (screenX  >= (mapWidth-tileSize)/(tileCountW/6) && (screenX <((mapWidth-tileSize)/(tileCountW/7)))){

                  xScreenposition=7;

              } else if (screenX  >= (mapWidth-tileSize)/(tileCountW/7) && (screenX <((mapWidth-tileSize)/(tileCountW/8)))){

                  xScreenposition=8;
              }else if (screenX  >= (mapWidth-tileSize)/(tileCountW/8) && (screenX <((mapWidth-tileSize)/(tileCountW/9)))){

                  xScreenposition=9;
              }else if (screenX  >= (mapWidth-tileSize)/(tileCountW/9) && (screenX <((mapWidth-tileSize)/(tileCountW/10)))){

                  xScreenposition=10;
              } else if (screenX  >= (mapWidth-tileSize)/(tileCountW/10) && (screenX <((mapWidth-tileSize)/(tileCountW/11)))) {

                  xScreenposition =11;

              } else if (screenX  >= (mapWidth-tileSize)/(tileCountW/11) && (screenX <((mapWidth-tileSize)/(tileCountW/12)))){

                  xScreenposition=12;
              } else if (screenX  >= (mapWidth-tileSize)/(tileCountW/12) && (screenX <((mapWidth-tileSize)/(tileCountW/13)))){

                  xScreenposition=13;
              } else if (screenX  >= (mapWidth-tileSize)/(tileCountW/14) && (screenX <((mapWidth-tileSize)/(tileCountW/15)))){

                  xScreenposition=14;

              }else
              {

                  xScreenposition=15;
              }


                double imagecenter = tileSize*0.25;

              if (girl.getX()+imagecenter>=0 && (girl.getX()+imagecenter) <(mapWidth-tileSize)/tileCountW)
                  xplayerposition=1;

              if (girl.getX()+imagecenter>=(mapWidth-tileSize)/tileCountW     && ((girl.getX()+imagecenter)   < (mapWidth-tileSize)/(tileCountW/2)))
                  xplayerposition=2;

              if (girl.getX()+imagecenter>=(mapWidth-tileSize)/(tileCountW/2) && ((girl.getX()+imagecenter)   <(mapWidth-tileSize)/(tileCountW/3)))
                  xplayerposition=3;

              if (girl.getX()+imagecenter>=(mapWidth-tileSize)/(tileCountW/3) && ((girl.getX()+imagecenter)   <(mapWidth-tileSize/(tileCountW/4))))
                  xplayerposition=4;

              if (girl.getX()+imagecenter>=(mapWidth-tileSize)/(tileCountW/4) && ((girl.getX()+imagecenter)   <(mapWidth-tileSize/(tileCountW/5))))
                  xplayerposition=5;

              if (girl.getX()+imagecenter>=(mapWidth-tileSize)/(tileCountW/5) && ((girl.getX()+imagecenter)   <(mapWidth-tileSize/(tileCountW/6))))
                  xplayerposition=6;

              if (girl.getX()+imagecenter>=(mapWidth-tileSize)/(tileCountW/6) && ((girl.getX()+imagecenter)   <(mapWidth-tileSize/(tileCountW/7))) )
                  xplayerposition=7;

              if (girl.getX()+imagecenter>=(mapWidth-tileSize)/(tileCountW/7) && ((girl.getX()+imagecenter)  <(mapWidth-tileSize/(tileCountW/8))))
                  xplayerposition=8;
              if (girl.getX()+imagecenter>=(mapWidth-tileSize)/(tileCountW/8) && ((girl.getX()+imagecenter)   <(mapWidth-tileSize/(tileCountW/9))) )
                  xplayerposition=9;

              if (girl.getX()+imagecenter>=(mapWidth-tileSize)/(tileCountW/9) && ((girl.getX()+imagecenter)   <(mapWidth-tileSize/(tileCountW/10))))
                  xplayerposition=10;

              if (girl.getX()+imagecenter>=(mapWidth-tileSize)/(tileCountW/10) && ((girl.getX()+imagecenter)  <(mapWidth-tileSize/(tileCountW/11))) )
                  xplayerposition=11;

              if (girl.getX()+imagecenter>=(mapWidth-tileSize)/(tileCountW/11) && ((girl.getX()+imagecenter)  <(mapWidth-tileSize/(tileCountW/12))))
                  xplayerposition=12;

              if (girl.getX()+imagecenter>=(mapWidth-tileSize)/(tileCountW/12) && ((girl.getX()+imagecenter)  <(mapWidth-tileSize/(tileCountW/13))))
                  xplayerposition=13;

              if  (girl.getX()+imagecenter>=(mapWidth-tileSize)/(tileCountW/13) && ((girl.getX()+imagecenter) <(mapWidth-tileSize/(tileCountW/14))))
                  xplayerposition=14;

              if (girl.getX()+imagecenter>=(mapWidth-tileSize)/(tileCountW/14) && ((girl.getX()+imagecenter)  <(mapWidth-tileSize/(tileCountW/15))) )
                  xplayerposition=15;
              if  (girl.getX()+imagecenter>=(mapWidth-tileSize)/(tileCountW/15) && ((girl.getX()+imagecenter) <(mapWidth-tileSize/(tileCountW/16))) )
                  xplayerposition=16;
              }



              if ( xScreenposition >= xplayerposition  ){






                  girl.move(oneStepHorizontaly, 0); //move right



                  Gdx.app.log(">>>>>player position inside   ", String.valueOf(playerposition));
                  Gdx.app.log(">>>>>  Temp y ", String.valueOf(tempy));

                  Gdx.app.log(">>>>>   x  player position    ", String.valueOf(xplayerposition));
                  Gdx.app.log(">>>>>   x temp  position            ", String.valueOf(tempx));



                }
              if(Gdx.input.isTouched(pointer) ){
                  if (  xplayerposition -xScreenposition >=0 ){

                      girl.move(-oneStepHorizontaly, 0); //move left



                      Gdx.app.log(">>>>>player position inside   ", String.valueOf(playerposition));
                      Gdx.app.log(">>>>>  Temp y ", String.valueOf(tempy));

                      Gdx.app.log(">>>>>   x  player position    ", String.valueOf(xplayerposition));
                      Gdx.app.log(">>>>>   x temp  position            ", String.valueOf(tempx));
                  }

              }


         else {

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

    public double getTileCountW() {
        return tileCountW;
    }

    public double getTileCountH() {
        return tileCountH;
    }

    public double getMapWidth() {
        return mapWidth;
    }

    public double getMapHeight() {
        return mapHeight;
    }

    public int getNumberOfMovedTiles() {
        return NumberOfMovedTiles;
    }

    public static double getTileSize() {
        return tileSize;
    }
}
