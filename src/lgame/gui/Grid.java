/**
 * ### ACHTUNG: Der Quelltext, sowie alle sonstigen hier gezeigten Erzeugnisse sind urheberrechtlich geschützt.
 *              Weiterverwendung, - verarbeitung oder -verbreitung ist nur mit ausdrücklicher schriftlicher Genehmigung von Moritz Rupp gestattet.
 *              No copy, reproduction or use without written permission of Moritz Rupp.
 *              Copyright (c) 2011 Moritz Rupp
 *              Alle Rechte vorbehalten. All rights reserved.
 */

package lgame.gui;

import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;
import lgame.org.Game;

/**
 * @author Moritz Rupp
 */
public class Grid extends Rectangle2D.Float {

    
	private static final long serialVersionUID = 4565692403725302417L;
	/**
     * Variables
     */

    //Tokens ltoken and ftoken as object references for checking if a grid is taken by one specific token.
    private LToken ltoken;
    private FToken ftoken;

    private int key; //A key for better recognition.

    private boolean taken; //If a grid is taken this one is true.

    /**
     * Constructor
     * @param size Size of the grid.
     * @param xcord x-position of the grid.
     * @param ycord y-position of the grid.
     * @param k Key for the grid.
     */
    public Grid(float size, float xcord, float ycord, int k) {

        width = size;
        height = size;
        x = xcord;
        y = ycord;

        key = k;
        ltoken = null;
        ftoken = null;

        taken = false;

        setRect(x, y, width, height); //Creating the rectangle as grid element.
    }

    /**
     * takenByPlayer() methode for returning the id of the player who toke the grid.
     * @return 0 Player1
     * @return 1 Player2
     */
    public int takenByPlayer() {

        if(takenByLToken()) {

            if(ltoken == Game.getPlayer1().getLToken()) {

                return 0;
            }
            else {

                return 1;
            }
        }
        else {

            if(ftoken == Game.getPlayer1().getFToken()) {

                return 0;
            }
            else {

                return 1;
            }
        }
    }

    /**
     * takenByLToken() methode for returning whether a grid is taken by LToken or not.
     * @return true/false, wether the grid is taken by LToken or not.
     */
    public boolean takenByLToken() {

        if(ltoken != null && ftoken == null && taken == true) {

            return true;
        }
        else {

            return false;
        }
    }

    /**
     * isTaken() methode for returning whether a grid is taken in generel or not.
     * @return true Grid is taken.
     * @return false Grid is not taken.
     */
    public boolean isTaken() {

        if(taken) {

            return true;
        }
        else {

            return false;
        }
    }

    /**
     * isTakenBy(LToken) methode for checking if a grid is taken by a committed LToken.
     * @param lt LToken which is supposed to lay on the grid.
     * @return true Grid is taken by LToken.
     * @return false Grid is not taken by LToken.
     */
    public boolean isTakenBy(LToken lt) {

        if(ltoken != null && ftoken == null && taken == true) {

            if(lt.equals(ltoken)) {

                return true;
            }
            else {

                return false;
            }
        }
        else {

            return false;
        }
    }

    /**
     * isTakenBy(FToken) methode for checking if a grid is taken by a committed FToken.
     * @param ft FToken which is supposed to lay on the grid.
     * @return true Grid is taken by FToken.
     * @return false Grid is not taken by FToken.
     */
    public boolean isTakenBy(FToken ft) {

        if(ftoken != null && ltoken == null && taken == true) {

            if(ft.equals(ftoken)) {

                return true;
            }
            else {

                return false;
            }
        }
        else {

            return false;
        }
    }

    /**
     * take(LToken) methode for taking a grid by committed LToken.
     * Take the LToken on the grid.
     * @param lt LToken which is supposed to take grid.
     */
    public void take(LToken lt) {

        if(ltoken == null || lt.equals(ltoken)) { //If the ltoken is null or the ltoken is taken by itself.

            ltoken = lt;
            taken = true;
        }
        else {

            Toolkit.getDefaultToolkit().beep();
        }
    }

    /**
     * take(FToken) methode for taking a grid by committed FToken.
     * Take the FToken on the grid.
     * @param ft LToken which is supposed to take grid.
     */
    public void take(FToken ft) {

        if(ftoken == null || ft.equals(ftoken)) { //If the ftoken is null or the grid is taken by ftoken.

            ftoken = ft;
            taken = true;
        }
        else {

            Toolkit.getDefaultToolkit().beep();
        }
    }

    /**
     * untake(LToken) methode for untaking a grid by committed LToken.
     * Untakes the LToken from grid.
     * @param lt LToken which should be untaken from the grid.
     */
    public void untake(LToken lt) {

        if(ltoken != null && lt.equals(ltoken)) { //If the ltoken is the one which is on the grid.

            ltoken = null;
            taken = false;
        }
        else {

            Toolkit.getDefaultToolkit().beep();
        }
    }

    /**
     * untake(FToken) methode for untaking a grid by committed FToken.
     * Untakes the FToken from grid
     * @param ft FToken which should be untaken from the grid
     */
    public void untake(FToken ft) {

        if(ftoken != null && ft.equals(ftoken)) { //If the ftoken is the one which is on the grid.

            ftoken = null;
            taken = false;
        }
        else {

            Toolkit.getDefaultToolkit().beep();
        }
    }

    /**
     * getKey() GETter method for returning the key of a grid.
     * @return key Returns the key of the grid.
     */
    public int getKey() {

        return key;
    }
    
    public LToken getLToken() {
        
        return ltoken;
    }
    
    public FToken getFToken() {
        
        return ftoken;
    }
}
