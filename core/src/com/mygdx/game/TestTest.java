package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.GL20;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.graphics.OrthographicCamera;

import java.util.ArrayList;

/**
 * Created by Giddy on 21/11/2017.
 */

public class TestTest extends BaseScreen {
    private int tileSize = 32;
    private int tileCountW = 40;
    private int tileCountH = 40;

    // calculate game world dimensions
    final int mapWidth  = tileSize * tileCountW;
    final int mapHeight = tileSize * tileCountH;

    private TiledMap tiledMap;
    private OrthographicCamera tiledCamera;
    private OrthogonalTiledMapRenderer tiledMapRenderer;
    private int[] backgroundLayers = {0,1};
    private int[] foregroundLayers = {2};

    public TestTest(BaseGame g)
    {
        super(g);
    }

    public void create() {
        // set up tile map, renderer, camera (just as each stage has its own)
        tiledMap = new TmxMapLoader().load("assets/game-map.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        tiledCamera = new OrthographicCamera();
        tiledCamera.setToOrtho(false,viewWidth,viewHeight);
        tiledCamera.update();


    }

    public void update(float dt) {
        // camera adjustment
        Camera mainCamera = mainStage.getCamera();
        // bound camera to layout
        mainCamera.position.x = MathUtils.clamp(
                mainCamera.position.x, viewWidth/2,  mapWidth - viewWidth/2);
        mainCamera.position.y = MathUtils.clamp(
                mainCamera.position.y, viewHeight/2, mapHeight - viewHeight/2);

        mainCamera.update();

        // adjust tilemap camera to stay in sync with main camera
        tiledCamera.position.x = mainCamera.position.x;
        tiledCamera.position.y = mainCamera.position.y;
        tiledCamera.update();
        tiledMapRenderer.setView(tiledCamera);
    }

    // override the render method to interleave tilemap rendering
    public void render(float dt)
    {
        uiStage.act(dt);

        // only pause gameplay events, not UI events
        if ( !isPaused() )
        {
            mainStage.act(dt);
            update(dt);
        }

        // render
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        tiledMapRenderer.render(backgroundLayers);
        mainStage.draw();
        tiledMapRenderer.render(foregroundLayers);
        uiStage.draw();
    }

    public boolean keyDown(int keycode)
    {
        if (keycode == Input.Keys.P)
            togglePaused();

        if (keycode == Input.Keys.R)
            game.setScreen( new GameScreen(game) );

        return false;
    }


}
