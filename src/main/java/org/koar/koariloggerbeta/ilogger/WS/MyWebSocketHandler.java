package org.koar.koariloggerbeta.ilogger.WS;


import com.fasterxml.jackson.core.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Component
public class MyWebSocketHandler extends TextWebSocketHandler {

    private static  Set<WebSocketSession> sessions = new HashSet<>();
    Logger logger = LoggerFactory.getLogger(WebSocketConfig.class);

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info(" ******************* WebSocket connection established");
        sessions.add(session);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        // Handle incoming message (if needed)
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        sessions.remove(session);
        logger.info("******************** WebSocket connection closed: " + status.toString());
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        super.handleTransportError(session, exception);
        logger.info("******************** WebSocket transport error: " + exception.getMessage());
    }

    public void sendLogToClients(String log) {
        for (WebSocketSession session : sessions) {
            try {
                System.out.println("Sending log to client: " + log);
                session.sendMessage(new TextMessage(log));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
