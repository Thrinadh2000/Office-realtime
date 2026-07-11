package com.example.office.config;

import com.example.office.websocket.OfficeSocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final OfficeSocketHandler officeSocketHandler;

    public WebSocketConfig(OfficeSocketHandler officeSocketHandler) {
        this.officeSocketHandler = officeSocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(officeSocketHandler, "/ws/office")
                .setAllowedOrigins("*"); // fine for a simple demo, restrict in production
    }
}
