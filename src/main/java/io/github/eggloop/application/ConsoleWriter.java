package io.github.eggloop.application;

import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

class ConsoleWriter {
    private static final Logger CONSOLE_LOGGER = Logger.getAnonymousLogger();

    static {
        CONSOLE_LOGGER.setUseParentHandlers(false);
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new Formatter() {
            @Override
            public String format(LogRecord record) {
                return record.getMessage() + "\n";
            }
        });
        CONSOLE_LOGGER.addHandler(handler);
    }


    static void info(String msg) {
        CONSOLE_LOGGER.info(msg);
        CONSOLE_LOGGER.info("");
    }
}