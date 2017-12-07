package com.mygdx.game;
/**
 * Created by tmp-sda-1124 on 12/4/17.
 */
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
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
public class MenuScreen implements Screen {
    private SpriteBatch batch;
    private Skin skin;
    private Stage stage;

    // options
    private Window popUpSettings;
    private TextButton btnWinOptSave;
    private TextButton btnWinOptCancel;
    private CheckBox checkBoxSound;
    private Slider sliderSound;
    private CheckBox checkkBoxMusic;
    private Slider sliderMusic;


    public MenuScreen() {
        create();
    }
    public void create() {

        batch = new SpriteBatch();
        skin = new Skin(Gdx.files.internal(Constants.skin));
        stage = new Stage();

        final TextButton playButton = new TextButton("PLAY", skin, "default");
        playButton.setSize(Constants.colWidth ,Constants.rowHeight);
        playButton.setPosition(Constants.centerX,Constants.centerY+200);
        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                //playButton.setText("You clicked the button");
                ((Game)Gdx.app.getApplicationListener()).setScreen(new TiledTest());
            }
        });
        final TextButton settingsButton = new TextButton("SETTINGS", skin, "default");
        settingsButton.setSize(Constants.colWidth ,Constants.rowHeight);
        settingsButton.setPosition(Constants.centerX ,Constants.centerY+50);
        settingsButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
               // buildOptionsWindowLayer();
                //setUpAudioSettings();
                onOptionsClicked();
               // settingsButton.setText("You clicked the settings button");
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
        Table layerOptionsWindow = buildOptionsWindowLayer();
        stage.addActor(playButton);
        stage.addActor(settingsButton);
        stage.addActor(scoreButton);
        stage.addActor(exitButton);
        stage.addActor(layerOptionsWindow);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void dispose() {
        stage.dispose();
        batch.dispose();
    }
    @Override
    public void show() {
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        stage.draw();
        batch.end();
    }

    private void option(){
       // Table layerOptionsWindow = buildOptionsWindowLayer();
    }

    private Table buildOptionsWindowLayer() {
        popUpSettings = new Window("Options", skin);
        // + Audio Settings: Sound/Music CheckBox and Volume Slider
        popUpSettings.add(setUpAudioSettings()).row();

        // + Separator and Buttons (Save, Cancel)
        popUpSettings.add(buildOptWinButtons()).pad(10, 0, 10, 0);

        // Hide options window by default
        popUpSettings.setVisible(false);

        // Let TableLayout recalculate widget sizes and positions
        popUpSettings.pack();
        return popUpSettings;
    }

    private Table setUpAudioSettings() {
        Table table = new Table();
        // + Title: "Audio"
        table.pad(10, 10, 0, 10);
//        table.add(new Label("Audio", skin, "default-font", Color.ORANGE)).colspan(3);
        table.row();
        table.columnDefaults(0).padRight(10);
        table.columnDefaults(1).padRight(10);
        // + Checkbox, "Sound" label, sound volume sliderSound
        checkBoxSound = new CheckBox("", skin);
        table.add(checkBoxSound);
        table.add(new Label("Sound", skin));
        sliderSound = new Slider(0, 100, 10, false, skin);
        table.add(sliderSound);
        table.row();
        // + Checkbox, "Music" label, music volume sliderSound
        checkkBoxMusic = new CheckBox("", skin);
        table.add(checkkBoxMusic);
        table.add(new Label("Music", skin));
        sliderMusic = new Slider(0, 100, 10, false, skin);
        table.add(sliderMusic);
        table.row();
        return table;
    }

    private Table buildOptWinButtons() {
        Table tbl = new Table();
        // + Separator
        Label lbl = null;
        lbl = new Label("", skin);
        lbl.setColor(0.75f, 0.75f, 0.75f, 1);
        lbl.setStyle(new Label.LabelStyle(lbl.getStyle()));
        lbl.getStyle().background = skin.newDrawable("white");
        tbl.add(lbl).colspan(2).height(1).width(220).pad(0, 0, 0, 1);
        tbl.row();
        lbl = new Label("", skin);
        lbl.setColor(0.5f, 0.5f, 0.5f, 1);
        lbl.setStyle(new Label.LabelStyle(lbl.getStyle()));
        lbl.getStyle().background = skin.newDrawable("white");
        tbl.add(lbl).colspan(2).height(1).width(220).pad(0, 1, 5, 0);
        tbl.row();
        // + Save Button with event handler
        btnWinOptSave = new TextButton("Save", skin);
        tbl.add(btnWinOptSave).padRight(30);
        btnWinOptSave.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                onSaveClicked();
            }
        });
        // + Cancel Button with event handler
        btnWinOptCancel = new TextButton("Cancel", skin);
        tbl.add(btnWinOptCancel);
        btnWinOptCancel.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                onCancelClicked();
            }
        });
        return tbl;
    }

    private void loadSettings() {
        GameSetting prefs = GameSetting.newSetting;
        prefs.load();
        checkBoxSound.setChecked(prefs.hasSoundOn);
        sliderSound.setValue(prefs.soundVolume);
        checkkBoxMusic.setChecked(prefs.hasMusicOn);
        sliderMusic.setValue(prefs.musicVolume);
    }

    private void onOptionsClicked() {
          loadSettings();
          popUpSettings.setVisible(true);
    }

    private void onSaveClicked() {
        saveSettings();
        onCancelClicked();
        SoundManager.newSoundManager.onSettingsUpdated();
    }

    private void onCancelClicked() {
        popUpSettings.setVisible(false);
        SoundManager.newSoundManager.onSettingsUpdated();
    }


    private void saveSettings() {
        GameSetting prefs = GameSetting.newSetting;
        prefs.hasSoundOn = checkBoxSound.isChecked();
        prefs.soundVolume = (int) sliderSound.getValue();
        prefs.hasMusicOn = checkkBoxMusic.isChecked();
        prefs.musicVolume = (int) sliderMusic.getValue();
        prefs.save();
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
