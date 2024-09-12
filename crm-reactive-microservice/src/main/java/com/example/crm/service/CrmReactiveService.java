package com.example.crm.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.stereotype.Service;

import com.example.crm.document.Customer;
import com.example.crm.event.CustomerAcquiredEvent;
import com.example.crm.event.CustomerReleasedEvent;
import com.example.crm.repository.CustomerReactiveRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CrmReactiveService {
	private final CustomerReactiveRepository reactiveRepository;
	private final ReactiveKafkaProducerTemplate<String, String> kafkaTemplate;
	private final ObjectMapper objectMapper;

	public CrmReactiveService(CustomerReactiveRepository reactiveRepository,
			ReactiveKafkaProducerTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
		this.reactiveRepository = reactiveRepository;
		this.kafkaTemplate = kafkaTemplate;
		this.objectMapper = objectMapper;
	}

	public Mono<Customer> findCustomerByIdentity(String identity) {
		return reactiveRepository.findById(identity);
	}

	public Flux<Customer> findCustomers(int pageNo, int pageSize) {
		return reactiveRepository.findAll(PageRequest.of(pageNo, pageSize));
	}

	public Mono<Customer> releaseCustomerByIdentity(String identity) {
		return reactiveRepository.findById(identity)
				.doOnSuccess(customer -> reactiveRepository.deleteById(identity).doOnSuccess((t) -> sendCustomerReleasedEvent(customer)).subscribe());
	}

	public Mono<Customer> acquireCustomer(Customer customer) {
		return reactiveRepository.insert(customer)
				                 .doOnSuccess(this::sendCustomerAcquiredEvent);
	}

	private void sendCustomerAcquiredEvent(Customer customer) {
		var event = new CustomerAcquiredEvent(customer);
		try {
			var eventsAsJson = objectMapper.writeValueAsString(event);
			kafkaTemplate.send("crm-events", customer.getIdentity(), eventsAsJson)
					.subscribe(result -> System.err.println("Event has been sent: %s".formatted(result)));
		} catch (JsonProcessingException e) {
			System.err.println("Error while converting to json: %s".formatted(e.getMessage()));
		}
		;
	}
	private void sendCustomerReleasedEvent(Customer customer) {
		var event = new CustomerReleasedEvent(customer);
		try {
			var eventsAsJson = objectMapper.writeValueAsString(event);
			kafkaTemplate.send("crm-events", customer.getIdentity(), eventsAsJson)
			.subscribe(result -> System.err.println("Event has been sent: %s".formatted(result)));
		} catch (JsonProcessingException e) {
			System.err.println("Error while converting to json: %s".formatted(e.getMessage()));
		}
		;
	}
}
