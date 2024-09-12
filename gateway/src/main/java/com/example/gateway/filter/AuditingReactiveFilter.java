package com.example.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class AuditingReactiveFilter implements GlobalFilter {

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		var request = exchange.getRequest();
		System.out.println("Path         : %s".formatted(request.getPath()));
		System.out.println("Local Address: %s".formatted(request.getLocalAddress()));
		System.out.println("Method       : %s".formatted(request.getMethod()));
		System.out.println("URI          : %s".formatted(request.getURI()));
		System.out.println("Headers:");
		request.getHeaders().forEach((name,value)->{
			System.out.println("%16s: %s".formatted(name,value));
		});
		request.getBody().subscribe(System.out::println);
		var response = exchange.getResponse();
		System.out.println("Status Code: %s".formatted(response.getStatusCode()));
		return chain.filter(exchange);
	}

}
