/* @(#)VirtualCube6Applet.java
 * Copyright (c) 2008 Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.rubik.virtual;

import ch.randelshofer.rubik.cube.AbstractCubeIdx3D;
import ch.randelshofer.rubik.cube3d.Cube6Idx3D;
import org.jhotdraw.annotation.Nonnull;

/**
 * VirtualCube6Applet.
 *
 * @author Werner Randelshofer
 */
public class VirtualCube6Applet extends AbstractVirtualCubeApplet {
    private final static long serialVersionUID = 1L;

    @Nonnull
    @Override
    protected AbstractCubeIdx3D createCube3D() {
        return new Cube6Idx3D();
    }
        @Override
    protected boolean canBeDisassembled() {
        return false;
    }
    
    
    /** This method is called from within the init() method to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
}
