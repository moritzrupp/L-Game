/**
 * ### ACHTUNG: Der Quelltext, sowie alle sonstigen hier gezeigten Erzeugnisse sind urheberrechtlich geschützt.
 *              Weiterverwendung, - verarbeitung oder -verbreitung ist nur mit ausdrücklicher schriftlicher Genehmigung von Moritz Rupp gestattet.
 *              No copy, reproduction or use without written permission of Moritz Rupp.
 *              Copyright (c) 2011 Moritz Rupp
 *              Alle Rechte vorbehalten. All rights reserved.
 */

package lgame.org;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;

import lgame.Main;
import lgame.org.round.States;

/**
 *
 * @author Moritz Rupp
 */
public class Move {

    /**
     * Variables
     */
    private int newGrid = Integer.MIN_VALUE;

    /**
     * Constructor
     */

    public Move() {

        try {

            if(Game.getRound().getState() == States.PLAYER1_FTOKEN) {

                newGrid = Game.getSetting().calcGrid(Game.getPlayer1().getFToken().getPosition().x, Game.getPlayer1().getFToken().getPosition().y);
            }
            else if(Game.getRound().getState() == States.PLAYER1_LTOKEN) {
                
                newGrid = Game.getSetting().calcGrid(Game.getPlayer1().getLToken().getPosition().x, Game.getPlayer1().getLToken().getPosition().y);
            }
            else if(Game.getRound().getState() == States.PLAYER2_FTOKEN) {

                newGrid = Game.getSetting().calcGrid(Game.getPlayer2().getFToken().getPosition().x, Game.getPlayer2().getFToken().getPosition().y);
            }
            else if(Game.getRound().getState() == States.PLAYER2_LTOKEN) {

                newGrid = Game.getSetting().calcGrid(Game.getPlayer2().getLToken().getPosition().x, Game.getPlayer2().getLToken().getPosition().y);
            }
        } catch (Exception ex) {

            Main.verbose("Exception: " + ex + " @Move.java");
        }

        if(validMove(newGrid)) {

            Game.getSetting().untakeGrids();
            Game.getSetting().takeGrids(newGrid);
            
//            Game.getSetting().printGrids();
            
            Game.getRound().isValidMove();
            
            if(Game.getPlayer2().getComp() && Game.getRound().getState() == States.PLAYER2_FTOKEN) {
                
                try {
                    
                    newGrid = Game.getSetting().calcGrid(Game.getPlayer2().getFToken().getPosition().x, Game.getPlayer2().getFToken().getPosition().y);
                } catch (Exception ex) {

                    Main.verbose("Exception: " + ex + " @Move.java");
                }
                
                if(validMove(newGrid)) {
                    
                    Game.getSetting().untakeGrids();
                    Game.getSetting().takeGrids(newGrid);
                    
                    Game.getRound().isValidMove();
                }
            }
            Main.verbose("Roundcounter: " + Game.getRound().getCount());
            
            if(!Game.getSetting().movePossible()) {

                Game.getRound().noMoreMove();
            }
        }
        else {

            Toolkit.getDefaultToolkit().beep();
            Main.verbose("Grid is not free. " + newGrid);
        }
    }

    /**
     * @param ng newgrid
     * @return true: isValidMove - false: invalidMove!
     */
    public final boolean validMove(int ng) {

        // Position existiert bereits
        // Es muss überprüft werden, ob angestrebtes Grid frei ist oder nicht
        // wenn ja, token setzen
        // wenn nein, überprüfen, ob vorher eigener token dort lag
        // wenn ja, dann setzten
        // wenn nein -> error
        // altes grid freigegeben
        // neues grid belegen

        if(Game.getRound().getState() == States.PLAYER1_FTOKEN) {
            
            if(Game.getSetting().checkGrid(ng)) {
                
                return true;
            }
            else {
                
                return false;
            }
        }
        else if(Game.getRound().getState() == States.PLAYER1_LTOKEN) {
            
            int currentShape = Game.getPlayer1().getLToken().getCurrentShape();
            int[] oldGrids = Game.getSetting().getTakenGrid(Game.getPlayer1().getLToken());
            int[] g = new int[4];
            int counter = 0;
            
            Rectangle rec = Game.getPlayer1().getLToken().getShape().getBounds();
            Point p = rec.getLocation();
            
            if(rec.getHeight() == 300) {
                
                if(p.getX() < 50 || p.getX()+200 > 450 || p.getY() < 100 || p.getY()+300 > 500) {
                    
                    return false;
                }
            }
            else if(rec.getHeight() == 200) {
                
                if(p.getX() < 50 || p.getX()+300 > 450 || p.getY() < 100 || p.getY()+200 > 500) {
                    
                    return false;
                }
            }
            
            if(Game.getSetting().checkGrid(ng)) {
                
                if(currentShape == 1) {
                    
                    g[0] = ng-5;
                    g[1] = ng-1;
                    g[2] = ng;
                    g[3] = ng+1;
                       
                    if(oldGrids[0] == g[0] && oldGrids[1] == g[1] && oldGrids[3] == g[3]) {

                        return false;
                    }

                    for(int i = 0; i < g.length; i++) {
                        
                        if(Game.getSetting().checkGrid(g[i])) {
                            
                            counter++;
                        }
                    }
                    
                    if(counter == 4) {
                        
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

                        return false;
                    }
                    
                    for(int i = 0; i < g.length; i++) {
                        
                        if(Game.getSetting().checkGrid(g[i])) {
                            
                            counter++;
                        }
                    }
                    
                    if(counter == 4) {
                        
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

                        return false;
                    }
                    
                    for(int i = 0; i < g.length; i++) {
                        
                        if(Game.getSetting().checkGrid(g[i])) {
                            
                            counter++;
                        }
                    }
                    
                    if(counter == 4) {
                        
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

                        return false;
                    }
                    
                    for(int i = 0; i < g.length; i++) {
                        
                        if(Game.getSetting().checkGrid(g[i])) {
                            
                            counter++;
                        }
                    }
                    
                    if(counter == 4) {
                        
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

                        return false;
                    }
                    
                    for(int i = 0; i < g.length; i++) {
                        
                        if(Game.getSetting().checkGrid(g[i])) {
                            
                            counter++;
                        }
                    }
                    
                    if(counter == 4) {
                        
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

                        return false;
                    }
                    
                    for(int i = 0; i < g.length; i++) {
                        
                        if(Game.getSetting().checkGrid(g[i])) {
                            
                            counter++;
                        }
                    }
                    
                    if(counter == 4) {
                        
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

                        return false;
                    }
                    
                    for(int i = 0; i < g.length; i++) {
                        
                        if(Game.getSetting().checkGrid(g[i])) {
                            
                            counter++;
                        }
                    }
                    
                    if(counter == 4) {
                        
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

                        return false;
                    }
                    
                    for(int i = 0; i < g.length; i++) {
                        
                        if(Game.getSetting().checkGrid(g[i])) {
                            
                            counter++;
                        }
                    }
                    
                    if(counter == 4) {
                        
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
            else {
                
                return false;
            }
        }
        else if(Game.getRound().getState() == States.PLAYER2_FTOKEN) {
            
            if(Game.getSetting().checkGrid(ng)) {
                
                return true;
            }
            else {
                
                return false;
            }
        }
        else if(Game.getRound().getState() == States.PLAYER2_LTOKEN) {
            
            int currentShape = Game.getPlayer2().getLToken().getCurrentShape();
            int[] oldGrids = Game.getSetting().getTakenGrid(Game.getPlayer2().getLToken());
            int[] g = new int[4];
            int counter = 0;
            
            Rectangle rec = Game.getPlayer2().getLToken().getShape().getBounds();
            Point p = rec.getLocation();
            
            if(rec.getHeight() == 300) {
                
                if(p.getX() < 50 || p.getX()+200 > 450 || p.getY() < 100 || p.getY()+300 > 500) {
                    
                    return false;
                }
            }
            else if(rec.getHeight() == 200) {
                
                if(p.getX() < 50 || p.getX()+300 > 450 || p.getY() < 100 || p.getY()+200 > 500) {
                    
                    return false;
                }
            }
            
            if(Game.getSetting().checkGrid(ng)) {
                
                if(currentShape == 1) {
                    
                    g[0] = ng-5;
                    g[1] = ng-1;
                    g[2] = ng;
                    g[3] = ng+1;
                       
                    if(oldGrids[0] == g[0] && oldGrids[1] == g[1] && oldGrids[3] == g[3]) {

                        return false;
                    }

                    for(int i = 0; i < g.length; i++) {
                        
                        if(Game.getSetting().checkGrid(g[i])) {
                            
                            counter++;
                        }
                    }
                    
                    if(counter == 4) {
                        
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

                        return false;
                    }
                    
                    for(int i = 0; i < g.length; i++) {
                        
                        if(Game.getSetting().checkGrid(g[i])) {
                            
                            counter++;
                        }
                    }
                    
                    if(counter == 4) {
                        
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

                        return false;
                    }
                    
                    for(int i = 0; i < g.length; i++) {
                        
                        if(Game.getSetting().checkGrid(g[i])) {
                            
                            counter++;
                        }
                    }
                    
                    if(counter == 4) {
                        
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

                        return false;
                    }
                    
                    for(int i = 0; i < g.length; i++) {
                        
                        if(Game.getSetting().checkGrid(g[i])) {
                            
                            counter++;
                        }
                    }
                    
                    if(counter == 4) {
                        
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

                        return false;
                    }
                    
                    for(int i = 0; i < g.length; i++) {
                        
                        if(Game.getSetting().checkGrid(g[i])) {
                            
                            counter++;
                        }
                    }
                    
                    if(counter == 4) {
                        
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

                        return false;
                    }
                    
                    for(int i = 0; i < g.length; i++) {
                        
                        if(Game.getSetting().checkGrid(g[i])) {
                            
                            counter++;
                        }
                    }
                    
                    if(counter == 4) {
                        
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

                        return false;
                    }
                    
                    for(int i = 0; i < g.length; i++) {
                        
                        if(Game.getSetting().checkGrid(g[i])) {
                            
                            counter++;
                        }
                    }
                    
                    if(counter == 4) {
                        
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

                        return false;
                    }
                    
                    for(int i = 0; i < g.length; i++) {
                        
                        if(Game.getSetting().checkGrid(g[i])) {
                            
                            counter++;
                        }
                    }
                    
                    if(counter == 4) {
                        
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
            else {
                
                return false;
            }
        }
        else {
            
            return false;
        }
    }
}
