package com.example.lottery.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name="lbStrategy",havingValue = "feign")
public class LotteryFeignConsumerService {
	private final LotteryService lotteryService;
	
	public LotteryFeignConsumerService(LotteryService lotteryService) {
		this.lotteryService = lotteryService;
	}

	@Scheduled(fixedRate = 3_000)
	public void callLotteryService() {
		lotteryService.draw(5)
		              .lotteryNumbers()
		              .forEach(System.out::println);;
	}
}
