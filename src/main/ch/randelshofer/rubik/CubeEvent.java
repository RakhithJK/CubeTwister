/*
 * @(#)CubeEvent.java  1.1  2007-12-31
 *
 * Copyright (c) 2003-2007 Werner Randelshofer, Switzerland.
 * You may not use, copy or modify this file, except in compliance with the
 * accompanying license terms.
 */

package ch.randelshofer.rubik;

/**
 * CubeEvent is used to notify interested parties that an event has occured
 * in a Cube object.
 *
 * @author  Werner Randelshofer
 * @version 1.1 2007-12-31 Added getChangedParts method. 
 * <br>1.0 December 21, 2003 Created.
 */
public class CubeEvent extends java.util.EventObject {
    private final static long serialVersionUID = 1L;
    private int axis;
    private int layerMask;
    private int angle;
    
    /** Creates a new instance. */
    public CubeEvent(Cube src, int axis, int layerMask, int angle) {
        super(src);
        this.axis = axis;
        this.layerMask = layerMask;
        this.angle = angle;
    }
    
    public Cube getCube() {
        return (Cube) getSource();
    }
    
    public int getAxis() {
        return axis;
    }

    public int getLayerMask() {
        return layerMask;
    }

    public int getAngle() {
        return angle;
    }
    
    /**
     * Returns a list of part ID's, for each part location which is affected
     * if a cube is transformed using the axis, layerMaska and angle
     * parameters of this event. 
     */
    public int[] getAffectedLocations() {
        Cube c1 = (Cube) getCube().clone();
        c1.reset();
        c1.transform(axis, layerMask, angle);
        return c1.getUnsolvedParts();
    }
}
