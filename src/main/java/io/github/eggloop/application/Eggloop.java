package io.github.eggloop.application;

import io.github.eggloop.spider.Spider;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Eggloop {

    public static void main(String[] args) throws IOException, ParseException, ClassNotFoundException {
        StartupUtils.generateWelcomeMessage();
        if (args.length == 0) {
            ConsoleWriter.info("write -help for available options");
            return;
        }
        if (EggloopOptions.HELP.is(args[0])) {
            ConsoleWriter.info(EggloopOptions.printAll());
        } else if (EggloopOptions.FEATURE_SELECTION.is(args[0])) {
            Spider.getTrajectories(args[1], args[2], Integer.parseInt(args[3]), Integer.parseInt(args[4]));
        } else if (EggloopOptions.EVALUATION.is(args[0])) {
            Spider.evaluateGradient(args[1], args[2], args[3], args[4], Double.parseDouble(args[5]), Double.parseDouble(args[6]));
        } else if (EggloopOptions.PREDICTION.is(args[0])) {
            Spider.prediction(args[1], args[2], args[3], args[4]);

        } else {
            throw new IllegalArgumentException("Unsupported Operation");
        }
    }

}