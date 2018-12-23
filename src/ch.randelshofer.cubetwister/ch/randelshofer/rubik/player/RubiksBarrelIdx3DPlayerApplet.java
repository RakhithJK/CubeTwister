/* @(#)RubiksBarrelIdx3DPlayerApplet.java
 * Copyright (c) 2005 Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.rubik.player;

import ch.randelshofer.rubik.Cube3DCanvasIdx3D;
import ch.randelshofer.rubik.RubiksBarrelIdx3D;
import ch.randelshofer.rubik.RubiksCube;
import ch.randelshofer.rubik.parser.ScriptPlayer;
import idx3d.idx3d_JCanvas;

/**
 * RubiksBarrelIdx3DPlayerApplet.
 *
 * @author  Werner Randelshofer
 * @version $Id$
 */
public class RubiksBarrelIdx3DPlayerApplet extends AbstractCubeIdx3DPlayerApplet {
    private final static long serialVersionUID = 1L;
    
    @Override
    protected ScriptPlayer createPlayer() {
        ScriptPlayer p = new ScriptPlayer();
        p.setResetCube(new RubiksCube());
        idx3d_JCanvas canvas3D = new idx3d_JCanvas();
        RubiksBarrelIdx3D cube3D = new RubiksBarrelIdx3D();
        cube3D.setAnimated(true);
        p.setCanvas(new Cube3DCanvasIdx3D(canvas3D, cube3D));
        p.setCube3D(cube3D);
        p.setCube(cube3D.getCube());
        return p;
    }

    protected int getLayerCount() {
        return 3;
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
