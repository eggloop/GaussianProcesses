package io.github.eggloop.stl.visitor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LogicOperatorTokenTest {

    private OperatorToken operatorToken;

    @BeforeEach
    void setUp() {
        operatorToken = new LogicOperatorToken();
    }

    @Test
    void conjunctionTest() {
        assertEquals("∧", operatorToken.conjunction());
    }

    @Test
    void disjunctionTest() {
        assertEquals("∨", operatorToken.disjunction());
    }

    @Test
    void negationTest() {
        assertEquals("¬", operatorToken.negation());
    }
}