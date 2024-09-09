package com.example.hr.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SalaryTest {

	@Test
	void test() {
		var salary = Salary.valueOf(10_000, FiatCurrency.TL);
		var newSalary = salary.multiply(Rate.of(0.5));
		assertEquals(15_000, newSalary.value());
	}

}
