package org.koar.koariloggerbeta.ilogger;



import org.koar.koariloggerbeta.ilogger.AOP.Levels;
import org.koar.koariloggerbeta.ilogger.WS.MyWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

import static org.koar.koariloggerbeta.ilogger.ILayout.*;


public class CustomFormatter extends Formatter {

    private final MyWebSocketHandler myWebSocketHandler;


    public CustomFormatter(MyWebSocketHandler myWebSocketHandler) {
        this.myWebSocketHandler = myWebSocketHandler;
    }


    @Override
    public String format(LogRecord record) {
        StringBuilder sb = new StringBuilder();
        var time = getLocalDateTime();
        sb.append(EMOJI(Levels.valueOf(record.getLevel().getName())).repeat(3)).append("\n");
        sb.append("[");
        sb.append(time);
        sb.append("] ");
        sb.append(TYPE(Levels.valueOf(record.getLevel().getName()))).append(record.getLevel().getName()).append(TYPE(Levels.NORMAL));
        sb.append(" -- [");
        sb.append(TYPE(Levels.FINE)).append(record.getLoggerName()).append(TYPE(Levels.NORMAL));
        sb.append("] --- ").append(TYPE(Levels.FINE));
        sb.append(formatMessage(record)).append("\n");
        Map<String, String> context = LoggingContext.get();
        if (context != null) {
            for (Map.Entry<String, String> entry : context.entrySet()) {
                sb.append(TYPE(Levels.WARNING)).append(" [");
                sb.append(TYPE(Levels.WTF)).append(entry.getKey()).append(TYPE(Levels.WARNING));
                sb.append(": ");
                sb.append(entry.getValue());
                sb.append("]").append("\n");
            }
        }
        sb.append(EMOJI(Levels.valueOf(record.getLevel().getName())).repeat(3))
          .append("\n").append(TYPE(Levels.NORMAL));

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("time", time);
            jsonObject.put("level", record.getLevel().getName());
            jsonObject.put("logger", record.getLoggerName());
            jsonObject.put("message", formatMessage(record));
            jsonObject.put("context", context);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(jsonObject);
        myWebSocketHandler.sendLogToClients(jsonObject.toString());
        return sb.toString();
    }


}







