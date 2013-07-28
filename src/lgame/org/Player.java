/**
 * ### ACHTUNG: Der Quelltext, sowie alle sonstigen hier gezeigten Erzeugnisse sind urheberrechtlich geschützt.
 *              Weiterverwendung, - verarbeitung oder -verbreitung ist nur mit ausdrücklicher schriftlicher Genehmigung von Moritz Rupp gestattet.
 *              No copy, reproduction or use without written permission of Moritz Rupp.
 *              Copyright (c) 2011 Moritz Rupp
 *              Alle Rechte vorbehalten. All rights reserved.
 */

package lgame.org;

/**
 * Imports
 */

import java.awt.Color;

import java.awt.geom.Point2D;
import lgame.gui.*;

/**
 * @author Moritz Rupp
 */
public class Player {

    private int pID; // 0 or 1

    private Color colorOn; // black(38,38,38) or yellow(255,215,0)
    private Color colorOff; // black(38,38,38, 70) or yellow(255,215,0, 70)

    private boolean moving; // moving atm? true or false

    private boolean comp; // comp? true or false

    private LToken ltoken; // Tokens for the player
    private FToken ftoken;
    
    private AI ai; //AI for player 2

    /**
     * Constructor
     * @param id ID of playr
     * @param cmp If Player plays as computer or not
     */

    public Player(int id, boolean cmp) {

        if(id == 1 || id == 0) {

            pID = id;
        }
        else {

            System.out.println("Player ID @Player.java incorrect. Transfer failed.");
        }

        comp = cmp;        
        moving = false;

        if(id == 0) {

            colorOn = new Color(255,215,0);
            colorOff = new Color(255,215,0, 70);
        }
        else if(id == 1) {

            colorOn = new Color(38,38,38);
            colorOff = new Color(38,38,38, 70);
        }
        else {

            System.out.println("Players Color @Player.java incorrect. Setting color failed.");
        }

        /**
         * Creates the tokens
         */

        if(id == 0) {

            ltoken = new LToken(colorOn, new Point2D.Float(200f, 350f));
            ftoken = new FToken(colorOn, new Point2D.Float(50f, 100f));
        }
        else if(id == 1) {

            ltoken = new LToken(colorOn, new Point2D.Float(300f, 250f));
            ftoken = new FToken(colorOn, new Point2D.Float(350f, 400f));
        }
    }

    /**
     * @param b Boolean to set move true or false
     */
    public void setMove(boolean b) {
        
        moving = b;
    }
    
    public void addAI() {
        
        if(pID == 1) {
        
            ai = new AI(this);
        }
    }

    /**
     * @return move If the Player is moving
     */
    public boolean getMove() {

        return moving;
    }

    /**
     * @return ftoken Returns the FToken of player
     */
    public FToken getFToken() {

        return ftoken;
    }

    /**
     * @return ltoken Returns the LToken of player
     */
    public LToken getLToken() {

        return ltoken;
    }

    /**
     * @return pID Returns the ID of player
     */
    public int getID() {

        return pID;
    }

    /**
     * Returns the color depending on moving or not
     * @return colorOn
     * @return colorOff
     */
    public Color getColor() {

        if(moving) {

            return colorOn;
        }
        else {

            return colorOff;
        }
    }
    
    /**
     * @return comp
     */
    public boolean getComp() {
        
        return comp;
    }
    
    public AI getAI() {
        
        return ai;
    }
}
