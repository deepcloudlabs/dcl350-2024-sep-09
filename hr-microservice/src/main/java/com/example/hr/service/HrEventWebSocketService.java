package com.example.hr.service;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.example.hr.domain.events.HrEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class HrEventWebSocketService implements WebSocketHandler{
	private final Map<String,WebSocketSession> sessions = new ConcurrentHashMap<>();
	private final ObjectMapper objectMapper;

	public HrEventWebSocketService(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("New connection is open: %s".formatted(session.getId()));
		sessions.put(session.getId(), session);
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
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

	@EventListener
	public void listenHrEvent(HrEvent event) {
		try {
			var eventAsJson = objectMapper.writeValueAsString(event);
			final var textMessage = new TextMessage(eventAsJson);
			sessions.values().forEach(session ->{
				try {
					session.sendMessage(textMessage);
				} catch (IOException e) {
					System.out.println("Error while sending event through ws[%s]: %s".formatted(session.getId(),e.getMessage()));
				}
			});
		} catch (JsonProcessingException e) {
			System.out.println("Erro while serializing to json: %s".formatted(e.getMessage()));
		}
	}	
}
