/* @(#)Symbol.java
 * Copyright (c) 2004 Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.rubik.notation;

import org.jhotdraw.annotation.Nonnull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Typesafe enum of Symbols generated by the Parser.
 *
 * @author Werner Randelshofer
 */
public enum Symbol {

    /**
     * Terminal symbols.
     */
    NOP("NOP"),
    MOVE("move", "twist"),

    // Note: Ordering of the enum constants is significant.
    // Class PermutationNode implies that PR < PU < PF < PL < PD < PB.
    FACE_R("permR"),
    FACE_U("permU"),
    FACE_F("permF"),
    FACE_L("permL"),
    FACE_D("permD"),
    FACE_B("permB"),

    PERMUTATION_PLUS("permPlus"),
    PERMUTATION_MINUS("permMinus"),
    PERMUTATION_PLUSPLUS("permPlusPlus"),
    PERMUTATION_BEGIN("permBegin", "permutationBegin"),
    PERMUTATION_END("permEnd", "permutationEnd"),
    PERMUTATION_DELIMITER("permDelim", "permutationDelimiter"),
    DELIMITER("delimiter", "statementDelimiter"),
    INVERSION_BEGIN("inversionBegin"),
    INVERSION_END("inversionEnd"),
    INVERSION_OPERATOR("invertor"),
    REFLECTION_BEGIN("reflectionBegin"),
    REFLECTION_END("reflectionEnd"),
    REFLECTION_OPERATOR("reflector"),
    GROUPING_BEGIN("groupingBegin", "sequenceBegin"),
    GROUPING_END("groupingEnd", "sequenceEnd"),
    REPETITION_BEGIN("repetitionBegin", "repetitorBegin"),
    REPETITION_END("repetitionEnd", "repetitorEnd"),
    REPETITION_DELIMITER("repetitionDelim", "repetitorDelimiter"),
    REPETITION_OPERATOR("repetitionOperator"),
    COMMUTATION_BEGIN("commutationBegin", "commutatorBegin"),
    COMMUTATION_END("commutationEnd", "commutatorEnd"),
    COMMUTATION_DELIMITER("commutationDelim", "commutatorDelimiter"),
    COMMUTATION_OPERATOR("commutationOperator"),
    CONJUGATION_BEGIN("conjugationBegin", "conjugatorBegin"),
    CONJUGATION_END("conjugationEnd", "conjugatorEnd"),
    CONJUGATION_DELIMITER("conjugationDelim", "conjugatorDelimiter"),
    CONJUGATION_OPERATOR("conjugationOperator"),
    ROTATION_BEGIN("rotationBegin", "rotatorBegin"),
    ROTATION_END("rotationEnd", "rotatorEnd"),
    ROTATION_DELIMITER("rotationDelim", "rotationDelimiter"),
    ROTATION_OPERATOR("rotationDelim", "rotatorDelimiter"),
    MACRO("macro"),
    MULTILINE_COMMENT_BEGIN("commentMultiLineBegin", "slashStarCommentBegin"),
    MULTILINE_COMMENT_END("commentMultiLineEnd", "slashStarCommentEnd"),
    SINGLELINE_COMMENT_BEGIN("commentSingleLineBegin", "slashSlashComment"),

    /**
     * Composite symbols.
     */

    COMMUTATION("commutation", new Symbol[]{
            COMMUTATION_BEGIN,
            COMMUTATION_END,
            COMMUTATION_DELIMITER,
            COMMUTATION_OPERATOR
    }),
    CONJUGATION("conjugation", new Symbol[]{
            CONJUGATION_BEGIN,
            CONJUGATION_END,
            CONJUGATION_DELIMITER,
            CONJUGATION_OPERATOR
    }),
    GROUPING("grouping", new Symbol[]{
            GROUPING_BEGIN,
            GROUPING_END,
    }),
    INVERSION("inversion", new Symbol[]{
            INVERSION_BEGIN,
            INVERSION_END,
            INVERSION_OPERATOR,
    }),
    PERMUTATION("permutation", new Symbol[]{
            FACE_R,
            FACE_U,
            FACE_F,
            FACE_L,
            FACE_D,
            FACE_B,
            PERMUTATION_PLUS,
            PERMUTATION_MINUS,
            PERMUTATION_PLUSPLUS,
            PERMUTATION_BEGIN,
            PERMUTATION_END,
            PERMUTATION_DELIMITER,
    }),

    REFLECTION("reflection", new Symbol[]{
            REFLECTION_BEGIN,
            REFLECTION_END,
            REFLECTION_OPERATOR,
    }),
    REPETITION("repetition", new Symbol[]{
            REPETITION_BEGIN,
            REPETITION_END,
            REPETITION_DELIMITER,
            REPETITION_OPERATOR,
    }),
    ROTATION("rotation", new Symbol[]{
            ROTATION_BEGIN,
            ROTATION_END,
            ROTATION_OPERATOR,
            ROTATION_DELIMITER,
    }),
    COMMENT("comment", new Symbol[]{
            MULTILINE_COMMENT_BEGIN,
            MULTILINE_COMMENT_END,
            SINGLELINE_COMMENT_BEGIN,
    }),
    PRIMARY("primary", new Symbol[]{
            NOP,
            MOVE,
            MACRO
    }),
    STATEMENT("statement", new Symbol[]{
            PRIMARY,
            GROUPING,
            INVERSION,
            REFLECTION,
            CONJUGATION,
            COMMUTATION,
            ROTATION,
            PERMUTATION,
            DELIMITER,
            REPETITION,
    }),
    SEQUENCE("sequence", new Symbol[]{
            STATEMENT,
            COMMENT
    });

    /**
     * Name of the symbol.
     */
    private final String name;
    /**
     * Alternative symbol name for backward compatibility with older versions
     * of CubeTwister.
     */
    private final String alternativeName;

    private Set<Symbol> subSymbols;
    private volatile static Map<String, Symbol> symbolValueSet;
    private volatile static Map<Symbol, Symbol> compositeSymbolMap;

    Symbol(String name) {
        this(name, null, new Symbol[0]);
    }

    Symbol(String name, String alternativeName) {
        this(name, alternativeName, new Symbol[0]);
    }

    Symbol(String name, Symbol[] subSymbols) {
        this(name, null, subSymbols);

    }

    Symbol(String name, String alternativeName, Symbol[] subSymbols) {
        this.name = name;
        this.alternativeName = alternativeName;
        this.subSymbols = Set.of(subSymbols);

    }

    public boolean isTerminalSymbol() {
        return subSymbols == null;
    }

    public boolean isSubSymbol(Symbol s) {
        return s == this || subSymbols.contains(s);
    }

    public Set<Symbol> getSubSymbols() {
        return subSymbols;
    }

    public String getName() {
        return name;
    }

    public String getAlternativeName() {
        return alternativeName;
    }

    public Symbol getCompositeSymbol() {
        return getCompositeSymbolMap().get(this);

    }


    @Override
    public String toString() {
        return name;
    }

    public static Map<String, Symbol> getSymbolValueSet() {
        if (symbolValueSet == null) {
            HashMap<String, Symbol> m = new HashMap<>();
            for (Symbol s : values()) {
                m.put(s.name, s);
                if (s.alternativeName != null) {
                    m.put(s.alternativeName, s);
                }
            }
            symbolValueSet = Collections.unmodifiableMap(m);
        }
        return symbolValueSet;
    }

    public static Map<Symbol, Symbol> getCompositeSymbolMap() {
        if (compositeSymbolMap == null) {
            HashMap<Symbol, Symbol> m = new HashMap<>();
            for (Symbol s : values()) {
                m.put(s, s);
            }
            for (Symbol s : values()) {
                for (Symbol subSymbol : s.subSymbols) {
                    if (subSymbol.subSymbols.isEmpty()) {
                        m.put(subSymbol, s);
                    }
                }
            }
            compositeSymbolMap = Collections.unmodifiableMap(m);
        }
        return compositeSymbolMap;
    }

    static public boolean isBegin(@Nonnull Symbol s) {
        switch (s) {
            case CONJUGATION_BEGIN:
            case COMMUTATION_BEGIN:
            case ROTATION_BEGIN:
            case PERMUTATION_BEGIN:
            case INVERSION_BEGIN:
            case REFLECTION_BEGIN:
            case GROUPING_BEGIN:
            case MULTILINE_COMMENT_BEGIN:
            case SINGLELINE_COMMENT_BEGIN:
                return true;
            default:
                return false;
        }
    }

    static public boolean isOperator(@Nonnull Symbol s) {
        switch (s) {
            case CONJUGATION_OPERATOR:
            case COMMUTATION_OPERATOR:
            case ROTATION_OPERATOR:
            case INVERSION_OPERATOR:
            case REFLECTION_OPERATOR:
            case REPETITION_OPERATOR:
                return true;
            default:
                return false;
        }
    }

    static public boolean isDelimiter(@Nonnull Symbol s) {
        switch (s) {
            case ROTATION_DELIMITER:
            case CONJUGATION_DELIMITER:
            case COMMUTATION_DELIMITER:
                return true;
            default:
                return false;
        }
    }

    static public boolean isEnd(@Nonnull Symbol s) {
        switch (s) {
            case CONJUGATION_END:
            case COMMUTATION_END:
            case PERMUTATION_END:
            case ROTATION_END:
            case INVERSION_END:
            case REFLECTION_END:
            case GROUPING_END:
            case MULTILINE_COMMENT_END:
                return true;
            default:
                return false;
        }
    }

    static public boolean isFaceSymbol(@Nonnull Symbol s) {
        switch (s) {
            case FACE_R:
            case FACE_U:
            case FACE_F:
            case FACE_L:
            case FACE_D:
            case FACE_B:
                return true;
            default:
                return false;
        }
    }

    static public boolean isPermutationSign(@Nonnull Symbol s) {
        switch (s) {
            case PERMUTATION_PLUS:
            case PERMUTATION_PLUSPLUS:
            case PERMUTATION_MINUS:
                return true;
            default:
                return false;
        }
    }
}
