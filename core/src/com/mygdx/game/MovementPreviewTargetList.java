package com.mygdx.game;

import com.badlogic.gdx.utils.Array;

/**
 * Created by Giddy on 10/12/2017.
 */

public class MovementPreviewTargetList {
    Array<MovementPreviewTarget> previewTargetArray;
    public MovementPreviewTargetList(int playerX, int playerY){
        previewTargetArray = new Array<MovementPreviewTarget>();
        update(playerX, playerY);
    }
    /*
    public void update(int playerX, int playerY) {
        //basically do the movement collision and add a preview icon everywhere the player can go to
        else if ((Math.abs(differenceInPositionX) < 3 && differenceInPositionY == 0)) {
            // set the animation for the horizontal movement with clothes
            if (collisionCheck(differenceInPositionX, differenceInPositionY)) {
                if (Math.signum((float) differenceInPositionX * tileWidth) == -1) {

                } else if (Math.signum((int) differenceInPositionX * tileWidth) == 1) {

                }
            }
        }
        else if( ( Math.abs(differenceInPositionY)<3 && differenceInPositionX==0 ) ) {
            //attempt at vertical movement - may be still blocked by collision, so let's check for that
            if (collisionCheck(differenceInPositionX, differenceInPositionY)) {
                // set the animation for the vertical movment with clothes
                if (Math.signum((float) differenceInPositionY) == -1) {


                } else if (Math.signum((float) differenceInPositionY) == 1) {


                }
            }
        }
    }
*/

}
