package com.example.exercises;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class Exercise01 {

	public static void main(String[] args) {
		var service = new Service1();
		var result1 = service.work();
		System.out.println("result1=%d".formatted(result1));
		var result2 = service.workHard();
		System.out.println("result2=%d".formatted(result2));
		service.workHardAsync()
		       .thenAcceptAsync(result3 -> {
		    	   System.out.println("[%s] result3=%d".formatted(Thread.currentThread().getName(), result3));
		       });
		for (var i=0;i<1_000;++i) {
			System.out.println("[%s] Working hard... %s".formatted(Thread.currentThread().getName(),i));
			try {TimeUnit.SECONDS.sleep(1);}catch (Exception e) {}			
		}
	}

}

class Service1 {
	public int work() {
		return 42;
	}
	
	public int workHard() {
		try {TimeUnit.SECONDS.sleep(8);}catch (Exception e) {}
		return 42;
	}
	public CompletableFuture<Integer> workHardAsync() {
		return CompletableFuture.supplyAsync(()->{
			System.out.println("[%s] workHardAsync() is running now...".formatted(Thread.currentThread().getName()));
			try {TimeUnit.SECONDS.sleep(8);}catch (Exception e) {}
			return 42;			
		});
	}
}
