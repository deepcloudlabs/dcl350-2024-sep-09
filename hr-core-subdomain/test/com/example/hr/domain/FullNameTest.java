package com.example.hr.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

class FullNameTest {

	@ParameterizedTest
	@CsvFileSource(resources = "fullnames.csv")
	void createFullName(String firstName, String lastName) {
		var jackBauer = new FullName(firstName, lastName);
		assertEquals(firstName, jackBauer.firstName());
		assertEquals(lastName, jackBauer.lastName());
		assertTrue(jackBauer.toString().contains(firstName));
		assertTrue(jackBauer.toString().contains(lastName));
		assertEquals(new FullName(firstName, lastName), jackBauer);
	}

	@ParameterizedTest
	@CsvFileSource(resources = "fullnames.csv")
	void createFullNameWithFactory(String firstName, String lastName) {
		var jackBauer = FullName.of(firstName, lastName);
		assertEquals(firstName, jackBauer.firstName());
		assertEquals(lastName, jackBauer.lastName());
		assertTrue(jackBauer.toString().contains(firstName));
		assertTrue(jackBauer.toString().contains(lastName));
		assertEquals(new FullName(firstName, lastName), jackBauer);
	}
}
