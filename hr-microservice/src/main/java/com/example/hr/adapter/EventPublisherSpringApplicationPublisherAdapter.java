package com.example.hr.adapter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.example.hexagonal.Adapter;
import com.example.hr.domain.events.HrEvent;
import com.example.hr.infrastructure.EventPublisher;

@Service
@Adapter(port = EventPublisher.class)
@ConditionalOnProperty(name = "messaging",havingValue = "all")
public class EventPublisherSpringApplicationPublisherAdapter implements EventPublisher<HrEvent> {
	private final ApplicationEventPublisher eventPublisher;
	
	public EventPublisherSpringApplicationPublisherAdapter(ApplicationEventPublisher eventPublisher) {
		this.eventPublisher = eventPublisher;
	}

	@Override
	public void publish(HrEvent event) {
		eventPublisher.publishEvent(event);
	}

}
