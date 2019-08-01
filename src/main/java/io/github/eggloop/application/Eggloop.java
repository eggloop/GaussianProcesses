package io.github.eggloop.application;

import io.github.eggloop.spider.AllFormulas2;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Eggloop {

    public static void main(String[] args) throws IOException, ParseException {
        StartupUtils.generateWelcomeMessage();
        if (EggloopOptions.HELP.is(args[0])) {
            ConsoleWriter.info(EggloopOptions.printAll());
        } else {
            AllFormulas2.getTrajectories(args[0], args[1]);
        }
    }
}