package com.example.crm.configuration;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import reactor.kafka.receiver.ReceiverOptions;

@Configuration
public class ReactiveKafkaConfiguration {

	@Bean
	ReceiverOptions<String, String> kafkaReceiver(KafkaProperties kafkaProperties) {

		Map<String, Object> config = new HashMap<>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "crm");
		config.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, false);
		config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		ReceiverOptions<String, String> basicReceiverOptions = ReceiverOptions.create(config);
		return basicReceiverOptions.subscription(Collections.singletonList("crm-events"));

	}

	@Bean
	ReactiveKafkaConsumerTemplate<String, String> reactiveKafkaConsumer(
			ReceiverOptions<String, String> kafkaReceiverOptions) {
		return new ReactiveKafkaConsumerTemplate<String, String>(kafkaReceiverOptions);
	}
}
