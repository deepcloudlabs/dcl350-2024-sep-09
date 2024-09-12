package com.example.lottery.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.lottery.dto.response.LotteryResponse;

@FeignClient(name = "lottery")
public interface LotteryService {
	@GetMapping(value = "/api/v1/numbers", params = "column")
	public LotteryResponse draw(@RequestParam int column);
}
