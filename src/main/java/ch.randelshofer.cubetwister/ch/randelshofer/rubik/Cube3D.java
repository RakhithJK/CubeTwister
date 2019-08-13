/* @(#)Cube3D.java
 * Copyright (c) 2006 Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.rubik;

import ch.randelshofer.util.Dispatcher;
import org.monte.media.player.Animator;

import javax.swing.event.ChangeListener;
/**
 * The interface for objects which represent the three dimensional geometry of 
 * a Rubik's Cube-like puzzle.
 * 
 * @author Werner Randelshofer.
 */
public interface Cube3D {

    public int getPartCount();

    public abstract Object getScene();
    
    /**
     * Returns the lock object used for synchronizing model and view changes.
     */
    public Object getLock();

    public void setAnimator(Animator animator);
    public Animator getAnimator();
    /**
     * Sets the lock object used for synchronizing model and view changes.
     */
    public void setLock(Object o);
    /**
     * Sets the dispatcher used to process animations of the 3D geometry.
     */
    public void setDispatcher(Dispatcher dispatcher);
    /**
     * Gets the dispatcher used to process animations of the 3D geometry.
     */
    public Dispatcher getDispatcher();
    
    
    /**
     * Sets whether changes in the permutation model shall cause an animated
     * (multi-frame) change in the 3D geometry or whether they shall be
     * reflected immediately.
     */
    public void setAnimated(boolean b);
    
    /**
     * Returns true when the 3D geometry animates permutation changes.
     */
    public boolean isAnimated();
    
    /**
     * Stops all running animations. 
     */
    public void stopAnimation();
    
    /**
     * Sets the underlying permutation model.
     */
    public void setCube(Cube cube);
    /**
     * Gets the underlying permutation model.
     */
    public Cube getCube();
    /**
     * Gets the kind of the cube.
     */
    public CubeKind getKind();

    /**
     * Sets cube attributees.
     */
    public void setAttributes(CubeAttributes attributes);
    
    /**
     * Gets cube attributees.
     */
    public CubeAttributes getAttributes();
    
    /**
     * Adds a change listener. The change listener is notified about geometry
     * changes. This is useful for a 3D canvas interested to know when to repaint.
     */
    public void addChangeListener(ChangeListener listener);
    
    /**
     * Removes a change listener.
     */
    public void removeChangeListener(ChangeListener listener);
    
    public void addCube3DListener(Cube3DListener listener);
    
    public void removeCube3DListener(Cube3DListener listener);

    /** Returns true if the cube is currently performing an animation. */
    public boolean isAnimating();
    
    /** Returns true if the cube is in a started script player. */
    public boolean isInStartedPlayer();
    /** This is set to true by the script player if it is started. */
    public void setInStartedPlayer(boolean newValue);

    public boolean isShowGhostParts();
    
    public void setShowGhostParts(boolean b);
    
    public void dispose();

    public int getPartIndexForStickerIndex(int i);

    public void setStickerBeveling(float newValue);
}
