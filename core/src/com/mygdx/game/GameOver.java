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
 * Created by Hammode on 12/8/17.
 */

    public class GameOver implements Screen {
        private Viewport viewport;
        private Stage stage;
        private Skin skin;
        private HUD hud ;
        private int score;
        private int Hscore = 0;
        private Texture background;
        //GameSetting prefs;

        public GameOver(SpriteBatch sp){
            viewport=new StretchViewport(Constants.mapWidth,Constants.mapHeight,new OrthographicCamera());//
            background =new Texture("winter.png");
            hud = new HUD(sp);
            stage = new Stage(viewport, sp);
            skin = new Skin ( Gdx.files.internal ( Constants.skin ) );
            score=hud.getScore();
            //prefs = GameSetting.newSetting;
            Table table = new Table();
            table.center();
            table.setFillParent(true);
            Label gameOverLabel = new Label("GAME OVER", skin,"default");
            gameOverLabel.setFontScale(10f,15f);

            Label ScoreLabel=new Label("Score: " + score ,skin,"default");
            Label HighestScoreLabel=new Label("Highest Score : " + Hscore ,skin,"default");
            ScoreLabel.setFontScale(5f,5f);
            HighestScoreLabel.setFontScale(5f,5f);


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



            table.add(gameOverLabel).expandX();
            table.row();
            table.add(ScoreLabel).expandX().padTop(10f);
            table.row();
            table.add(HighestScoreLabel).expandX().padTop(10f);
            table.row();
            table.add(replay).expand().padTop(5f);
            table.add(menu).expand().padTop(10f);
            stage.addActor(table);

        }
        public int getHscore() {
            return Hscore;
        }

        public void setHscore(int hscore) {
            Hscore = hscore;
        }

        @Override
        public void show() {

        }

        @Override
        public void render(float delta) {
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            Gdx.input.setInputProcessor(stage);
            stage.getBatch().begin();
            stage.getBatch().draw(background,0,0,Constants.mapWidth,Constants.mapHeight);
            stage.getBatch().end();
            stage.draw();

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
            stage.dispose();
        }
    }


