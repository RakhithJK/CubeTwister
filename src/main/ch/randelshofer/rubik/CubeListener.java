/*
 * @(#)CubeListener.java  1.0  December 21, 2003
 *
 * Copyright (c) 2003 Werner Randelshofer, Switzerland.
 * You may not use, copy or modify this file, except in compliance with the
 * accompanying license terms.
 */

package ch.randelshofer.rubik;

/**
 * The listener interface for receiving cube events.
 *
 * @author  Werner Randelshofer
 * @version 1.0 December 21, 2003 Create..
 */
public interface CubeListener extends java.util.EventListener {
    public void cubeTwisted(CubeEvent evt);
    public void cubeChanged(CubeEvent evt);
}
