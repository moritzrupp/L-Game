/**s
 * ### ACHTUNG: Der Quelltext, sowie alle sonstigen hier gezeigten Erzeugnisse sind urheberrechtlich geschützt.
 *              Weiterverwendung, - verarbeitung oder -verbreitung ist nur mit ausdrücklicher schriftlicher Genehmigung von Moritz Rupp gestattet.
 *              No copy, reproduction or use without written permission of Moritz Rupp.
 *              Copyright (c) 2011 Moritz Rupp
 *              Alle Rechte vorbehalten. All rights reserved.
 */

package lgame.gui;

/**
 * Imports
 */

import java.awt.*;
import java.awt.geom.*;

/**
 * @author Moritz Rupp
 */
public class Token {

    /**
     * Variables
     */

    private Shape shape; // Shape of token
    private Color color; // color of token
    private Point2D.Float position; // current position of token

    private boolean ltoken;

    /**
     * Constructor
     * @param id ID of player, which
     * @param c Color of the token. Depens on playr
     * @param point The current Position of the token
     */
    public Token(Color c, Point2D.Float point) {

        shape = null;
        color = c;
        position = point;
    }

    /**
     * @return ltoken If token is LToken or not
     */
    public boolean isLToken() {

        return ltoken;
    }

    /**
     * @return shape Returns the shape of token
     */
    public Shape getShape() {

        return shape;
    }

    /**
     * @return color Returns of the color of token
     */
    public Color getColor() {

        return color;
    }

    /**
     * @return position Current position of Token
     */
    public Point2D.Float getPosition() {

        return position;
    }


    /**
     * @param x x-coordinate of current position
     * @param y y-coordinate of current position
     */
    public void setPosition(float x, float y) {

        position.x = x;
        position.y = y;
    }

    /**
     * @param shape Sets the shape of token to "shape"
     */
    public void setShape(Shape shape) {

        this.shape = shape;
    }

    /**
     * @param color Sets the color of token to "color"
     */
    public void setColor(Color color) {

        this.color = color;
    }

    /**
     * @param b Sets boolean ltoken true or false
     */
    public void setLToken(boolean b) {

        ltoken = b;
    }
}
