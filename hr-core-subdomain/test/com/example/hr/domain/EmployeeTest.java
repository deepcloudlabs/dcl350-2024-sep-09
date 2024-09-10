package com.example.hr.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.provider.CsvFileSource;

class EmployeeTest {

	//@ParameterizedTest
	@CsvFileSource(resources = "employees.csv")
	void createEmployee(String identity,String firstName,String lastName,String iban,double salary,String currency,String departments,int birthYear,String jobStyle,String photo) {
		var employee = new Employee.Builder()
				                   .identity(identity)
				                   .photo(photo)
				                   .salary(salary,currency)
				                   .fullname(firstName, lastName)
				                   .iban(iban)
				                   .birthYear(birthYear)
				                   .departments(departments.split(":"))
				                   .jobStyle(jobStyle)
				                   .build();
		assertEquals(identity, employee.getIdentity().getValue());				                              
		assertEquals(firstName, employee.getFullname().firstName());				                              
		assertEquals(lastName, employee.getFullname().lastName());				                              
	}

}
