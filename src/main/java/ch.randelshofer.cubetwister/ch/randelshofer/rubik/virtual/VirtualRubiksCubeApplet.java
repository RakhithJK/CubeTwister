/* @(#)VirtualRubiksCubeApplet.java
 * Copyright (c) 2007 Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.rubik.virtual;

import ch.randelshofer.rubik.cube.AbstractCubeIdx3D;
import ch.randelshofer.rubik.cube3d.RubiksCubeIdx3D;
import org.jhotdraw.annotation.Nonnull;

/**
 * VirtualRubiksCubeApplet.
 *
 * @author Werner Randelshofer
 */
public class VirtualRubiksCubeApplet extends AbstractVirtualCubeApplet {
    private final static long serialVersionUID = 1L;

    @Nonnull
    @Override
    protected AbstractCubeIdx3D createCube3D() {
        return new RubiksCubeIdx3D();
    }
    
    
    
    /** This method is called from within the init() method to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {

    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
}
