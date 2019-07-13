package io.github.eggloop.expression.arithmetic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AssignmentTest {

    @Test
    void testGet() {
        Assignment assignment = new Assignment();
        assignment.put("A", 10);

        double actualValue = assignment.get("A");

        assertEquals(10, actualValue);
    }

    @Test
    void testContains() {
        Assignment assignment = new Assignment();
        assignment.put("A", 10);

        assertTrue(assignment.contains("A"));
    }

    @Test
    void testPut() {
        Assignment assignment = new Assignment();

        assignment.put("A", 10);

        assertTrue(assignment.contains("A"));
        assertEquals(10, assignment.get("A"));

    }
}