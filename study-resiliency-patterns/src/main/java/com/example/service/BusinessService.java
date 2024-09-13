package com.example.service;

import java.time.ZonedDateTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class BusinessService {
	private final IntegrationService integrationService;
	
	public BusinessService(IntegrationService integrationService) {
		this.integrationService = integrationService;
	}

	//@Scheduled(fixedRate = 5_000)
	public void doBusiness() {
		var result = integrationService.mayFail();
		System.out.println("[%s] result from the remote service is received: %d".formatted(ZonedDateTime.now(),result));
	}
	
	@Scheduled(fixedRate = 5_000)
	public void doAnotherBusiness() {
		integrationService.longProcess()
		                  .thenAcceptAsync(result -> System.out.println("result is %d".formatted(result)));
	}
}
