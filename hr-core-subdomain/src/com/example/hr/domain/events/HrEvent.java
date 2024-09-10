package com.example.hr.domain.events;

import java.time.ZonedDateTime;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import com.example.ddd.DomainEvent;
import com.example.hr.domain.TcKimlikNo;

@DomainEvent
public abstract class HrEvent {
	private static final AtomicLong sequence = new AtomicLong();
	private final String id = UUID.randomUUID().toString();
	private final HrEventType type;
	private final long timestamp = ZonedDateTime.now().toEpochSecond();
	private final long order = sequence.getAndIncrement();
	private final TcKimlikNo identity;

	public HrEvent(HrEventType type, TcKimlikNo identity) {
		this.type = type;
		this.identity = identity;
	}

	public String getId() {
		return id;
	}

	public HrEventType getType() {
		return type;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public long getOrder() {
		return order;
	}

	public TcKimlikNo getIdentity() {
		return identity;
	}

}
