package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;

/**
 * Created by Pintu on 12/5/2017.
 */


 import com.badlogic.gdx.ApplicationListener;
          import com.badlogic.gdx.Gdx;
          import com.badlogic.gdx.graphics.GL20;
          import com.badlogic.gdx.graphics.g2d.SpriteBatch;
          import com.badlogic.gdx.scenes.scene2d.InputEvent;
          import com.badlogic.gdx.scenes.scene2d.Stage;
          import com.badlogic.gdx.scenes.scene2d.ui.Skin;
          import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import javax.swing.text.PasswordView;

import sun.security.util.Password;


public class PlayerLogin implements ApplicationListener {
 private TiledTest game;
 private SpriteBatch batch;
 private Skin skin;
 private Stage stage;
 @Override
 public void create() {
         batch = new SpriteBatch();
         game=new TiledTest();
         skin = new Skin(Gdx.files.internal(Constants.skin));
         stage = new Stage();

                 final TextButton LoginButton = new TextButton("LOGIN", skin, "default");
     LoginButton.setSize(Constants.colWidth ,Constants.rowHeight);
     LoginButton.setPosition(Constants.centerX,Constants.centerY+200);
     LoginButton.addListener(new ClickListener(){
 @Override
 public void clicked(InputEvent event, float x, float y){
     LoginButton.setText("You clicked the button");
                 }
 });
         final TextButton NewPlayerButton = new TextButton("NEW PLAYER", skin, "default");
     NewPlayerButton.setSize(Constants.colWidth ,Constants.rowHeight);
     NewPlayerButton.setPosition(Constants.centerX ,Constants.centerY+50);
     NewPlayerButton.addListener(new ClickListener(){
 @Override
 public void clicked(InputEvent event, float x, float y){
     NewPlayerButton.setText("You clicked the button");
                 }
 });

                final TextField Player = new TextField("", skin, "default");
     Player.setSize(Constants.colWidth ,Constants.rowHeight);
     Player.setPosition(Constants.centerX-200 ,Constants.centerY+200);


     final TextField Password = new TextField("", skin, "default");
     Password.setSize(Constants.colWidth ,Constants.rowHeight);
     Password.setPosition(Constants.centerX-200 ,Constants.centerY+50);



 /*@Override

                 }*/
        /* final TextButton exitButton = new TextButton("EXIT", skin, "default");
         exitButton.setSize(Constants.colWidth ,Constants.rowHeight);
         exitButton.setPosition(Constants.centerX ,Constants.centerY-250);
         exitButton.addListener(new ClickListener(){
 @Override
 public void clicked(InputEvent event, float x, float y){
                 exitButton.setText("You clicked the button");
                 }
 });*/
         stage.addActor(LoginButton);
         stage.addActor(NewPlayerButton);
         stage.addActor(Player);
         stage.addActor(Password);
         //stage.addActor(exitButton);
         Gdx.input.setInputProcessor(stage);
         }
 @Override
 public void dispose() {
         batch.dispose();
         }
 @Override
 public void render() {
         Gdx.gl.glClearColor(1, 1, 1, 1);
         Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
         batch.begin();
         stage.draw();
         batch.end();
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
}

