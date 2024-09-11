package com.example.hr.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer{
	private final WebSocketHandler webSocketHandler;
	
	public WebSocketConfiguration(WebSocketHandler webSocketHandler) {
		this.webSocketHandler = webSocketHandler;
	}

	// ws://localhost:9100/hr/api/v1/hr-events
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(webSocketHandler, "hr-events")
		        .setAllowedOrigins("*");
		
	}

	
}
