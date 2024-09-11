package com.example.sc.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class HrEventListenerService {

	@KafkaListener(topics = "hr-events", groupId = "security-card")
	public void listenHrEvent(String event) {
		System.out.println(event);
	}
}
