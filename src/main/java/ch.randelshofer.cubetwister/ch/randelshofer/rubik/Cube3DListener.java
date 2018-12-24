/* @(#)Cube3DListener.java
 * Copyright (c) 2003 Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.rubik;

/**
 * The listener interface for Cube3D events.
 *
 * @author  Werner Randelshofer
 * @version $Id$
 * <br>1.0 December 22, 2003 Created.
 */
public interface Cube3DListener extends java.util.EventListener {
    /**
     * Invoked when an action on a part of the geometry occured.
     */
    public void actionPerformed(Cube3DEvent evt);
    
    /**
     * Invoked when the mouse entered a part.
     */
    public void mouseEntered(Cube3DEvent evt);
    /**
     * Invoked when the mouse exited a part.
     */
    public void mouseExited(Cube3DEvent evt);
    /**
     * Invoked when the mouse button is pressed.
     */
    public void mousePressed(Cube3DEvent evt);
    /**
     * Invoked when the mouse button is released.
     */
    public void mouseReleased(Cube3DEvent evt);
}
