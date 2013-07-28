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

import java.awt.geom.Point2D;
import java.util.ArrayList;

import lgame.Main;
import lgame.gui.*;
import lgame.org.round.*;

/**
 * @author Moritz Rupp
 */
public class GameSetting {

    /**
     * Variables
     */

    private final ArrayList<Grid> grids = Board.getGrids();
    
    private ArrayList<Grid> player1LToken = new ArrayList<Grid>();
    private ArrayList<Grid> player2LToken = new ArrayList<Grid>();
    private ArrayList<Grid> ftoken = new ArrayList<Grid>();

    /**
     * Constructor
     */
    public GameSetting() {

        /**
         * Take default grids on start
         */

        // Player1 (a-5), (a-1), a, (a+1)
        grids.get(4).take(Game.getPlayer1().getLToken());
        grids.get(8).take(Game.getPlayer1().getLToken());
        grids.get(9).take(Game.getPlayer1().getLToken());
        grids.get(10).take(Game.getPlayer1().getLToken());

        grids.get(0).take(Game.getPlayer1().getFToken());

        // Player2 (a+5), (a+1), a, (a-1)
        grids.get(11).take(Game.getPlayer2().getLToken());
        grids.get(7).take(Game.getPlayer2().getLToken());
        grids.get(6).take(Game.getPlayer2().getLToken());
        grids.get(5).take(Game.getPlayer2().getLToken());

        grids.get(15).take(Game.getPlayer2().getFToken());

        sortGrids();
    }

    /**
     * Sorts the grids into the specific ArrayLists of player1 or player2
     * depending on which grid is taken by whom
     */

    private void sortGrids() {

        player1LToken.clear();
        player2LToken.clear();
        ftoken.clear();
        
        int id = 1;

        while(id <= 2) {

            if(id == 1) {

                for(int i = 0; i < grids.size(); i++) {

                    if(grids.get(i).isTaken()) {

                        if(grids.get(i).isTakenBy(Game.getPlayer1().getLToken())) {
                            
                            player1LToken.add(grids.get(i));
                        }
                        else if(grids.get(i).isTakenBy(Game.getPlayer1().getFToken())) {
                            
                            ftoken.add(grids.get(i));
                        }
                    }
                }
            }
            else if(id == 2) {

                for(int i = 0; i < grids.size(); i++) {

                    if(grids.get(i).isTaken()) {

                        if(grids.get(i).isTakenBy(Game.getPlayer2().getLToken())) {

                            player2LToken.add(grids.get(i));
                        }
                        else if(grids.get(i).isTakenBy(Game.getPlayer2().getFToken())) {

                            ftoken.add(grids.get(i));
                        }
                    }
                }
            }
            id++;
        }
    }

    public void printGrids() {

        System.out.println("Folgende Grids sind belegt:\n");

        System.out.println("");
        System.out.println("Player1 LToken:");
        for(int i = 0; i < player1LToken.size(); i++) {
            
            System.out.println("• Grid" + player1LToken.get(i).getKey());
        }
        System.out.println("");
        System.out.println("Player2 LToken:");
        for(int i = 0; i < player2LToken.size(); i++) {
            
            System.out.println("• Grid" + player2LToken.get(i).getKey());
        }
        System.out.println("");
        System.out.println("FToken:");
        for(int i = 0; i < ftoken.size(); i++) {
            
            System.out.println("• Grid" + ftoken.get(i).getKey());
        }
        System.out.println("");
    }

    /**
     * @param lt LToken to test
     * @return res Returns the key of grid where the LToken lays on
     */
    public int[] getTakenGrid(LToken lt) {
        
        int[] res = new int[4];
        int j = -1;

        for(int i = 0; i < grids.size(); i++) {

            if(grids.get(i).isTakenBy(lt)) {
                j++;
                res[j] = i;
            }
        }

        return res;
    }

    /**
     * @param ft FToken to test
     * @return res Returns the key of grid where the FToken lays on
     */
    public int getTakenGrid(FToken ft) {

        int res = Integer.MIN_VALUE;

        for(int i = 0; i < grids.size(); i++) {

            if(grids.get(i).isTakenBy(ft)) {

                res = i;
                break;
            }
        }

        return res;
    }

    /**
     * Untakes all grids
     */
    public void untakeAll() {

        for(int i = 0; i < grids.size(); i++) {

            if(grids.get(i).takenByPlayer() == 0) {

                if(grids.get(i).takenByLToken()) {

                    grids.get(i).untake(Game.getPlayer1().getLToken());
                }
                else {

                    grids.get(i).untake(Game.getPlayer1().getFToken());
                }
            }
            else {

                if(grids.get(i).takenByLToken()) {

                    grids.get(i).untake(Game.getPlayer2().getLToken());
                }
                else {

                    grids.get(i).untake(Game.getPlayer2().getFToken());
                }
            }
        }

        player1LToken.clear();
        player2LToken.clear();
        ftoken.clear();
    }

    /**
     * @param a rectangle where token is
     */
    public void takeGrids(int a) {

        if(Game.getRound().getState() == States.PLAYER1_FTOKEN) {

            grids.get(a).take(Game.getPlayer1().getFToken());
        }
        else if(Game.getRound().getState() == States.PLAYER2_FTOKEN) {

            grids.get(a).take(Game.getPlayer2().getFToken());
        }
        else if(Game.getRound().getState() == States.PLAYER1_LTOKEN) {
            
            if(Game.getPlayer1().getLToken().getCurrentShape() == 1) {
                
                // currentShape1: (a-5), (a-1), a, (a+1)
                grids.get(a-5).take(Game.getPlayer1().getLToken());
                grids.get(a-1).take(Game.getPlayer1().getLToken());
                grids.get(a).take(Game.getPlayer1().getLToken());
                grids.get(a+1).take(Game.getPlayer1().getLToken());
            }
            else if(Game.getPlayer1().getLToken().getCurrentShape() == 2) {
                
                // currentShape2: (a-3), (a-4), a, (a+4)
                grids.get(a-3).take(Game.getPlayer1().getLToken());
                grids.get(a-4).take(Game.getPlayer1().getLToken());
                grids.get(a).take(Game.getPlayer1().getLToken());
                grids.get(a+4).take(Game.getPlayer1().getLToken());
            }
            else if(Game.getPlayer1().getLToken().getCurrentShape() == 3) {
                
                // currentShape3: (a+5), (a+1), a, (a-1)
                grids.get(a+5).take(Game.getPlayer1().getLToken());
                grids.get(a+1).take(Game.getPlayer1().getLToken());
                grids.get(a).take(Game.getPlayer1().getLToken());
                grids.get(a-1).take(Game.getPlayer1().getLToken());
            }
            else if(Game.getPlayer1().getLToken().getCurrentShape() == 4) {
                
                // currentShape4: (a+3), (a+4), a, (a-4)
                grids.get(a+3).take(Game.getPlayer1().getLToken());
                grids.get(a+4).take(Game.getPlayer1().getLToken());
                grids.get(a).take(Game.getPlayer1().getLToken());
                grids.get(a-4).take(Game.getPlayer1().getLToken());
            }
            else if(Game.getPlayer1().getLToken().getCurrentShape() == 5) {
                
                //  currentShape5: (a+3), (a-1), a, (a+1)
                grids.get(a+3).take(Game.getPlayer1().getLToken());
                grids.get(a-1).take(Game.getPlayer1().getLToken());
                grids.get(a).take(Game.getPlayer1().getLToken());
                grids.get(a+1).take(Game.getPlayer1().getLToken());
            }
            else if(Game.getPlayer1().getLToken().getCurrentShape() == 6) {
                
                // currentShape6: (a+5), (a+4), a, (a-4)
                grids.get(a+5).take(Game.getPlayer1().getLToken());
                grids.get(a+4).take(Game.getPlayer1().getLToken());
                grids.get(a).take(Game.getPlayer1().getLToken());
                grids.get(a-4).take(Game.getPlayer1().getLToken());
            }
            else if(Game.getPlayer1().getLToken().getCurrentShape() == 7) {
                
                // currentShape7: (a-3), (a+1), a, (a-1)
                grids.get(a-3).take(Game.getPlayer1().getLToken());
                grids.get(a+1).take(Game.getPlayer1().getLToken());
                grids.get(a).take(Game.getPlayer1().getLToken());
                grids.get(a-1).take(Game.getPlayer1().getLToken());
            }
            else if(Game.getPlayer1().getLToken().getCurrentShape() == 8) {
                
                // currentShape8: (a-5), (a-4), a, (a+4)
                grids.get(a-5).take(Game.getPlayer1().getLToken());
                grids.get(a-4).take(Game.getPlayer1().getLToken());
                grids.get(a).take(Game.getPlayer1().getLToken());
                grids.get(a+4).take(Game.getPlayer1().getLToken());
            }
        }
        else if(Game.getRound().getState() == States.PLAYER2_LTOKEN) {

            if(Game.getPlayer2().getLToken().getCurrentShape() == 1) {

                // currentShape1: (a-5), (a-1), a, (a+1)
                grids.get(a-5).take(Game.getPlayer2().getLToken());
                grids.get(a-1).take(Game.getPlayer2().getLToken());
                grids.get(a).take(Game.getPlayer2().getLToken());
                grids.get(a+1).take(Game.getPlayer2().getLToken());
            }
            else if(Game.getPlayer2().getLToken().getCurrentShape() == 2) {
                
                // currentShape2: (a-3), (a-4), a, (a+4)
                grids.get(a-3).take(Game.getPlayer2().getLToken());
                grids.get(a-4).take(Game.getPlayer2().getLToken());
                grids.get(a).take(Game.getPlayer2().getLToken());
                grids.get(a+4).take(Game.getPlayer2().getLToken());
            }
            else if(Game.getPlayer2().getLToken().getCurrentShape() == 3) {
                
                // currentShape3: (a+5), (a+1), a, (a-1)
                grids.get(a+5).take(Game.getPlayer2().getLToken());
                grids.get(a+1).take(Game.getPlayer2().getLToken());
                grids.get(a).take(Game.getPlayer2().getLToken());
                grids.get(a-1).take(Game.getPlayer2().getLToken());
            }
            else if(Game.getPlayer2().getLToken().getCurrentShape() == 4) {
                
                // currentShape4: (a+3), (a+4), a, (a-4)
                grids.get(a+3).take(Game.getPlayer2().getLToken());
                grids.get(a+4).take(Game.getPlayer2().getLToken());
                grids.get(a).take(Game.getPlayer2().getLToken());
                grids.get(a-4).take(Game.getPlayer2().getLToken());
            }
            else if(Game.getPlayer2().getLToken().getCurrentShape() == 5) {
                
                //  currentShape5: (a+3), (a-1), a, (a+1)
                grids.get(a+3).take(Game.getPlayer2().getLToken());
                grids.get(a-1).take(Game.getPlayer2().getLToken());
                grids.get(a).take(Game.getPlayer2().getLToken());
                grids.get(a+1).take(Game.getPlayer2().getLToken());
            }
            else if(Game.getPlayer2().getLToken().getCurrentShape() == 6) {
                
                // currentShape6: (a+5), (a+4), a, (a-4)
                grids.get(a+5).take(Game.getPlayer2().getLToken());
                grids.get(a+4).take(Game.getPlayer2().getLToken());
                grids.get(a).take(Game.getPlayer2().getLToken());
                grids.get(a-4).take(Game.getPlayer2().getLToken());
            }
            else if(Game.getPlayer2().getLToken().getCurrentShape() == 7) {
                
                // currentShape7: (a-3), (a+1), a, (a-1)
                grids.get(a-3).take(Game.getPlayer2().getLToken());
                grids.get(a+1).take(Game.getPlayer2().getLToken());
                grids.get(a).take(Game.getPlayer2().getLToken());
                grids.get(a-1).take(Game.getPlayer2().getLToken());
            }
            else if(Game.getPlayer2().getLToken().getCurrentShape() == 8) {
                
                // currentShape8: (a-5), (a-4), a, (a+4)
                grids.get(a-5).take(Game.getPlayer2().getLToken());
                grids.get(a-4).take(Game.getPlayer2().getLToken());
                grids.get(a).take(Game.getPlayer2().getLToken());
                grids.get(a+4).take(Game.getPlayer2().getLToken());
            }
        }

        sortGrids();
    }

    /**
     * Untakes the grid of the current Token
     */
    public void untakeGrids() {

        int grid = Integer.MIN_VALUE;
        int[] grd = new int[4];

        if(Game.getRound().getState() == States.PLAYER1_FTOKEN) {

            grid = getTakenGrid(Game.getPlayer1().getFToken());
            if(grid != Integer.MIN_VALUE && grid >= 0 && grid <= 15) {

                grids.get(grid).untake(Game.getPlayer1().getFToken());
            }
        }
        else if(Game.getRound().getState() == States.PLAYER2_FTOKEN) {

            grid = getTakenGrid(Game.getPlayer2().getFToken());
            if(grid != Integer.MIN_VALUE && grid >= 0 && grid <= 15) {

                grids.get(grid).untake(Game.getPlayer2().getFToken());
            }
        }
        else if(Game.getRound().getState() == States.PLAYER1_LTOKEN) {

            grd = getTakenGrid(Game.getPlayer1().getLToken());

            for(int i = 0; i < grd.length; i++) {

                if(grd[i] >= 0 && grd[i] <= 15) {

                    grids.get(grd[i]).untake(Game.getPlayer1().getLToken());
                }
            }
        }
        else if(Game.getRound().getState() == States.PLAYER2_LTOKEN) {

            grd = getTakenGrid(Game.getPlayer2().getLToken());

            for(int i = 0; i < grd.length; i++) {

                if(grd[i] >= 0 && grd[i] <= 15) {

                    grids.get(grd[i]).untake(Game.getPlayer2().getLToken());
                }
            }
        }

        sortGrids();
    }

    public Point2D.Float calcPosition(int grid) throws Exception {
        
        /**
         * Grid0: x=100; y=150
         * Grid1: x=200; y=150
         * Grid2: x=300; y=150
         * Grid3: x=400; y=150
         * Grid4: x=100; y=250
         * Grid5: x=200; y=250
         * Grid6: x=300; y=250
         * Grid7: x=400; y=250
         * Grid8: x=100; y=350
         * Grid9: x=200; y=350
         * Grid10: x=300; y=350
         * Grid11: x=400; y=350
         * Grid12: x=100; y=450
         * Grid13: x=200; y=450
         * Grid14: x=300; y=450
         * Grid15: x=400, y=450
         */
        
        Point2D.Float p = null;
        
        if(grid == 0) {
            
            p = new Point2D.Float(100f, 150f);
        }
        else if(grid == 1) {
            
            p = new Point2D.Float(200f, 150f);
        }
        else if(grid == 2) {
            
            p = new Point2D.Float(300f, 150f);
        }
        else if(grid == 3) {
            
            p = new Point2D.Float(400f, 150f);
        }
        else if(grid == 4) {
            
            p = new Point2D.Float(100f, 250f);
        }
        else if(grid == 5) {
            
            p = new Point2D.Float(200f, 250f);
        }
        else if(grid == 6) {
            
            p = new Point2D.Float(300f, 250f);
        }
        else if(grid == 7) {
            
            p = new Point2D.Float(400f, 250f);
        }
        else if(grid == 8) {
            
            p = new Point2D.Float(100f, 350f);
        }
        else if(grid == 9) {
            
            p = new Point2D.Float(200f, 350f);
        }
        else if(grid == 10) {
            
            p = new Point2D.Float(300f, 350f);
        }
        else if(grid == 11) {
            
            p = new Point2D.Float(400f, 350f);
        }
        else if(grid == 12) {
            
            p = new Point2D.Float(100f, 450f);
        }
        else if(grid == 13) {
            
            p = new Point2D.Float(200f, 450f);
        }
        else if(grid == 14) {
            
            p = new Point2D.Float(300f, 450f);
        }
        else if(grid == 15) {
            
            p = new Point2D.Float(400f, 450f);
        }
        else {
            
            throw new Exception("Error during calculating the position.");
        }
        
        return p;
    }
    
    /**
     * @param x x-coordinate of token
     * @param y y-coordinate of token
     * @return key of the grid
     * @throws Exception if it fails
     */
    public int calcGrid(float x, float y) throws Exception {

        /**
         * Grid0: x=100; y=150
         * Grid1: x=200; y=150
         * Grid2: x=300; y=150
         * Grid3: x=400; y=150
         * Grid4: x=100; y=250
         * Grid5: x=200; y=250
         * Grid6: x=300; y=250
         * Grid7: x=400; y=250
         * Grid8: x=100; y=350
         * Grid9: x=200; y=350
         * Grid10: x=300; y=350
         * Grid11: x=400; y=350
         * Grid12: x=100; y=450
         * Grid13: x=200; y=450
         * Grid14: x=300; y=450
         * Grid15: x=400, y=450
         */

        int a = -1;

        if(x >= 50 && x < 150 && y >= 100 && y < 200) {
            //grid0

            a = 0;
        }
        else if(x >= 150 && x < 250 && y >= 100 && y < 200) {
            //grid1

            a = 1;

        }
        else if(x >= 250 && x < 350 && y >= 100 && y < 200) {
            //grid2

            a = 2;

        }
        else if(x >= 350 && x < 450 && y >= 100 && y < 200) {
            //grid3

            a = 3;

        }
        else if(x >= 50 && x < 150 && y >= 200 && y < 300) {
            //grid4

            a = 4;

        }
        else if(x >= 150 && x < 250 && y >= 200 && y < 300) {
            //grid5

            a = 5;

        }
        else if(x >= 250 && x < 350 && y >= 200 && y < 300) {
            //grid6

            a = 6;

        }
        else if(x >= 350 && x < 450 && y >= 200 && y < 300) {
            //grid7

            a = 7;

        }
        else if(x >= 50 && x < 150 && y >= 300 && y < 400) {
            //grid8

            a = 8;

        }
        else if(x >= 150 && x < 250 && y >= 300 && y < 400) {
            //grid9

            a = 9;

        }
        else if(x >= 250 && x < 350 && y >= 300 && y < 400) {
            //grid10

            a = 10;

        }
        else if(x >= 350 && x < 450 && y >= 300 && y < 400) {
            //grid11

            a = 11;

        }
        else if(x >= 50 && x < 150 && y >= 400 && y < 500) {
            //grid12

            a = 12;

        }
        else if(x >= 150 && x < 250 && y >= 400 && y < 500) {
            //grid13

            a = 13;

        }
        else if(x >= 250 && x < 350 && y >= 400 && y < 500) {
            //grid14

            a = 14;

        }
        else if(x >= 350 && x < 450 && y >= 400 && y < 500) {
            //grid15

            a = 15;

        }
        else {

            throw new Exception("Error during calculating the grid. Out of range.");
        }
        
        return a;
    }

    /**
     * @param grid number of grid
     * @param p current Player
     * @return false grid is taken
     * @return true grid is free
     */
    public boolean checkGrid(int grid) {

        boolean b = true;

        if(grids.get(grid).isTaken()) {

            if(Game.getRound().getState() == States.PLAYER1_LTOKEN) {

                if(grids.get(grid).isTakenBy(Game.getPlayer1().getLToken())) {

                    b = true;
                }
                else {

                    b = false;
                }
            }
            else if(Game.getRound().getState() == States.PLAYER1_FTOKEN) {

                if(grids.get(grid).isTakenBy(Game.getPlayer1().getFToken())) {

                    b = true;
                }
                else {

                    b = false;
                }
            }

            if(Game.getRound().getState() == States.PLAYER2_LTOKEN) {

                if(grids.get(grid).isTakenBy(Game.getPlayer2().getLToken())) {

                    b = true;
                }
                else {

                    b = false;
                }
            }
            else if(Game.getRound().getState() == States.PLAYER2_FTOKEN) {

                if(grids.get(grid).isTakenBy(Game.getPlayer2().getFToken())) {

                    b = true;
                }
                else {

                    b = false;
                }
            }
        }

        return b;
    }

    /**
     * movePossible() Decides if the game is over depending on the position of the tokens.
     * If there is another possible position for the LToken the game is not over yet.
     * @return true or false
     *
     * PLAYER
     * currentShape1: (a-5), (a-1), a, (a+1)
     * currentShape2: (a-3), (a-4), a, (a+4)
     * currentShape3: (a+5), (a+1), a, (a-1)
     * currentShape4: (a+3), (a+4), a, (a-4)
     * currentShape5: (a+3), (a-1), a, (a+1)
     * currentShape6: (a+5), (a+4), a, (a-4)
     * currentShape7: (a-3), (a+1), a, (a-1)
     * currentShape8: (a-5), (a-4), a, (a+4)
     */
    public boolean movePossible() {

        ArrayList<Grid> freeGrids = new ArrayList<Grid>();
        int counter = 0;
        
        if(Game.getRound().getState() == States.PLAYER1_LTOKEN || Game.getRound().getState() == States.PLAYER2_LTOKEN) {

            for(int i = 0; i < grids.size(); i++) {

                if(!grids.get(i).isTaken() || grids.get(i).isTakenBy(((Game.getRound().getState() == States.PLAYER1_LTOKEN)) ? Game.getPlayer1().getLToken() : Game.getPlayer2().getLToken())) {

                    freeGrids.add(grids.get(i));
                }
            }
            
            for(int i = 0; i < freeGrids.size(); i++) {
            
                if(freeGrids.get(i).getKey() == 0 || freeGrids.get(i).getKey() == 3 || freeGrids.get(i).getKey() == 12 || freeGrids.get(i).getKey() == 15) {

                    freeGrids.remove(i);
                }
            }
            
            for(int i = 0; i < freeGrids.size(); i++) {

                if(check_sGrid(freeGrids.get(i).getKey())) {

                    counter++;
                }
            }
            
            if(counter > 0) {

                return true;
            }
            else {

                return false;
            }
        }
        else {

            return true;
        }
    }
    
    public boolean selfTaken(int ng, int ns) {
        
        Player p = null;
        
        if(Game.getRound().getState() == States.PLAYER1_FTOKEN || Game.getRound().getState() == States.PLAYER1_LTOKEN) {
            
            p = Game.getPlayer1();
        }
        else if(Game.getRound().getState() == States.PLAYER2_FTOKEN || Game.getRound().getState() == States.PLAYER2_LTOKEN) {
            
            p = Game.getPlayer2();
        }
        
        int currentShape = p.getLToken().getCurrentShape();
        int newShape = ns;
        int[] oldGrids = Game.getSetting().getTakenGrid(p.getLToken());
        int[] g = new int[4];
        
        if(newShape != currentShape) {
            
            return false;
        }
        
        if(currentShape == 1) {
                    
            g[0] = ng-5;
            g[1] = ng-1;
            g[2] = ng;
            g[3] = ng+1;

            if(oldGrids[0] == g[0] && oldGrids[1] == g[1] && oldGrids[3] == g[3]) {

                return true;
            }
            else {
                
                return false;
            }
        }
        else if(currentShape == 2) {

            g[0] = ng-4;
            g[1] = ng-3;
            g[2] = ng;
            g[3] = ng+4;

            if(oldGrids[0] == g[0] && oldGrids[1] == g[1] && oldGrids[3] == g[3]) {

                return true;
            }
            else {
                
                return false;
            }
        }
        else if(currentShape == 3) {

            g[0] = ng-1;
            g[1] = ng;
            g[2] = ng+1;
            g[3] = ng+5;

            if(oldGrids[0] == g[0] && oldGrids[1] == g[1] && oldGrids[3] == g[3]) {

                return true;
            }
            else {
                
                return false;
            }
        }
        else if(currentShape == 4) {

            g[0] = ng-4;
            g[1] = ng;
            g[2] = ng+3;
            g[3] = ng+4;

            if(oldGrids[0] == g[0] && oldGrids[1] == g[1] && oldGrids[3] == g[3]) {

                return true;
            }
            else {
                
                return false;
            }
        }
        else if(currentShape == 5) {

            g[0] = ng-1;
            g[1] = ng;
            g[2] = ng+1;
            g[3] = ng+3;

            if(oldGrids[0] == g[0] && oldGrids[1] == g[1] && oldGrids[3] == g[3]) {

                return true;
            }
            else {
                
                return false;
            }
        }
        else if(currentShape == 6) {

            g[0] = ng-4;
            g[1] = ng;
            g[2] = ng+4;
            g[3] = ng+5;

            if(oldGrids[0] == g[0] && oldGrids[1] == g[1] && oldGrids[3] == g[3]) {

                return true;
            }
            else {
                
                return false;
            }
        }
        else if(currentShape == 7) {

            g[0] = ng-3;
            g[1] = ng-1;
            g[2] = ng;
            g[3] = ng+1;

            if(oldGrids[0] == g[0] && oldGrids[1] == g[1] && oldGrids[3] == g[3]) {

                return true;
            }
            else {
                
                return false;
            }
        }
        else if(currentShape == 8) {

            g[0] = ng-5;
            g[1] = ng-4;
            g[2] = ng;
            g[3] = ng+4;

            if(oldGrids[0] == g[0] && oldGrids[1] == g[1] && oldGrids[3] == g[3]) {

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
        
    //inkl. selfTaken-Check
    public boolean check_sGrid(int sGrid) {

        /*
         * PLAYER
         * currentShape1: (a-5), (a-1), a, (a+1)
         * currentShape2: (a-3), (a-4), a, (a+4)
         * currentShape3: (a+5), (a+1), a, (a-1)
         * currentShape4: (a+3), (a+4), a, (a-4)
         * currentShape5: (a+3), (a-1), a, (a+1)
         * currentShape6: (a+5), (a+4), a, (a-4)
         * currentShape7: (a-3), (a+1), a, (a-1)
         * currentShape8: (a-5), (a-4), a, (a+4)
         */            
        if(sGrid == 6) {
            
            if((checkGrid(sGrid-5) && checkGrid(sGrid-1) && checkGrid(sGrid) && checkGrid(sGrid+1))) { //Shape 1.

                if(!Game.getSetting().selfTaken(sGrid, 1)) {
                
                    Main.verbose("Move is possible. sGrid==6; Shape1");
                    return true;
                }
            }
            
            if((checkGrid(sGrid-3) && checkGrid(sGrid-4) && checkGrid(sGrid) && checkGrid(sGrid+4))) { //Shape 2.
                
                if(!Game.getSetting().selfTaken(sGrid, 2)) {
                
                    Main.verbose("Move is possible. sGrid==6; Shape2");
                    return true;
                }
            }
            if((checkGrid(sGrid+5) && checkGrid(sGrid+1) && checkGrid(sGrid) && checkGrid(sGrid-1))) { //Shape 3.
                
                if(!Game.getSetting().selfTaken(sGrid, 3)) {
                
                    Main.verbose("Move is possible. sGrid==6; Shape3");
                    return true;
                }
            }
            
            if((checkGrid(sGrid+3) && checkGrid(sGrid+4) && checkGrid(sGrid) && checkGrid(sGrid-4))) { //Shape 4.
                
                if(!Game.getSetting().selfTaken(sGrid, 4)) {
                
                    Main.verbose("Move is possible. sGrid==6; Shape4");
                    return true;
                }
            }
            
            if((checkGrid(sGrid+3) && checkGrid(sGrid-1) && checkGrid(sGrid) && checkGrid(sGrid+1))) { //Shape 5.
                
                if(!Game.getSetting().selfTaken(sGrid, 5)) {
                
                    Main.verbose("Move is possible. sGrid==6; Shape5");
                    return true;
                }
            }
            
            if((checkGrid(sGrid+5) && checkGrid(sGrid+4) && checkGrid(sGrid) && checkGrid(sGrid-4))) { //Shape 6.
                
                if(!Game.getSetting().selfTaken(sGrid, 6)) {
                
                    Main.verbose("Move is possible. sGrid==6; Shape6");
                    return true;
                }
            }
            
            if(checkGrid(sGrid-3) && checkGrid(sGrid+1) && checkGrid(sGrid) && checkGrid(sGrid-1)) { //Shape 7.
                
                if(!Game.getSetting().selfTaken(sGrid, 7)) {
                
                    Main.verbose("Move is possible. sGrid==6; Shape7");
                    return true;
                }
            }
            
            if((checkGrid(sGrid-5) && checkGrid(sGrid-4) && checkGrid(sGrid) && checkGrid(sGrid+4))) { //Shape 8.
                
                if(!Game.getSetting().selfTaken(sGrid, 8)) {
                
                    Main.verbose("Move is possible. sGrid==6; Shape8");
                    return true;
                }
            }
        }
        
        if(sGrid == 9) {
            
            if((checkGrid(sGrid-5) && checkGrid(sGrid-1) && checkGrid(sGrid) && checkGrid(sGrid+1))) { //Shape 1.

                if(!Game.getSetting().selfTaken(sGrid, 1)) {
                
                    Main.verbose("Move is possible. sGrid==9; Shape1");
                    return true;
                }
            }
            
            if((checkGrid(sGrid-3) && checkGrid(sGrid-4) && checkGrid(sGrid) && checkGrid(sGrid+4))) { //Shape 2.
                
                if(!Game.getSetting().selfTaken(sGrid, 2)) {
                
                    Main.verbose("Move is possible. sGrid==9; Shape2");
                    return true;
                }
            }
            if((checkGrid(sGrid+5) && checkGrid(sGrid+1) && checkGrid(sGrid) && checkGrid(sGrid-1))) { //Shape 3.
                
                if(!Game.getSetting().selfTaken(sGrid, 3)) {
                
                    Main.verbose("Move is possible. sGrid==9; Shape3");
                    return true;
                }
            }
            
            if((checkGrid(sGrid+3) && checkGrid(sGrid+4) && checkGrid(sGrid) && checkGrid(sGrid-4))) { //Shape 4.
                
                if(!Game.getSetting().selfTaken(sGrid, 4)) {
                
                    Main.verbose("Move is possible. sGrid==9; Shape4");
                    return true;
                }
            }
            
            if((checkGrid(sGrid+3) && checkGrid(sGrid-1) && checkGrid(sGrid) && checkGrid(sGrid+1))) { //Shape 5.
                
                if(!Game.getSetting().selfTaken(sGrid, 5)) {
                
                    Main.verbose("Move is possible. sGrid==9; Shape5");
                    return true;
                }
            }
            
            if((checkGrid(sGrid+5) && checkGrid(sGrid+4) && checkGrid(sGrid) && checkGrid(sGrid-4))) { //Shape 6.
                
                if(!Game.getSetting().selfTaken(sGrid, 6)) {
                
                    Main.verbose("Move is possible. sGrid==9; Shape6");
                    return true;
                }
            }
            
            if(checkGrid(sGrid-3) && checkGrid(sGrid+1) && checkGrid(sGrid) && checkGrid(sGrid-1)) { //Shape 7.
                
                if(!Game.getSetting().selfTaken(sGrid, 7)) {
                
                    Main.verbose("Move is possible. sGrid==9; Shape7");
                    return true;
                }
            }
            
            if((checkGrid(sGrid-5) && checkGrid(sGrid-4) && checkGrid(sGrid) && checkGrid(sGrid+4))) { //Shape 8.
                
                if(!Game.getSetting().selfTaken(sGrid, 8)) {
                
                    Main.verbose("Move is possible. sGrid==9; Shape8");
                    return true;
                }
            }
        }
        
        if(sGrid == 10) {
            
            if((checkGrid(sGrid-5) && checkGrid(sGrid-1) && checkGrid(sGrid) && checkGrid(sGrid+1))) { //Shape 1.

                if(!Game.getSetting().selfTaken(sGrid, 1)) {
                
                    Main.verbose("Move is possible. sGrid==10; Shape1");
                    return true;
                }
            }
            
            if((checkGrid(sGrid-3) && checkGrid(sGrid-4) && checkGrid(sGrid) && checkGrid(sGrid+4))) { //Shape 2.
                
                if(!Game.getSetting().selfTaken(sGrid, 2)) {
                
                    Main.verbose("Move is possible. sGrid==10; Shape2");
                    return true;
                }
            }
            if((checkGrid(sGrid+5) && checkGrid(sGrid+1) && checkGrid(sGrid) && checkGrid(sGrid-1))) { //Shape 3.
                
                if(!Game.getSetting().selfTaken(sGrid, 3)) {
                
                    Main.verbose("Move is possible. sGrid==10; Shape3");
                    return true;
                }
            }
            
            if((checkGrid(sGrid+3) && checkGrid(sGrid+4) && checkGrid(sGrid) && checkGrid(sGrid-4))) { //Shape 4.
                
                if(!Game.getSetting().selfTaken(sGrid, 4)) {
                
                    Main.verbose("Move is possible. sGrid==10; Shape4");
                    return true;
                }
            }
            
            if((checkGrid(sGrid+3) && checkGrid(sGrid-1) && checkGrid(sGrid) && checkGrid(sGrid+1))) { //Shape 5.
                
                if(!Game.getSetting().selfTaken(sGrid, 5)) {
                
                    Main.verbose("Move is possible. sGrid==10; Shape5");
                    return true;
                }
            }
            
            if((checkGrid(sGrid+5) && checkGrid(sGrid+4) && checkGrid(sGrid) && checkGrid(sGrid-4))) { //Shape 6.
                
                if(!Game.getSetting().selfTaken(sGrid, 6)) {
                
                    Main.verbose("Move is possible. sGrid==10; Shape6");
                    return true;
                }
            }
            
            if(checkGrid(sGrid-3) && checkGrid(sGrid+1) && checkGrid(sGrid) && checkGrid(sGrid-1)) { //Shape 7.
                
                if(!Game.getSetting().selfTaken(sGrid, 7)) {
                
                    Main.verbose("Move is possible. sGrid==10; Shape7");
                    return true;
                }
            }
            
            if((checkGrid(sGrid-5) && checkGrid(sGrid-4) && checkGrid(sGrid) && checkGrid(sGrid+4))) { //Shape 8.
                
                if(!Game.getSetting().selfTaken(sGrid, 8)) {
                
                    Main.verbose("Move is possible. sGrid==10; Shape8");
                    return true;
                }
            }
        }
        
        if(sGrid == 5) {

            if((checkGrid(sGrid-5) && checkGrid(sGrid-1) && checkGrid(sGrid) && checkGrid(sGrid+1))) { //Shape 1.

                if(!Game.getSetting().selfTaken(sGrid, 1)) {
                
                    Main.verbose("Move is possible. sGrid==5; Shape1");
                    return true;
                }
            }
            
            if((checkGrid(sGrid-3) && checkGrid(sGrid-4) && checkGrid(sGrid) && checkGrid(sGrid+4))) { //Shape 2.
                
                if(!Game.getSetting().selfTaken(sGrid, 2)) {
                
                    Main.verbose("Move is possible. sGrid==5; Shape2");
                    return true;
                }
            }
            if((checkGrid(sGrid+5) && checkGrid(sGrid+1) && checkGrid(sGrid) && checkGrid(sGrid-1))) { //Shape 3.
                
                if(!Game.getSetting().selfTaken(sGrid, 3)) {
                
                    Main.verbose("Move is possible. sGrid==5; Shape3");
                    return true;
                }
            }
            
            if((checkGrid(sGrid+3) && checkGrid(sGrid+4) && checkGrid(sGrid) && checkGrid(sGrid-4))) { //Shape 4.
                
                if(!Game.getSetting().selfTaken(sGrid, 4)) {
                
                    Main.verbose("Move is possible. sGrid==5; Shape4");
                    return true;
                }
            }
            
            if((checkGrid(sGrid+3) && checkGrid(sGrid-1) && checkGrid(sGrid) && checkGrid(sGrid+1))) { //Shape 5.
                
                if(!Game.getSetting().selfTaken(sGrid, 5)) {
                
                    Main.verbose("Move is possible. sGrid==5; Shape5");
                    return true;
                }
            }
            
            if((checkGrid(sGrid+5) && checkGrid(sGrid+4) && checkGrid(sGrid) && checkGrid(sGrid-4))) { //Shape 6.
                
                if(!Game.getSetting().selfTaken(sGrid, 6)) {
                
                    Main.verbose("Move is possible. sGrid==5; Shape6");
                    return true;
                }
            }
            
            if(checkGrid(sGrid-3) && checkGrid(sGrid+1) && checkGrid(sGrid) && checkGrid(sGrid-1)) { //Shape 7.
                
                if(!Game.getSetting().selfTaken(sGrid, 7)) {
                
                    Main.verbose("Move is possible. sGrid==5; Shape7");
                    return true;
                }
            }
            
            if((checkGrid(sGrid-5) && checkGrid(sGrid-4) && checkGrid(sGrid) && checkGrid(sGrid+4))) { //Shape 8.
                
                if(!Game.getSetting().selfTaken(sGrid, 8)) {
                
                    Main.verbose("Move is possible. sGrid==5; Shape8");
                    return true;
                }
            }
        }
        
        if(sGrid == 4){

            if((checkGrid(sGrid-3) && checkGrid(sGrid-4) && checkGrid(sGrid) && checkGrid(sGrid+4))) { //Shape 2.
                
                if(!Game.getSetting().selfTaken(sGrid, 2)) {
                
                    Main.verbose("Move is possible. sGrid==4; Shape2");
                    return true;
                }
            }
            
            if((checkGrid(sGrid+5) && checkGrid(sGrid+4) && checkGrid(sGrid) && checkGrid(sGrid-4))) { //Shape 6.
                
                if(!Game.getSetting().selfTaken(sGrid, 6)) {
                
                    Main.verbose("Move is possible. sGrid==4; Shape6");
                    return true;
                }
            }
        }
        
        if(sGrid == 8) {
            
            if((checkGrid(sGrid-3) && checkGrid(sGrid-4) && checkGrid(sGrid) && checkGrid(sGrid+4))) { //Shape 2.
                
                if(!Game.getSetting().selfTaken(sGrid, 2)) {
                
                    Main.verbose("Move is possible. sGrid==8; Shape2");
                    return true;
                }
            }
            
            if((checkGrid(sGrid+5) && checkGrid(sGrid+4) && checkGrid(sGrid) && checkGrid(sGrid-4))) { //Shape 6.
                
                if(!Game.getSetting().selfTaken(sGrid, 6)) {
                
                    Main.verbose("Move is possible. sGrid==8; Shape6");
                    return true;
                }
            }
        }
        
        if(sGrid == 7) {

            if((checkGrid(sGrid+3) && checkGrid(sGrid+4) && checkGrid(sGrid) && checkGrid(sGrid-4))) { //Shape 4.
                
                if(!Game.getSetting().selfTaken(sGrid, 4)) {
                
                    Main.verbose("Move is possible. sGrid==7; Shape4");
                    return true;
                }
            }
            
            if((checkGrid(sGrid-5) && checkGrid(sGrid-4) && checkGrid(sGrid) && checkGrid(sGrid+4))) { //Shape 8.
                
                if(!Game.getSetting().selfTaken(sGrid, 8)) {
                
                    Main.verbose("Move is possible. sGrid==7; Shape8");
                    return true;
                }
            }
        }
        
        if(sGrid == 11) {
            
            if((checkGrid(sGrid+3) && checkGrid(sGrid+4) && checkGrid(sGrid) && checkGrid(sGrid-4))) { //Shape 4.
                
                if(!Game.getSetting().selfTaken(sGrid, 4)) {
                
                    Main.verbose("Move is possible. sGrid==11; Shape4");
                    return true;
                }
            }
            
            if((checkGrid(sGrid-5) && checkGrid(sGrid-4) && checkGrid(sGrid) && checkGrid(sGrid+4))) { //Shape 8.
                
                if(!Game.getSetting().selfTaken(sGrid, 8)) {
                
                    Main.verbose("Move is possible. sGrid==11; Shape8");
                    return true;
                }
            }
        }
        
        if(sGrid == 1) {

            if((checkGrid(sGrid+5) && checkGrid(sGrid+1) && checkGrid(sGrid) && checkGrid(sGrid-1))) { //Shape 3.
                
                if(!Game.getSetting().selfTaken(sGrid, 3)) {
                
                    Main.verbose("Move is possible. sGrid==1; Shape3");
                    return true;
                }
            }
            
            if((checkGrid(sGrid+3) && checkGrid(sGrid-1) && checkGrid(sGrid) && checkGrid(sGrid+1))) { //Shape 5.
                
                if(!Game.getSetting().selfTaken(sGrid, 5)) {
                
                    Main.verbose("Move is possible. sGrid==1; Shape5");
                    return true;
                }
            }
        }
        
        if(sGrid == 2) {
            
            if((checkGrid(sGrid+5) && checkGrid(sGrid+1) && checkGrid(sGrid) && checkGrid(sGrid-1))) { //Shape 3.
                
                if(!Game.getSetting().selfTaken(sGrid, 3)) {
                
                    Main.verbose("Move is possible. sGrid==2; Shape3");
                    return true;
                }
            }
            
            if((checkGrid(sGrid+3) && checkGrid(sGrid-1) && checkGrid(sGrid) && checkGrid(sGrid+1))) { //Shape 5.
                
                if(!Game.getSetting().selfTaken(sGrid, 5)) {
                
                    Main.verbose("Move is possible. sGrid==2; Shape5");
                    return true;
                }
            }
        }
        
        if(sGrid == 13) {

            if((checkGrid(sGrid-5) && checkGrid(sGrid-1) && checkGrid(sGrid) && checkGrid(sGrid+1))) { //Shape 1.

                if(!Game.getSetting().selfTaken(sGrid, 1)) {
                
                    Main.verbose("Move is possible. sGrid==13; Shape1");
                    return true;
                }
            }
            
            if(checkGrid(sGrid-3) && checkGrid(sGrid+1) && checkGrid(sGrid) && checkGrid(sGrid-1)) { //Shape 7.
                
                if(!Game.getSetting().selfTaken(sGrid, 7)) {
                
                    Main.verbose("Move is possible. sGrid==13; Shape7");
                    return true;
                }
            }
        }
        
        if(sGrid == 14) {
            
            if((checkGrid(sGrid-5) && checkGrid(sGrid-1) && checkGrid(sGrid) && checkGrid(sGrid+1))) { //Shape 1.

                if(!Game.getSetting().selfTaken(sGrid, 1)) {
                
                    Main.verbose("Move is possible. sGrid==14; Shape1");
                    return true;
                }
            }
            
            if(checkGrid(sGrid-3) && checkGrid(sGrid+1) && checkGrid(sGrid) && checkGrid(sGrid-1)) { //Shape 7.
                
                if(!Game.getSetting().selfTaken(sGrid, 7)) {
                
                    Main.verbose("Move is possible. sGrid==14; Shape7");
                    return true;
                }
            }
        }
        Main.verbose("Move is not possible in any position.");
        return false;
    }
    
    public ArrayList<Grid> getPlayer1LToken() {
        
        return player1LToken;
    }
    
    public ArrayList<Grid> getPlayer2LToken() {
        
        return player2LToken;
    }
    
    public ArrayList<Grid> getFToken() {
        
        return ftoken;
    }
    
    public ArrayList<Grid> getGrids() {
        
        return grids;
    }
}