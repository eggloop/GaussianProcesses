package io.github.eggloop.application;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class EggloopTest {

    @Test
    void testMain() throws IOException, ParseException, ClassNotFoundException {
        String[] arguments = new String[]{"-help"};
        Eggloop.main(arguments);
    }
}