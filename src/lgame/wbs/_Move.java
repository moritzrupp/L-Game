/*
 * ### ACHTUNG: Der Quelltext, sowie alle sonstigen hier gezeigten Erzeugnisse sind urheberrechtlich geschützt.
 *              Weiterverwendung, - verarbeitung oder -verbreitung ist nur mit ausdrücklicher schriftlicher Genehmigung von Moritz Rupp gestattet.
 *              No copy, reproduction or use without written permission of Moritz Rupp.
 *              Copyright (c) 2011 Moritz Rupp
 *              Alle Rechte vorbehalten. All rights reserved.
 */
package lgame.wbs;

/**
 *
 * @author Moritz Rupp
 */
public class _Move {
    
    private _Player p;
    private int used;
    private boolean win;
    
    public _Move(_Player p, int used, boolean b) {
        
        this.p = p;
        this.used = used;
        win = b;
    }
    
    public _Player getPlayer() {
        
        return p;
    }
    
    public int getUsed() {
        
        return used;
    }
    
    public boolean getWin() {
        
        return win;
    }
}
