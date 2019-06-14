package io.github.eggloop.learning;

import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class LoggerFactory {

    public static Logger get() {
        Logger logger = Logger.getAnonymousLogger();
        logger.setLevel(Level.INFO);
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new Formatter() {
            @Override
            public String format(LogRecord record) {
                return super.formatMessage(record) + "\n";
            }
        });
        logger.setUseParentHandlers(false);
        logger.addHandler(handler);
        handler.setLevel(Level.INFO);
        return logger;
    }
}
