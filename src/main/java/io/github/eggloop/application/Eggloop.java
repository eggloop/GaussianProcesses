package io.github.eggloop.application;

public class Eggloop {


    public static void main(String[] args) {
        StartupUtils.generateWelcomeMessage();
        if (EggloopOptions.HELP.is(args[0])) {
            ConsoleWriter.info(EggloopOptions.printAll());
        }
    }
}
