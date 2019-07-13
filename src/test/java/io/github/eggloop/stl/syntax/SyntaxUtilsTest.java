package io.github.eggloop.stl.syntax;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SyntaxUtilsTest {

    @Test
    void wrapTest() {
        String formula = "formula";
        String expected = "(formula)";

        String actual = SyntaxUtils.wrap(formula);

        assertEquals(expected, actual);
    }

    @Test
    void toStringBinaryFormulaTest() {
        String left = "left";
        String right = "right";
        String token = "??";
        String expected = "(left ?? right)";

        String actual = SyntaxUtils.toStringBinaryFormula(left, token, right);

        assertEquals(expected, actual);
    }

    @Test
    void toStringUnaryFormulaTest() {
        String argument = "argument";
        String token = "??";
        String expected = "??(argument)";

        String actual = SyntaxUtils.toStringUnaryFormula(token, argument);

        assertEquals(expected, actual);
    }


}