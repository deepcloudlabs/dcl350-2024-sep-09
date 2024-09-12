package com.example.crm.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;

@Service
public class ReactiveConsumerService {
	private final ReactiveKafkaConsumerTemplate<String, String> reactiveKafkaConsumer;

    public ReactiveConsumerService(ReactiveKafkaConsumerTemplate<String, String> reactiveKafkaConsumer) {
		this.reactiveKafkaConsumer = reactiveKafkaConsumer;
	}

    @EventListener(ApplicationStartedEvent.class)
	public Flux<String> startKafkaConsumer() {
        return reactiveKafkaConsumer
                .receiveAutoAck()
                .doOnNext(consumerRecord -> System.out.println("received key=%s, value=%s from topic=%s, offset=%d".formatted(
                        consumerRecord.key(),
                        consumerRecord.value(),
                        consumerRecord.topic(),
                        consumerRecord.offset()))
                )
                .map(ConsumerRecord::value)
                .doOnNext(message -> System.out.println("successfully consumed %s=%s".formatted(String.class.getSimpleName(), message)))
                .doOnError(throwable -> System.out.println("something bad happened while consuming : %s".formatted(throwable.getMessage())));
    }
}
