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
import java.awt.Graphics2D;
import lgame.org.round.*;

/**
 * @author Moritz Rupp
 */
public class Game {

    /**
     * Variables
     */

    private boolean player2comp;
    private static Graphics2D g2d;

    private static Player player1;
    private static Player player2;

    private static GameSetting setting;
    private static Controller controller;

    private static Round round;

    /**
     * Constructor
     * @param g2d Grahpics2D object for painting  the tokens
     * @param comp Boolean whether player 2 is computer or not
     */
    public Game(Graphics2D g2, boolean comp) {

        g2d = g2;
        player2comp = comp;

        /**
         * Creates the two player
         */

        player1 = new Player(0, false);
        player2 = new Player(1, player2comp);
        
        /**
         * Creates the setting
         */

        setting = new GameSetting();
//        setting.printGrids(); //prints Grids
        
        player1.getLToken().setMain(setting.getGrids().get(9));
        player2.getLToken().setMain(setting.getGrids().get(6));

        /**
         *  Creates the controller
         */

        controller = new Controller();

        /**
         * Creates a new round
         */

        round = new Round();
        round.setState(States.PLAYER1_LTOKEN);
        
        if(player2comp) {
            
            player2.addAI();
        }
    }

    /**
     * Paints the tokens on board
     */
    public void paint(Graphics2D g) {

        int pID = 0;
        if(Game.getRound().getState() == States.LOOSE_P1) {
            
            pID = 2;
        }
        else if(Game.getRound().getState() == States.LOOSE_P2) {
            
            pID = 1;
        }
        String playerLose = "Player " + pID + " wins!";


        g.setColor(player1.getColor());
        g.fill(player1.getFToken().getShape());
        g.fill(player1.getLToken().getShape());
        g.setColor(new Color(38,38,38));
        g.draw(player1.getLToken().getShape());
        g.draw(player1.getFToken().getShape());

        g.setColor(player2.getColor());
        g.fill(player2.getFToken().getShape());
        g.fill(player2.getLToken().getShape());
        g.setColor(new Color(255,215,0));
        g.draw(player2.getLToken().getShape());
        g.draw(player2.getFToken().getShape());
        
        //Current State
        g.setColor(Color.BLACK);
        g.drawString("Current state: ", 50, 40);
        g.setColor(Color.RED);
        g.drawString(Game.getRound().getState().toString(), 160, 40);

        //Current round
        g.setColor(Color.BLACK);
        g.drawString("Current round: ", 50, 70);
        g.drawString(Game.getRound().getCount()+"", 160, 70);

        if(Game.getRound().isInFinalState()) {

            Game.getPlayer1().setMove(false);
            Game.getPlayer2().setMove(false);

            g.setColor(new Color(0, 0, 0, 40));
            g.fillRect((500-300)/2+10, (600-200)/2+10, 300, 200);
            g.setColor(g2d.getBackground());
            g.fillRect((500-300)/2, (600-200)/2, 300, 200);
            g.setColor(Color.RED);
            g.drawRect((500-300)/2, (600-200)/2, 300, 200);

            g.drawString("Game over!", 200, 300);
            g.drawString(playerLose, 200, 315);
        }
    }

    /**
     * @return g2d Returns the Graphics2D object
     */
    public Graphics2D getG2D() {

        return g2d;
    }

    /**
     * @return player1
     */
    public static Player getPlayer1() {

        return player1;
    }

    /**
     * @return player2
     */
    public static Player getPlayer2() {

        return player2;
    }

    /**
     * @return setting
     */
    public static GameSetting getSetting() {

        return setting;
    }

    /**
     * @return round
     */
    public static Round getRound() {

        return round;
    }

    /**
     * @return controller
     */
    public Controller getController() {

        return controller;
    }
}
