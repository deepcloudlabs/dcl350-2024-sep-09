package com.example.hr.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;

public class Exercise01 {

	public static void main(String[] args) {
		var httpClient = HttpClient.newBuilder().build();
		var request1 = HttpRequest.newBuilder(URI.create("http://localhost:9100/hr/api/v1/employees/40608049742"))
				                  .GET()
				                  .build();
		var request2 = HttpRequest.newBuilder(URI.create("http://localhost:9100/hr/api/v1/employees/40608049742"))
				.DELETE()
				.build();
		try {
			var response1= httpClient.send(request1, BodyHandlers.ofString()).body();
			System.out.println(response1);
			var response2= httpClient.send(request2, BodyHandlers.ofString()).body();
			System.out.println(response2);
		} catch (IOException | InterruptedException e) {
			System.out.println("Error has occured: %s".formatted(e.getMessage()));
		}

	}

}
