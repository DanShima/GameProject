package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
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
/**
*The Hud  is responsible for displaying the current level, score and health status on top of the screen.
*The constructor takes "SpriteBatch" in order to be able to draw the "stage" which is a box that contain
 * the widgets such as labels, buttons and progress bar.
 */
public class HUD implements Disposable  {

    private int level = 0;
    private Stage stage;
    private Viewport viewport;
    private static int score;
    private  Label ScoreLabel;
    private Label LevelLabel;
    private Table table;
    private TextButton button ;
    private Skin skin ;
    private TextButton Continue ;
    private TextButton  TryAgain;
    private TextButton  Settings ;
    private TextButton  Menu;
    private MenuScreen menuScreen;
    private Window PauseMenu;
    private final ProgressBar progressBar;
    boolean isPaused = false;
    
    /**
     *The constructor initializing the labels, buttons, health bar
     * @param sb spritebatch used to render the hud
     */



    public HUD(SpriteBatch sb) {
        viewport=new StretchViewport (Constants.MAP_WIDTH,Constants.MAP_HEIGHT,new OrthographicCamera ());//
        stage=new Stage(viewport,sb);//stage is as box and try to put widget and organize things inside that table
        skin = new Skin ( Gdx.files.internal ( Constants.SKIN) );
        menuScreen = new MenuScreen();
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
                isPaused = true;
                PauseMenu.setVisible(true);
                // set size and position
                return true; }});

        LevelLabel =new Label("Level :" + level ,skin, "default");//label for gdx
        LevelLabel.setFontScale(3,2);

        ProgressBar.ProgressBarStyle progressBarStyle = skin.get("fancy", ProgressBar.ProgressBarStyle.class);
        TiledDrawable tiledDrawable = skin.getTiledDrawable("progress-bar");// take the SKIN and put it inside TiledDrawable
        tiledDrawable.setMinWidth(0.0f);
        progressBarStyle.background = tiledDrawable;// background of the health bar( when the bar is empty).

        tiledDrawable = skin.getTiledDrawable("progress-bar-knob");
        tiledDrawable.setMinWidth(0.0f);
        progressBarStyle.knobBefore = tiledDrawable;

        progressBar = new ProgressBar(0, 100, 1, false, skin, "fancy");
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
        stage.addActor(menuScreen.settingsWindow ());
    }

    /**
     * Setting up the pop-up window containing options
     * @return the pop-up window with table of buttons
     */
    private Table createSettingsButtons() {
        Table table = new Table();
        table.row();

        Continue = new TextButton("Continue", skin);
        table.add(Continue);
        Continue.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.graphics.setContinuousRendering(true);
                isPaused = false;
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
                Constants.CURRENT_LEVEL = 0;
                Gdx.graphics.setContinuousRendering(true);
                ((Game)Gdx.app.getApplicationListener()).setScreen(new GameView());
            }
        });
        table.row();
        Settings = new TextButton("Settings", skin);
        table.add(Settings);
        Settings.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                menuScreen.onSettingsClicked();

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

    public void setHealth(float value) {
        progressBar.setValue(value);
    }

    /**
     * when the monster is on the same position as the player, they do damage to the player
     * and reduce the player's health
     * @param value
     * @return
     */
    public boolean reduceHealth(float value) {
        float newHealth = progressBar.getValue() - value;
        progressBar.setValue( Math.max(0,newHealth) );
        //true if dead, false if still alive
        if(newHealth <= 0){
            return true;
        }else{
            return false;
        }
    }

    public Stage getStage() {
        return stage;
    }

    public  float getHealth()
    {
        return progressBar.getValue();
    }
    public boolean isPaused() {
        return isPaused;
    }
    @Override
    public void dispose() {
        stage.dispose();
    }

}
