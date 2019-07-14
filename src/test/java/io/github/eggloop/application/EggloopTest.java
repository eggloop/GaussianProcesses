package io.github.eggloop.application;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EggloopTest {

    @Test
    void testMain() {
        String[] arguments = new String[]{"-help"};
        Eggloop.main(arguments);
    }
}