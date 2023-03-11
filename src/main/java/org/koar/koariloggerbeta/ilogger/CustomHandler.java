package org.koar.koariloggerbeta.ilogger;

import java.util.logging.Handler;
import java.util.logging.LogRecord;


public class CustomHandler extends Handler {



    @Override
    public void publish(LogRecord record) {
        if (isLoggable(record)) {
            System.out.print(getFormatter().format(record));
        }
    }

    @Override
    public void flush() {
        System.out.flush();
    }

    @Override
    public void close() throws SecurityException {
        System.out.close();
    }
}

