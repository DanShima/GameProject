package com.mygdx.game;
/**
 * Created by tmp-sda-1124 on 12/4/17.
 */

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * MenuScreen class displays the Game menu for the player
 */
public class MenuScreen implements Screen {
    private SpriteBatch batch;
    private SpriteBatch bgbatch;
    private Skin skin;
    private Stage stage;
    private Texture background;

    // options
    private Window popUpSettings;
    private TextButton saveButton;
    private TextButton cancelButton;
    private CheckBox checkBoxSound;
    private CheckBox checkBoxMusic;
    private Slider sliderSound;
    private Slider sliderMusic;
    static firebase fb;
    PlayerLogin play;

    private int maxscore;

    private Window HighScoreDisplay;

    public MenuScreen() {
        create();

    }
    public void create() {

        SoundEffect.newSoundEffect.create(new AssetManager()); //load audio
        GameSetting.newSetting.load(); //load audio settings
        SoundManager.newSoundManager.play(SoundEffect.newSoundEffect.backgroundMusic.musicStartMenu); //play background music
        Gdx.app.debug("SOUND", "MENUUUU");

        batch = new SpriteBatch();
        skin = new Skin(Gdx.files.internal(Constants.SKIN));
        stage = new Stage();
        bgbatch =new SpriteBatch();
        background = new Texture("background_02.png");

        //Firebase
        maxscore=fb.getHighScore(play.name);

        HighScoreDisplay=new Window("SCORE",skin);
        HighScoreDisplay.add(scorebutton()).row();
        HighScoreDisplay.pack();
        HighScoreDisplay.setPosition(1200,700);
        HighScoreDisplay.setVisible(false);
        HighScoreDisplay.toFront();

        final TextButton playButton = new TextButton("PLAY", skin, "default");
        playButton.setSize(Constants.COL_WIDTH,Constants.ROW_HEIGHT);
        playButton.setPosition(Constants.CENTER_X,Constants.CENTER_Y +200);
        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){

                ((Game)Gdx.app.getApplicationListener()).setScreen(new GameView());

            }
        });
        final TextButton settingsButton = new TextButton("SETTINGS", skin, "default");
        settingsButton.setSize(Constants.COL_WIDTH,Constants.ROW_HEIGHT);
        settingsButton.setPosition(Constants.CENTER_X,Constants.CENTER_Y +50);
        settingsButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){

                onSettingsClicked();
            }
        });


        final TextButton scoreButton = new TextButton("SCORE", skin, "default");
        scoreButton.setSize(Constants.COL_WIDTH,Constants.ROW_HEIGHT);
        scoreButton.setPosition(Constants.CENTER_X,Constants.CENTER_Y -100);
        scoreButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){

                                HighScoreDisplay.setVisible(true);
            }
        });
        final TextButton exitButton = new TextButton("EXIT", skin, "default");
        exitButton.setSize(Constants.COL_WIDTH,Constants.ROW_HEIGHT);
        exitButton.setPosition(Constants.CENTER_X,Constants.CENTER_Y -250);
        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                Gdx.app.exit();
            }
        });

        Table settingsWindow = createSettingsWindow(); //the settings pop-up window
        stage.addActor(playButton);
        stage.addActor(settingsButton);
        stage.addActor(settingsWindow ());
        stage.addActor(scoreButton);
        stage.addActor(exitButton);
        stage.addActor(HighScoreDisplay);
        Gdx.input.setInputProcessor(stage);

    }
    //Method to display popup for score
    public Table scorebutton()
    {
        Table table =new Table();
        table.row();
        Label label=new Label("SCORE: "+maxscore,skin,"default");
        label.setFontScale(3,2);
        table.add(label);
        table .row();
        return table;
    }

    @Override
    public void dispose() {
        batch.dispose();
        bgbatch.dispose();
        SoundEffect.newSoundEffect.backgroundMusic.musicStartMenu.stop();
    }
    @Override
    public void show() {
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        bgbatch.begin();
        bgbatch.draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        bgbatch.end();
        batch.begin();
        stage.draw();
        batch.end();

    }

    /**
     * Creating a pop up window that shows the audio settings
     * @return
     */
    private Table createSettingsWindow() {
        popUpSettings = new Window("Options", skin);
        // fill the window with content: Sound/Music checkBoxes and volume sliders
        popUpSettings.add(setUpAudioSettings()).row();
        // include save and cancel buttons
        popUpSettings.add(createSettingsButtons()).pad(10, 0, 10, 0);
        // hide options window by default
        popUpSettings.setVisible(false);
        // set size and position
        popUpSettings.pack();
        popUpSettings.setPosition(500, 500);
        return popUpSettings;
    }

    /**
     * Filling the settings window with a table with checkboxes and sliders for audio
     * @return the table that contains the audio options
     */
    private Table setUpAudioSettings() {
        Table table = new Table();
        //checkbox for turning on and off sound and setting volume
        checkBoxSound = new CheckBox("", skin);
        table.add(checkBoxSound).padTop(30f);
        checkBoxSound.setTransform(true);
        checkBoxSound.scaleBy(1.15f);
        Label soundLabel = new Label("Sound", skin);
        soundLabel.setFontScale(3f,3f);
        table.add(soundLabel);
        sliderSound = new Slider(0.0f, 1.0f, 0.1f, false, skin);
        sliderSound.getStyle().knob.setMinHeight(30f);
        sliderSound.getStyle().knob.setMinWidth(30f);
        table.add(sliderSound).width(300).padLeft(20).padRight(30);
        table.row();
        //checkbox for turning on and off music and setting volume
        checkBoxMusic = new CheckBox("", skin);
        checkBoxMusic.setTransform(true);
        checkBoxMusic.scaleBy(1.15f);
        table.add(checkBoxMusic).padTop(30f);
        Label musicLabel = new Label("Music", skin);
        musicLabel.setFontScale(3f,3f);
        table.add(musicLabel).padLeft(20).padRight(30);
        sliderMusic = new Slider(0.0f, 1.0f, 0.1f, false, skin);
        sliderMusic.getStyle().knob.setMinHeight(30f);
        sliderMusic.getStyle().knob.setMinWidth(30f);
        table.add(sliderMusic).width(300);
        table.row();
        return table;
    }

    /**
     * Adding save and cancel buttons to the settings window
     * @return the table that contains the save and cancel buttons
     */
    private Table createSettingsButtons() {
        Table table = new Table();
        table.row();

        saveButton = new TextButton("Save", skin);
        table.add(saveButton).padRight(30).padLeft(30);
        saveButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                onSaveClicked();
            }
        });

        cancelButton = new TextButton("Cancel", skin);
        table.add(cancelButton).padRight(30);
        cancelButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                onCancelClicked();
            }
        });
        return table;
    }

    /**
     * Load sound preferences from GameSetting class
     */
    private void loadSettings() {
        GameSetting setting = GameSetting.newSetting;
        setting.load();
        checkBoxSound.setChecked(setting.isHasSoundOn());
        sliderSound.setValue(setting.getSoundVolume());
        checkBoxMusic.setChecked(setting.isHasMusicOn());
        sliderMusic.setValue(setting.getMusicVolume());
    }

    /**
     * Define what happens when the player clicks on Settings in the menu
     */
    protected void onSettingsClicked() {
          loadSettings();
          popUpSettings.setVisible(true);//make the pop-up visible
        popUpSettings.toFront();
    }

    /**
     * Define what happens when the player clicks on "Save" button in the settings
     */
    private void onSaveClicked() {
        saveSettings();
        onCancelClicked(); //close the pop-up window
        SoundManager.newSoundManager.onSettingsUpdated(); //update soundmanager with the new settings
    }

    /**
     * Define what happens when the player clicks on "Cancel" button in the settings
     */
    private void onCancelClicked() {
        popUpSettings.setVisible(false);
        SoundManager.newSoundManager.onSettingsUpdated();
    }

    /**
     * Save the new audio preferences. for instance, if the checkbox is unchecked. it stays unchecked
     */
    private void saveSettings() {
        GameSetting setting = GameSetting.newSetting;
        setting.setHasSoundOn(checkBoxSound.isChecked());
        setting.setHasMusicOn(checkBoxMusic.isChecked());
        setting.setSoundVolume(sliderSound.getValue());
        setting.setMusicVolume(sliderMusic.getValue());
        setting.save();
  }
  public Table settingsWindow (){

      Table settingsWindow = createSettingsWindow(); //the settings pop-up window
      return settingsWindow;
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
}
