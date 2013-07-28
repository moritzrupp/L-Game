/*
 * ### ACHTUNG: Der Quelltext, sowie alle sonstigen hier gezeigten Erzeugnisse sind urheberrechtlich geschützt.
 *              Weiterverwendung, - verarbeitung oder -verbreitung ist nur mit ausdrücklicher schriftlicher Genehmigung von Moritz Rupp gestattet.
 *              No copy, reproduction or use without written permission of Moritz Rupp.
 *              Copyright (c) 2011 Moritz Rupp
 *              Alle Rechte vorbehalten. All rights reserved.
 */

package lgame.gui;

/**
 * Imports
 */
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Locale;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileFilter;

import lgame.Main;

/**
 * @author Moritz Rupp
 */
public class Mainwindow extends JFrame {

	private static final long serialVersionUID = 6545780115697177648L;
	
	public int width = 500;
    public int height = 600;

    private static boolean player2comp;

    private Board board;
    
    private JMenuBar menu;
    private JMenu file;
    private JMenuItem newGame;
    private JMenuItem quit;
    private JCheckBoxMenuItem p2c;
    
    private JMenu edit;
    private JMenuItem _export;
    private JMenuItem _import;
    
    private final JFileChooser fc;
    
    @SuppressWarnings("unused")
	private Locale locale;

    public Mainwindow() {
        
        setFocusable(false);
        setSize(width,height);
        setLocation((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth() - this.getWidth()) / 2, (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight() - this.getHeight()) / 2);
        setResizable(false);
        setTitle("L-Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        board = new Board();
        
        menu = new JMenuBar();
        
        fc = new JFileChooser();
        
        FileFilter ff = new FileFilter() {

            @Override
            public boolean accept(File file) {
                
                if(file.toString().toLowerCase().endsWith(".xml")) {
                    
                    return true;
                }
                else {
                    
                    return false;
                }
            }

            @Override
            public String getDescription() {
                
                return "*.xml";
            }
        };
        
        fc.setFileFilter(ff);
        
        file = new JMenu();
        file.setText("File");
        menu.add(file);
        
        newGame = new JMenuItem();
        newGame.setText("New Game");
        newGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        
        newGame.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                
                board.newGame(player2comp);
            }
        });
        
        file.add(newGame);
        
        p2c = new JCheckBoxMenuItem();
        p2c.setText("Player 2 as Computer");
        
        p2c.setSelected(true);
        p2c.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, (InputEvent.SHIFT_DOWN_MASK | Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())));
        
        player2comp = true;
        
        p2c.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                
                if(p2c.isSelected()) {
                    
                    player2comp = true;
                    Main.verbose("player2comp is " + player2comp);
                }
                else {
                    
                    player2comp = false;
                    Main.verbose("player2comp is " + player2comp);
                }
            }
        });
        
        file.add(p2c);
        
        if(Main._SYSTEM.indexOf("mac") == -1) {
            
            quit = new JMenuItem();
            quit.setText("Quit");
            quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
            quit.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    System.exit(0);
                }
            });
            
            file.add(quit);
        }

        edit = new JMenu();
        edit.setText("Edit");
        menu.add(edit);
        
        _export = new JMenuItem();
        _export.setText("Export WBS...");
        _export.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        _export.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
        
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
        
        edit.add(_export);
        
        _import = new JMenuItem();
        _import.setText("Import WBS...");
        _import.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        _import.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                
                int returnVal = fc.showOpenDialog(Mainwindow.this);
                
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    
                    
                }
            }
        });
        
        edit.add(_import);
        
        setJMenuBar(menu);
        add(board);

        setVisible(true);
    }

    public static boolean getPlayer2Comp() {

        return player2comp;
    }
}
