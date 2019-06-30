package io.github.eggloop.stl.syntax;

class SyntaxUtils {

    private static final String LPAR = "(";
    private static final String RPAR = ")";

    private SyntaxUtils() {
        //do nothing
    }

    static String wrap(String formula) {
        return LPAR + formula + RPAR;
    }

    static String toStringBinaryFormula(String left, String token, String right) {
        String formula = left + " " + token + " " + right;
        return wrap(formula);
    }

    static String toStringUnaryFormula(String token, String argument) {
        return token + wrap(argument);
    }
}
