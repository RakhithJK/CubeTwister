/*
 * @(#)VCube6Idx3DPlayerApplet.java  1.0  2008-08-17
 *
 * Copyright (c) 2008 Werner Randelshofer, Switzerland.
 * You may not use, copy or modify this file, except in compliance with the
 * accompanying license terms.
 */


package ch.randelshofer.rubik.player;

import idx3d.*;
import ch.randelshofer.rubik.*;
import ch.randelshofer.rubik.parser.*;
import java.io.InputStream;

/**
 * VCube6Idx3DPlayerApplet.
 *
 * @author  Werner Randelshofer
 * @version 1.0 2008-08-17 Created.
 */
public class VCube6Idx3DPlayerApplet extends AbstractCubeIdx3DPlayerApplet {
    private final static long serialVersionUID = 1L;
    
    @Override
    protected ScriptPlayer createPlayer() {
        ScriptPlayer p = new ScriptPlayer();
        p.setResetCube(new Cube6());
        idx3d_JCanvas canvas3D = new idx3d_JCanvas();
        VCube6Idx3D cube3D = new VCube6Idx3D();
        cube3D.setAnimated(true);
        p.setCanvas(new Cube3DCanvasIdx3D(canvas3D, cube3D));
        p.setCube3D(cube3D);
        p.setCube(cube3D.getCube());
        return p;
    }
    protected int getLayerCount() {
        return 6;
    }


    /**
     * Returns the Default XML Resource Data of this Applet as an Input Stream. 
     */
    @Override
    protected InputStream getPlayerResources() {
        return getClass().getResourceAsStream("/ch/randelshofer/rubik/player/VCube6PlayerResources.xml");
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
