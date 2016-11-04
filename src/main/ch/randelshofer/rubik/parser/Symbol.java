/*
 * @(#)Symbol.java  10.0  2013-12-15
 * Copyright (c) 2004 Werner Randelshofer, Switzerland.
 * You may only use this software in accordance with the license terms.
 */

package ch.randelshofer.rubik.parser;
import java.util.*;
/**
 * Typesafe enum of Symbols generated by the Parser.
 *
 * @author  Werner Randelshofer
 * @version 10.0 2013-12-15 Converted from class to enum.
 * <br>9.0 2009-01-22 Reworked for ScriptParser 9.0. Renamed SCRIPT to
 * SEQUENCE.
 * <br>5.0 2005-01-31 Reworked.
 * <br>1.0  November 14, 2004  Created.
 */
public enum Symbol {
    
    /**
     * Terminal symbols.
     */
    NOP("NOP"),
    MOVE("move", "twist"),
    FACE_R("permR"),
    FACE_U("permU"),
    FACE_F("permF"),
    FACE_L("permL"),
    FACE_D("permD"),
    FACE_B("permB"),
    PERMUTATION_PLUS("permPlus"),
    PERMUTATION_MINUS("permMinus"),
    PERMUTATION_PLUSPLUS("permPlusPlus"),
    PERMUTATION_BEGIN("permBegin","permutationBegin"),
    PERMUTATION_END("permEnd","permutationEnd"),
    PERMUTATION_DELIMITER("permDelim","permutationDelimiter"),
    DELIMITER("delimiter", "statementDelimiter"),
    INVERSION_BEGIN("inversionBegin"),
    INVERSION_END("inversionEnd"),
    INVERSION_DELIMITER("inversionDelim"),
    INVERTOR("invertor"),
    REFLECTION_BEGIN("reflectionBegin"),
    REFLECTION_END("reflectionEnd"),
    REFLECTION_DELIMITER("reflectionDelim"),
    REFLECTOR("reflector"),
    GROUPING_BEGIN("groupingBegin","sequenceBegin"),
    GROUPING_END("groupingEnd","sequenceEnd"),
    REPETITION_BEGIN("repetitionBegin","repetitorBegin"),
    REPETITION_END("repetitionEnd","repetitorEnd"),
    REPETITION_DELIMITER("repetitionDelim","repetitorDelimiter"),
    COMMUTATION_BEGIN("commutationBegin","commutatorBegin"),
    COMMUTATION_END("commutationEnd","commutatorEnd"),
    COMMUTATION_DELIMITER("commutationDelim","commutatorDelimiter"),
    CONJUGATION_BEGIN("conjugationBegin","conjugatorBegin"),
    CONJUGATION_END("conjugationEnd","conjugatorEnd"),
    CONJUGATION_DELIMITER("conjugationDelim","conjugatorDelimiter"),
    ROTATION_BEGIN("rotationBegin","rotatorBegin"),
    ROTATION_END("rotationEnd","rotatorEnd"),
    ROTATION_DELIMITER("rotationDelim","rotatorDelimiter"),
    MACRO("macro"),
    MULTILINE_COMMENT_BEGIN("commentMultiLineBegin", "slashStarCommentBegin"),
    MULTILINE_COMMENT_END("commentMultiLineEnd", "slashStarCommentEnd"),
    SINGLELINE_COMMENT_BEGIN("commentSingleLineBegin", "slashSlashComment"),
    
    /**
     * Composite symbols.
     */
    
    COMMUTATION("commutation", new Symbol[] {
        COMMUTATION_BEGIN,
        COMMUTATION_END,
        COMMUTATION_DELIMITER,
    }),
    CONJUGATION("conjugation", new Symbol[] {
        CONJUGATION_BEGIN,
        CONJUGATION_END,
        CONJUGATION_DELIMITER,
    }),
    GROUPING("grouping", new Symbol[] {
        GROUPING_BEGIN,
        GROUPING_END,
    }),
    INVERSION("inversion", new Symbol[] {
        INVERSION_BEGIN,
        INVERSION_END,
        INVERSION_DELIMITER,
        INVERTOR,
    }),
    PERMUTATION("permutation", new Symbol[] {
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
    REFLECTION("reflection", new Symbol[] {
        REFLECTION_BEGIN,
        REFLECTION_END,
        REFLECTION_DELIMITER,
        REFLECTOR,
    }),
    REPETITION("repetition", new Symbol[] {
        REPETITION_BEGIN,
        REPETITION_END,
        REPETITION_DELIMITER,
    }),
    ROTATION("rotation", new Symbol[] {
        ROTATION_BEGIN,
        ROTATION_END,
        ROTATION_DELIMITER,
    }),
    COMMENT("comment", new Symbol[] {
        MULTILINE_COMMENT_BEGIN,
        MULTILINE_COMMENT_END,
        SINGLELINE_COMMENT_BEGIN,
    }),    
    STATEMENT("statement", new Symbol[] {
        NOP,
        MOVE,
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
    SEQUENCE("sequence", new Symbol[] {
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
    
    private Symbol[] subSymbols;
    
    private static HashMap<String,Symbol> symbolValueSet;
    
    private Symbol(String name) {
        this(name, null, null);
    }
    private Symbol(String name, String alternativeName) {
        this(name, alternativeName, null);
    }
    private Symbol(String name, Symbol[] terminalSymbols) {
        this(name, null, terminalSymbols);
        
    }
    private Symbol(String name, String alternativeName, Symbol[] terminalSymbols) {
        this.name = name;
        this.alternativeName = alternativeName;
        this.subSymbols = terminalSymbols;
    }
    
    public boolean isTerminalSymbol() {
        return subSymbols == null;
    }
    public boolean isSubSymbol(Symbol s) {
        if (subSymbols != null) {
            for (int i=0; i < subSymbols.length; i++) {
                if (s == subSymbols[i]) return true;
            }
            return false;
        } else {
            return s == this;
        }
    }
    public Symbol[] getSubSymbols() {
        return (subSymbols == null) ? null : subSymbols.clone();
    }
    
    public String getName() {
        return name;
    }
    
    public String getAlternativeName() {
        return alternativeName;
    }
    
    @Override
    public String toString() {
        return name;
    }
    
    public static Map<String,Symbol> getSymbolValueSet() {
        if (symbolValueSet == null) {
        symbolValueSet = new HashMap<String,Symbol>();
        for (Symbol s: values()) {
            symbolValueSet.put(s.name, s);
            if (s.alternativeName != null) {
                symbolValueSet.put(s.alternativeName, s);
            }
        }
            }
        return Collections.unmodifiableMap(symbolValueSet);
    }
}
