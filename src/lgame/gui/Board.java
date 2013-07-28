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

import java.awt.*;

import java.util.ArrayList;
import java.util.TimerTask;
import java.util.Timer;

import javax.swing.*;
import lgame.Main;

import lgame.org.*;

/**
 * @author Moritz Rupp
 */
public class Board extends JComponent {

    /**
     * Variables
     * All variables are declared as private.
     * grids is declared as a static ArrayList because of its static content.
     */

    
	private static final long serialVersionUID = -534993762630750182L;

	/**
     * Predefined colors for drawing on the board.
     */
    private Color _BOARDCOLOR = new Color(139,26,26);

    private boolean player2comp; //Boolan weather player 2 is computer or not.

    private Game game; //Instance for a game.
    private static final ArrayList<Grid> grids = new ArrayList<Grid>(); //Static ArrayList grids with all 16 grids in it.
    
    private Graphics2D g2d; //Graphics2D object is needed because it is used in class Game.

    /**
     * Fields of board in form of Grids.
     * 16 on the count.
     */
    private Grid grid0;
    private Grid grid1;
    private Grid grid2;
    private Grid grid3;
    private Grid grid4;
    private Grid grid5;
    private Grid grid6;
    private Grid grid7;
    private Grid grid8;
    private Grid grid9;
    private Grid grid10;
    private Grid grid11;
    private Grid grid12;
    private Grid grid13;
    private Grid grid14;
    private Grid grid15;

    /**
     * Constructor
     */
    public Board() {

        setFocusable(true); //Needed for the keyListener

        game = null;
        player2comp = Mainwindow.getPlayer2Comp();

        initGrids();

        /**
         * Timer and TimerTask for painting the canvas at a fixed rate in a periodic way all 33 milliseconds.
         */
        TimerTask tt = new TimerTask() {

            @Override
            public void run() {

                repaint();
            }
        };

        Timer t = new Timer();
        t.scheduleAtFixedRate(tt, 0, 33);
    }
    
    /**
     * paint(Graphics g) methode for painting the canvas.
     * @param Graphics g: Graphics object which will be painted on the canvas.
     */
    @Override
    public void paint(Graphics g) {

        g2d = (Graphics2D) g; //Casting the Grahpics object to an Graphics2D object.

        /**
         * Setting RenderingHints.
         */
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_DEFAULT);

        /**
         * Initializing of board.
         * Call to method drawGrid().
         * Drawing all Grids from 0 to 15 in a for()-loop.
         */
        paintGrid();

        /**
         * Call to method drawNumbers().
         * Writing numbers in field for easier recognition.
         */
        paintNumbers();

        /**
         * Checks if a game is initialized.
         * If it is, it will paint the tokens, if not, it won't paint the tokens.
         */
        if(game != null) {

            game.paint(g2d);
        }
    }

    /**
     * drawGrid() method for painting the grid.
     * Draws all grids from 0 to 15 on JComponent.
     */
    private void paintGrid() {

        g2d.setColor(_BOARDCOLOR); //Set the color to specific color of the board.

        for(int i = 0; i < grids.size(); i++) { //Iterating the 16 grids while drawing each on JComponent.

            g2d.draw(grids.get(i));
        }
    }

    /**
     * drawNumbers() method for painting the numbers in the grid.
     * This Method is just for debugging.
     * Draws numbers from 0 to 15 in the accordingly grid.
     */
    private void paintNumbers() {

        g2d.setColor(_BOARDCOLOR);
        g2d.drawString("0", 100, 150);
        g2d.drawString("1", 200, 150);
        g2d.drawString("2", 300, 150);
        g2d.drawString("3", 400, 150);
        g2d.drawString("4", 100, 250);
        g2d.drawString("5", 200, 250);
        g2d.drawString("6", 300, 250);
        g2d.drawString("7", 400, 250);
        g2d.drawString("8", 100, 350);
        g2d.drawString("9", 200, 350);
        g2d.drawString("10", 300, 350);
        g2d.drawString("11", 400, 350);
        g2d.drawString("12", 100, 450);
        g2d.drawString("13", 200, 450);
        g2d.drawString("14", 300, 450);
        g2d.drawString("15", 400, 450);
    }

    public void newGame(boolean b) {
        
        newGame(g2d, b);
    }

    /**
     * newGame(Graphics2d, boolean) methode for creating a Game object and starting a new game.
     * @param g2d Graphics2D object for drawing something
     * @param b True if player 2 is computer, false if player 2 is not computer
     */
    private void newGame(Graphics2D g, boolean b) {

        if(game != null) {

            initGrids();
            this.removeKeyListener(game.getController());
            Main.verbose("KeyListener removed.");
            Main.verbose("initGrids() was executed.");
        }

        game = new Game(g, b);
        g2d =  game.getG2D();

        this.addKeyListener(game.getController());
        
        Main.verbose("New game started.");
    }

    private void initGrids() {

        /**
         * Initializing of fields
         * The size of one grid is 100x100 pixels
         * Order:
         * 01 02 03 04
         * 05 06 07 08
         * 09 10 11 12
         * 13 14 15 16
         */
        grid0 = new Grid(100f, 50f, 100f, 0);
        grid1 = new Grid(100f, 150f, 100f, 1);
        grid2 = new Grid(100f, 250f, 100f, 2);
        grid3 = new Grid(100f, 350f, 100f, 3);
        grid4 = new Grid(100f, 50f, 200f, 4);
        grid5 = new Grid(100f, 150f, 200f, 5);
        grid6 = new Grid(100f, 250f, 200f, 6);
        grid7 = new Grid(100f, 350f, 200f, 7);
        grid8 = new Grid(100f, 50f, 300f, 8);
        grid9 = new Grid(100f, 150f, 300f, 9);
        grid10 = new Grid(100f, 250f, 300f, 10);
        grid11 = new Grid(100f, 350f, 300f, 11);
        grid12 = new Grid(100f, 50f, 400f, 12);
        grid13 = new Grid(100f, 150f, 400f, 13);
        grid14 = new Grid(100f, 250f, 400f, 14);
        grid15 = new Grid(100f, 350f, 400f, 15);

        /**
         * Adding grids from 0 to 15 to static ArayList of all grids
         */
        grids.clear();

        grids.add(grid0);
        grids.add(grid1);
        grids.add(grid2);
        grids.add(grid3);
        grids.add(grid4);
        grids.add(grid5);
        grids.add(grid6);
        grids.add(grid7);
        grids.add(grid8);
        grids.add(grid9);
        grids.add(grid10);
        grids.add(grid11);
        grids.add(grid12);
        grids.add(grid13);
        grids.add(grid14);
        grids.add(grid15);
    }

    /**
     * getGrids() GETter for static ArrayList grids.
     * @return grids ArrayList<Grid> with all grids in it.
     */
    public static ArrayList<Grid> getGrids() {

        return grids;
    }

    /**
     * getPlayer2comp() GETter for boolean player2comp.
     * @return player2comp If player 2 is (true) computer or not (false).
     */
    public boolean getPlayer2Comp() {

        return player2comp;
    }    
}
