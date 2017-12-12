package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * The Game Over screen shows up after the player finishes the game
 */

    public class GameOverScreen implements Screen {
        private Viewport viewport;
        private Stage stage;
        private Skin skin;
        private HUD hud ;
        private int MaxScore;
        private Texture background;

       // Firebase
       public static firebase fb;
       public  String name;
       PlayerLogin play;

    /**
     * Create the layout of GameOverScreen screen with a table of buttons
     * @param sp spritebatch used to render the screen
     */
    public GameOverScreen(SpriteBatch sp,int MaxScore) {
        this.MaxScore=MaxScore;
        viewport = new StretchViewport(Constants.MAP_WIDTH, Constants.MAP_HEIGHT, new OrthographicCamera());//
        background = new Texture("winter.png");
        hud = new HUD(sp);
        stage = new Stage(viewport, sp);
        skin = new Skin(Gdx.files.internal(Constants.SKIN));

            Table table = new Table();
            table.center();
            table.setFillParent(true);
            Label gameOverLabel = new Label("GAME OVER", skin,"default");
            gameOverLabel.setFontScale(9f,9f);

            TextButton replay = new TextButton("Play again!",skin,"default");
            replay.addListener(new InputListener() {
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    ((Game)Gdx.app.getApplicationListener()).setScreen(new GameView());
                    return true;}});
            replay.setTransform(true);
            replay.setScale(2f);

            TextButton menu = new TextButton("Menu",skin,"default");
            menu.addListener(new InputListener() {
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    ((Game)Gdx.app.getApplicationListener()).setScreen(new MenuScreen());
                    return true;}});
            menu.setTransform(true);
            menu.setScale(2f);

             Label ScoreLabel=new Label("Score: " + MaxScore ,skin,"default");
             ScoreLabel.setFontScale(7f,7f);

             name=play.name;
             fb.setHighScore(MaxScore,name);

            table.center();
            table.add(gameOverLabel).padTop(50f).expandX();
            table.row();
            table.add(ScoreLabel).padTop(40f);
            table.row();
            table.row();
            table.add(replay).padTop(150f).padRight(200f);
            table.row();
            table.add(menu).padTop(150f).padRight(200f);
            stage.addActor(table);
        }

        @Override
        public void show() {}

        @Override
        public void render(float delta) {
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            Gdx.input.setInputProcessor(stage);
            stage.getBatch().begin();
            stage.getBatch().draw(background,0,0,Constants.MAP_WIDTH,Constants.MAP_HEIGHT);
            stage.getBatch().end();
            stage.draw();
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
        public void dispose() {
            stage.dispose();
        }
    }


