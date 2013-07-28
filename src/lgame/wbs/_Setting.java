/*
 * ### ACHTUNG: Der Quelltext, sowie alle sonstigen hier gezeigten Erzeugnisse sind urheberrechtlich geschützt.
 *              Weiterverwendung, - verarbeitung oder -verbreitung ist nur mit ausdrücklicher schriftlicher Genehmigung von Moritz Rupp gestattet.
 *              No copy, reproduction or use without written permission of Moritz Rupp.
 *              Copyright (c) 2011 Moritz Rupp
 *              Alle Rechte vorbehalten. All rights reserved.
 */

package lgame.wbs;

import java.util.ArrayList;

/**
 *
 * @author Moritz Rupp
 */
public class _Setting {
    
    private int id;
    private _Current current;
    private ArrayList<_Move> newmove;
    
    public _Setting(int id, _Current c, ArrayList<_Move> m) {
        
        this.id = id;
        current = c;
        newmove = m;
    }
    
    public _Current getCurrent() {
        
        return current;
    }
    
    public ArrayList<_Move> getNewMove() {
        
        return newmove;
    }
    
    public int getID() {
        
        return id;
    }
    
    
    public void addNewMove(_Move m) {
        
        newmove.add(m);
    }
    
    public int getMoveSize() {
        
        return newmove.size();
    }
}
