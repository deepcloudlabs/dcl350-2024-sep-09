package com.example.sc.service;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;

import jakarta.annotation.PostConstruct;

@Service
public class HrWebSocketClientService implements WebSocketHandler {
	private final WebSocketClient webSocketClient;
	
	public HrWebSocketClientService(WebSocketClient webSocketClient) {
		this.webSocketClient = webSocketClient;
	}

	@PostConstruct
	public void connect() {
		webSocketClient.execute(this, "ws://localhost:9100/hr/api/v1/hr-events");
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("Connected to the hr ws service: %s".formatted(session.getId()));
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		System.out.println("New event has arrived through ws: %s".formatted(message.getPayload().toString()));
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable t) throws Exception {
		System.out.println("Error has occured in ws session[%s]: %s".formatted(session.getId(),t.getMessage()));		
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		System.out.println("Connection is closed: %s".formatted(session.getId()));
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}
}
