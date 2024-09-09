package com.example.hr.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

class IbanTest {

	@ParameterizedTest
	@CsvFileSource(resources = "ibans.csv")
	void createIbanSuccessfully(String value) {
		var iban = Iban.of(value);
		assertEquals(value, iban.getValue());
	}

	@ParameterizedTest
	@CsvFileSource(resources = "invalid-ibans.csv")
	void createIbanThrowsException(String value) {
		assertThrows(IllegalArgumentException.class, () -> Iban.of(value));
	}

}
