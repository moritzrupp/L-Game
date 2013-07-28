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
public class _Player {
    
    private String ltoken_grids;
    private int ltoken_shape;
    private int ftoken_grid;
    private int ltoken_main;
    
    public _Player(String l, int s, int m, int f) {
        
        ltoken_grids = l;
        ltoken_shape = s;
        ltoken_main = m;
        ftoken_grid = f;
    }

    /**
     * @return the ltoken_grids
     */
    public String getLToken_grids() {
        return ltoken_grids;
    }

    /**
     * @return the ltoken_shape
     */
    public String getLToken_shape() {
        return ltoken_shape+"";
    }
    
    /**
     * @return the ftoken_grid
     */
    public String getFToken_grids() {
        return ftoken_grid+"";
    }
    
    /**
     * @return the ftoken_grid
     */
    public String getLToken_main() {
        return ltoken_main+"";
    }
}
