/* @(#)MoveNode.java
 * Copyright (c) 2001 Werner Randelshofer, Switzerland. MIT License.
 */
package ch.randelshofer.rubik.parser;

import ch.randelshofer.rubik.Cube;
import ch.randelshofer.rubik.notation.Move;
import ch.randelshofer.rubik.notation.Notation;
import ch.randelshofer.rubik.notation.Symbol;
import ch.randelshofer.rubik.notation.Syntax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * A MoveNode holds one rotation of one or multiple layers in a specific
 * direction by a specific angle.
 * The side effect of a MoveNode on a Cube is the concatenation of all
 * permutation cycles caused by the rotation.
 *
 * @author Werner Randelshofer
 */
public class MoveNode extends Node {
    private final static long serialVersionUID = 1L;

    private int axis;
    private int layerMask;
    private int angle;
    private int layerCount;

    /**
     * Creates new MoveNode
     */
    public MoveNode(int layerCount) {
        this(layerCount, 0, 0, 0, -1, -1);
    }

    public MoveNode( Move t) {
        this(t.getLayerCount(), t.getAxis(), t.getLayerMask(), t.getAngle(), -1, -1);
    }

    /**
     * Creates new MoveNode
     */
    public MoveNode(int layerCount, int axis, int layerMask, int angle, int startpos, int endpos) {
        super(startpos, endpos);
        this.axis = axis;
        this.layerMask = layerMask;
        this.angle = angle;
        setAllowsChildren(false);
        this.layerCount=layerCount;
    }



    @Override
    public void applyTo(Cube cube, boolean inverse) {
        int normalizedAngle = angle % 4;
        if (normalizedAngle == 3) {
            normalizedAngle = -1;
        }
        if (normalizedAngle == -3) {
            normalizedAngle = 1;
        }

        cube.transform(axis, layerMask, (inverse) ? -normalizedAngle : normalizedAngle);
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

    public void setAxis(int newValue) {
        axis = newValue;
    }

    public void setLayerMask(int newValue) {
        layerMask = newValue;
    }

    public void setAngle(int newValue) {
        angle = newValue;
    }

    public int getLayerCount() {
        return layerCount;
    }

    public void setLayerCount(int newValue) {
        layerCount = newValue;
    }

    @Override
    public Iterator<Node> resolvedIterator(boolean isInverse) {
        if (isInverse) {
            MoveNode inversedNode = ((MoveNode) clone());
            inversedNode.inverse();
            return List.<Node>of(inversedNode).iterator();
        } else {
            return List.<Node>of(this).iterator();
        }
    }

    /**
     * Transformes the node by the given ScriptParser.symbol constant.
     * Does nothing if the transformation can not be done.
     */
    @Override
    public void transform(int axis, int layerMask, int angle) {
        if (layerMask == (2 << getLayerCount()) - 1) {
            // XXX - To be implemented
            if (axis != this.axis) {
                boolean swapLayers = false;
                switch (axis) {
                    case 0:
                        this.axis = ((this.axis - 1) + angle) % 2 + 1;
                        swapLayers = this.axis == 2 && angle > 1;
                        break;
                    case 1:
                        this.axis = ((this.axis / 2) + angle) % 2 * 2;
                        break;
                    case 2:
                        this.axis = (this.axis + angle) % 2 + 1;
                        break;
                }
            }
        }
        //symbol = ScriptParser.transformSymbol(transformerSymbol, symbol);
        // XXX - to be implemented
        System.out.println("MoveNode: [Warning] transform not implemented");
    }

    @Override
    public void transform(MoveNode move, boolean inverse) {
        transform(move.axis, move.layerMask, (inverse) ? -move.angle : move.angle);
    }

    /**
     * Inverses the node.
     */
    @Override
    public void inverse() {
        angle = -angle;
    }

    /**
     * Reflects the node.
     */
    @Override
    public void reflect() {
        int count = 0;
        int oldMask = layerMask;
        layerMask = 0;
        if (angle != 0) {
            for (int i = 0; i < layerCount; i++) {
                layerMask <<= 1;
                layerMask |= (oldMask & 1);
                oldMask >>>= 1;
            }
        }
    }

    public void setTo(MoveNode that) {
        this.layerMask = that.layerMask;
        this.angle = that.angle;
        this.axis = that.axis;
        this.layerCount = that.layerCount;
    }

    public boolean isRotation() {
        int allLayers = layerCount >>> 1;
        return layerMask == allLayers;
    }

    @Override
    public String toString() {
        StringBuilder b= new StringBuilder( );
        b.append(getStartPosition());
        b.append("..");
        b.append(getEndPosition());
        b.append(getClass().getSimpleName());
        b.append("{");
        b.append("ax:").append(axis).append(" lm:").append(layerMask).append(" an:").append(angle);
        b.append("}");
        return b.toString();
    }

    /**
     * Returns a string representation of this node using the specified notation.
     */
    @Override
    public void writeTokens(PrintWriter w, Notation notation, Map<String, MacroNode> macroMap)
            throws IOException {
        Move move = new Move(3, axis, layerMask, angle);
        String token = notation.getToken(move);

        // no token for +/-180° twist? 
        if (token == null && Math.abs(angle) == 2) {
            // look for a twist into the other clockwise direction
            move = new Move(3, axis, layerMask, -angle);
            token = notation.getToken(move);
            // no token for 180° twist into the other direction?
            if (token == null) {
                // look for a +/-90° twist
                move = new Move(3, axis, layerMask, -angle);
                token = notation.getToken(move);
                if (token == null) {
                    move = new Move(3, axis, layerMask, -1);
                    token = notation.getToken(move);
                }
                // print the +/-90° twist twice
                if (token != null) {
                    token = token + " " + token;
                }
            }

            // no token for +/-90° twist, but is inversion supported?
        } else if (token == null && notation.isSupported(Symbol.INVERSION)) {
            // look for a twist into the other clockwise direction
            token = notation.getToken(new Move(3, axis, layerMask, -angle));
            if (token != null) {
                Syntax syntax = notation.getSyntax(Symbol.INVERSION);
                String invertor = notation.getToken(Symbol.INVERSION_OPERATOR);
                if (syntax == Syntax.PREFIX && invertor != null) {
                    token = invertor + token;
                } else if (syntax == Syntax.SUFFIX && invertor != null) {
                    token = token + invertor;
                } else {
                    token = null;
                }
            }
        }
        if (token == null) {
            throw new IOException("Notation " + notation + " has no token for " + this);
        }
        w.write(token);
    }

    @Override
    public int hashCode() {
        return angle << 6 | layerMask << 2 | axis;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof MoveNode) {
            MoveNode that = (MoveNode) o;
            return this.layerMask == that.layerMask && this.angle == that.angle && this.axis == that.axis;
        }
        return false;
    }
}
