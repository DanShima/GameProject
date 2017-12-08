package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
/*
The Hud class is responsible for displaying the current level, score and health status on top of the screen.
The constructor takes "SpriteBatch" in order to be able to draw the "stage" which is a box that contain
 the widgets such as labels, buttons and progress bar.
 */


public class HUD implements Disposable  {

    private int level = 0;
    public Stage stage;
    private Viewport viewport;
    private static int score = Constants.SCORE_START;
    private  Label ScoreLabel;
    private Label LevelLabel;
    private Table table;
    private TextButton button ;
    private Skin skin ;// skin for better UI
    final ProgressBar progressBar;
    TextButton Continue ;
    TextButton  TryAgain;
    TextButton  Settings ;
    TextButton  Menu;
    MenuScreen menuScreen;
    private Window PauseMenu;


    public HUD(SpriteBatch sb)

    {

        viewport=new StretchViewport (Constants.mapWidth,Constants.mapHeight,new OrthographicCamera ());//
        stage=new Stage(viewport,sb);//stage is as box and try to put widget and organize things inside that table
        skin = new Skin ( Gdx.files.internal ( Constants.skin ) );
        menuScreen = new MenuScreen();
        final Dialog dialog = new Dialog ( "Pause",skin,"default" );
        table = new Table();
        table.setFillParent(true);//table is now fill all the stage
        table.top();//table at top of the stage
        ScoreLabel=new Label("Score" + score ,skin,"default");//label for gdx
        ScoreLabel.setFontScale(3,2);
        button = new TextButton ( "Menu",skin,"default" );
        PauseMenu = new Window("Options", skin);
        PauseMenu.add(createSettingsButtons()).row();
        // hide options window by default
        PauseMenu.setVisible(false);
        // set size and position
        PauseMenu.pack();
        PauseMenu.setPosition(900, 500);
        PauseMenu.setMovable(false);
        PauseMenu.toFront();

        button.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, final int button) {
                // fill the window with content: Sound/Music checkBoxes and volume sliders
                Gdx.graphics.setContinuousRendering(false);
                PauseMenu.setVisible(true);
                // set size and position
                return true; }});


        LevelLabel =new Label("Level :" + level ,skin, "default");//label for gdx
        LevelLabel.setFontScale(3,2);

        ProgressBar.ProgressBarStyle progressBarStyle = skin.get("fancy", ProgressBar.ProgressBarStyle.class);
        TiledDrawable tiledDrawable = skin.getTiledDrawable("progress-bar");// take the skin and put it inside TiledDrawable
        tiledDrawable.setMinWidth(0.0f);
        progressBarStyle.background = tiledDrawable;// background of the health bar( when the bar is empty).

        tiledDrawable = skin.getTiledDrawable("progress-bar-knob");
        tiledDrawable.setMinWidth(0.0f);
        progressBarStyle.knobBefore = tiledDrawable;

        progressBar = new ProgressBar(0.0f, 100.0f, 1.0f, false, skin, "fancy");
        progressBar.setValue(75.0f);//initializing the bar

        // add the widgets to a table
        table.add(progressBar).width(335.0f);
        table.add(ScoreLabel).expandX();
        table.add(LevelLabel).expandX();
        table.add (button);
        // table.add(popUpSettings);
        table.row();
        stage.addActor(table);
        stage.addActor(PauseMenu);
    }

//    private Table createSettingsWindow() {
//        // fill the window with content: Sound/Music checkBoxes and volume sliders
//        PauseMenu.add(createSettingsButtons()).row();
//        // hide options window by default
//        PauseMenu.setVisible(true);
//        // set size and position
//        PauseMenu.pack();
//        return PauseMenu;
//    }
    private Table createSettingsButtons() {
        Table table = new Table();
        table.row();

        Continue = new TextButton("Continue", skin);
        table.add(Continue);
        Continue.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.graphics.setContinuousRendering(true);
               // Gdx.graphics.requestRendering();
                PauseMenu.setVisible(false);
            }
        });
        table.row();
        TryAgain = new TextButton("TryAgain", skin);
        table.add(TryAgain);
        TryAgain.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new GameView());
            }
        });
        table.row();
        Settings = new TextButton("Settings", skin);
        table.add(Settings);
        Settings.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
               // menuScreen.onSettingsClicked();
                // menuScreen.
            }
        });
        table.row();
        Menu = new TextButton("Menu", skin);
        table.add(Menu);
        Menu.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new MenuScreen());
            }
        });


        return table;
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
