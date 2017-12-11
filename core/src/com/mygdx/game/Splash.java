package com.mygdx.game;

/**
 * Created by Pintu on 12/8/2017.
 * fading is added
 */
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;
public class Splash implements Screen
{
    private Texture logo;
    private SpriteBatch spriteBatch;
    private LoginScreen game;
    private Sprite sprite;
    private TweenManager tweenManager;



    public Splash( LoginScreen game )
    {
        this.game = game;
    }

    @Override
    public void show()
    {
        logo = new Texture(Gdx.files.internal("teamlogo.png"));//
        spriteBatch = new SpriteBatch();
        tweenManager=new TweenManager();// manage fading effect
        Tween.registerAccessor(Sprite.class, new SpriteAccessor());// registering the manager to the sprite
        sprite = new Sprite(logo);
        Tween.set(sprite, SpriteAccessor.ALPHA).target(0).start(tweenManager);
        Tween.to(sprite, SpriteAccessor.ALPHA, 2).target(1).start(tweenManager);
        Tween.to(sprite, SpriteAccessor.ALPHA, 2).target(0).delay(3).repeatYoyo(1, 4).setCallback(new TweenCallback() {
            // change to the Screen of LogIn
            @Override
            public void onEvent(int type, BaseTween<?> source) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new PlayerLogin());
            }
        }).start(tweenManager);
        tweenManager.update(Float.MIN_VALUE);

    }

    @Override
    public void render(float delta)
    {
        handleInput();
        GL20 gl = Gdx.graphics.getGL20();
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        spriteBatch.draw(sprite, 0, 0, LoginScreen.WIDTH, LoginScreen.HEIGHT);
        spriteBatch.end();
        tweenManager.update(delta);
    }

    private void handleInput()
    {
        //if(Gdx.input.justTouched())
        //{
            //game.setScreen(new PlayerLogin());
        //}
    }

    @Override
    public void resize(int width, int height) {
    }
    @Override
    public void hide() {
    }
    @Override
    public void pause() {
    }
    @Override
    public void resume() {
    }
    @Override
    public void dispose() {
        sprite.getTexture().dispose();
        spriteBatch.dispose();


    }
}