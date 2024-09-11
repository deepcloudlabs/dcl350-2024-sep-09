package com.example.lottery.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.lottery.dto.response.LotteryResponse;
import com.example.lottery.service.LotteryService;

@RestController
@RequestScope
@RequestMapping("/numbers")
@CrossOrigin
public class LotteryRestController {
	private final LotteryService lotteryService;

	public LotteryRestController(LotteryService lotteryService) {
		this.lotteryService = lotteryService;
	}

	@GetMapping(params = "column")
	public LotteryResponse draw(@RequestParam int column) {
		return new LotteryResponse(lotteryService.draw(column));
	}
}
