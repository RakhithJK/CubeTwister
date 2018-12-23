/* @(#)RubiksCuboctahedronGeom3DPlayerApplet.java
 * Copyright (c) 2005 Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.rubik.player;

import ch.randelshofer.geom3d.*;
import ch.randelshofer.rubik.parser.*;
import ch.randelshofer.rubik.*;

/** 
 * RubiksCuboctahedronGeom3DPlayerApplet.
 *
 * @author  Werner Randelshofer
 * @version $Id$
 */
public class RubiksCuboctahedronGeom3DPlayerApplet extends AbstractCubeGeom3DPlayerApplet {
    private final static long serialVersionUID = 1L;
    
    @Override
    protected ScriptPlayer createPlayer() {
        ScriptPlayer p = new ScriptPlayer();
        p.setResetCube(new RubiksCube());
        JCanvas3D canvas3D = new JCanvas3D();
        //RubiksCubeSimple3D cube3D = new RubiksCubeSimple3D();
        RubiksCuboctahedronGeom3D cube3D = new RubiksCuboctahedronGeom3D();
        cube3D.setAnimated(true);
        p.setCanvas(new Cube3DCanvasGeom3D(canvas3D, cube3D));
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
    }// </editor-fold>//GEN-END:initComponents
    */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
}