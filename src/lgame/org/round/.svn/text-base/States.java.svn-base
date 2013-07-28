/**
 * ### ACHTUNG: Der Quelltext, sowie alle sonstigen hier gezeigten Erzeugnisse sind urheberrechtlich geschützt.
 *              Weiterverwendung, - verarbeitung oder -verbreitung ist nur mit ausdrücklicher schriftlicher Genehmigung von Moritz Rupp gestattet.
 *              No copy, reproduction or use without written permission of Moritz Rupp.
 *              Copyright (c) 2011 Moritz Rupp
 *              Alle Rechte vorbehalten. All rights reserved.
 */

package lgame.org.round;

import java.io.IOException;
import lgame.Main;
import lgame.org.AI;
import lgame.org.Game;
import lgame.wbs.*;

/**
 *
 * @author Moritz Rupp
 */
public enum States /*implements Transitions*/ {

    PLAYER1_LTOKEN {

        @Override
        public void isValidMove(Round r) {
            
            r.setState(PLAYER1_FTOKEN);
            Game.getPlayer1().setMove(true);
            Game.getPlayer2().setMove(false);
            
            if(!Game.getSetting().movePossible()) {

                Game.getRound().noMoreMove();
            }
        }

        @Override
        public void noMoreMove(Round r) {

            r.setState(LOOSE_P1);
            
            //addWin to move from WBS
            AI ai = Game.getPlayer2().getAI();
            WBS wbs = ai.getWBS();
            
            int shape = Integer.parseInt(ai.getLastMove().getPlayer().getLToken_shape());
            int main = Integer.parseInt(ai.getLastMove().getPlayer().getLToken_main());
            int fgrid = Integer.parseInt(ai.getLastMove().getPlayer().getFToken_grids());
            
            try {
                
                wbs.addWin(ai.getLastSetting(), ai.getLastMove().getPlayer().getLToken_grids(), shape, main, fgrid);
            } catch (IOException ex) {
                
                Main.verbose("IOException: " + ex + " @States.java");
            }
        }

        @Override
        public boolean isFinal() {

            return false;
        }
    },

    PLAYER1_FTOKEN {

        @Override
        public void isValidMove(Round r) {

            r.setState(PLAYER2_LTOKEN);
            Game.getPlayer1().setMove(false);
            Game.getPlayer2().setMove(true);
            
            if(!Game.getSetting().movePossible()) {

                Game.getRound().noMoreMove();
            }
            
            if(Game.getPlayer2().getComp() && Game.getRound().getState() != States.LOOSE_P2) {
                                
                Game.getPlayer2().getAI().doMove();
            }
        }

        @Override
        public void noMoreMove(Round r) {

            r.setState(LOOSE_P1);
        }

        @Override
        public boolean isFinal() {

            return false;
        }
    },

    PLAYER2_LTOKEN {

        @Override
        public void isValidMove(Round r) {

            r.setState(PLAYER2_FTOKEN);
            Game.getPlayer1().setMove(false);
            Game.getPlayer2().setMove(true);
            
            if(!Game.getSetting().movePossible()) {

                Game.getRound().noMoreMove();
            }
            
//            if(Game.getPlayer2().getComp()) {
//                                
//                Game.getPlayer2().getAI().doMove();
//            }
        }

        @Override
        public void noMoreMove(Round r) {

            r.setState(LOOSE_P2);
            
            //Delete move from WBS
            AI ai = Game.getPlayer2().getAI();
            WBS wbs = ai.getWBS();
            
            int shape = Integer.parseInt(ai.getLastMove().getPlayer().getLToken_shape());
            int main = Integer.parseInt(ai.getLastMove().getPlayer().getLToken_main());
            int fgrid = Integer.parseInt(ai.getLastMove().getPlayer().getFToken_grids());
            
            try {
                
                wbs.removeMove(ai.getLastSetting(), ai.getLastMove().getPlayer().getLToken_grids(), shape, main, fgrid);
            } catch (IOException ex) {
                
                Main.verbose("IOException: " + ex + " @States.java");
            }
        }

        @Override
        public boolean isFinal() {

            return false;
        }
    },

    PLAYER2_FTOKEN {

        @Override
        public void isValidMove(Round r) {
            
            r.setState(PLAYER1_LTOKEN);
            Game.getPlayer2().setMove(false);
            Game.getPlayer1().setMove(true);
            
            if(!Game.getSetting().movePossible()) {

                Game.getRound().noMoreMove();
            }
        }

        @Override
        public void noMoreMove(Round r) {

            r.setState(LOOSE_P2);
        }

        @Override
        public boolean isFinal() {

            return false;
        }
    },

    LOOSE_P1 {

        @Override
        public void isValidMove(Round r) {

            r.setState(LOOSE_P1);
        }

        @Override
        public void noMoreMove(Round r) {

            r.setState(LOOSE_P1);
        }

        @Override
        public boolean isFinal() {

            return true;
        }
    },
    
    LOOSE_P2 {

        @Override
        public void isValidMove(Round r) {

            r.setState(LOOSE_P2);
        }

        @Override
        public void noMoreMove(Round r) {

            r.setState(LOOSE_P2);
        }

        @Override
        public boolean isFinal() {

            return true;
        }
    };
    
    abstract public boolean isFinal();
    abstract public void isValidMove(Round r);
    abstract public void noMoreMove(Round r);    
}
