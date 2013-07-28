/**
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

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/**
 * @author Moritz Rupp
 */
public class FToken extends Token {

    /**
     * Variables
     */

    private Shape shape;

    /**
     * Constructor
     * @param st
     */
    public FToken(Color color, Point2D.Float position) {

        super(color, position);

        /**
         * Initialize the FToken
         */
        draw();
    }

    /**
     * Initializing the FToken depending on the current position
     * @param point Current Position of FToken
     */
    private void initFToken(Point2D.Float point) {

        float x = point.x;
        float y = point.y;

        shape = new Ellipse2D.Float(x, y, 100f, 100f); // Creating new ellipse
        setShape(shape); // setting shape of token to ellipse
    }

    /**
     * Initializing the FToken depending on the current position
     */
    public void draw() {

        initFToken(getPosition());
    }
}
