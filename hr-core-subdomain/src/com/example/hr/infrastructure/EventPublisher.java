package com.example.hr.infrastructure;

import com.example.hexagonal.Port;
import com.example.hexagonal.PortType;

@Port(PortType.DRIVEN) // SPI
public interface EventPublisher<E> {
	void publish(E event);
}
