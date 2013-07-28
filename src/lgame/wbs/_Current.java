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
public class _Current {
        
    private _Player p1;
    private _Player p2;
    
    public _Current(_Player p1, _Player p2) {
        
        this.p1 = p1;
        this.p2 = p2;
    }
    
    public _Player getPlayer1() {
        
        return p1;
    }
    
    public _Player getPlayer2() {
        
        return p2;
    }
    
    public void setPlayer1(_Player p) {
        
        p1 = p;
    }
    
    public void setPlayer2(_Player p) {
        
        p2 = p;
    }
}
