package com.example.hr.domain;

import java.util.Objects;

@ValueObject
public record Salary(double value, FiatCurrency currency) {
	public static Salary valueOf(double value, FiatCurrency currency) {
		if (value <= 0.0)
			throw new IllegalArgumentException("Salary must be a positive value [%8.2f].".formatted(value));
		Objects.requireNonNull(currency);
		return new Salary(value, currency);
	}

	public Salary multiply(Rate rate) {
		return new Salary(this.value * (1.0 + rate.value()), this.currency);
	}
	
	public boolean lessThan(Salary threshold) {
		return this.value < threshold.value();
	}
}
