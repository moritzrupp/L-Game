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
import java.awt.geom.*;

import java.util.*;
import lgame.Main;
import lgame.org.Game;

/**
 * @author Moritz Rupp
 */
public class LToken extends Token {

    /**
     * Variables
     */

    /**
     * 8 different shapes of "L" are possible
     */
    private Shape shape1;
    private Shape shape2;
    private Shape shape3;
    private Shape shape4;
    private Shape shape5;
    private Shape shape6;
    private Shape shape7;
    private Shape shape8;

    private ArrayList<Shape> turns = new ArrayList<Shape>();

    private int currentShape;
    private Grid main = null;

    /**
     * Constructor
     * @param color Sets the color of token. Depends on player
     * @param position Sets th position of token
     */
    public LToken(Color color, Point2D.Float position) {

        super(color, position);

        setLToken(true); // This is a LToken, so ltokn has to be true

        /**
         * Depending on player we have to decide which has to be
         * the first shape of ltoken
         */

        if(getPosition().x == 200f && getPosition().y == 350f) {

            currentShape = 1;
        }
        else if(getPosition().x == 300f && getPosition().y == 250f) {

            currentShape = 3;
        }

        /**
         * Initialize all 8 possible shapes of LToken
         * depending on the current position
         */
        initLToken(getPosition());

        /**
         * Last operation is to set the current shape of LToken
         * to shape1 for player 1 or shape2 for player 2
         */

        if(currentShape == 1) {

            setShape(shape1);
            
        }
        else if(currentShape == 3) {

            setShape(shape3);
        }
    }

    /**
     * Intializing all 8 possible shapes of LToken
     * depending on the current position of LToken
     */
    public void draw() {

        initLToken(getPosition());
    }

    /**
     * Intializing all 8 possible shapes of LToken
     * depending on the current position of LToken
     * @param point Current position of LToken
     */
    private void initLToken(Point2D.Float point) {

        float x = point.x;
        float y = point.y;

        /**
         * Positions of LToken
         * 8 possibilites to go...
         */

        /**
         * 100
         * 111
         */

        float _y1 = y-50;

        Path2D.Float path1 = new Path2D.Float();
        path1.moveTo(x, _y1);
        path1.lineTo(x-50, _y1);
        path1.lineTo(x-50, _y1-100);
        path1.lineTo(x-150, _y1-100);
        path1.lineTo(x-150, _y1+100);
        path1.lineTo(x+150, _y1+100);
        path1.lineTo(x+150, _y1);
        path1.lineTo(x, _y1);
        path1.closePath();
        shape1 = path1;

        /**
         * 11
         * 10
         * 10
         */

        float _x2 = x+50;

        Path2D.Float path2 = new Path2D.Float();
        path2.moveTo(_x2, y);
        path2.lineTo(_x2, y-50);
        path2.lineTo(_x2+100, y-50);
        path2.lineTo(_x2+100, y-150);
        path2.lineTo(_x2-100, y-150);
        path2.lineTo(_x2-100, y+150);
        path2.lineTo(_x2, y+150);
        path2.lineTo(_x2, y);
        path2.closePath();
        shape2 = path2;

        /**
         * 111
         * 001
         */

        float _y3 = y+50;

        Path2D.Float path3 = new Path2D.Float();
        path3.moveTo(x, _y3);
        path3.lineTo(x+50, _y3);
        path3.lineTo(x+50, _y3+100);
        path3.lineTo(x+150, _y3+100);
        path3.lineTo(x+150, _y3-100);
        path3.lineTo(x-150, _y3-100);
        path3.lineTo(x-150, _y3);
        path3.lineTo(x, _y3);
        path3.closePath();
        shape3 = path3;

        /**
         * 01
         * 01
         * 11
         */

        float _x4 = x-50;

        Path2D.Float path4 = new Path2D.Float();
        path4.moveTo(_x4, y);
        path4.lineTo(_x4, y+50);
        path4.lineTo(_x4-100, y+50);
        path4.lineTo(_x4-100, y+150);
        path4.lineTo(_x4+100, y+150);
        path4.lineTo(_x4+100, y-150);
        path4.lineTo(_x4, y-150);
        path4.lineTo(_x4, y);
        path4.closePath();
        shape4 = path4;


        /**
         * Mirrored...
         */

        /**
         * 111
         * 100
         */

        float _y5 = y+50;

        Path2D.Float mPath1 = new Path2D.Float();
        mPath1.moveTo(x, _y5);
        mPath1.lineTo(x-50, _y5);
        mPath1.lineTo(x-50, _y5+100);
        mPath1.lineTo(x-150, _y5+100);
        mPath1.lineTo(x-150, _y5-100);
        mPath1.lineTo(x+150, _y5-100);
        mPath1.lineTo(x+150, _y5);
        mPath1.lineTo(x, _y5);
        mPath1.closePath();
        shape5 = mPath1;

        /**
         * 11
         * 01
         * 01
         */


        float _x6 = x+50;

        Path2D.Float mPath2 = new Path2D.Float();
        mPath2.moveTo(_x6, y);
        mPath2.lineTo(_x6, y+50);
        mPath2.lineTo(_x6+100, y+50);
        mPath2.lineTo(_x6+100, y+150);
        mPath2.lineTo(_x6-100, y+150);
        mPath2.lineTo(_x6-100, y-150);
        mPath2.lineTo(_x6, y-150);
        mPath2.lineTo(_x6, y);
        mPath2.closePath();
        shape6 = mPath2;


        /**
         * 001
         * 111
         */

        float _y7 = y-50;

        Path2D.Float mPath3 = new Path2D.Float();
        mPath3.moveTo(x, _y7);
        mPath3.lineTo(x+50, _y7);
        mPath3.lineTo(x+50, _y7-100);
        mPath3.lineTo(x+150, _y7-100);
        mPath3.lineTo(x+150, _y7+100);
        mPath3.lineTo(x-150, _y7+100);
        mPath3.lineTo(x-150, _y7);
        mPath3.lineTo(x, _y7);
        mPath3.closePath();
        shape7 = mPath3;

        /**
         * 10
         * 10
         * 11
         */

        float _x8 = x-50;

        Path2D.Float mPath4 = new Path2D.Float();
        mPath4.moveTo(_x8, y);
        mPath4.lineTo(_x8, y-50);
        mPath4.lineTo(_x8-100, y-50);
        mPath4.lineTo(_x8-100, y-150);
        mPath4.lineTo(_x8+100, y-150);
        mPath4.lineTo(_x8+100, y+150);
        mPath4.lineTo(_x8, y+150);
        mPath4.lineTo(_x8, y);
        mPath4.closePath();
        shape8 = mPath4;

        turns.clear(); //neccessary, 'cause all shapes will be added again
        turns.add(shape1);
        turns.add(shape2);
        turns.add(shape3);
        turns.add(shape4);
        turns.add(shape5);
        turns.add(shape6);
        turns.add(shape7);
        turns.add(shape8);

        /**
         * Now we have to decide which shape is the current shape
         * so we can set the shape to the exact shape
         */

        if(currentShape == 1) {

            setShape(shape1);
        }
        else if(currentShape == 2) {

            setShape(shape2);
        }
        else if(currentShape == 3) {

            setShape(shape3);
        }
        else if(currentShape == 4) {

            setShape(shape4);
        }
        else if(currentShape == 5) {

            setShape(shape5);
        }
        else if(currentShape == 6) {

            setShape(shape6);
        }
        else if(currentShape == 7) {

            setShape(shape7);
        }
        else if(currentShape == 8) {

            setShape(shape8);
        }
    }
    
    /**
     * @param i Index from 1 to 8 for current shape
     */
    public void setCurrentShape(int i) {

        currentShape = i;
    }
    
    public void setMain(Grid g) {
        
        main = g;
    }

    /**
     * @return turns Returns all 8 possible turns of one LToken in an ArrayList
     */
    public ArrayList<Shape> getTurns() {

        return turns;
    }

    /**
     * @return currentShape Returns the index (1 to 8) of current shape
     */
    public int getCurrentShape() {

        return currentShape;
    }
    
    public Grid getMain() {
        
        return main;
    }
    
    /**
     * @param x x-coordinate of current position
     * @param y y-coordinate of current position
     */
    @Override
    public void setPosition(float x, float y) {

        getPosition().x = x;
        getPosition().y = y;
        try {
            
            main = Game.getSetting().getGrids().get(Game.getSetting().calcGrid(getPosition().x, getPosition().y));
        } catch (Exception ex) {
            
            Main.verbose("Exception: " + ex + " @LToken.java");
        }
    }
}
