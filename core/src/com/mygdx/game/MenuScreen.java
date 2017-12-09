package com.mygdx.game;
/**
 * Created by tmp-sda-1124 on 12/4/17.
 */

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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


    public MenuScreen() {
        create();

    }
    public void create() {

        batch = new SpriteBatch();
        skin = new Skin(Gdx.files.internal(Constants.skin));
        stage = new Stage();
        bgbatch =new SpriteBatch();
        background = new Texture("background_02.png");

        final TextButton playButton = new TextButton("PLAY", skin, "default");
        playButton.setSize(Constants.colWidth ,Constants.rowHeight);
        playButton.setPosition(Constants.centerX,Constants.centerY+200);
        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){

                //playButton.setText("You clicked the button");
                ((Game)Gdx.app.getApplicationListener()).setScreen(new GameView());

            }
        });
        final TextButton settingsButton = new TextButton("SETTINGS", skin, "default");
        settingsButton.setSize(Constants.colWidth ,Constants.rowHeight);
        settingsButton.setPosition(Constants.centerX ,Constants.centerY+50);
        settingsButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){

                onSettingsClicked();
            }
        });
        final TextButton scoreButton = new TextButton("SCORE", skin, "default");
        scoreButton.setSize(Constants.colWidth ,Constants.rowHeight);
        scoreButton.setPosition(Constants.centerX ,Constants.centerY-100);
        scoreButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){

                scoreButton.setText("You clicked the score button");
            }
        });
        final TextButton exitButton = new TextButton("EXIT", skin, "default");
        exitButton.setSize(Constants.colWidth ,Constants.rowHeight);
        exitButton.setPosition(Constants.centerX ,Constants.centerY-250);
        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                Gdx.app.exit();
            }
        });
        Table settingsWindow = createSettingsWindow(); //the settings pop-up window
        stage.addActor(playButton);
        stage.addActor(settingsButton);
        stage.addActor(settingsWindow);
        stage.addActor(scoreButton);
        stage.addActor(exitButton);
        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void dispose() {
        batch.dispose();
        bgbatch.dispose();
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
        table.add(checkBoxSound);
        Label soundLabel = new Label("Sound", skin);
        soundLabel.setFontScale(3f,3f);
        table.add(soundLabel);
        sliderSound = new Slider(0.0f, 1.0f, 0.1f, false, skin);
        table.add(sliderSound).width(300).padLeft(20).padRight(30);
        table.row();
        //checkbox for turning on and off music and setting volume
        checkBoxMusic = new CheckBox("", skin);
        table.add(checkBoxMusic);
        Label musicLabel = new Label("Music", skin);
        musicLabel.setFontScale(3f,3f);
        table.add(musicLabel).padLeft(20).padRight(30);
        sliderMusic = new Slider(0.0f, 1.0f, 0.1f, false, skin);
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
    private void onSettingsClicked() {
          loadSettings();
          popUpSettings.setVisible(true); //make the pop-up visible

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
