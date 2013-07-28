/*
 * ### ACHTUNG: Der Quelltext, sowie alle sonstigen hier gezeigten Erzeugnisse sind urheberrechtlich geschützt.
 *              Weiterverwendung, - verarbeitung oder -verbreitung ist nur mit ausdrücklicher schriftlicher Genehmigung von Moritz Rupp gestattet.
 *              No copy, reproduction or use without written permission of Moritz Rupp.
 *              Copyright (c) 2011 Moritz Rupp
 *              Alle Rechte vorbehalten. All rights reserved.
 */

package lgame.org;

import java.io.IOException;
import java.util.ArrayList;
import lgame.Main;
import lgame.gui.Grid;
import lgame.org.round.States;

import lgame.wbs.*;
import org.jdom.JDOMException;

/**
 *
 * @author Moritz Rupp
 */
public class AI {
    

    private ArrayList<Grid> free_ltoken_grids;
    private ArrayList<Grid> free_ftoken_grids;
    
    private int newGrid_l;
    private int newGrid_f;
    
    private WBS wbs;
    private String lgrid;
    private int[] l;
    private int main;
    private int shape;
    private int ftoken;
    private int id_s;
    
    private _Move lastMove;
    private _Setting lastSetting;
    
    private Player player;
        
    public AI(Player p) {
        
        player = p;
        
        free_ltoken_grids = new ArrayList<Grid>();
        free_ftoken_grids = new ArrayList<Grid>();
        
        lastMove = null;
        lastSetting = null;
        
        try {
            
            wbs = new WBS();
        } catch (JDOMException ex) {
            
            Main.verbose("Exception: " + ex + " @AI.java");
        } catch (IOException ex) {
            
            Main.verbose("Exception: " + ex + " @AI.java");
        }
    }
    
    private _Setting createSetting() {
        
        try {
            
            //p1
            l = Game.getSetting().getTakenGrid(Game.getPlayer1().getLToken());
            lgrid = l[0] + ";" + l[1] + ";" + l[2] + ";" + l[3];
            main = Game.getPlayer1().getLToken().getMain().getKey();
            shape = Game.getPlayer1().getLToken().getCurrentShape();
            ftoken = Game.getSetting().calcGrid(Game.getPlayer1().getFToken().getPosition().x, Game.getPlayer1().getFToken().getPosition().y);

            _Player p1 = new _Player(lgrid, shape, main, ftoken);

            //p2
            l = Game.getSetting().getTakenGrid(Game.getPlayer2().getLToken());
            lgrid = l[0] + ";" + l[1] + ";" + l[2] + ";" + l[3];
            main = Game.getPlayer2().getLToken().getMain().getKey();
            shape = Game.getPlayer2().getLToken().getCurrentShape();
            ftoken = Game.getSetting().calcGrid(Game.getPlayer2().getFToken().getPosition().x, Game.getPlayer2().getFToken().getPosition().y);

            id_s = wbs.getSettings().size()+1;
            
            _Player p2 = new _Player(lgrid, shape, main, ftoken);

            _Current c = new _Current(p1, p2);
            _Setting s = new _Setting(id_s, c, new ArrayList<_Move>());
            
            return s;
        } catch (Exception ex) {
            
            Main.verbose("Exception: " + ex + " @AI.java");
            return null;
        }
        
    }
    
    public void doMove() {
        
        //Add current _Setting to WBS
        try {
            
            lastSetting = createSetting();
            
            wbs.addSetting(lastSetting);
        
            free_ltoken_grids.clear();
            free_ftoken_grids.clear();

            for(int i = 0; i < Game.getSetting().getGrids().size(); i++) {

                if(!Game.getSetting().getGrids().get(i).isTaken() || Game.getSetting().getGrids().get(i).isTakenBy(player.getLToken())) {

                    free_ltoken_grids.add(Game.getSetting().getGrids().get(i));
                }

                if(!Game.getSetting().getGrids().get(i).isTaken() || Game.getSetting().getGrids().get(i).isTakenBy(player.getFToken())) {

                    free_ftoken_grids.add(Game.getSetting().getGrids().get(i));
                }
            }

            if(Game.getRound().getState() == States.PLAYER2_LTOKEN) {

                lastMove = moveTokens();
            }

            //Adding new move to WBS
            wbs.addMove(lastSetting, lastMove.getPlayer().getLToken_grids(), Integer.parseInt(lastMove.getPlayer().getLToken_shape()), Integer.parseInt(lastMove.getPlayer().getLToken_main()), Integer.parseInt(lastMove.getPlayer().getFToken_grids()));
            
            if(lastMove.getWin()) {
                
                wbs.addWin(lastSetting, lastMove.getPlayer().getLToken_grids(), Integer.parseInt(lastMove.getPlayer().getLToken_shape()), Integer.parseInt(lastMove.getPlayer().getLToken_main()), Integer.parseInt(lastMove.getPlayer().getFToken_grids()));
            }
            
            new Move();
            
        } catch (Exception ex) {

            Main.verbose("Exception: " + ex + " @AI.java");
        }
    }
    
    private _Move moveTokens() {
        
        //Wenn lastSetting vorhanden ist..
        
        ArrayList<_Move> moves = null;
        
        for(int i = 0; i < wbs.getSettings().size(); i++) {
            
            if(wbs.getSettings().get(i).getCurrent().getPlayer1().getLToken_grids().equalsIgnoreCase(lastSetting.getCurrent().getPlayer1().getLToken_grids())
                    && wbs.getSettings().get(i).getCurrent().getPlayer1().getLToken_shape().equalsIgnoreCase(lastSetting.getCurrent().getPlayer1().getLToken_shape())
                    && wbs.getSettings().get(i).getCurrent().getPlayer1().getLToken_main().equalsIgnoreCase(lastSetting.getCurrent().getPlayer1().getLToken_main())
                    && wbs.getSettings().get(i).getCurrent().getPlayer1().getFToken_grids().equalsIgnoreCase(lastSetting.getCurrent().getPlayer1().getFToken_grids())
                    
                    && wbs.getSettings().get(i).getCurrent().getPlayer2().getLToken_grids().equalsIgnoreCase(lastSetting.getCurrent().getPlayer2().getLToken_grids())
                    && wbs.getSettings().get(i).getCurrent().getPlayer2().getLToken_shape().equalsIgnoreCase(lastSetting.getCurrent().getPlayer2().getLToken_shape())
                    && wbs.getSettings().get(i).getCurrent().getPlayer2().getLToken_main().equalsIgnoreCase(lastSetting.getCurrent().getPlayer2().getLToken_main())
                    && wbs.getSettings().get(i).getCurrent().getPlayer2().getFToken_grids().equalsIgnoreCase(lastSetting.getCurrent().getPlayer2().getFToken_grids())
                    ) {

                moves = wbs.getSettings().get(i).getNewMove();
            }
        }
        
        if(moves.isEmpty()) {
            
            boolean b = true;
            shape = Integer.MIN_VALUE;

            for(int i = 0; i < free_ltoken_grids.size(); i++) {

                if(free_ltoken_grids.get(i).getKey() == 0 || free_ltoken_grids.get(i).getKey() == 3 || free_ltoken_grids.get(i).getKey() == 12 || free_ltoken_grids.get(i).getKey() == 15) {

                    free_ltoken_grids.remove(i);
                }
            }

            while(b) {

                int rnd = (int)((Math.random()*free_ltoken_grids.size()));
                shape = Integer.MIN_VALUE;

                if(!free_ltoken_grids.get(rnd).isTaken() || free_ltoken_grids.get(rnd).isTakenBy(player.getLToken())) {

                    newGrid_l = free_ltoken_grids.get(rnd).getKey();
                    ArrayList<Integer> shapes = getPossibleShapes(newGrid_l);

                    if(shapes.size() > 0) {

                        //Choose one
                        int rnd2 = (int) ((Math.random()*shapes.size()));
                        shape = shapes.get(rnd2);
                    }

                    if(!Game.getSetting().selfTaken(newGrid_l, shape) && shape != Integer.MIN_VALUE) {

                        b = false;
                    }
                }
            }
        }
        else {
            
            //Choose possible move.
            int best = 0;
            int index = 0;

            for(int j = 0; j < moves.size(); j++) {

                if(moves.get(j).getWin()) {
                    
                    index = j;
                    best = Integer.MAX_VALUE;
                    break;
                }
                
                if(moves.get(j).getUsed() > best) {

                    best = moves.get(j).getUsed();
                    index = j;
                }
            }
            
            lgrid = moves.get(index).getPlayer().getLToken_grids();
            shape = Integer.parseInt(moves.get(index).getPlayer().getLToken_shape());
            newGrid_l = Integer.parseInt(moves.get(index).getPlayer().getLToken_main());
            ftoken = Integer.parseInt(moves.get(index).getPlayer().getFToken_grids());
            @SuppressWarnings("unused")
			ArrayList<Grid> grids = wbs.convertGrids(lgrid);
        }
        
        player.getLToken().setCurrentShape(shape);
        player.getLToken().setShape(player.getLToken().getTurns().get(player.getLToken().getCurrentShape()-1));

        try {

            player.getLToken().setPosition(Game.getSetting().calcPosition(newGrid_l).x, Game.getSetting().calcPosition(newGrid_l).y);
            player.getLToken().draw();
        } catch (Exception ex) {

            Main.verbose("Exception: " + ex + " @AI.java");
        }
        
        //Clear free_ftoken_grids from taken grids 'cause of ltoken
        String ltoken_grids = "";
        
        if(shape == 1) {
            
            ltoken_grids = (player.getLToken().getMain().getKey()-5) + ";" + (player.getLToken().getMain().getKey()-1) + ";" + player.getLToken().getMain().getKey() + ";" + (player.getLToken().getMain().getKey()+1);
        }
        else if(shape == 2) {
            
            ltoken_grids = (player.getLToken().getMain().getKey()-4) + ";" + (player.getLToken().getMain().getKey()-3) + ";" + player.getLToken().getMain().getKey() + ";" + (player.getLToken().getMain().getKey()+4);
        }
        else if(shape == 3) {
            
            ltoken_grids = (player.getLToken().getMain().getKey()-1) + ";" + player.getLToken().getMain().getKey() + ";" + (player.getLToken().getMain().getKey()+1) + ";" + (player.getLToken().getMain().getKey()+5);
        }
        else if(shape == 4) {
            
            ltoken_grids = (player.getLToken().getMain().getKey()-4) + ";" + player.getLToken().getMain().getKey() + ";" + (player.getLToken().getMain().getKey()+3) + ";" + (player.getLToken().getMain().getKey()+4);
        }
        else if(shape == 5) {
            
            ltoken_grids = (player.getLToken().getMain().getKey()-1) + ";" + player.getLToken().getMain().getKey() + ";" + (player.getLToken().getMain().getKey()+1) + ";" + (player.getLToken().getMain().getKey()+3);
        }
        else if(shape == 6) {
            
            ltoken_grids = (player.getLToken().getMain().getKey()-4) + ";" + player.getLToken().getMain().getKey() + ";" + (player.getLToken().getMain().getKey()+4) + ";" + (player.getLToken().getMain().getKey()+5);
        }
        else if(shape == 7) {
            
            ltoken_grids = (player.getLToken().getMain().getKey()-3) + ";" + (player.getLToken().getMain().getKey()-1) + ";" + player.getLToken().getMain().getKey() + ";" + (player.getLToken().getMain().getKey()+1);
        }
        else if(shape == 8) {
            
            ltoken_grids = (player.getLToken().getMain().getKey()-5) + ";" + (player.getLToken().getMain().getKey()-4) + ";" + player.getLToken().getMain().getKey() + ";" + (player.getLToken().getMain().getKey()+4);
        }

        
        if(moves.isEmpty()) {

            ArrayList<Grid> _new = wbs.convertGrids(ltoken_grids);

            for(int i = 0; i < ((player == Game.getPlayer2()) ? Game.getSetting().getPlayer2LToken().size() : Game.getSetting().getPlayer1LToken().size()); i++) {

                if(!free_ftoken_grids.contains(Game.getSetting().getPlayer2LToken().get(i))) {

                    free_ftoken_grids.add(Game.getSetting().getPlayer2LToken().get(i));
                }
            }

            for(int j = 0; j < _new.size(); j++) {

                for(int i = 0; i < free_ftoken_grids.size(); i++) {

                    if(free_ftoken_grids.get(i) == _new.get(j)) {

                        free_ftoken_grids.remove(_new.get(j));
                    }
                }
            }

            int rnd3 = (int)((Math.random() * free_ftoken_grids.size()));

            while(free_ftoken_grids.get(rnd3).isTaken()) {

                rnd3 = (int)((Math.random() * free_ftoken_grids.size()));
            }

            newGrid_f = free_ftoken_grids.get(rnd3).getKey();
        }
        else {
            
            newGrid_f = ftoken;
        }
        
        try {

            player.getFToken().setPosition(Game.getSetting().calcPosition(newGrid_f).x-50, Game.getSetting().calcPosition(newGrid_f).y-50);
            player.getFToken().draw();
        } catch (Exception ex) {

            Main.verbose("Exception: " + ex + " @AI.java");
        }
        
        return new _Move(new _Player(ltoken_grids, shape, newGrid_l, newGrid_f), 1, false);
    }
    
    public ArrayList<Integer> getPossibleShapes(int sGrid) {
        
        ArrayList<Integer> shapes = new ArrayList<Integer>();
        
        if(!Game.getSetting().getGrids().get(sGrid).isTaken()|| Game.getSetting().getGrids().get(sGrid).isTakenBy(((Game.getRound().getState() == States.PLAYER1_LTOKEN)) ? Game.getPlayer1().getLToken() : Game.getPlayer2().getLToken())) {
            
            if(sGrid == 5 || sGrid == 6 || sGrid == 9 || sGrid == 10) {
            
                if(Game.getSetting().checkGrid(sGrid-5) && Game.getSetting().checkGrid(sGrid-1) && Game.getSetting().checkGrid(sGrid) && Game.getSetting().checkGrid(sGrid+1)) { //Shape 1.

                    shapes.add(1);
                }
                
                if((Game.getSetting().checkGrid(sGrid-3) && Game.getSetting().checkGrid(sGrid-4) && Game.getSetting().checkGrid(sGrid) && Game.getSetting().checkGrid(sGrid+4))) { //Shape 2.
                    
                    shapes.add(2);
                }
                
                if((Game.getSetting().checkGrid(sGrid+5) && Game.getSetting().checkGrid(sGrid+1) && Game.getSetting().checkGrid(sGrid) && Game.getSetting().checkGrid(sGrid-1))) { //Shape 3.
                    
                    shapes.add(3);
                }
                
                if((Game.getSetting().checkGrid(sGrid+3) && Game.getSetting().checkGrid(sGrid+4) && Game.getSetting().checkGrid(sGrid) && Game.getSetting().checkGrid(sGrid-4))) { //Shape 4.
                    
                    shapes.add(4);
                }
                
                if((Game.getSetting().checkGrid(sGrid+3) && Game.getSetting().checkGrid(sGrid-1) && Game.getSetting().checkGrid(sGrid) && Game.getSetting().checkGrid(sGrid+1))) { //Shape 5.
                    
                    shapes.add(5);
                }
                
                if((Game.getSetting().checkGrid(sGrid+5) && Game.getSetting().checkGrid(sGrid+4) && Game.getSetting().checkGrid(sGrid) && Game.getSetting().checkGrid(sGrid-4))) { //Shape 6.
                    
                    shapes.add(6);
                }
                
                if((Game.getSetting().checkGrid(sGrid-3) && Game.getSetting().checkGrid(sGrid+1) && Game.getSetting().checkGrid(sGrid) && Game.getSetting().checkGrid(sGrid-1))) { //Shape 7.
                    
                    shapes.add(7);
                }
                
                if((Game.getSetting().checkGrid(sGrid-5) && Game.getSetting().checkGrid(sGrid-4) && Game.getSetting().checkGrid(sGrid) && Game.getSetting().checkGrid(sGrid+4))) { //Shape 8.
                    
                    shapes.add(8);
                }
            }
            else if(sGrid == 4 || sGrid == 8){

                if((Game.getSetting().checkGrid(sGrid-3) && Game.getSetting().checkGrid(sGrid-4) && Game.getSetting().checkGrid(sGrid) && Game.getSetting().checkGrid(sGrid+4))) { //Shape 2.

                    shapes.add(2);
                }
                
                if((Game.getSetting().checkGrid(sGrid+5) && Game.getSetting().checkGrid(sGrid+4) && Game.getSetting().checkGrid(sGrid) && Game.getSetting().checkGrid(sGrid-4))) { //Shape 6.
                    
                    shapes.add(6);
                }
            }
            else if(sGrid == 7 || sGrid == 11) {

                if((Game.getSetting().checkGrid(sGrid+3) && Game.getSetting().checkGrid(sGrid+4) && Game.getSetting().checkGrid(sGrid) && Game.getSetting().checkGrid(sGrid-4))) { //Shpe 4.

                    shapes.add(4);
                }
                
                if((Game.getSetting().checkGrid(sGrid-5) && Game.getSetting().checkGrid(sGrid-4) && Game.getSetting().checkGrid(sGrid) && Game.getSetting().checkGrid(sGrid+4))) { //Shape 8.
                    
                    shapes.add(8);
                }
            }
            else if(sGrid == 1 || sGrid == 2) {

                if((Game.getSetting().checkGrid(sGrid+5) && Game.getSetting().checkGrid(sGrid+1) && Game.getSetting().checkGrid(sGrid) && Game.getSetting().checkGrid(sGrid-1))) { //Shape 3.

                    shapes.add(3);
                }
                
                if((Game.getSetting().checkGrid(sGrid+3) && Game.getSetting().checkGrid(sGrid-1) && Game.getSetting().checkGrid(sGrid) && Game.getSetting().checkGrid(sGrid+1))) { //Shape 5.
                    
                    shapes.add(5);
                }
            }
            else if(sGrid == 13 || sGrid == 14) {

                if((Game.getSetting().checkGrid(sGrid-5) && Game.getSetting().checkGrid(sGrid-1) && Game.getSetting().checkGrid(sGrid) && Game.getSetting().checkGrid(sGrid+1))) { //Shape 1.

                    shapes.add(1);
                }
                
                if((Game.getSetting().checkGrid(sGrid-3) && Game.getSetting().checkGrid(sGrid+1) && Game.getSetting().checkGrid(sGrid) && Game.getSetting().checkGrid(sGrid-1))) { //Shape 7.
                    
                    shapes.add(7);
                }
            }   
        }
        
        return shapes;
    }

    public _Move getLastMove() {
        
        return lastMove;
    }
    
    public _Setting getLastSetting() {
        
        return lastSetting;
    }
    
    public WBS getWBS() {
        
        return wbs;
    }
}
