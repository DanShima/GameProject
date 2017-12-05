package com.mygdx.game;

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
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
/*
The Hud class is responsible for displaying the current level, score and health status on top of the screen.
The constructor takes "SpriteBatch" in order to be able to draw the "stage" which is a box that contain
 the widgets such as labels, buttons and progress bar.
 */


public class HUD implements Disposable  {

    private int level = 1;
    public Stage stage;
    private Viewport viewport;
    private  int score = 100;
    private  Label ScoreLabel;
    private Label LevelLabel;
    private Table table;
    private TextButton button ;
    private Skin myskin ;// skin for better UI
    final ProgressBar progressBar;
    public HUD(SpriteBatch sb)

    {
        viewport=new StretchViewport (TiledTest.mapWidth,TiledTest.mapHeight,new OrthographicCamera ());//
        stage=new Stage(viewport,sb);//stage is as box and try to put widget and organize things inside that table
        myskin = new Skin ( Gdx.files.internal ( Constants.skin ) );
        final Dialog dialog = new Dialog ( "click me",myskin,"default" );
        table = new Table();
        table.setFillParent(true);//table is now fill all the stage
        table.top();//table at top of the stage
        ScoreLabel=new Label("Score" + score ,myskin,"default");//label for gdx
        ScoreLabel.setFontScale(3,2);
        button = new TextButton ( "Menu",myskin,"default" );

        button.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
               // dialog.show(stage);
                return true;}});

        LevelLabel =new Label("Level :" + level ,myskin, "default");//label for gdx
        LevelLabel.setFontScale(3,2);

        ProgressBar.ProgressBarStyle progressBarStyle = myskin.get("fancy", ProgressBar.ProgressBarStyle.class);
        TiledDrawable tiledDrawable = myskin.getTiledDrawable("progress-bar");// take the skin and put it inside TiledDrawable
        tiledDrawable.setMinWidth(0.0f);
        progressBarStyle.background = tiledDrawable;// background of the health bar( when the bar is empty).

        tiledDrawable = myskin.getTiledDrawable("progress-bar-knob");
        tiledDrawable.setMinWidth(0.0f);
        progressBarStyle.knobBefore = tiledDrawable;

        progressBar = new ProgressBar(0.0f, 100.0f, 1.0f, false, myskin, "fancy");
        progressBar.setValue(75.0f);//initializing the bar


        // add the widgets to a table
        table.add(progressBar).width(335.0f);
        table.add(ScoreLabel).expandX();
        table.add(LevelLabel).expandX();
        table.add (button);

        table.row();

        stage.addActor(table);

    }

   // getters and setters
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

    public void setHealth(float value) {
        progressBar.setValue(value);
    }
    public  float getHealth()
    {
        return progressBar.getValue();
    }





    @Override
    public void dispose() {
        stage.dispose();
    }
}