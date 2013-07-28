/*
 * ### ACHTUNG: Der Quelltext, sowie alle sonstigen hier gezeigten Erzeugnisse sind urheberrechtlich geschützt.
 *              Weiterverwendung, - verarbeitung oder -verbreitung ist nur mit ausdrücklicher schriftlicher Genehmigung von Moritz Rupp gestattet.
 *              No copy, reproduction or use without written permission of Moritz Rupp.
 *              Copyright (c) 2011 Moritz Rupp
 *              Alle Rechte vorbehalten. All rights reserved.
 */

package lgame.org;

import java.awt.Toolkit;
import java.awt.event.*;
import java.awt.geom.Point2D;

import lgame.Main;
import lgame.gui.LToken;
import lgame.org.round.*;

/**
 * @author Moritz Rupp
 */
public class Controller extends KeyAdapter {

    private int space = KeyEvent.VK_SPACE;
    private int rarrow = KeyEvent.VK_RIGHT;
    private int larrow = KeyEvent.VK_LEFT;
    private int uarrow = KeyEvent.VK_UP;
    private int darrow = KeyEvent.VK_DOWN;
    private int enter = KeyEvent.VK_ENTER;
    @SuppressWarnings("unused")
	private Point2D.Float np;


    public Controller() {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode() == rarrow) {

            calcNewPosition(rarrow);
        }
        else if(e.getKeyCode() == larrow) {

            calcNewPosition(larrow);
        }
        else if(e.getKeyCode() == uarrow) {

            calcNewPosition(uarrow);
        }
        else if(e.getKeyCode() == darrow) {

            calcNewPosition(darrow);
        }
        else if(e.getKeyCode() == space) {

            if(Game.getRound().getState() == States.PLAYER1_LTOKEN) {

                try {
                    
                    rotateLToken();
                } catch (Exception ex) {
                    
                    System.out.println("Exception: " + ex);
                }
            }
            
            if(!Game.getPlayer2().getComp()) {

                if(Game.getRound().getState() == States.PLAYER2_LTOKEN) {

                    try {

                        rotateLToken();
                    } catch (Exception ex) {

                        System.out.println("Exception: " + ex);
                    }
                }
            }
        }
        else if(e.getKeyCode() == enter) {

            if(Game.getRound().getState() == States.PLAYER1_FTOKEN || Game.getRound().getState() == States.PLAYER1_LTOKEN) {
            
                new Move();
            }
            else if((Game.getRound().getState() == States.PLAYER2_FTOKEN || Game.getRound().getState() == States.PLAYER2_LTOKEN) && !Game.getPlayer2().getComp()) {
                
                new Move();
            }
        }
    }

    private void calcNewPosition(int keyCode) {

        Point2D.Float pos =  new Point2D.Float(0f,0f);
        
        if(keyCode == rarrow) {

            if(Game.getRound().getState() == States.PLAYER1_LTOKEN) {

                pos = Game.getPlayer1().getLToken().getPosition();

                if(Game.getPlayer1().getLToken().getCurrentShape() == 1) {

                    if(pos.x < 300) {

                        pos.x = pos.x+100;
                    }
                    else {

                        Toolkit.getDefaultToolkit().beep();
                    }
                }
                else if(Game.getPlayer1().getLToken().getCurrentShape() == 2) {

                    if(pos.x < 300) {

                        pos.x = pos.x+100;
                    }
                    else {

                        Toolkit.getDefaultToolkit().beep();
                    }
                }
                else if(Game.getPlayer1().getLToken().getCurrentShape() == 3) {

                    if(pos.x < 300) {

                        pos.x = pos.x+100;
                    }
                    else {

                        Toolkit.getDefaultToolkit().beep();
                    }
                }
                else if(Game.getPlayer1().getLToken().getCurrentShape() == 4) {

                    if(pos.x < 400) {

                        pos.x = pos.x+100;
                    }
                    else {

                        Toolkit.getDefaultToolkit().beep();
                    }
                }
                else if(Game.getPlayer1().getLToken().getCurrentShape() == 5) {

                    if(pos.x < 300) {

                        pos.x = pos.x+100;
                    }
                    else {

                        Toolkit.getDefaultToolkit().beep();
                    }
                }
                else if(Game.getPlayer1().getLToken().getCurrentShape() == 6) {

                    if(pos.x < 300) {

                        pos.x = pos.x+100;
                    }
                    else {

                        Toolkit.getDefaultToolkit().beep();
                    }
                }
                else if(Game.getPlayer1().getLToken().getCurrentShape() == 7) {

                    if(pos.x < 300) {

                        pos.x = pos.x+100;
                    }
                    else {

                        Toolkit.getDefaultToolkit().beep();
                    }
                }
                else if(Game.getPlayer1().getLToken().getCurrentShape() == 8) {

                    if(pos.x < 400) {

                        pos.x = pos.x+100;
                    }
                    else {

                        Toolkit.getDefaultToolkit().beep();
                    }
                }

                Game.getPlayer1().getLToken().setPosition(pos.x, pos.y);
                Game.getPlayer1().getLToken().draw();
            }
            else if(Game.getRound().getState() == States.PLAYER1_FTOKEN) {

                pos = Game.getPlayer1().getFToken().getPosition();

                if(pos.x < 350) {

                    pos.x = pos.x+100;
                    Game.getPlayer1().getFToken().setPosition(pos.x, pos.y);
                    Game.getPlayer1().getFToken().draw();
                }
                else {

                    Toolkit.getDefaultToolkit().beep();
                }
            }
            
            if(!Game.getPlayer2().getComp()) {

                if(Game.getRound().getState() == States.PLAYER2_LTOKEN) {

                    pos = Game.getPlayer2().getLToken().getPosition();

                    if(Game.getPlayer2().getLToken().getCurrentShape() == 1) {

                        if(pos.x < 300) {

                            pos.x = pos.x+100;
                        }
                        else {

                            Toolkit.getDefaultToolkit().beep();
                        }
                    }
                    else if(Game.getPlayer2().getLToken().getCurrentShape() == 2) {

                        if(pos.x < 300) {

                            pos.x = pos.x+100;
                        }
                        else {

                            Toolkit.getDefaultToolkit().beep();
                        }
                    }
                    else if(Game.getPlayer2().getLToken().getCurrentShape() == 3) {

                        if(pos.x < 300) {

                            pos.x = pos.x+100;
                        }
                        else {

                            Toolkit.getDefaultToolkit().beep();
                        }
                    }
                    else if(Game.getPlayer2().getLToken().getCurrentShape() == 4) {

                        if(pos.x < 400) {

                            pos.x = pos.x+100;
                        }
                        else {

                            Toolkit.getDefaultToolkit().beep();
                        }
                    }
                    else if(Game.getPlayer2().getLToken().getCurrentShape() == 5) {

                        if(pos.x < 300) {

                            pos.x = pos.x+100;
                        }
                        else {

                            Toolkit.getDefaultToolkit().beep();
                        }
                    }
                    else if(Game.getPlayer2().getLToken().getCurrentShape() == 6) {

                        if(pos.x < 300) {

                            pos.x = pos.x+100;
                        }
                        else {

                            Toolkit.getDefaultToolkit().beep();
                        }
                    }
                    else if(Game.getPlayer2().getLToken().getCurrentShape() == 7) {

                        if(pos.x < 300) {

                            pos.x = pos.x+100;
                        }
                        else {

                            Toolkit.getDefaultToolkit().beep();
                        }
                    }
                    else if(Game.getPlayer2().getLToken().getCurrentShape() == 8) {

                        if(pos.x < 400) {

                            pos.x = pos.x+100;
                        }
                        else {

                            Toolkit.getDefaultToolkit().beep();
                        }
                    }

                    Game.getPlayer2().getLToken().setPosition(pos.x, pos.y);
                    Game.getPlayer2().getLToken().draw();
                }
                else if(Game.getRound().getState() == States.PLAYER2_FTOKEN) {

                    pos = Game.getPlayer2().getFToken().getPosition();

                    if(pos.x < 350) {

                        pos.x = pos.x+100;
                        Game.getPlayer2().getFToken().setPosition(pos.x, pos.y);
                        Game.getPlayer2().getFToken().draw();
                    }
                    else {

                        Toolkit.getDefaultToolkit().beep();
                    }
                }
            }
        }
        else if(keyCode == larrow) {

            if(Game.getRound().getState() == States.PLAYER1_LTOKEN) {

                pos = Game.getPlayer1().getLToken().getPosition();

                if(Game.getPlayer1().getLToken().getCurrentShape() == 1) {

                    if(pos.x > 200) {

                        pos.x = pos.x-100;
                    }
                    else {

                        Toolkit.getDefaultToolkit().beep();
                    }
                }
                else if(Game.getPlayer1().getLToken().getCurrentShape() == 2) {

                    if(pos.x > 100) {

                        pos.x = pos.x-100;
                    }
                    else {

                        Toolkit.getDefaultToolkit().beep();
                    }
                }
                else if(Game.getPlayer1().getLToken().getCurrentShape() == 3) {

                    if(pos.x > 200) {

                        pos.x = pos.x-100;
                    }
                    else {

                        Toolkit.getDefaultToolkit().beep();
                    }
                }
                else if(Game.getPlayer1().getLToken().getCurrentShape() == 4) {

                    if(pos.x > 200) {

                        pos.x = pos.x-100;
                    }
                    else {

                        Toolkit.getDefaultToolkit().beep();
                    }
                }
                else if(Game.getPlayer1().getLToken().getCurrentShape() == 5) {

                    if(pos.x > 200) {

                        pos.x = pos.x-100;
                    }
                    else {

                        Toolkit.getDefaultToolkit().beep();
                    }
                }
                else if(Game.getPlayer1().getLToken().getCurrentShape() == 6) {

                    if(pos.x > 100) {

                        pos.x = pos.x-100;
                    }
                    else {

                        Toolkit.getDefaultToolkit().beep();
                    }
                }
                else if(Game.getPlayer1().getLToken().getCurrentShape() == 7) {

                    if(pos.x > 200) {

                        pos.x = pos.x-100;
                    }
                    else {

                        Toolkit.getDefaultToolkit().beep();
                    }
                }
                else if(Game.getPlayer1().getLToken().getCurrentShape() == 8) {

                    if(pos.x > 200) {

                        pos.x = pos.x-100;
                    }
                    else {

                        Toolkit.getDefaultToolkit().beep();
                    }
                }

                Game.getPlayer1().getLToken().setPosition(pos.x, pos.y);
                Game.getPlayer1().getLToken().draw();
            }
            else if(Game.getRound().getState() == States.PLAYER1_FTOKEN) {

                pos = Game.getPlayer1().getFToken().getPosition();

                if(pos.x > 50) {

                    pos.x = pos.x-100;
                    Game.getPlayer1().getFToken().setPosition(pos.x, pos.y);
                    Game.getPlayer1().getFToken().draw();
                }
                else {

                    Toolkit.getDefaultToolkit().beep();
                }
            }
            
            if(!Game.getPlayer2().getComp()) {

                if(Game.getRound().getState() == States.PLAYER2_LTOKEN) {

                    pos = Game.getPlayer2().getLToken().getPosition();

                    if(Game.getPlayer2().getLToken().getCurrentShape() == 1) {

                        if(pos.x > 200) {

                            pos.x = pos.x-100;
                        }
                        else {

                            Toolkit.getDefaultToolkit().beep();
                        }
                    }
                    else if(Game.getPlayer2().getLToken().getCurrentShape() == 2) {

                        if(pos.x > 100) {

                            pos.x = pos.x-100;
                        }
                        else {

                            Toolkit.getDefaultToolkit().beep();
                        }
                    }
                    else if(Game.getPlayer2().getLToken().getCurrentShape() == 3) {

                        if(pos.x > 200) {

                            pos.x = pos.x-100;
                        }
                        else {

                            Toolkit.getDefaultToolkit().beep();
                        }
                    }
                    else if(Game.getPlayer2().getLToken().getCurrentShape() == 4) {

                        if(pos.x > 200) {

                            pos.x = pos.x-100;
                        }
                        else {

                            Toolkit.getDefaultToolkit().beep();
                        }
                    }
                    else if(Game.getPlayer2().getLToken().getCurrentShape() == 5) {

                        if(pos.x > 200) {

                            pos.x = pos.x-100;
                        }
                        else {

                            Toolkit.getDefaultToolkit().beep();
                        }
                    }
                    else if(Game.getPlayer2().getLToken().getCurrentShape() == 6) {

                        if(pos.x > 100) {

                            pos.x = pos.x-100;
                        }
                        else {

                            Toolkit.getDefaultToolkit().beep();
                        }
                    }
                    else if(Game.getPlayer2().getLToken().getCurrentShape() == 7) {

                        if(pos.x > 200) {

                            pos.x = pos.x-100;
                        }
                        else {

                            Toolkit.getDefaultToolkit().beep();
                        }
                    }
                    else if(Game.getPlayer2().getLToken().getCurrentShape() == 8) {

                        if(pos.x > 200) {

                            pos.x = pos.x-100;
                        }
                        else {

                            Toolkit.getDefaultToolkit().beep();
                        }
                    }

                    Game.getPlayer2().getLToken().setPosition(pos.x, pos.y);
                    Game.getPlayer2().getLToken().draw();
                }
                else if(Game.getRound().getState() == States.PLAYER2_FTOKEN) {

                    pos = Game.getPlayer2().getFToken().getPosition();

                    if(pos.x > 50) {

                        pos.x = pos.x-100;
                        Game.getPlayer2().getFToken().setPosition(pos.x, pos.y);
                        Game.getPlayer2().getFToken().draw();
                    }
                    else {

                        Toolkit.getDefaultToolkit().beep();
                    }
                }
            }
        }
        else if(keyCode == uarrow) {

            if(Game.getRound().getState() == States.PLAYER1_LTOKEN) {

                pos = Game.getPlayer1().getLToken().getPosition();

                if(Game.getPlayer1().getLToken().getCurrentShape() == 1) {

                    if(pos.y > 250) {

                        pos.y = pos.y-100;
                    }
                    else {

                        Toolkit.getDefaultToolkit().beep();
                    }
                }
                else if(Game.getPlayer1().getLToken().getCurrentShape() == 2) {

                    if(pos.y > 250) {

                        pos.y = pos.y-100;
                    }
                    else {

                        Toolkit.getDefaultToolkit().beep();
                    }
                }
                else if(Game.getPlayer1().getLToken().getCurrentShape() == 3) {

                    if(pos.y > 150) {

                        pos.y = pos.y-100;
                    }
                    else {

                        Toolkit.getDefaultToolkit().beep();
                    }
                }
                else if(Game.getPlayer1().getLToken().getCurrentShape() == 4) {

                    if(pos.y > 250) {

                        pos.y = pos.y-100;
                    }
                    else {

                        Toolkit.getDefaultToolkit().beep();
                    }
                }
                else if(Game.getPlayer1().getLToken().getCurrentShape() == 5) {

                    if(pos.y > 150) {

                        pos.y = pos.y-100;
                    }
                    else {

                        Toolkit.getDefaultToolkit().beep();
                    }
                }
                else if(Game.getPlayer1().getLToken().getCurrentShape() == 6) {

                    if(pos.y > 250) {

                        pos.y = pos.y-100;
                    }
                    else {

                        Toolkit.getDefaultToolkit().beep();
                    }
                }
                else if(Game.getPlayer1().getLToken().getCurrentShape() == 7) {

                    if(pos.y > 250) {

                        pos.y = pos.y-100;
                    }
                    else {

                        Toolkit.getDefaultToolkit().beep();
                    }
                }
                else if(Game.getPlayer1().getLToken().getCurrentShape() == 8) {

                    if(pos.y > 250) {

                        pos.y = pos.y-100;
                    }
                    else {

                        Toolkit.getDefaultToolkit().beep();
                    }
                }

                Game.getPlayer1().getLToken().setPosition(pos.x, pos.y);
                Game.getPlayer1().getLToken().draw();
            }
            else if(Game.getRound().getState() == States.PLAYER1_FTOKEN) {

                pos = Game.getPlayer1().getFToken().getPosition();

                if(pos.y > 100) {

                    pos.y = pos.y-100;
                    Game.getPlayer1().getFToken().setPosition(pos.x, pos.y);
                    Game.getPlayer1().getFToken().draw();
                }
                else {

                    Toolkit.getDefaultToolkit().beep();
                }
            }
            
            if(!Game.getPlayer2().getComp()) {

                if(Game.getRound().getState() == States.PLAYER2_LTOKEN) {

                    pos = Game.getPlayer2().getLToken().getPosition();

                    if(Game.getPlayer2().getLToken().getCurrentShape() == 1) {

                        if(pos.y > 250) {

                            pos.y = pos.y-100;
                        }
                        else {

                            Toolkit.getDefaultToolkit().beep();
                        }
                    }
                    else if(Game.getPlayer2().getLToken().getCurrentShape() == 2) {

                        if(pos.y > 250) {

                            pos.y = pos.y-100;
                        }
                        else {

                            Toolkit.getDefaultToolkit().beep();
                        }
                    }
                    else if(Game.getPlayer2().getLToken().getCurrentShape() == 3) {

                        if(pos.y > 150) {

                            pos.y = pos.y-100;
                        }
                        else {

                            Toolkit.getDefaultToolkit().beep();
                        }
                    }
                    else if(Game.getPlayer2().getLToken().getCurrentShape() == 4) {

                        if(pos.y > 250) {

                            pos.y = pos.y-100;
                        }
                        else {

                            Toolkit.getDefaultToolkit().beep();
                        }
                    }
                    else if(Game.getPlayer2().getLToken().getCurrentShape() == 5) {

                        if(pos.y > 150) {

                            pos.y = pos.y-100;
                        }
                        else {

                            Toolkit.getDefaultToolkit().beep();
                        }
                    }
                    else if(Game.getPlayer2().getLToken().getCurrentShape() == 6) {

                        if(pos.y > 250) {

                            pos.y = pos.y-100;
                        }
                        else {

                            Toolkit.getDefaultToolkit().beep();
                        }
                    }
                    else if(Game.getPlayer2().getLToken().getCurrentShape() == 7) {

                        if(pos.y > 250) {

                            pos.y = pos.y-100;
                        }
                        else {

                            Toolkit.getDefaultToolkit().beep();
                        }
                    }
                    else if(Game.getPlayer2().getLToken().getCurrentShape() == 8) {

                        if(pos.y > 250) {

                            pos.y = pos.y-100;
                        }
                        else {

                            Toolkit.getDefaultToolkit().beep();
                        }
                    }

                    Game.getPlayer2().getLToken().setPosition(pos.x, pos.y);
                    Game.getPlayer2().getLToken().draw();
                }
                else if(Game.getRound().getState() == States.PLAYER2_FTOKEN) {

                    pos = Game.getPlayer2().getFToken().getPosition();

                    if(pos.y > 100) {

                        pos.y = pos.y-100;
                        Game.getPlayer2().getFToken().setPosition(pos.x, pos.y);
                        Game.getPlayer2().getFToken().draw();
                    }
                    else {

                        Toolkit.getDefaultToolkit().beep();
                    }
                }
            }
        }
        else if(keyCode == darrow) {

            if(Game.getRound().getState() == States.PLAYER1_LTOKEN) {

                pos = Game.getPlayer1().getLToken().getPosition();

                if(Game.getPlayer1().getLToken().getCurrentShape() == 1) {

                    if(pos.y < 450) {

                        pos.y = pos.y+100;
                    }
                    else {

                        Toolkit.getDefaultToolkit().beep();
                    }
                }
                else if(Game.getPlayer1().getLToken().getCurrentShape() == 2) {

                    if(pos.y < 350) {

                        pos.y = pos.y+100;
                    }
                    else {

                        Toolkit.getDefaultToolkit().beep();
                    }
                }
                else if(Game.getPlayer1().getLToken().getCurrentShape() == 3) {

                    if(pos.y < 350) {

                        pos.y = pos.y+100;
                    }
                    else {

                        Toolkit.getDefaultToolkit().beep();
                    }
                }
                else if(Game.getPlayer1().getLToken().getCurrentShape() == 4) {

                    if(pos.y < 350) {

                        pos.y = pos.y+100;
                    }
                    else {

                        Toolkit.getDefaultToolkit().beep();
                    }
                }
                else if(Game.getPlayer1().getLToken().getCurrentShape() == 5) {

                    if(pos.y < 350) {

                        pos.y = pos.y+100;
                    }
                    else {

                        Toolkit.getDefaultToolkit().beep();
                    }
                }
                else if(Game.getPlayer1().getLToken().getCurrentShape() == 6) {

                    if(pos.y < 350) {

                        pos.y = pos.y+100;
                    }
                    else {

                        Toolkit.getDefaultToolkit().beep();
                    }
                }
                else if(Game.getPlayer1().getLToken().getCurrentShape() == 7) {

                    if(pos.y < 450) {

                        pos.y = pos.y+100;
                    }
                    else {

                        Toolkit.getDefaultToolkit().beep();
                    }
                }
                else if(Game.getPlayer1().getLToken().getCurrentShape() == 8) {

                    if(pos.y < 350) {

                        pos.y = pos.y+100;
                    }
                    else {

                        Toolkit.getDefaultToolkit().beep();
                    }
                }

                Game.getPlayer1().getLToken().setPosition(pos.x, pos.y);
                Game.getPlayer1().getLToken().draw();
            }
            else if(Game.getRound().getState() == States.PLAYER1_FTOKEN) {

                pos = Game.getPlayer1().getFToken().getPosition();

                if(pos.y < 400) {

                    pos.y = pos.y+100;
                    Game.getPlayer1().getFToken().setPosition(pos.x, pos.y);
                    Game.getPlayer1().getFToken().draw();
                }
                else {

                    Toolkit.getDefaultToolkit().beep();
                }
            }
            
            if(!Game.getPlayer2().getComp()) {

                if(Game.getRound().getState() == States.PLAYER2_LTOKEN) {

                    pos = Game.getPlayer2().getLToken().getPosition();

                    if(Game.getPlayer2().getLToken().getCurrentShape() == 1) {

                        if(pos.y < 450) {

                            pos.y = pos.y+100;
                        }
                        else {

                            Toolkit.getDefaultToolkit().beep();
                        }
                    }
                    else if(Game.getPlayer2().getLToken().getCurrentShape() == 2) {

                        if(pos.y < 350) {

                            pos.y = pos.y+100;
                        }
                        else {

                            Toolkit.getDefaultToolkit().beep();
                        }
                    }
                    else if(Game.getPlayer2().getLToken().getCurrentShape() == 3) {

                        if(pos.y < 350) {

                            pos.y = pos.y+100;
                        }
                        else {

                            Toolkit.getDefaultToolkit().beep();
                        }
                    }
                    else if(Game.getPlayer2().getLToken().getCurrentShape() == 4) {

                        if(pos.y < 350) {

                            pos.y = pos.y+100;
                        }
                        else {

                            Toolkit.getDefaultToolkit().beep();
                        }
                    }
                    else if(Game.getPlayer2().getLToken().getCurrentShape() == 5) {

                        if(pos.y < 350) {

                            pos.y = pos.y+100;
                        }
                        else {

                            Toolkit.getDefaultToolkit().beep();
                        }
                    }
                    else if(Game.getPlayer2().getLToken().getCurrentShape() == 6) {

                        if(pos.y < 350) {

                            pos.y = pos.y+100;
                        }
                        else {

                            Toolkit.getDefaultToolkit().beep();
                        }
                    }
                    else if(Game.getPlayer2().getLToken().getCurrentShape() == 7) {

                        if(pos.y < 450) {

                            pos.y = pos.y+100;
                        }
                        else {

                            Toolkit.getDefaultToolkit().beep();
                        }
                    }
                    else if(Game.getPlayer2().getLToken().getCurrentShape() == 8) {

                        if(pos.y < 350) {

                            pos.y = pos.y+100;
                        }
                        else {

                            Toolkit.getDefaultToolkit().beep();
                        }
                    }

                    Game.getPlayer2().getLToken().setPosition(pos.x, pos.y);
                    Game.getPlayer2().getLToken().draw();
                }
                else if(Game.getRound().getState() == States.PLAYER2_FTOKEN) {

                    pos = Game.getPlayer2().getFToken().getPosition();

                    if(pos.y < 400) {

                        pos.y = pos.y+100;
                        Game.getPlayer2().getFToken().setPosition(pos.x, pos.y);
                        Game.getPlayer2().getFToken().draw();
                    }
                    else {

                        Toolkit.getDefaultToolkit().beep();
                    }
                }
            }
        }

        Main.verbose("Position Player1: " + Game.getPlayer1().getLToken().getPosition());
        Main.verbose("Position Player2: " + Game.getPlayer2().getLToken().getPosition());

    }

    /**
     * rotateLToken() methode for rotating the LToken
     * @throws Exception 
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
    public void rotateLToken() throws Exception {

        LToken lt = null;

        if(Game.getRound().getState() == States.PLAYER1_LTOKEN) {

            lt = Game.getPlayer1().getLToken();
        }
        else if(Game.getRound().getState() == States.PLAYER2_LTOKEN && !Game.getPlayer2().getComp()) {

            lt = Game.getPlayer2().getLToken();
        }

        if(lt.getCurrentShape() == 1) {

                lt.setCurrentShape(2);
            }
            else if(lt.getCurrentShape() == 2) {

                lt.setCurrentShape(3);
            }
            else if(lt.getCurrentShape() == 3) {

                lt.setCurrentShape(4);
            }
            else if(lt.getCurrentShape() == 4) {

                lt.setCurrentShape(5);
            }
            else if(lt.getCurrentShape() == 5) {

                lt.setCurrentShape(6);
            }
            else if(lt.getCurrentShape() == 6) {

                lt.setCurrentShape(7);
            }
            else if(lt.getCurrentShape() == 7) {

                lt.setCurrentShape(8);
            }
            else if(lt.getCurrentShape() == 8) {

                lt.setCurrentShape(1);
            }

            lt.setShape(lt.getTurns().get(lt.getCurrentShape()-1));

            Main.verbose("Current Shape LToken Player1: " + Game.getPlayer1().getLToken().getCurrentShape());
            Main.verbose("Current Shape LToken Player2: " + Game.getPlayer2().getLToken().getCurrentShape());
    }
}