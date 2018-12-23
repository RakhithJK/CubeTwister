/* @(#)VCube7Idx3DPlayerApplet.java
 * Copyright (c) 2008 Werner Randelshofer, Switzerland. MIT License.
 */


package ch.randelshofer.rubik.player;

import ch.randelshofer.rubik.Cube3DCanvasIdx3D;
import ch.randelshofer.rubik.Cube7;
import ch.randelshofer.rubik.VCube7Idx3D;
import ch.randelshofer.rubik.parser.ScriptPlayer;
import idx3d.idx3d_JCanvas;

import java.io.InputStream;

/**
 * VCube7Idx3DPlayerApplet.
 *
 * @author  Werner Randelshofer
 * @version $Id$
 */
public class VCube7Idx3DPlayerApplet extends AbstractCubeIdx3DPlayerApplet {
    private final static long serialVersionUID = 1L;
    
    @Override
    protected ScriptPlayer createPlayer() {
        ScriptPlayer p = new ScriptPlayer();
        p.setResetCube(new Cube7());
        idx3d_JCanvas canvas3D = new idx3d_JCanvas();
        VCube7Idx3D cube3D = new VCube7Idx3D();
        cube3D.setAnimated(true);
        p.setCanvas(new Cube3DCanvasIdx3D(canvas3D, cube3D));
        p.setCube3D(cube3D);
        p.setCube(cube3D.getCube());
        return p;
    }
    @Override
    protected int getLayerCount() {
        return 7;
    }


    /**
     * Returns the Default XML Resource Data of this Applet as an Input Stream. 
     */
    @Override
    protected InputStream getPlayerResources() {
        return getClass().getResourceAsStream("/ch/randelshofer/rubik/player/VCube7PlayerResources.xml");
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
