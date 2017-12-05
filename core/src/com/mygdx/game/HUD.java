package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;



public class HUD implements Disposable  {

    private int level = 1;
    public Stage stage;
    private Viewport viewport;
    private  int score = 100;
    private  Label ScoreLabel;
    private Label LevelLabel;
    private Table table;
    private TextButton button ;
    private Skin myskin ;
    private HealthBar healthBar;
    public HUD(SpriteBatch sb)

    {
        viewport=new StretchViewport (TiledTest.mapWidth,TiledTest.mapHeight,new OrthographicCamera ());//2
        stage=new Stage(viewport,sb);//stage is as box and try to put widget and organize things inside that table
        myskin = new Skin ( Gdx.files.internal ( Constants.skin ) );
        final Dialog dialog = new Dialog ( "click me",myskin,"default" );
        table = new Table();
        table.top();//table at top of our stage
        table.setFillParent(true);//table is now fill all the stage
        ScoreLabel=new Label("Score" + score ,myskin,"default");//label for gdx
        ScoreLabel.setFontScale(2,2);
        button = new TextButton ( "Menu",myskin,"default" );

        button.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new MenuScreen());
               // dialog.show(stage);
              return true;
            }});

        LevelLabel =new Label("Level :" + level ,myskin, "default");//label for gdx
        LevelLabel.setFontScale(2,2);
        healthBar = new HealthBar(90, 30);
        table.add (healthBar);
        table.add(ScoreLabel).expandX().padTop(10);
        table.add(LevelLabel).expandX().padTop(10);
        table.add (button);

        table.row();

        stage.addActor(table);

    }


    public  void setScore(int value) {
        score = value;
        ScoreLabel.setText(String.format("Score :" + score));
    }
    public  int getScore()
    {
        return score;
    }


    public void setLevel(int value) {
        level =value;
        LevelLabel.setText(String.format("Level :" + level));
    }
    public  int getLevel()
    {
        return level;
    }




    @Override
    public void dispose() {
        stage.dispose();
    }
}