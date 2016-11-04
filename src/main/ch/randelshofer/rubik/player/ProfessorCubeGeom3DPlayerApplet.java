/*
 * @(#)ProfessorCubeGeom3DPlayerApplet.java  2.0  2007-11-15
 * Copyright (c) 2005 Werner Randelshofer, Switzerland.
 * You may only use this software in accordance with the license terms.
 */

package ch.randelshofer.rubik.player;

import ch.randelshofer.geom3d.*;
import ch.randelshofer.rubik.parser.*;
import ch.randelshofer.rubik.*;
import java.io.InputStream;

/**
 * ProfessorCubeGeom3DPlayerApplet.
 *
 * @author  Werner Randelshofer
 * @version 2.0 2007-11-15 Upgraded to Java 1.4.
 */
public class ProfessorCubeGeom3DPlayerApplet extends AbstractCubeGeom3DPlayerApplet {
    private final static long serialVersionUID = 1L;
    
    protected ScriptPlayer createPlayer() {
        ScriptPlayer player = new ScriptPlayer();
        player.setResetCube(new ProfessorCube());
        JCanvas3D canvas3D = new JCanvas3D();
        Cube3D cube3D = new ProfessorCubeGeom3D();
        cube3D.setAnimated(true);
        player.setCanvas(new Cube3DCanvasGeom3D(canvas3D, cube3D));
        player.setCube3D(cube3D);
        player.setCube(cube3D.getCube());
        return player;
    }
    protected int getLayerCount() {
        return 5;
    }

    /**
     * Returns the Default XML Resource Data of this Applet as an Input Stream. 
     */
    protected InputStream getPlayerResources() {
        return getClass().getResourceAsStream("/ch/randelshofer/rubik/player/ProfessorPlayerResources.xml");
    }
    
    /** This method is called from within the init() method to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     * /
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
    }// </editor-fold>//GEN-END:initComponents
    */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
}
