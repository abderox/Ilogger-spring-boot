package org.koar.koariloggerbeta.ilogger.WS;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@EnableWebSocket
@Configuration
public class WebSocketConfig implements WebSocketConfigurer {


    private final MyWebSocketHandler myWebSocketHandler;
    Logger logger = LoggerFactory.getLogger(WebSocketConfig.class);

    public WebSocketConfig(MyWebSocketHandler myWebSocketHandler) {
        this.myWebSocketHandler = myWebSocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        logger.warn("Registering web socket handler for ilogger, ws://localhost:{port}/logs");
        registry.addHandler(myWebSocketHandler, "/logs").setAllowedOrigins("*");
    }

}
