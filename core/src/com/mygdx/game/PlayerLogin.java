package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * PlayerLogin class allows the player to choose login or Quickplay without login to enter the Game
  */
public class PlayerLogin implements ApplicationListener, Screen {

    //For display
    private SpriteBatch batch;
    private SpriteBatch bgbatch;
    private Skin skin;
    private Stage stage;
    private Texture background;

    //for login and New user
    private String name,password;
    static public firebase fb;
    private TextButton LoginButton;
    private TextButton NewPlayerButton;
    private TextButton QuickPlayButton;
    private TextField Playername;
    private TextField Password;

    //Constructor
    public PlayerLogin() {
       create();
    }

    /**
     * bring the user to the next screen which is the menuscreen
     */
    public void GoMenu(){
        create();
        ((Game) Gdx.app.getApplicationListener()).setScreen(new MenuScreen());    }


    @Override
    public void create() {
        batch = new SpriteBatch();
        bgbatch =new SpriteBatch();
        background = new Texture("bg.jpg");
        skin = new Skin(Gdx.files.internal(Constants.SKIN));
        stage = new Stage();
        name=new String();
        password=new String();

        //Login button :Validation of player in Firebase
         LoginButton = new TextButton("LOGIN", skin, "default");
        LoginButton.setSize(Constants.COL_WIDTH +50 ,Constants.ROW_HEIGHT);
        LoginButton.setPosition(Constants.CENTER_X,Constants.CENTER_Y +250);
        LoginButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                name=Playername.getText().toString().trim();
                password=Password.getText().toString().trim();
                if(fb.onclicklogin(name,password)) { GoMenu(); }
            }
        });

        //NewPlayer button: registration of new player in Firebase
         NewPlayerButton = new TextButton("NEW PLAYER", skin, "default");
        NewPlayerButton.setSize(Constants.COL_WIDTH +50 ,Constants.ROW_HEIGHT);
        NewPlayerButton.setPosition(Constants.CENTER_X,Constants.CENTER_Y +50);
        NewPlayerButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                name=Playername.getText().toString().trim();
                password=Password.getText().toString().trim();
                if(fb.onclicknewuser(name,password)){ GoMenu();   }
                }
            });

        //Quickaplay button allows the player to directly enter the Game
       QuickPlayButton = new TextButton("QUICK PLAY", skin, "default");
        QuickPlayButton.setSize(Constants.COL_WIDTH +50 ,Constants.ROW_HEIGHT);
        QuickPlayButton.setPosition(Constants.CENTER_X -200 ,Constants.CENTER_Y -150);
        QuickPlayButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){ GoMenu();  }
        });

        //User Input:player name
        Playername = new TextField("", skin, "default");
        TextField.TextFieldStyle textFieldStyle = skin.get(TextField.TextFieldStyle.class);
        textFieldStyle.font.getData().setScale(2.0f);
        Playername.setMessageText("PLAYER");
        Playername.setSize(Constants.COL_WIDTH +50 ,Constants.ROW_HEIGHT);
        Playername.setPosition(Constants.CENTER_X -350 ,Constants.CENTER_Y +250);


        //Password to be entered by the player
        Password = new TextField("", skin, "default");
        TextField.TextFieldStyle textFieldStyle1 = skin.get(TextField.TextFieldStyle.class);
        textFieldStyle1.font.getData().setScale(2.0f);
        Password.setMessageText("PASSWORD");
        Password.setPasswordCharacter('*');
        Password.setPasswordMode(true);
        Password.setSize(Constants.COL_WIDTH +50 ,Constants.ROW_HEIGHT);
        Password.setPosition(Constants.CENTER_X -350 ,Constants.CENTER_Y +50);


        //Adding all Buttons and TextFiels to stage
        stage.addActor(LoginButton);
        stage.addActor(NewPlayerButton);
        stage.addActor(QuickPlayButton);
        stage.addActor(Playername);
        stage.addActor(Password);

        //set the InputProcessor
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void dispose() {
    batch.dispose();
    bgbatch.dispose();
    }

    //To render the Screen
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

    @Override
    public void show() {}

    @Override
    public void resize(int width, int height) {}

    @Override
    public void render() {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

}
