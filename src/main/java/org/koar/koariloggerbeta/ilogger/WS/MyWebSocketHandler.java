package org.koar.koariloggerbeta.ilogger.WS;


import com.fasterxml.jackson.core.JsonParser;
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

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("WebSocket connection established");
        sessions.add(session);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        // Handle incoming message (if needed)
    }

    public void sendLogToClients(String log) {
        for (WebSocketSession session : sessions) {
            try {
                System.out.println("Sending log to client: " + log);
                session.sendMessage(new TextMessage(log));
            } catch (IOException e) {
                // Handle exception
            }
        }
    }
}
