/*
 * @(#)PocketCubeIdx3DPlayerApplet.java  2.0  2007-11-15
 *
 * Copyright (c) 2005-2007 Werner Randelshofer, Switzerland.
 * You may not use, copy or modify this file, except in compliance with the
 * accompanying license terms.
 */


package ch.randelshofer.rubik.player;

import ch.randelshofer.rubik.Cube3DCanvasIdx3D;
import ch.randelshofer.rubik.PocketCube;
import ch.randelshofer.rubik.PocketCubeIdx3D;
import ch.randelshofer.rubik.parser.ScriptPlayer;
import idx3d.idx3d_JCanvas;
import java.io.InputStream;


/**
 * PocketCubeIdx3DPlayerApplet.
 *
 * @author  Werner Randelshofer
 * @version 2.0 2007-11-15 Upgraded to Java 1.4.
 */
public class PocketCubeIdx3DPlayerApplet extends AbstractCubeIdx3DPlayerApplet {
    private final static long serialVersionUID = 1L;
    
    @Override
    protected ScriptPlayer createPlayer() {
        ScriptPlayer p = new ScriptPlayer();
        idx3d_JCanvas canvas3D = new idx3d_JCanvas();
        PocketCubeIdx3D cube3D = new PocketCubeIdx3D();
        cube3D.setAnimated(true);
        p.setCanvas(new Cube3DCanvasIdx3D(canvas3D, cube3D));
        p.setCube3D(cube3D);
        p.setCube(cube3D.getCube());
        p.setResetCube(new PocketCube());
        return p;
    }
    @Override
    protected int getLayerCount() {
        return 2;
    }


    /**
     * Returns the Default XML Resource Data of this Applet as an Input Stream. 
     */
    @Override
    protected InputStream getPlayerResources() {
        return getClass().getResourceAsStream("/ch/randelshofer/rubik/player/PocketPlayerResources.xml");
    }
    
    /** This method is called from within the init() method to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     * /
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        getContentPane().setLayout(new java.awt.FlowLayout());
    }// </editor-fold>//GEN-END:initComponents
    */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
}
