package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;



public class HUD implements Disposable {

    public Stage stage;
    private Viewport viewport;
    private static Integer score;
    private static Label scorelabel;
    private Label levellabel;
    private Label scorename;
    private Image health;
    private Table table;
    private TextButton button ;
    public HUD(SpriteBatch sb)

    {
        viewport=new StretchViewport (1025,900,new OrthographicCamera ());//2
        stage=new Stage(viewport,sb);//stage is as box and try to put widget and organize things inside that table
        table = new Table();
        table.top();//table at top of our stage
        table.setFillParent(true);//table is now fill all the stage


        //label for gdx
        scorelabel=new Label(String.format("%02d",10),new Label.LabelStyle(new BitmapFont(), Color.GREEN));//label for gdx
        levellabel=new Label("1-1",new Label.LabelStyle(new BitmapFont(), Color.GREEN));//label for gdx
         scorename=new Label("Score",new Label.LabelStyle(new BitmapFont(), Color.GREEN));//label for gdx
      //  health = new Image(new Texture("10%.png"));
        //loading.setSize(40, 10);
        table.add(scorename).expandX().padTop(10);
        table.add(levellabel).expandX().padTop(10);
       // table.add (button).expandX ().padTop ( 10 );


        table.row();
        table.add(scorelabel).expandX();

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