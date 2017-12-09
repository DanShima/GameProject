
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
     * This class is the controller for game objects and map
     * Event handling is done using the observer pattern. InputProcessor, a listener interface, is implemented
     */

    public class Controller implements InputProcessor,Screen,ApplicationListener {
    private int turnCounter = 0;
    private GameView interactView;
    private Map interactMap;
    private float oldX, oldY;


    public final static int mapWidth = Constants.tileSize * Constants.tileCountW;
    public final static int mapHeight = Constants.tileSize * Constants.tileCountH;
    private int NumberOfMovedTiles = 2;
    public int tileWidth = 128;
    public int tileHeight = 128;

    private TiledMapTileLayer Blockedlayer;
    private TiledMapTileLayer terrain;
    private InputMultiplexer multiplexer;

    private Player girl; //animated player

    private GazetiMonster gazeti;
    private MushRoomMonster yeti;
    private Monster wasp;
    private Monster golem;

    private String message;

    private HUD hud;
    private SpriteBatch sp;

    //  static int currentLevel = 0;

    private int oneStepHorizontaly;
    private int twoStepsHorizontally;
    private int oneStepVertically;
    private int twoStepsvertically;

    private TiledMapTileLayer.Cell ground;
    private TiledMapTileLayer.Cell obstacles;

    private int playerPositionY;
    private int playerPositionX;

    private GameObjectList gameObjectList;

    public Controller(GameView GameView) {
        interactView = GameView;
        interactMap = new Map();
        create();
    }


    @Override
    public void create() {
        sp = new SpriteBatch();
        hud = new HUD(sp);

        interactMap.create();
        getTiledMapRender().setView(interactView.getCamera());

        girl = new Player();
        girl.create();

        SoundEffect.newSoundEffect.create(new AssetManager()); //load audio
        GameSetting.newSetting.load(); //load audio settings
        SoundManager.newSoundManager.play(SoundEffect.newSoundEffect.backgroundMusic.musicDesertMap); //play background music

        gazeti = new GazetiMonster();
        yeti = new MushRoomMonster();
        wasp = new Monster(Constants.WASP, 4, 3, 1, 1);
        golem = new Monster(Constants.GOLEM, 4, 3, 1, 2);
        gameObjectList = new GameObjectList();

    }



    public TiledMapRenderer getTiledMapRender() {
        return interactMap.getTiledMapRenderer();
    }
    // Initial render

    public void initialRender() {
        //set the background color to black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //pass it in to the TiledMapRenderer with setView() and finally render() the map.
        interactView.getCamera().update();
        interactMap.getTiledMapRenderer().setView(interactView.getCamera());
        interactMap.getTiledMapRenderer().render();

        sp.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    //Initial Item Render
    public void initialItemRender() {
                girl.render();
                girl.updateSpriteBatch(gameObjectList.getItemsLevelZero());
                gameObjectList.renderItems();

                gazeti.render(782, 640); //spawn gazeti at the given position in the map
                yeti.render(256, 352); //spawn yeti at the given position in the map
        if(Constants.currentLevel == 1){
            wasp.render(512, 256);
            golem.render(256, 782); //
        }
    }

        //Player collide with Item
        private void playerCollideWithItem(Item item){
            if(item.isCollected()==false) {
            item.setCollected(true);
            initialItemRender();
            //TODO should add to score, here!

            //plays a sound effect when collecting a cloth item
            SoundManager.newSoundManager.play(SoundEffect.newSoundEffect.sounds.collect);
            }
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
        //free allocated memory
        interactMap.dispose();
        SoundEffect.newSoundEffect.backgroundMusic.musicSnowMap.stop();
        SoundEffect.newSoundEffect.backgroundMusic.musicDesertMap.stop();
        SoundEffect.newSoundEffect.sounds.collect.stop();
    }

    @Override
    public void render(float delta) {
        initialRender();
        initialItemRender();

        multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(hud.stage);
        multiplexer.addProcessor(this);
        Gdx.input.setInputProcessor(multiplexer);
    }

    public void checkCollisionPlayerAndItem() {
        Gdx.app.log("SOUND", "COLLISION QUERY");
        // convertPlayerPositionToSimplified(); TODO change this hardcoded positionCheck
        if (girl.getOldX() > 1152 && girl.getOldX() < 1408 && girl.getOldY() > 768 && girl.getOldY() < 1024) {
            playerCollideWithItem(gameObjectList.getSpecificItem(2)); //1280, 896
        }
        if (girl.getOldX() > 1216 && girl.getOldX() < 1344 && girl.getOldY() > 320 && girl.getOldY() < 448) {
            playerCollideWithItem(gameObjectList.getSpecificItem(1));
        }
        if (girl.getOldX() > 192 && girl.getOldX() < 320 && girl.getOldY() > 192 && girl.getOldY() < 320) {
            playerCollideWithItem(gameObjectList.getSpecificItem(0));
        }
        if (girl.getOldX() > 509 && girl.getOldX() < 765 && girl.getOldY() > 192 && girl.getOldY() < 320) {
            playerCollideWithItem(gameObjectList.getSpecificItem(3)); //637, 1021
        }
        if (girl.getOldX() > 256 && girl.getOldX() < 512 && girl.getOldY() > 384 && girl.getOldY() < 640) {
            playerCollideWithItem(gameObjectList.getSpecificItem(4)); //384, 512
        }
    }

    @Override
    public void render() {
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    /**
     * Navigating around the map is simply a matter of moving around the camera. Move in 128px per tile size.
     * move left/right/up/down by one tile each time an arrow key is pressed. A/D/W/S for moving two tiles(no collision tho)
     *
     * @param keycode The key pressed on the keyboard
     * @return true if a key is pressed.
     */
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.LEFT) {// one step left
            collisionL();
        }
        if (keycode == Input.Keys.A) {  // 2 steps left
            girl.setCurrentAnimation(girl.getWalkAnimationLEFT());
            girl.move(-twoStepsHorizontally, 0);
        }
        if (keycode == Input.Keys.RIGHT) {// one step right
            collisionR();
        }
        if (keycode == Input.Keys.D) {       // two steps step right
            girl.setCurrentAnimation(girl.getWalkAnimationRIGHT());
            girl.move(twoStepsHorizontally, 0);
        }
        if (keycode == Input.Keys.UP) {        // one step up
            collisionU();
        }
        if (keycode == Input.Keys.W) {          // 2 steps up
            girl.setCurrentAnimation(girl.getWalkAnimationUP());
            girl.move(0, twoStepsvertically);
        }
        if (keycode == Input.Keys.DOWN) {     // one step down
            collisionD();
        }
        if (keycode == Input.Keys.S) {      // 2 steps down
            girl.setCurrentAnimation(girl.getWalkAnimationDOWN());
            girl.move(0, -twoStepsvertically);
        }
        return false;
    }


    public void debugMe() {
        //Gdx.app.log("movement","ground: " + checkFirstLayer(ground) + " obstacles:" + checkSecondLayer(obstacles) );
        // Gdx.app.log("movement", "oldX: " + (oldX / tileWidth) + " oldY: " + (oldY / tileHeight));
    }

    /**
     * check the collision on the left side. if the Properties is blocked the character will stay on the old x, y
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
     * collision for the right side
     */
    public void collisionR() {
        getProperties();
        girl.resetTimeTillIdle();
        ground = Blockedlayer.getCell((int) (oldX / tileWidth) + 2, (int) (oldY / tileHeight) + 1);
        obstacles = terrain.getCell((int) (oldX / tileWidth) + 2, (int) (oldY / tileHeight) + 1);

        debugMe();
        if (checkFirstLayer(ground) || checkSecondLayer(obstacles)) {
            girl.move(0, 0);
        } else {
            girl.setCurrentAnimation(girl.getWalkAnimationRIGHT());
            girl.move(+oneStepHorizontaly, 0);
        }
    }


    public void collisionU() {
        getProperties();
        girl.resetTimeTillIdle(); //go back to idle state
        ground = Blockedlayer.getCell((int) (oldX / tileWidth) + 1, (int) (oldY / tileHeight) + 2);
        obstacles = terrain.getCell((int) (oldX / tileWidth) + 1, (int) (oldY / tileHeight) + 2);

        debugMe();
        if (checkFirstLayer(ground) || checkSecondLayer(obstacles)) {
            girl.move(0, 0);
        } else {
            girl.setCurrentAnimation(girl.getWalkAnimationUP());
            girl.move(0, +oneStepVertically);
        }
    }

    /**
     * collision for the downward
     */
    public void collisionD() {
        getProperties();
        girl.resetTimeTillIdle();
        ground = Blockedlayer.getCell((int) (oldX / tileWidth) + 1, (int) (oldY / tileHeight));

        debugMe();
        obstacles = terrain.getCell((int) (oldX / tileWidth) + 1, (int) (oldY / tileHeight));
        if (checkFirstLayer(ground) || checkSecondLayer(obstacles)) {
            girl.move(0, 0);
        } else {
            girl.setCurrentAnimation(girl.getWalkAnimationDOWN());
            girl.move(0, -oneStepVertically);
        }
    }

    public boolean collisionCheck(int stepsX, int stepsY) {
        getProperties();
        girl.resetTimeTillIdle(); //go back to idle state
        //Gdx.app.log("movement","ground: " + checkFirstLayer(ground) + " obstacles:" + checkSecondLayer(obstacles) );
        debugMe();
        boolean blocked = false;
        int posX = (int) (girl.getOldX() / tileWidth) + 1;
        int posY = (int) (girl.getOldY() / tileHeight) + 1;
        Gdx.app.log("movement", "before move: X: " + posX + " Y: " + posY);
        if (stepsY == 0) {//horizontal movement
            int directionSign = Integer.signum(stepsX); //-1 for left, otherwise 1
            int limit = Math.abs(stepsX);
            ground = Blockedlayer.getCell(posX + directionSign, posY);
            obstacles = terrain.getCell(posX + directionSign, posY);
            blocked = checkFirstLayer(ground) || checkSecondLayer(obstacles) || blocked;
            if (limit == 2) {
                //Gdx.app.log("movement","horizontal: oldX: " + (posX + directionSign) + " oldY: " +  posY  );
                ground = Blockedlayer.getCell(posX + directionSign * 2, posY);
                obstacles = terrain.getCell(posX + directionSign * 2, posY);
                blocked = checkFirstLayer(ground) || checkSecondLayer(obstacles) || blocked;
            }
        } else if (stepsX == 0) {//vertical movement
            int directionSign = Integer.signum(stepsY); //-1 for left, otherwise 1
            int limit = Math.abs(stepsY);
            //Gdx.app.log("movement","directionSign: " + directionSign + " limit: " +  limit  );
            ground = Blockedlayer.getCell(posX, posY + directionSign);
            obstacles = terrain.getCell(posX, posY + directionSign);
            blocked = checkFirstLayer(ground) || checkSecondLayer(obstacles) || blocked;
            if (limit == 2) {
                //Gdx.app.log("movement","vertical: oldX: " + posX + " oldY: " +  (posY + directionSign) );
                ground = Blockedlayer.getCell(posX, posY + directionSign * 2);
                obstacles = terrain.getCell(posX, posY + directionSign * 2);
                blocked = checkFirstLayer(ground) || checkSecondLayer(obstacles) || blocked;
            }
        } else {
            blocked = true;
        }
        //Gdx.app.log("movement","horizontal: oldX: " + (posX + directionSign*i) + " oldY: " +  posY  );
        return !blocked;
    }

    /**
     * assign the values of the tiles Properties
     */
    public void getProperties() {
        Blockedlayer = (TiledMapTileLayer) interactMap.getTiledMap().getLayers().get("background");
        terrain = (TiledMapTileLayer) interactMap.getTiledMap().getLayers().get("terrain");

        oldX = girl.getOldX();
        oldY = girl.getOldY();
        tileWidth = (int) Blockedlayer.getTileWidth();
        tileHeight = (int) Blockedlayer.getTileHeight();

        oneStepHorizontaly = mapWidth / Constants.tileCountW;
        twoStepsHorizontally = mapWidth / Constants.tileCountW * NumberOfMovedTiles;
        oneStepVertically = mapHeight / Constants.tileCountH;
        twoStepsvertically = mapHeight / Constants.tileCountH * NumberOfMovedTiles;
    }

    /**
     * This method checks whether a tile for the second layer contains the property "blocked"
     */
    public boolean checkSecondLayer(TiledMapTileLayer.Cell obstacle) {
        if (obstacle != null) { //if it is not an empty cell
            return obstacle.getTile().getProperties().containsKey("blocked");
        }
        return false;     //else do nothing
    }

    /**
     * This method checks whether a tile from the first layer contains the property "blocked"
     *
     * @param ground the first tile layer
     * @return false if it is an empty cell
     */

    public boolean checkFirstLayer(TiledMapTileLayer.Cell ground) {
        if (ground != null) { //if it is not an empty cell
            return ground.getTile().getProperties().containsKey("blocked");
        }
        return false;     //else do nothing
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    /**
     * Called when the user touches the screen
     */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    /**
     * This method converts screen Y position to simplified Y
     **/
    public int ScreenPosYtoSimplified(float PositionY) {
        float temporary = (PositionY - (float) interactView.getMarginTop()) / (float) Constants.tileSize;
        // Gdx.app.log("move","marginTop: " + marginTop + " tilesize: " + tileSize + "result" + temporary  );
        return (int) Math.floor(Math.max(0.0, temporary));
        //return (int) Math.floor( Math.max(0,(PositionY-56)/128.0));
    }

    /**
     * This method converts screen X position to simplified X
     */
    public int ScreenPosXtoSimplified(float PositionX) { //convert screen X position to simplified X
        return (int) Math.floor(Math.max(0, PositionX / (float) Constants.tileSize));
    }

    public int simplifiedXtoScreenPos(int PositionX) { //convert simplified X to screen X position
        return PositionX * tileWidth;
    }

    public int simplifiedYtoScreenPos(int PositionY) { //convert simplified Y to screen Y position
        return PositionY * tileHeight + interactView.getMarginTop() + tileHeight - 1;
    }

    public int invertScreenPos(int PositionY) { //convert sprite position to screenPosition which in turn can be used in ScreenPosYtoSimplified()
        int screenHeight = Gdx.graphics.getHeight();
        return screenHeight - interactView.getMarginTop() - PositionY; //probably slightly wrong in the offset (+-1 or something like that), but works to convert sprite position
    }

    public int invertSimplifiedHeight(int simplified) {
        return Constants.tileCountH - 1 - simplified;
    }


    /**
     * Called when the user lifts their finger from the screen.
     * We use touchUp instead of touchDown to avoid actions triggered by double clicks
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
        if (differenceInPositionX == 0 && differenceInPositionY == 0) {
            //probably best to give some kind of feedback. Probably best to draw where the player can go.
        } else if ((Math.abs(differenceInPositionX) < 3 && differenceInPositionY == 0)) {
            // set the animation for the horizontal movement with clothes
            if (collisionCheck(differenceInPositionX, differenceInPositionY)) {
                if (Math.signum((float) differenceInPositionX * tileWidth) == -1) {
                    girl.setCurrentAnimation(girl.getWalkAnimationLEFT());
                    girl.setCurrentAnimationUnderwear(girl.getWalkAnimationLEFTUnderwear());
                    girl.setCurrentAnimationSocks(girl.getWalkAnimationLEFTSocks());
                    girl.setCurrentAnimationShirt(girl.getWalkAnimationLEFTShirt());
                    girl.setCurrentAnimationPants(girl.getWalkAnimationLEFTPants());
                    girl.move(differenceInPositionX * tileWidth, 0);
                    checkCollisionPlayerAndItem();
                    turnCounter++;
                    checkTurn();

                } else if (Math.signum((int) differenceInPositionX * tileWidth) == 1) {
                    girl.setCurrentAnimation(girl.getWalkAnimationRIGHT());
                    girl.setCurrentAnimationUnderwear(girl.getWalkAnimationRIGHTUnderwear());
                    girl.setCurrentAnimationSocks(girl.getWalkAnimationRIGHTSocks());
                    girl.setCurrentAnimationShirt(girl.getWalkAnimationRIGHTShirt());
                    girl.setCurrentAnimationPants(girl.getWalkAnimationRIGHTPants());
                    girl.move(differenceInPositionX * tileWidth, 0);
                    checkCollisionPlayerAndItem();
                    turnCounter++;
                    checkTurn();
                }
            }
        }
        else if( ( Math.abs(differenceInPositionY)<3 && differenceInPositionX==0 ) ) {
            //attempt at vertical movement - may be still blocked by collision, so let's check for that
            if (collisionCheck(differenceInPositionX, differenceInPositionY)) {
                // set the animation for the vertical movment with clothes
                if (Math.signum((float) differenceInPositionY) == -1) {
                    girl.setCurrentAnimation(girl.getWalkAnimationDOWN());
                    girl.setCurrentAnimationUnderwear(girl.getWalkAnimationDOWNUnderwear());
                    girl.setCurrentAnimationSocks(girl.getWalkAnimationDOWNSocks());
                    girl.setCurrentAnimationShirt(girl.getWalkAnimationDOWNShirt());
                    girl.setCurrentAnimationPants(girl.getWalkAnimationDOWNPants());
                    girl.move(0, differenceInPositionY * tileHeight);
                    checkCollisionPlayerAndItem();
                    turnCounter++;
                    checkTurn();
                } else if (Math.signum((float) differenceInPositionY) == 1) {
                    girl.setCurrentAnimation(girl.getWalkAnimationUP());
                    girl.setCurrentAnimationUnderwear(girl.getWalkAnimationUPUnderwear());
                    girl.setCurrentAnimationSocks(girl.getWalkAnimationUPSocks());
                    girl.setCurrentAnimationShirt(girl.getWalkAnimationUPShirt());
                    girl.setCurrentAnimationPants(girl.getWalkAnimationUPPants());
                    girl.move(0, differenceInPositionY * tileHeight);
                    checkCollisionPlayerAndItem();
                    turnCounter++;
                    checkTurn();
                }
            }
        }


        if(Constants.currentLevel == 0) {
            exitLevel(4, 1);
            exitLevel(3, 1);
        }
        if(Constants.currentLevel == 1) {
            exitLevel(13, 7);}
        return false;
    }

    /**
     * Called when a finger or the mouse was dragged.
     *
     * @param screenX
     * @param screenY
     * @param pointer the pointer for the event.  @return whether the input was processed
     */
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    /**
     * Called when the mouse was moved without any buttons being pressed. Will not be called on iOS.
     *
     * @param screenX
     * @param screenY
     * @return whether the input was processed
     */
    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    /**
     * Called when the mouse wheel was scrolled. Will not be called on iOS.
     *
     * @param amount the scroll amount, -1 or 1 depending on the direction the wheel was scrolled.
     * @return whether the input was processed.
     */
    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    public void convertPlayerPositionToSimplified () {
                    //get player positions
                    //we need to invert the Y because the sprite is in a different coordinate system
                    playerPositionY = invertScreenPos((int) girl.getOldY());
                    playerPositionX = (int) girl.getOldX();
                    //convert player positions to the simplified version
                    playerPositionX = ScreenPosXtoSimplified(playerPositionX);
                    playerPositionY = ScreenPosYtoSimplified(playerPositionY);
                }

                public int getPlayerPositionY () {
                    return playerPositionY;
                }

                public int getPlayerPositionX () {
                    return playerPositionX;
                }

                /**
                 * This method checks player position against a position that we specify as the exit (Danning)
                 * We want to have the exit as parameter because each level might have its exit in a different place.
                 *
                 * @param tileX exit tile position in X
                 * @param tileY exit tile position in Y
                 */
                public void exitLevel ( int tileX, int tileY){
                    convertPlayerPositionToSimplified();
                    //if player position is the same as the tile position marked as "exit", then call the next level loader method
                    if (playerPositionX == tileX && playerPositionY == tileY) {
                        updateLevel();
                    }
                }

                public void checkTurn () {

                    if (turnCounter % 2 == 0) {
                        yeti.move(playerPositionX, playerPositionY);


                        if (yeti.getSimpleMonsterX() == playerPositionX - 1 && yeti.getSimpleMonsterY() == yeti.getSimplePlayerInvertedy()) {
                            hitByMonster();

                        }
                    } else if (turnCounter % 2 == 1) {
                        Gdx.app.log("GGGGGG  xxx  " + yeti.getSimpleMonsterX(), "YYYYYY" + (yeti.getSimpleMonsterY() + 1));

                        Gdx.app.log("PLAYER XXX " + (playerPositionX - 1), "PLAYER   YYYYYY" + yeti.getSimplePlayerInvertedy());
                        gazeti.move(playerPositionX, playerPositionY);
                        if (gazeti.getSimpleMonsterX() == playerPositionX - 1 && gazeti.getSimpleMonsterY() == gazeti.getSimplePlayerInvertedy()) {
                            hitByMonster();
                        }
                    }

                }

                public void hitByMonster () {
                    float halfHelth = 50;
                    float noHealth = 0;

                    if (hud.getHealth() >= noHealth && hud.getHealth() >= halfHelth) {

                        hud.setHealth(hud.getHealth() * 0.75f);
                    } else if (hud.getHealth() < halfHelth && hud.getHealth() > noHealth)

                        hud.setHealth(noHealth);
                }


         /** Load the next level map
         * @return false after loading once. otherwise it will keep loading for some reason
         */
                public boolean updateLevel() {
                    boolean notMovedYet = true;
                    if (notMovedYet) {
                        Constants.currentLevel++;

                        interactMap.setTiledMap(new TmxMapLoader().load(Constants.LEVELS[Constants.currentLevel]));
                        interactMap.setTiledMapRenderer(new OrthogonalTiledMapRenderer(interactMap.getTiledMap()));

                        //getProperties();
                        //clear monster from the previous level
                        yeti.dispose();
                        gazeti.dispose();
                        hud.setLevel(Constants.currentLevel);
                        //change background music
                        SoundManager.newSoundManager.play(SoundEffect.newSoundEffect.backgroundMusic.musicSnowMap);
                        //TODO add monsters and itemsLevelZero to next level and finalize exit position. change player starting position in the second map
                    }
                    return false;
                }



}

