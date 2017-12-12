package com.mygdx.game;

/**
 * This class creates the Phreoni Monster monster with specified starting position
 */

public class PhreoniMonster extends Monster {

    public PhreoniMonster() {
        super(Constants.PHREONI, 4, 3, 2, 4,1152,384);
    }




    /**
     *
     * @param fixedPathMonster
     */
    public boolean monsterFixedPath(Monster fixedPathMonster, int getPlayerPositionSimplifiedX ,int invertedPlayerPostionY ) {
        final int playerPosX = getPlayerPositionSimplifiedX;
        final int playerPosY = invertedPlayerPostionY ;

        if ( fixedPathMonster.move2( playerPosX, playerPosY ) ) {

            return true ;
        }
        return false ;
    }

}
