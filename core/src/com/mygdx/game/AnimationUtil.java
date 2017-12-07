package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static com.mygdx.game.Constants.FPS;

/**
 * This class contains methods that can be used to animate game objects
 * Created by Giddy on 04/12/2017.
 */

public class AnimationUtil {


    /**
     * This method creates an Animation<textureRegion> from a texture, row, column and frame specifications
     * @param texture
     * @param totalColumn total amount of columns on the texture
     * @param totalRow total amount of rows on the texture
     * @param row which row in the texture sheet
     * @param columns which columns in the texture sheet
     * @return returns a new Animation<textureRegion> object
     */
    public Animation<TextureRegion> makeAnimation(Texture texture, int totalColumn ,int totalRow , int row, int[] columns ){

        //split texture into regions
        TextureRegion[][] textureReg = TextureRegion.split(texture,
                texture.getWidth() / totalColumn,
                texture.getHeight() / totalRow);

        //make 1d collection of regions based on specified row and columns (assumes that all animation frames are in one row)
        TextureRegion[] animatedFrames = new TextureRegion[columns.length];
        int currentRow;
        for (int i = 0; i < columns.length; i++) {
            currentRow = columns[i];
            animatedFrames[i] = textureReg[row][currentRow];
        }

        //returns animation based on 1d texture region
        return new Animation<TextureRegion>(FPS, animatedFrames);
    }

    /**
     * same method as the one above but different parameters.
     * If we use a whole row from a sprite sheet for a frame then we use this method.
     */
    public Animation<TextureRegion> makeAnimation(Texture texture, int totalColumn ,int totalRow , int row ){
        //split texture into regions
        TextureRegion[][] textureReg = TextureRegion.split(texture,
                texture.getWidth() / totalColumn,
                texture.getHeight() / totalRow);
        //make 1d collection of regions based on specified row and columns (assumes that all animation frames are in one row)
        TextureRegion[] animatedFrames = new TextureRegion[totalColumn];
        for (int i = 0; i < totalColumn; i++) {
            animatedFrames[i] = textureReg[row][i];
        }
        //returns animation based on 1d texture region
        return new Animation<TextureRegion>(FPS, animatedFrames);
    }

    /**
     * Animation method used specifically for the character girl
     * @param texture the sprite sheet used
     * @param row a specific row in a sprite sheet
     * @return animation made from assembling frames from a sprite sheet
     */
    public Animation<TextureRegion> makeAnimation(Texture texture, int row){
        TextureRegion[][] textureReg = TextureRegion.split(texture,
                texture.getWidth() / 3,
                texture.getHeight() / 5);
        //make 1d collection of regions based on specified row and columns (assumes that all animation frames are in one row)
        TextureRegion[] animatedFrames = new TextureRegion[3];
        for (int i = 0; i < 3; i++) {
            animatedFrames[i] = textureReg[row][i];
        }
        //returns animation based on 1d texture region
        return new Animation<TextureRegion>(FPS, animatedFrames);
    }




}
