/**
 * ### ACHTUNG: Der Quelltext, sowie alle sonstigen hier gezeigten Erzeugnisse sind urheberrechtlich geschützt.
 *              Weiterverwendung, - verarbeitung oder -verbreitung ist nur mit ausdrücklicher schriftlicher Genehmigung von Moritz Rupp gestattet.
 *              No copy, reproduction or use without written permission of Moritz Rupp.
 *              Copyright (c) 2011 Moritz Rupp
 *              Alle Rechte vorbehalten. All rights reserved.
 */

package lgame.org.round;

import lgame.org.Game;

/**
 *
 * @author Moritz Rupp
 */
public class Round {

    private States state = States.PLAYER1_LTOKEN;
    private int count;

    public Round() {

        Game.getPlayer1().setMove(true);        
        count = 0;
    }

    public void setState(States s) {

        this.state = s;
    }

    public States getState() {

        return state;
    }

    public void isValidMove() {
        state.isValidMove(this);
        count++;
    }

    public void noMoreMove() {

        state.noMoreMove(this);
    }

    public boolean isInFinalState() {

        return state.isFinal();
    }

    public int getCount() {

        return count/2;
    }
}
