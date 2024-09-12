package com.example.crm.event;

import java.time.ZonedDateTime;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public abstract class CrmEvent {
	private static final AtomicLong sequence = new AtomicLong();
	private final String id = UUID.randomUUID().toString();
	private final CrmEventType type;
	private final long timestamp = ZonedDateTime.now().toEpochSecond();
	private final long order = sequence.getAndIncrement();
	private final String identity;

	public CrmEvent(CrmEventType type, String identity) {
		this.type = type;
		this.identity = identity;
	}

	public String getId() {
		return id;
	}

	public CrmEventType getType() {
		return type;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public long getOrder() {
		return order;
	}

	public String getIdentity() {
		return identity;
	}

	@Override
	public String toString() {
		return "CrmEvent [id=" + id + ", type=" + type + ", timestamp=" + timestamp + ", order=" + order + ", identity="
				+ identity + "]";
	}

}
