package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Created by Hammode on 12/3/17.
 */

public class HealthBar extends ProgressBar {
   Skin myskin = new Skin (Gdx.files.internal ( Constants.skin ) );

    public HealthBar(int width, int height) {

        super(0f, 1f, 0.01f, false, new ProgressBarStyle());
        getStyle().background = getColoredDrawable(width, height, Color.RED);
        getStyle().knob = getColoredDrawable(0, height, Color.GREEN);
        getStyle().knobBefore =getColoredDrawable(width, height, Color.GREEN);

        setWidth(width);
        setHeight(height);

        setAnimateDuration(0.0f);
        setValue(1f);

        setAnimateDuration(0.25f);
    }

    public static Drawable getColoredDrawable(int width, int height, Color color) {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fill();

        TextureRegionDrawable drawable = new TextureRegionDrawable(new TextureRegion (new Texture (pixmap)));

        pixmap.dispose();

        return drawable;
    }
}
