package com.example.lottery.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.annotation.PostConstruct;

@Service
@ConditionalOnProperty(name="lbStrategy",havingValue = "custom")
public class LotteryConsumerService {
	private static final String LOTTERY_SERVICE_URL = "http://%s:%d/api/v1/numbers?column=1";
	private static final AtomicInteger counter = new AtomicInteger();
	private final DiscoveryClient discoveryClient;
	private List<ServiceInstance> instances;
	
	public LotteryConsumerService(DiscoveryClient discoveryClient) {
		this.discoveryClient = discoveryClient;
	}

	@PostConstruct
	@Scheduled(fixedRate = 10_000)
	public void loadInstances() {
		this.instances = this.discoveryClient.getInstances("lottery");
	    this.instances.forEach(instance -> {
	    	System.out.println("%s:%d".formatted(instance.getHost(),instance.getPort()));
	    });
	}
	
	@Scheduled(fixedRate = 3_000)
	public void callLotteryService() {
		var restTemplate = new RestTemplate();
		// client-side lb: round-robin
		var nextInstance = counter.getAndIncrement() % this.instances.size();
		var instance = this.instances.get(nextInstance);
		String url = LOTTERY_SERVICE_URL.formatted(instance.getHost(),instance.getPort());
		System.out.println("Sending request to %s".formatted(url));
		try {
			var response = restTemplate.getForEntity(url, String.class).getBody();
			System.out.println("Response is received: %s".formatted(response));
		}catch (Throwable t) {
			System.out.println("Something is wrong: %s".formatted(t.getMessage()));
			loadInstances();
		}
	}
}
