/* @(#)ConjugationNode.java
 * Copyright (c) 2001 Werner Randelshofer, Switzerland. MIT License.
 */
package ch.randelshofer.rubik.parser;

import ch.randelshofer.rubik.Cube;
import ch.randelshofer.rubik.notation.Notation;
import ch.randelshofer.rubik.notation.Symbol;
import ch.randelshofer.rubik.notation.Syntax;
import ch.randelshofer.util.ReverseListIterator;
import ch.randelshofer.util.SequenceIterator;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * A ConjugationNode holds a conjugator A and a single child B.
 * The side effect of a conjugation node is A B A'.
 *
 * @author Werner Randelshofer
 */
public class ConjugationNode extends BinaryNode {
    private final static long serialVersionUID = 1L;

    public ConjugationNode() {
        this(-1, -1);
    }

    public ConjugationNode(int startpos, int endpos) {
        super(startpos, endpos);
        operand1 = new SequenceNode();
        operand1.setParent(this);
    }

    public ConjugationNode(Node operand1, Node conjugate, int startpos, int endpos) {
        super(startpos, endpos);
        operand1.removeFromParent();
        operand1.setParent(this);
        this.operand1 = operand1;
        if (conjugate != null) {
            add(conjugate);
        }
    }

    /**
     * Applies the symbol represented by this node to the cube.
     *
     * @param cube    A cube to be transformed by this symbol.
     * @param inverse If true, the transform will be done in inverse order.
     */
    public void applyTo(Cube cube, boolean inverse) {
        operand1.applyTo(cube, false);
        super.applyTo(cube, inverse);
        operand1.applyTo(cube, true);
    }

    /**
     * Inverses the subtree starting at this node.
     */
    @Override
    public void inverse() {
        //conjugator.inverse(); / Conjugator does not have to be inverted
        super.inverse();
    }

    /**
     * Reflect the subtree starting at this node.
     */
    @Override
    public void reflect() {
        operand1.reflect();
        super.reflect();
    }

    /**
     * Transformes the subtree starting at this node by
     * the given ScriptParser.symbol constant.
     * Does nothing if the transformation can not be done.
     */
    public void transform(int axis, int layerMask, int angle) {
        operand1.transform(axis, layerMask, angle);
        super.transform(axis, layerMask, angle);
    }

    /**
     * Returns a deep clone of the subtree starting at this node.
     */
    public Node cloneSubtree() {
        ConjugationNode that = (ConjugationNode) super.cloneSubtree();
        that.operand1 = this.operand1.cloneSubtree();
        return that;
    }

    @Override
    public Iterator<Node> resolvedIterator(boolean inverse) {
        return new SequenceIterator<>(
                List.of(
                        operand1.resolvedIterator(inverse),
                        super.resolvedIterator(inverse),
                        operand1.resolvedIterator(!inverse)
                )
        );
    }

    @Override
    public void writeTokens(PrintWriter w, Notation p, Map<String, MacroNode> macroMap)
            throws IOException {
        Syntax pos = (p.isSupported(Symbol.GROUPING)) ? p.getSyntax(Symbol.CONJUGATION) : null;
        if (pos == null) {
            // Extract rotators at the beginning of the conjugator
            // if any.
            // Break the loop, if other symbols than rotators are encountered.
            boolean isPureRotation = true;
            Vector<Node> rotators = new Vector<Node>();
            Iterator<Node> i = operand1.resolvedIterator(false);
            while (i.hasNext()) {
                Node node = i.next();
                if (node instanceof MoveNode) {
                    MoveNode transform = (MoveNode) node;
                    if (transform.isRotation()) {
                        rotators.addElement(transform);
                    } else {
                        isPureRotation = false;
                        break;
                    }
                } else if (node instanceof PermutationNode) {
                    isPureRotation = false;
                    break;
                }
            }

            // If the conjugator consists of rotators only it can be
            // replaced by the rotated version of the children only
            // if none of the macros must be preserved.
            if (isPureRotation) {
                for (Node node : preorderIterable()) {
                    if (node instanceof MacroNode
                            && macroMap.containsKey(((MacroNode) node).getIdentifier())) {
                        isPureRotation = false;
                        break;
                    }
                }
            }

            if (isPureRotation) {
                // If the conjugator consists of rotators only it can be
                // written as rotated children.
                ConjugationNode rotated = (ConjugationNode) cloneSubtree();
                rotated.operand1.removeAllChildren();

                i = new ReverseListIterator<Node>(rotators);
                while (i.hasNext()) {
                    MoveNode rotator = (MoveNode) i.next();
                    rotated.transform(rotator, true);
                }
                i = rotated.childIterator();
                while (i.hasNext()) {
                    i.next().writeTokens(w, p, macroMap);
                    if (i.hasNext()) {
                        p.writeToken(w, Symbol.DELIMITER);
                        w.write(' ');
                    }
                }
            } else {
                // Write the commutation as (A B A').
                if (p.isSupported(Symbol.GROUPING)) {
                    p.writeToken(w, Symbol.GROUPING_BEGIN);
                }
                operand1.writeTokens(w, p, macroMap);
                w.write(' ');
                super.writeTokens(w, p, macroMap);
                w.write(' ');
                Node inverseCommutator = operand1.cloneSubtree();
                inverseCommutator.inverse();
                inverseCommutator.writeTokens(w, p, macroMap);
                if (p.isSupported(Symbol.GROUPING)) {
                    p.writeToken(w, Symbol.GROUPING_END);
                }
            }
        } else if (pos == Syntax.PREFIX) {
            if (operand1.getChildCount() != 0) {
                p.writeToken(w, Symbol.CONJUGATION_BEGIN);
                operand1.writeTokens(w, p, macroMap);
                p.writeToken(w, Symbol.CONJUGATION_END);
            }
            super.writeTokens(w, p, macroMap);
        } else if (pos == Syntax.SUFFIX) {
            super.writeTokens(w, p, macroMap);
            if (operand1.getChildCount() != 0) {
                p.writeToken(w, Symbol.CONJUGATION_BEGIN);
                operand1.writeTokens(w, p, macroMap);
                p.writeToken(w, Symbol.CONJUGATION_END);
            }
        } else if (pos == Syntax.PRECIRCUMFIX) {
            p.writeToken(w, Symbol.CONJUGATION_BEGIN);
            operand1.writeTokens(w, p, macroMap);
            p.writeToken(w, Symbol.CONJUGATION_DELIMITER);
            super.writeTokens(w, p, macroMap);
            p.writeToken(w, Symbol.CONJUGATION_END);
        }
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append(getStartPosition());
        b.append("..");
        b.append(getEndPosition());
        b.append(getClass().getSimpleName());
        b.append("{");
        b.append(' ');
        b.append(operand1);
        b.append(",");
        for (Node n : getChildren()) {
            b.append(' ');
            b.append(n.toString());
        }
        b.append(' ');
        b.append("}");
        return b.toString();
    }
}
