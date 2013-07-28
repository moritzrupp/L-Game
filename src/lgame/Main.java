/*
 * ### ACHTUNG: Der Quelltext, sowie alle sonstigen hier gezeigten Erzeugnisse sind urheberrechtlich geschützt.
 *              Weiterverwendung, - verarbeitung oder -verbreitung ist nur mit ausdrücklicher schriftlicher Genehmigung von Moritz Rupp gestattet.
 *              No copy, reproduction or use without written permission of Moritz Rupp.
 *              Copyright (c) 2011 Moritz Rupp
 *              Alle Rechte vorbehalten. All rights reserved.
 */


package lgame;

import java.io.IOException;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import lgame.gui.Mainwindow;

/**
 * @author Moritz Rupp
 */
public class Main {

    private static boolean verbose = true;
    public static String _SYSTEM = System.getProperty("os.name").toLowerCase();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        if(_SYSTEM.indexOf("mac") != -1) {
            
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            System.setProperty("com.apple.mrj.application.apple.menu.about.name", "L-Game");
            
            verbose("You're running on Mac OS X.");
        }
        
        try {
                
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException ex) {
                
                System.out.println("ClassNotFoundException: " + ex);
            } catch (InstantiationException ex) {
                
                System.out.println("InstantiationException: " + ex);
            } catch (IllegalAccessException ex) {
                
                System.out.println("IllegalAccessException: " + ex);
            } catch (UnsupportedLookAndFeelException ex) {
                
                System.out.println("UnsupportedLookAndFeelException: " + ex);
            }
        
        new Mainwindow();
    }

    public static void verbose(String s) {

        if(verbose == true) {

            System.out.println(s);
        }
    }
}
