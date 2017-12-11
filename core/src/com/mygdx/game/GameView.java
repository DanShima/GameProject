package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * The class controls the camera and positions the game on the screen
 */
public class GameView implements Screen {
    private  int width ;
    private  int height ;
    private  OrthographicCamera camera;
    private  final  int mapWidth;
    private  final   int mapHeight;
    private   int marginTop=55;

    private Controller control;

    public GameView() {
         width = Gdx.graphics.getWidth();
         height = Gdx.graphics.getHeight();
         mapWidth = Constants.TILE_SIZE * Constants.TILE_COUNT_WIDTH;
         mapHeight = Constants.TILE_SIZE * Constants.TILE_COUNT_HEIGHT;
         create();
        control= new Controller(this);

    }

    public  OrthographicCamera getCamera() {
        return camera;
    }


    public int getMarginTop() {
        return marginTop;
    }

    /**
     * creates and sets up the camera and positions it to show the map only (excluding collision borders).
     */
    public void create() {
        marginTop = height-1-mapHeight;
        //set up an OrthographicCamera, set it to the dimensions of the screen and update() it.
        camera = new OrthographicCamera();
        camera.setToOrtho(false,width,height);
        camera.translate ( 128 ,128 );
        camera.update();
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        control.render(delta);
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {}
}
