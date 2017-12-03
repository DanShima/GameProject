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
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;



public class HUD implements Disposable  {

    public Stage stage;
    private Viewport viewport;
    private static Integer score;
    private static Label scorelabel;
    private Label levellabel;
    private Label scorename;
    private Table table;
    private TextButton button ;
    private Skin myskin ;
    private HealthBar healthBar;
    public HUD(SpriteBatch sb)

    {
        viewport=new StretchViewport (TiledTest.mapWidth,TiledTest.mapHeight,new OrthographicCamera ());//2
        stage=new Stage(viewport,sb);//stage is as box and try to put widget and organize things inside that table
        myskin = new Skin ( Gdx.files.internal ( Constants.skin ) );
        final Dialog dialog = new Dialog ( "click me",myskin,"default" );
        table = new Table();
        table.top();//table at top of our stage
        table.setFillParent(true);//table is now fill all the stage
        //label for gdx
        Label skinlabel = new Label ("my new skin" ,myskin, "default");
        scorelabel=new Label(String.format("%02d",10),myskin, "default");//label for gdx
        button = new TextButton ( "cool",myskin,"default" );

        button.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                dialog.show(stage);
                return true;}});

        levellabel=new Label("1-1",myskin, "default");//label for gdx
         scorename=new Label("Score",myskin,"default");//label for gdx
        healthBar = new HealthBar(100, 10);
        healthBar.setPosition(10, Gdx.graphics.getHeight() - 20);
        table.add (healthBar);

        table.add(scorename).expandX().padTop(10);
        table.add(levellabel).expandX().padTop(10);
        table.add(skinlabel).expandX().padTop(10);
        table.add (button);



        table.row();
        table.add(scorelabel).expandX();
        //table.background ( "blue.png" );
       // table.add(health).width(50).height(10);



        stage.addActor(table);

    }


    public static void addScore(int value)
    {
        score +=value;
        scorelabel.setText(String.format("%02d",score));
    }
    public static int getScore()
    {
        return score;
    }
    @Override
    public void dispose() {
        stage.dispose();
    }
    //loading.setDrawable(new TextureRegionDrawable(new TextureRegion(new Texture("90.png")));
}