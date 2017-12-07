package com.mygdx.game;
/**
 * Created by Shashidhar on 07-12-2017.
 */
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


    private SpriteBatch batch;
    private SpriteBatch bgbatch;
    private Skin skin;
    private Stage stage;
    private Texture background;

    //Constructor
    public PlayerLogin() {
       create();
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        bgbatch =new SpriteBatch();
        background = new Texture("snowbackground1.jpg");
        skin = new Skin(Gdx.files.internal(Constants.skin));
        stage = new Stage();

        //Login button validate the player from firebase
        final TextButton LoginButton = new TextButton("LOGIN", skin, "default");
        LoginButton.setSize(Constants.colWidth+50 ,Constants.rowHeight);
        LoginButton.setPosition(Constants.centerX,Constants.centerY+250);
        LoginButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                LoginButton.setText("You clicked the button");
            }
        });

        //NewPlayer button helps the player to register
        final TextButton NewPlayerButton = new TextButton("NEW PLAYER", skin, "default");
        NewPlayerButton.setSize(Constants.colWidth+50 ,Constants.rowHeight);
        NewPlayerButton.setPosition(Constants.centerX ,Constants.centerY+50);
        NewPlayerButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                 NewPlayerButton.setText("You clicked the button");
                }
                });

        //Quickaplay button allows the player to directly enter the Game
        final TextButton QuickPlayButton = new TextButton("QUICK PLAY", skin, "default");
        QuickPlayButton.setSize(Constants.colWidth+50 ,Constants.rowHeight);
        QuickPlayButton.setPosition(Constants.centerX-200 ,Constants.centerY-150);
        QuickPlayButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                ((Game)Gdx.app.getApplicationListener()).setScreen(new MenuScreen());
            }
        });

        //To get name from the player
        final TextField Player = new TextField("", skin, "default");
        TextField.TextFieldStyle textFieldStyle = skin.get(TextField.TextFieldStyle.class);
        textFieldStyle.font.getData().setScale(2.0f);
        Player.setMessageText("PLAYER");
        Player.setSize(Constants.colWidth+50 ,Constants.rowHeight);
        Player.setPosition(Constants.centerX-350 ,Constants.centerY+250);

        //To get password from the player
        final TextField Password = new TextField("", skin, "default");
        TextField.TextFieldStyle textFieldStyle1 = skin.get(TextField.TextFieldStyle.class);
        textFieldStyle1.font.getData().setScale(2.0f);
        Password.setMessageText("PASSWORD");
        Password.setPasswordCharacter('*');
        Password.setPasswordMode(true);
        Password.setSize(Constants.colWidth+50 ,Constants.rowHeight);
        Password.setPosition(Constants.centerX-350 ,Constants.centerY+50);

        //Adding all Buttons and TextFiels to stage
        stage.addActor(LoginButton);
        stage.addActor(NewPlayerButton);
        stage.addActor(QuickPlayButton);
        stage.addActor(Player);
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
    public void show() {

    }



    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {

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
