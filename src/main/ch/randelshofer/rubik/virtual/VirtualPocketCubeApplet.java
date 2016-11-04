/*
 * @(#)RubiksCubeApplet.java  1.0  25. August 2007
 *
 * Copyright (c) 2007 Werner Randelshofer, Switzerland.
 * You may not use, copy or modify this file, except in compliance with the
 * accompanying license terms.
 */

package ch.randelshofer.rubik.virtual;

import ch.randelshofer.rubik.*;
/**
 * RubiksCubeApplet.
 * 
 * 
 * @author Werner Randelshofer
 * @version 1.0 25. August 2007 Created.
 */
public class VirtualPocketCubeApplet extends AbstractVirtualCubeApplet {
        private final static long serialVersionUID = 1L;

        @Override
    protected AbstractCubeIdx3D createCube3D() {
        return new PocketCubeIdx3D();
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
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {

    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
}
