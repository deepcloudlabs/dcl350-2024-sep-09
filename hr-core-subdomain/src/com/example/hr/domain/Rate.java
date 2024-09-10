package com.example.hr.domain;

@ValueObject
public record Rate(double value) {
	public static Rate of(double value) {
		if (value <= 0.0)
			throw new IllegalArgumentException("Rate [%1.3f] must be positive".formatted(value));
		return new Rate(value);
	}

}
