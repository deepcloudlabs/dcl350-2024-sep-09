package com.example.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Repository;

import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

@Repository
public class IntegrationService {

	//@Retry(name = "integrationService", fallbackMethod = "mayFailFallback")
	// @RateLimiter(name = "integrationService")
	public int mayFail() {
//		if (ThreadLocalRandom.current().nextDouble()<0.8)
//			throw new IllegalStateException("Cannot acces to the remote service");
		return 42;
	}
	
	public int mayFailFallback(Throwable t) {
		System.out.println("mayFailFallback() is running: %s".formatted(t.getMessage()));
		return 108;
	}
	
	@TimeLimiter(name="integrationService",fallbackMethod = "longProcessFallback")
	public CompletableFuture<Integer> longProcess() {
		return CompletableFuture.supplyAsync(() -> {
			try {TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(0,8));} catch (InterruptedException e) { }
			return 42;
		});
	}
	
	public CompletableFuture<Integer> longProcessFallback(Throwable t) {
		return CompletableFuture.supplyAsync(() -> {
			return 108;
		});
	}
}
