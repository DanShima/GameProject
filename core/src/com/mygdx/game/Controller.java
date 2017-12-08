
package com.mygdx.game;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;


/**
 * Created by Waseem on 12/7/2017.
 */

    /**
     * This class renders the tile map made with Tiled and shows it on the screen
     * Event handling is done using the observer pattern. InputProcessor, a listener interface, is implemented
     */

    public class Controller implements InputProcessor,Screen,ApplicationListener {
        private int turnCounter=0;
        private GameView interactView;
        private Map interactMap;
        private int animatioPlayerYpos;
        private int animatioPlayerXpos;
        //calculate the game world dimensions

        float oldX , oldY;

        public final static int mapWidth = Constants.tileSize * Constants.tileCountW;
        public final static int mapHeight = Constants.tileSize * Constants.tileCountH;
        private int NumberOfMovedTiles=2;
        public   int tileWidth = 128;
        public   int tileHeight = 128;
      //  public static TiledMap tiledMap;
        //public static OrthographicCamera camera;
      //  public static TiledMapRenderer tiledMapRenderer;
        private TiledMapTileLayer Blockedlayer;
        private TiledMapTileLayer terrain;
        private InputMultiplexer multiplexer;


        private Item underwear,socks,tshirt;
        private Player girl; //animated player


        private GazetiMonster gazeti;
        private YetiMonster yeti;



        private String message;

        private HUD hud ;
        private SpriteBatch sp;

      //  static int currentLevel = 0;

        int oneStepHorizontaly ;
        int twoStepsHorizontally;
        int oneStepVertically ;
        int twoStepsvertically ;

        private TiledMapTileLayer.Cell ground;
        private TiledMapTileLayer.Cell obstacles;

        //int marginTop = 55; //parameterize as: screen height -1 -mapHeight


        int differenceInPositionX; //difference between simplified player position and simplified touch position in X
        int differenceInPositionY;
        int playerPositionY;
        int playerPositionX;

        public Controller(GameView GameView)
        {
            interactView= GameView;
            interactMap= new Map();
            create();
        }

        @Override
        public void create () {

           // float width = Gdx.graphics.getWidth();
           // float height = Gdx.graphics.getHeight();
            sp=new SpriteBatch (  );
            hud = new HUD ( sp );
          /*  screenHeight = Gdx.graphics.getHeight(); //this is here, since it seems it cannot be done at init time    MOVEDDDDD
            marginTop = screenHeight-1-mapHeight; //this depends on screenHeight so it needs to be done after that

            //set up an OrthographicCamera, set it to the dimensions of the screen and update() it.
            camera = new OrthographicCamera();
            camera.setToOrtho(false,width,screenHeight);
            camera.translate ( 128 ,128 );
            camera.update();*/


/////////////////////////////////////////////////////////////////////////////


           /* //load map and create a renderer passing in our tiled map                                                  MOVEDDDDDD
            tiledMap = new TmxMapLoader().load(Constants.levels[currentLevel]);
            tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
            tiledMapRenderer.setView(camera);*/

          interactMap.create();


          getTiledMapRender().setView(interactView.getCamera());

///////////////////////////////////////////////////////////////////////////////
            girl = new Player();
            girl.create();

            SoundEffect.newSoundEffect.create(new AssetManager()); //load audio
            GameSetting.newSetting.load(); //load audio settings
            SoundManager.newSoundManager.play(SoundEffect.newSoundEffect.backgroundMusic.musicSnowMap); //play background music

            //items
            underwear = new Item("underwear", Constants.UNDERWEAR, 256,256);
            socks=new Item("socks", Constants.SOCKS,1280, 896);
            tshirt=new Item("tshirt", Constants.TSHIRT,1280, 384);

            //Monster Gazeti

            gazeti = new GazetiMonster();
            yeti = new YetiMonster();


        }

        public TiledMapRenderer getTiledMapRender(){


          return   interactMap.getTiledMapRenderer();
        }
        // Initial render

        public void initialRender()
        {
            //set the background color to black
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            //pass it in to the TiledMapRenderer with setView() and finally render() the map.
            interactView.getCamera().update();
            interactMap.getTiledMapRenderer().setView(interactView.getCamera());
            interactMap.getTiledMapRenderer().render();

            sp.setProjectionMatrix ( hud.stage.getCamera ().combined);
            hud.stage.draw ();
        }

        //Initial Item Render
        public void initialItemRender()
        {

            girl.render();
            girl.updateSpriteBatch(underwear);
            girl.updateSpriteBatch(tshirt);
            girl.updateSpriteBatch(socks);
            underwear.render();
            socks.render();
            tshirt.render();

            gazeti.render(782, 640); //spawn gazeti at the given position in the map
            yeti.render(256, 352); //spawn yeti at the given position in the map

        }

        //Player collide with Item
        private void playerCollideWithItem(Item item){
            item.setCollected(true);
            initialItemRender();

        }

        @Override
        public void show() {

        }



        @Override
        public void resize(int width, int height) {

        }

        @Override
        public void pause() {

        }

        @Override
        public void resume() {

        }

        @Override
        public void hide() {

        }

        @Override
        public void dispose() {
            //free allocated memory by disposing the instance
            SoundEffect.newSoundEffect.backgroundMusic.musicSnowMap.stop();
        }

        @Override
        public void render(float delta) {
//Calling initial render
            initialRender();
            initialItemRender();
            // convertPlayerPositionToSimplified(); TODO change this hardcoded positionCheck
            //Grab Item

            if(girl.getOldX ()>1152 && girl.getOldX ()<1408  && girl.getOldY()>768&& girl.getOldY()<1024) {
                playerCollideWithItem(socks); //1280, 896
            }

            if(girl.getOldX ()>1216 && girl.getOldX ()<1344  && girl.getOldY()>320&& girl.getOldY()<448) {
                playerCollideWithItem(tshirt);
            }
            if(girl.getOldX ()>192 && girl.getOldX ()<320  && girl.getOldY()>192&& girl.getOldY()<320) {
                playerCollideWithItem(underwear);
            }
            multiplexer = new InputMultiplexer();
            multiplexer.addProcessor(hud.stage);
            multiplexer.addProcessor(this);
            Gdx.input.setInputProcessor(multiplexer);
        }
        @Override
        public void render () {

        }

        @Override
        public boolean keyDown(int keycode) {return false;}
        /**
         * Navigating around the map is simply a matter of moving around the camera. Move in 128px per tile size.
         * move left/right/up/down by one tile each time an arrow key is pressed. A/D/W/S for moving two tiles(no collision tho)
         * @param keycode The key pressed on the keyboard
         * @return true if a key is pressed.
         */
        public boolean keyUp(int keycode) {
            if (keycode == Input.Keys.LEFT){// one step left


                collisionL();

            }
            if (keycode == Input.Keys.A)    {  // 2 steps left
                girl.setCurrentAnimation(girl.getWalkAnimationLEFT());
                girl.move(-twoStepsHorizontally, 0);
            }
            if (keycode == Input.Keys.RIGHT)   {// one step right
                collisionR ();
            }
            if (keycode == Input.Keys.D)  {       // two steps step right
                girl.setCurrentAnimation(girl.getWalkAnimationRIGHT());
                girl.move(twoStepsHorizontally, 0);}
            if (keycode == Input.Keys.UP)    {        // one step up
                collisionU ();            }
            if (keycode == Input.Keys.W)  {          // 2 steps up
                girl.setCurrentAnimation(girl.getWalkAnimationUP());
                girl.move(0, twoStepsvertically); }
            if (keycode == Input.Keys.DOWN)    {     // one step down
                collisionD ();               }
            if (keycode == Input.Keys.S)    {      // 2 steps down
                girl.setCurrentAnimation(girl.getWalkAnimationDOWN());
                girl.move(0, -twoStepsvertically);}
            return false;
        }


        public void debugMe(){
            //Gdx.app.log("movement","ground: " + checkFirstLayer(ground) + " obstacles:" + checkSecondLayer(obstacles) );
            Gdx.app.log("movement","oldX: " + (oldX / tileWidth) + " oldY: " + (oldY / tileHeight) );
        }

        /**
         *check the collision on the left side. if the Properties is blocked the character will stay on the old x, y
         *
         */

        public void collisionL() {
            getProperties();
            girl.resetTimeTillIdle();
            ground = Blockedlayer.getCell((int) (oldX / tileWidth), (int) (oldY / tileHeight) + 1);

            debugMe();
            obstacles = terrain.getCell((int) (oldX / tileWidth), (int) (oldY / tileHeight) + 1);
            if (checkFirstLayer(ground) || checkSecondLayer(obstacles)) {
                girl.move(0, 0);
            } else {
                girl.setCurrentAnimation(girl.getWalkAnimationLEFT());
                girl.move(-oneStepHorizontaly, 0);
            }
        }

        /**
         *  collision for the right side
         */
        public void collisionR(){
            getProperties();
            girl.resetTimeTillIdle();
            ground = Blockedlayer.getCell((int) (oldX / tileWidth)+2 , (int) (oldY / tileHeight)+1);
            obstacles = terrain.getCell((int) (oldX / tileWidth)+2, (int) (oldY / tileHeight) + 1);

            debugMe();
            if (checkFirstLayer(ground) || checkSecondLayer(obstacles) ){
                girl.move ( 0,0 );}
            else
            {girl.setCurrentAnimation(girl.getWalkAnimationRIGHT ());
                girl.move(+oneStepHorizontaly, 0);}
        }


        public void collisionU(){
            getProperties();
            girl.resetTimeTillIdle(); //go back to idle state
            ground = Blockedlayer.getCell((int) (oldX / tileWidth)+1 , (int) (oldY / tileHeight)+2);
            obstacles = terrain.getCell((int) (oldX / tileWidth)+1, (int) (oldY / tileHeight) +2);

            debugMe();
            if(checkFirstLayer(ground) || checkSecondLayer(obstacles) ){
                girl.move ( 0,0 );}
            else
            {girl.setCurrentAnimation(girl.getWalkAnimationUP ());
                girl.move(0, +oneStepVertically);}
        }
        /**
         *  collision for the downward
         */
        public void collisionD(){
            getProperties();
            girl.resetTimeTillIdle();
            ground = Blockedlayer.getCell((int) (oldX / tileWidth)+1 , (int) (oldY / tileHeight));

            debugMe();
            obstacles = terrain.getCell((int) (oldX / tileWidth)+1, (int) (oldY / tileHeight));
            if ( checkFirstLayer(ground) || checkSecondLayer(obstacles) ){
                girl.move ( 0,0 );}
            else {
                girl.setCurrentAnimation(girl.getWalkAnimationDOWN());
                girl.move(0, -oneStepVertically);}
        }

        public boolean collisionCheck(int stepsX, int stepsY){
            getProperties();
            girl.resetTimeTillIdle(); //go back to idle state
            //Gdx.app.log("movement","ground: " + checkFirstLayer(ground) + " obstacles:" + checkSecondLayer(obstacles) );
            debugMe();
            boolean blocked = false;
            int posX = (int) (girl.getOldX () / tileWidth) + 1;
            int posY = (int) (girl.getOldY () / tileHeight) + 1;
            Gdx.app.log("movement","before move: X: " + posX + " Y: " + posY  );
            if(stepsY == 0 ){//horizontal movement
                int directionSign = Integer.signum(stepsX); //-1 for left, otherwise 1
                int limit = Math.abs( stepsX );
                ground = Blockedlayer.getCell(posX + directionSign , posY );
                obstacles = terrain.getCell(posX + directionSign , posY );
                blocked = checkFirstLayer(ground) || checkSecondLayer(obstacles) || blocked;
                if(limit == 2){
                    //Gdx.app.log("movement","horizontal: oldX: " + (posX + directionSign) + " oldY: " +  posY  );
                    ground = Blockedlayer.getCell(posX + directionSign*2 , posY );
                    obstacles = terrain.getCell(posX + directionSign*2 , posY );
                    blocked = checkFirstLayer(ground) || checkSecondLayer(obstacles) || blocked;
                }
            }
            else if(stepsX == 0){//vertical movement
                int directionSign = Integer.signum(stepsY); //-1 for left, otherwise 1
                int limit = Math.abs( stepsY );
                //Gdx.app.log("movement","directionSign: " + directionSign + " limit: " +  limit  );
                ground = Blockedlayer.getCell(posX , posY + directionSign );
                obstacles = terrain.getCell(posX , posY +  directionSign );
                blocked = checkFirstLayer(ground) || checkSecondLayer(obstacles) || blocked;
                if(limit==2){
                    //Gdx.app.log("movement","vertical: oldX: " + posX + " oldY: " +  (posY + directionSign) );
                    ground = Blockedlayer.getCell(posX , posY + directionSign*2 );
                    obstacles = terrain.getCell(posX , posY +  directionSign*2 );
                    blocked = checkFirstLayer(ground) || checkSecondLayer(obstacles) || blocked;
                }
            }
            else{ blocked = true;}
            //Gdx.app.log("movement","horizontal: oldX: " + (posX + directionSign*i) + " oldY: " +  posY  );
            return !blocked;
        }

        /**
         * assign the values of the tiles Properties
         */
        public void getProperties(){
            Blockedlayer = (TiledMapTileLayer)interactMap.getTiledMap().getLayers().get("background");
            terrain = (TiledMapTileLayer)interactMap.getTiledMap().getLayers().get("terrain");

            oldX = girl.getOldX () ;
            oldY = girl.getOldY ();
            tileWidth= (int) Blockedlayer.getTileWidth ();
            tileHeight= (int) Blockedlayer.getTileHeight ();

            oneStepHorizontaly = mapWidth / Constants.tileCountW;
            twoStepsHorizontally = mapWidth / Constants.tileCountW * NumberOfMovedTiles;
            oneStepVertically = mapHeight / Constants.tileCountH;
            twoStepsvertically = mapHeight / Constants.tileCountH * NumberOfMovedTiles;
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
            return false;
        }


        /** This method converts screen Y position to simplified Y
         **/
        public int ScreenPosYtoSimplified(float PositionY){
            float temporary = (PositionY-(float) interactView.getMarginTop())/(float) Constants.tileSize;
            // Gdx.app.log("move","marginTop: " + marginTop + " tilesize: " + tileSize + "result" + temporary  );
            return (int) Math.floor( Math.max(0.0,temporary));
            //return (int) Math.floor( Math.max(0,(PositionY-56)/128.0));
        }


        /**
         * This method converts screen X position to simplified X
         */
        public int ScreenPosXtoSimplified(float PositionX){ //convert screen X position to simplified X
            return (int) Math.floor( Math.max(0,PositionX/(float) Constants.tileSize));
        }

        public int simplifiedXtoScreenPos(int PositionX){ //convert simplified X to screen X position
            return PositionX*tileWidth;
        }

        public int simplifiedYtoScreenPos(int PositionY){ //convert simplified Y to screen Y position
            return PositionY*tileHeight+interactView.getMarginTop()+tileHeight-1;
        }

        public int invertScreenPos(int PositionY){ //convert sprite position to screenPosition which in turn can be used in ScreenPosYtoSimplified()
             int screenHeight=Gdx.graphics.getHeight();
            return screenHeight-interactView.getMarginTop()-PositionY; //probably slightly wrong in the offset (+-1 or something like that), but works to convert sprite position

        }

        public int invertSimplifiedHeight(int simplified){
            return Constants.tileCountH-1-simplified;
        }



        public void checkTurn(){

            if(turnCounter%2 == 0){
                yeti.move(playerPositionX,playerPositionY);
            }else if (turnCounter % 2==1){

                gazeti.move(playerPositionX,playerPositionY);
            }



        }

        /**
         *Called when the user lifts their finger from the screen.
         * We use touchUp instead of touchDown to avoid actions triggered by double clicks
         *
         */
        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {

            int differenceInPositionX; //difference between simplified player position and simplified touch position in X
            int differenceInPositionY; //difference between simplified player position and simplified touch position in Y

            int touchPositionX = ScreenPosXtoSimplified(screenX); //simplified touch position X
            int touchPositionY = ScreenPosYtoSimplified(screenY); //simplified touch position Y
            //Gdx.app.log("move", "Clicked pos X: " + touchPositionX + " Set pos X:" + simplifiedXtoScreenPos(touchPositionX) );
            //Gdx.app.log("move", "screenY: " + screenY + " Simplified pos Y: " + touchPositionY + " Set pos Y:" + simplifiedYtoScreenPos(touchPositionY) );
            convertPlayerPositionToSimplified();
            differenceInPositionX = touchPositionX - playerPositionX;
            differenceInPositionY = playerPositionY - touchPositionY;
            if( differenceInPositionX == 0 && differenceInPositionY==0 ) {
                //probably best to give some kind of feedback. Probably best to draw where the player can go.
            }
            else if( ( Math.abs(differenceInPositionX)<3 && differenceInPositionY==0 ) ) {
                // set the animation for the horizontal movement with clothes
                if(collisionCheck(differenceInPositionX , differenceInPositionY) ) {
                    if (Math.signum((float) differenceInPositionX * tileWidth) == -1) {
                        girl.setCurrentAnimation(girl.getWalkAnimationLEFT());
                        girl.setCurrentAnimationUnderwear(girl.getWalkAnimationLEFTUnderwear());
                        girl.setCurrentAnimationSocks(girl.getWalkAnimationLEFTSocks());
                        girl.setCurrentAnimationShirt(girl.getWalkAnimationLEFTShirt());
                        girl.move(differenceInPositionX * tileWidth, 0);
                        turnCounter++;
                        checkTurn();
                    } else if (Math.signum((int) differenceInPositionX * tileWidth) == 1) {
                        girl.setCurrentAnimation(girl.getWalkAnimationRIGHT());
                        girl.setCurrentAnimationUnderwear(girl.getWalkAnimationRIGHTUnderwear());
                        girl.setCurrentAnimationSocks(girl.getWalkAnimationRIGHTSocks());
                        girl.setCurrentAnimationShirt(girl.getWalkAnimationRIGHTShirt());
                        girl.move(differenceInPositionX * tileWidth, 0);
                        turnCounter++;
                        checkTurn();
                        exitLevel(13, 7);
                    }
                }


            }

            else if( ( Math.abs(differenceInPositionY)<3 && differenceInPositionX==0 ) ) {
                //attempt at vertical movement - may be still blocked by collision, so let's check for that
                if(collisionCheck(differenceInPositionX , differenceInPositionY) ){
                    // set the animation for the vertical movment with clothes
                    if(Math.signum((float)differenceInPositionY)==-1){
                        girl.setCurrentAnimation(girl.getWalkAnimationDOWN());
                        girl.setCurrentAnimationUnderwear(girl.getWalkAnimationDOWNUnderwear());
                        girl.setCurrentAnimationSocks(girl.getWalkAnimationDOWNSocks());
                        girl.setCurrentAnimationShirt(girl.getWalkAnimationDOWNShirt());

                        girl.move(0,differenceInPositionY*tileHeight);
                        turnCounter++;
                        checkTurn();
                    }else if(Math.signum((float)differenceInPositionY)==1) {
                        girl.setCurrentAnimation(girl.getWalkAnimationUP());
                        girl.setCurrentAnimationUnderwear(girl.getWalkAnimationUPUnderwear());
                        girl.setCurrentAnimationSocks(girl.getWalkAnimationUPSocks());
                        girl.setCurrentAnimationShirt(girl.getWalkAnimationUPShirt());
                        girl.move(0,differenceInPositionY*tileHeight);
                        turnCounter++;
                        checkTurn();
                        exitLevel(13 , 7); //if the player moves to tile(13,7), he can go to the next level
                    }
                }
            }

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

        public void convertPlayerPositionToSimplified() {
            //get player positions
            //we need to invert the Y because the sprite is in a different coordinate system
            playerPositionY = invertScreenPos((int) girl.getOldY());
            playerPositionX = (int) girl.getOldX();
            //convert player positions to the simplified version
            playerPositionX = ScreenPosXtoSimplified(playerPositionX);
            playerPositionY = ScreenPosYtoSimplified(playerPositionY);
        }

        public int getPlayerPositionY() {
            return playerPositionY;
        }

        public int getPlayerPositionX() {
            return playerPositionX;
        }

        /**
         * This method checks player position against a position that we specify as the exit (Danning)
         * We want to have the exit as parameter because each level might have its exit in a different place.
         * @param tileX exit tile position in X
         * @param tileY exit tile position in Y
         */
        public void exitLevel(int tileX, int tileY) {
            convertPlayerPositionToSimplified();
            //if player position is the same as the tile position marked as "exit", then call the next level loader method
            if( playerPositionX == tileX && playerPositionY==tileY ) {
                updateLevel();}
        }


        /**
         * Load the next level map
         * @return false after loading once. otherwise it will keep loading for some reason
         */
        public boolean updateLevel(){
            boolean notMovedYet = true;
            if(notMovedYet) {
                Constants.currentLevel++;

                interactMap.setTiledMap(new TmxMapLoader().load(Constants.levels[Constants.currentLevel])) ;
                interactMap.setTiledMapRenderer( new OrthogonalTiledMapRenderer(interactMap.getTiledMap()));

                //getProperties();
                //clear monster from the previous level
                yeti.dispose();
                gazeti.dispose();
                hud.setLevel(Constants.currentLevel);
                //TODO add monsters and items to next level and finalize exit position. change player starting position in the second map
            }
            return false;
        }
    }


