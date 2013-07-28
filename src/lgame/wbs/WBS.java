/*
 * ### ACHTUNG: Der Quelltext, sowie alle sonstigen hier gezeigten Erzeugnisse sind urheberrechtlich geschützt.
 *              Weiterverwendung, - verarbeitung oder -verbreitung ist nur mit ausdrücklicher schriftlicher Genehmigung von Moritz Rupp gestattet.
 *              No copy, reproduction or use without written permission of Moritz Rupp.
 *              Copyright (c) 2011 Moritz Rupp
 *              Alle Rechte vorbehalten. All rights reserved.
 */

package lgame.wbs;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lgame.gui.Grid;
import lgame.org.Game;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.xml.sax.InputSource;

/**
 *
 * @author Moritz Rupp
 */

/**
 * 
 * This WBS is a WBS is negative callback.
 */
public class WBS {
    
    private SAXBuilder sxbuild;
    private InputSource input;
    private FileOutputStream output;
    private XMLOutputter outputter;
    private Format format;
    
    private String path  = "src/lgame/wbs/WBS.xml";
//    private URL url = this.getClass().getResource("WBS.xml");
//    private String path = url.getPath();
    
    private Document doc;
    private Element root;
    
    private final ArrayList<_Setting> settings = new ArrayList<_Setting>();
    
    public WBS() throws JDOMException, IOException {

        sxbuild = new SAXBuilder();
        
        input = new InputSource(path);
        
        doc = sxbuild.build(input);
        root = doc.getRootElement();
        
        readWBS();
    }
    
    public void writeWBS() throws IOException {
        
        output = new FileOutputStream(path);
        outputter = new XMLOutputter();
        
        format = outputter.getFormat();
        format.setExpandEmptyElements(true);
        format.setLineSeparator(System.getProperty("line.separator"));
        format.setTextMode(Format.TextMode.NORMALIZE);
        format.setIndent("    ");
        outputter.setFormat(format);
        
        outputter.output(doc, output);
    }
    
    public void addSetting(_Setting s) throws IOException {
        
        //Check if setting already exists
        List<?> allChildren = root.getChildren();
        
        int c = 0;
        for(int i = 0; i < allChildren.size(); i++) {
            
            if(((Element) allChildren.get(i)).getChild("current").getChild("player1").getChild("ltoken").getChildText("grids").equalsIgnoreCase(s.getCurrent().getPlayer1().getLToken_grids())
                    && ((Element) allChildren.get(i)).getChild("current").getChild("player1").getChild("ltoken").getChildText("shape").equalsIgnoreCase(s.getCurrent().getPlayer1().getLToken_shape())
                    && ((Element) allChildren.get(i)).getChild("current").getChild("player1").getChild("ltoken").getChildText("main").equalsIgnoreCase(s.getCurrent().getPlayer1().getLToken_main())
                    && ((Element) allChildren.get(i)).getChild("current").getChild("player1").getChild("ftoken").getChildText("grids").equalsIgnoreCase(s.getCurrent().getPlayer1().getFToken_grids())
                    
                    && ((Element) allChildren.get(i)).getChild("current").getChild("player2").getChild("ltoken").getChildText("grids").equalsIgnoreCase(s.getCurrent().getPlayer2().getLToken_grids())
                    && ((Element) allChildren.get(i)).getChild("current").getChild("player2").getChild("ltoken").getChildText("shape").equalsIgnoreCase(s.getCurrent().getPlayer2().getLToken_shape())
                    && ((Element) allChildren.get(i)).getChild("current").getChild("player2").getChild("ltoken").getChildText("main").equalsIgnoreCase(s.getCurrent().getPlayer2().getLToken_main())
                    && ((Element) allChildren.get(i)).getChild("current").getChild("player2").getChild("ftoken").getChildText("grids").equalsIgnoreCase(s.getCurrent().getPlayer2().getFToken_grids())) {
                
                c++;
            }
        }
        
        if(c == 0) {
        
            Element setting = new Element("setting");
            Element current = new Element("current");
            Element p1 = new Element("player1");
            Element p2 = new Element("player2");
            Element ltoken_p1 = new Element("ltoken");
            Element ftoken_p1 = new Element("ftoken");
            Element ltoken_p2 = new Element("ltoken");
            Element ftoken_p2 = new Element("ftoken");
            Element grid_l_p1 = new Element("grids");
            Element grid_f_p1 = new Element("grids");
            Element shape_p1 = new Element("shape");
            Element main_p1 = new Element("main");
            Element grid_l_p2 = new Element("grids");
            Element grid_f_p2 = new Element("grids");
            Element shape_p2 = new Element("shape");
            Element main_p2 = new Element("main");

            grid_l_p1.setText(s.getCurrent().getPlayer1().getLToken_grids());
            grid_f_p1.setText(s.getCurrent().getPlayer1().getFToken_grids());
            shape_p1.setText(s.getCurrent().getPlayer1().getLToken_shape());
            main_p1.setText(s.getCurrent().getPlayer1().getLToken_main());

            grid_l_p2.setText(s.getCurrent().getPlayer2().getLToken_grids());
            grid_f_p2.setText(s.getCurrent().getPlayer2().getFToken_grids());
            shape_p2.setText(s.getCurrent().getPlayer2().getLToken_shape());
            main_p2.setText(s.getCurrent().getPlayer2().getLToken_main());

            ltoken_p1.addContent(grid_l_p1);
            ltoken_p1.addContent(shape_p1);
            ltoken_p1.addContent(main_p1);
            ftoken_p1.addContent(grid_f_p1);

            ltoken_p2.addContent(grid_l_p2);
            ltoken_p2.addContent(shape_p2);
            ltoken_p2.addContent(main_p2);
            ftoken_p2.addContent(grid_f_p2);

            p1.addContent(ltoken_p1);
            p1.addContent(ftoken_p1);

            p2.addContent(ltoken_p2);
            p2.addContent(ftoken_p2);

            current.addContent(p1);
            current.addContent(p2);

            setting.addContent(current);
            setting.setAttribute("id", s.getID()+"");

            root.addContent(setting);

            writeWBS();
            readWBS();
        }
    }
    
    public void removeMove(_Setting s, String l_grids, int shape, int main, int f_grid) throws IOException {
        
        List<?> allChildren = root.getChildren();
        
        for(int i = 0; i < allChildren.size(); i++) {
            
            if(((Element) allChildren.get(i)).getChild("current").getChild("player1").getChild("ltoken").getChildText("grids").equalsIgnoreCase(s.getCurrent().getPlayer1().getLToken_grids())
                    && ((Element) allChildren.get(i)).getChild("current").getChild("player1").getChild("ltoken").getChildText("shape").equalsIgnoreCase(s.getCurrent().getPlayer1().getLToken_shape())
                    && ((Element) allChildren.get(i)).getChild("current").getChild("player1").getChild("ltoken").getChildText("main").equalsIgnoreCase(s.getCurrent().getPlayer1().getLToken_main())
                    && ((Element) allChildren.get(i)).getChild("current").getChild("player1").getChild("ftoken").getChildText("grids").equalsIgnoreCase(s.getCurrent().getPlayer1().getFToken_grids())
                    
                    && ((Element) allChildren.get(i)).getChild("current").getChild("player2").getChild("ltoken").getChildText("grids").equalsIgnoreCase(s.getCurrent().getPlayer2().getLToken_grids())
                    && ((Element) allChildren.get(i)).getChild("current").getChild("player2").getChild("ltoken").getChildText("shape").equalsIgnoreCase(s.getCurrent().getPlayer2().getLToken_shape())
                    && ((Element) allChildren.get(i)).getChild("current").getChild("player2").getChild("ltoken").getChildText("main").equalsIgnoreCase(s.getCurrent().getPlayer2().getLToken_main())
                    && ((Element) allChildren.get(i)).getChild("current").getChild("player2").getChild("ftoken").getChildText("grids").equalsIgnoreCase(s.getCurrent().getPlayer2().getFToken_grids())) {
                
                
                List<?> moves = ((Element) allChildren.get(i)).getChildren("newmove");

                for(int j = 0; j < moves.size(); j++) {
                
                    if(((Element) moves.get(j)).getChild("ltoken").getChildText("grids").equalsIgnoreCase(l_grids)
                            && ((Element) moves.get(j)).getChild("ltoken").getChildText("shape").equalsIgnoreCase(shape+"")
                            && ((Element) moves.get(j)).getChild("ltoken").getChildText("main").equalsIgnoreCase(main+"")
                            && ((Element) moves.get(j)).getChild("ftoken").getChildText("grids").equalsIgnoreCase(f_grid+"")) {
                        
                        int value = Integer.parseInt(((Element) moves.get(j)).getAttributeValue("used"));
                        
                        if(value > 0) {
                            
                            ((Element) moves.get(j)).setAttribute("used", "0");
                        }
                    }
                }
            }
        }
        
        writeWBS();
        readWBS();
    }
    
    public void addMove(_Setting s, String l_grids, int shape, int main, int f_grid) throws IOException {
                
        List<?> allChildren = root.getChildren();
        
        for(int i = 0; i < allChildren.size(); i++) {
            
            if(((Element) allChildren.get(i)).getChild("current").getChild("player1").getChild("ltoken").getChildText("grids").equalsIgnoreCase(s.getCurrent().getPlayer1().getLToken_grids())
                    && ((Element) allChildren.get(i)).getChild("current").getChild("player1").getChild("ltoken").getChildText("shape").equalsIgnoreCase(s.getCurrent().getPlayer1().getLToken_shape())
                    && ((Element) allChildren.get(i)).getChild("current").getChild("player1").getChild("ltoken").getChildText("main").equalsIgnoreCase(s.getCurrent().getPlayer1().getLToken_main())
                    && ((Element) allChildren.get(i)).getChild("current").getChild("player1").getChild("ftoken").getChildText("grids").equalsIgnoreCase(s.getCurrent().getPlayer1().getFToken_grids())
                    
                    && ((Element) allChildren.get(i)).getChild("current").getChild("player2").getChild("ltoken").getChildText("grids").equalsIgnoreCase(s.getCurrent().getPlayer2().getLToken_grids())
                    && ((Element) allChildren.get(i)).getChild("current").getChild("player2").getChild("ltoken").getChildText("shape").equalsIgnoreCase(s.getCurrent().getPlayer2().getLToken_shape())
                    && ((Element) allChildren.get(i)).getChild("current").getChild("player2").getChild("ltoken").getChildText("main").equalsIgnoreCase(s.getCurrent().getPlayer2().getLToken_main())
                    && ((Element) allChildren.get(i)).getChild("current").getChild("player2").getChild("ftoken").getChildText("grids").equalsIgnoreCase(s.getCurrent().getPlayer2().getFToken_grids())) {

                //Check if new move already exists:
                List<?> moves = ((Element) allChildren.get(i)).getChildren("newmove");
                
                int c = 0;
                for(int j = 0; j < moves.size(); j++) {
                
                    if(((Element) moves.get(j)).getChild("ltoken").getChildText("grids").equalsIgnoreCase(l_grids)
                            && ((Element) moves.get(j)).getChild("ltoken").getChildText("shape").equalsIgnoreCase(shape+"")
                            &&((Element) moves.get(j)).getChild("ltoken").getChildText("main").equalsIgnoreCase(main+"")
                            && ((Element) moves.get(j)).getChild("ftoken").getChildText("grids").equalsIgnoreCase(f_grid+"")) {
                        
                        c++;
                        
                        //Increase attribute used
                        int value = Integer.parseInt(((Element) moves.get(j)).getAttributeValue("used"));
                        value = value+1;

                        ((Element) moves.get(j)).setAttribute("used", value+"");
                    }
                }
                
                
                if(c == 0) {
                    
                    Element newmove = new Element("newmove");
                    Element ltoken = new Element("ltoken");
                    Element ftoken = new Element("ftoken");
                    Element lgrid = new Element("grids");
                    Element lshape = new Element("shape");
                    Element lmain = new Element("main");
                    Element fgrid = new Element("grids");

                    lgrid.setText(l_grids);
                    lshape.setText(shape+"");
                    lmain.setText(main+"");
                    fgrid.setText(f_grid+"");

                    ltoken.addContent(lgrid);
                    ltoken.addContent(lshape);
                    ltoken.addContent(lmain);
                    ftoken.addContent(fgrid);

                    newmove.addContent(ltoken);
                    newmove.addContent(ftoken);
                    
                    newmove.setAttribute("used", "1");
                    
                    ((Element) allChildren.get(i)).addContent(newmove);
                }
            }
        }
        
        writeWBS();
        readWBS();
    }
    
    public void addWin(_Setting s, String l_grids, int shape, int main, int f_grid) throws IOException {
        
        List<?> allChildren = root.getChildren();
        
        for(int i = 0; i < allChildren.size(); i++) {
            
            if(((Element) allChildren.get(i)).getChild("current").getChild("player1").getChild("ltoken").getChildText("grids").equalsIgnoreCase(s.getCurrent().getPlayer1().getLToken_grids())
                    && ((Element) allChildren.get(i)).getChild("current").getChild("player1").getChild("ltoken").getChildText("shape").equalsIgnoreCase(s.getCurrent().getPlayer1().getLToken_shape())
                    && ((Element) allChildren.get(i)).getChild("current").getChild("player1").getChild("ltoken").getChildText("main").equalsIgnoreCase(s.getCurrent().getPlayer1().getLToken_main())
                    && ((Element) allChildren.get(i)).getChild("current").getChild("player1").getChild("ftoken").getChildText("grids").equalsIgnoreCase(s.getCurrent().getPlayer1().getFToken_grids())
                    
                    && ((Element) allChildren.get(i)).getChild("current").getChild("player2").getChild("ltoken").getChildText("grids").equalsIgnoreCase(s.getCurrent().getPlayer2().getLToken_grids())
                    && ((Element) allChildren.get(i)).getChild("current").getChild("player2").getChild("ltoken").getChildText("shape").equalsIgnoreCase(s.getCurrent().getPlayer2().getLToken_shape())
                    && ((Element) allChildren.get(i)).getChild("current").getChild("player2").getChild("ltoken").getChildText("main").equalsIgnoreCase(s.getCurrent().getPlayer2().getLToken_main())
                    && ((Element) allChildren.get(i)).getChild("current").getChild("player2").getChild("ftoken").getChildText("grids").equalsIgnoreCase(s.getCurrent().getPlayer2().getFToken_grids())) {

                //Check if new move already exists:
                List<?> moves = ((Element) allChildren.get(i)).getChildren("newmove");
                
                for(int j = 0; j < moves.size(); j++) {
                
                    if(((Element) moves.get(j)).getChild("ltoken").getChildText("grids").equalsIgnoreCase(l_grids)
                            && ((Element) moves.get(j)).getChild("ltoken").getChildText("shape").equalsIgnoreCase(shape+"")
                            &&((Element) moves.get(j)).getChild("ltoken").getChildText("main").equalsIgnoreCase(main+"")
                            && ((Element) moves.get(j)).getChild("ftoken").getChildText("grids").equalsIgnoreCase(f_grid+"")) {
                                                
                        ((Element) moves.get(j)).setAttribute("win", "1");
                    }
                }
            }
        }
        
        writeWBS();
        readWBS();
    }
    
    public final void readWBS() {
        
        settings.clear();
        
        List<?> allChildren = root.getChildren();
        
        _Player p1;
        _Player p2;
        _Current c;
        
        String l_grids;
        int main;
        int shape;
        int f_grid;
        int move_used;
        int id;
        boolean win;
        
        for(int i = 0; i < allChildren.size(); i++) {
            
            ArrayList<_Move> m = new ArrayList<_Move>();
            List<?> moves = ((Element) allChildren.get(i)).getChildren("newmove");
            
            if(!moves.isEmpty()) {
                
                for(int j = 0; j < moves.size(); j++) {
                    
                    l_grids = ((Element) moves.get(j)).getChild("ltoken").getChildText("grids");
                    shape = Integer.parseInt(((Element) moves.get(j)).getChild("ltoken").getChildText("shape"));
                    main = Integer.parseInt(((Element) moves.get(j)).getChild("ltoken").getChildText("main"));
                    f_grid = Integer.parseInt(((Element) moves.get(j)).getChild("ftoken").getChildText("grids"));
                    
                    move_used = Integer.parseInt(((Element) moves.get(j)).getAttributeValue("used"));
                    
                    if(((Element) moves.get(j)).getAttribute("win") != null) {
                        
                        win = true;
                    }
                    else {
                        
                        win = false;
                    }
                    
                    m.add(new _Move(new _Player(l_grids, shape, main, f_grid), move_used, win));
                }
            }
            
            l_grids = ((Element) allChildren.get(i)).getChild("current").getChild("player1").getChild("ltoken").getChildText("grids");
            main = Integer.parseInt(((Element) allChildren.get(i)).getChild("current").getChild("player1").getChild("ltoken").getChildText("main"));
            shape = Integer.parseInt(((Element) allChildren.get(i)).getChild("current").getChild("player1").getChild("ltoken").getChildText("shape"));
            f_grid = Integer.parseInt(((Element) allChildren.get(i)).getChild("current").getChild("player1").getChild("ftoken").getChildText("grids"));
            
            p1 = new _Player(l_grids, shape, main, f_grid);
            
            l_grids = ((Element) allChildren.get(i)).getChild("current").getChild("player2").getChild("ltoken").getChildText("grids");
            main = Integer.parseInt(((Element) allChildren.get(i)).getChild("current").getChild("player2").getChild("ltoken").getChildText("main"));
            shape = Integer.parseInt(((Element) allChildren.get(i)).getChild("current").getChild("player2").getChild("ltoken").getChildText("shape"));
            f_grid = Integer.parseInt(((Element) allChildren.get(i)).getChild("current").getChild("player2").getChild("ftoken").getChildText("grids"));
            
            p2 = new _Player(l_grids, shape, main, f_grid);
            
            c = new _Current(p1, p2);
            
            id = Integer.parseInt(((Element) allChildren.get(i)).getAttributeValue("id"));
            
            settings.add(new _Setting(id, c, m));
        }
    }
    
    public ArrayList<_Setting> getSettings() {
        
        return settings;
    }
    
    public ArrayList<Grid> convertGrids(String s) {
        
        String[] tmp = s.split(";");
        ArrayList<Grid> grids = new ArrayList<Grid>();
        
        @SuppressWarnings("unused")
		int[] g = new int[tmp.length];
        
        
        for(int i = 0; i < tmp.length; i++) {
            
            grids.add(Game.getSetting().getGrids().get(Integer.parseInt(tmp[i])));
        }
        
        return grids;
    }
}