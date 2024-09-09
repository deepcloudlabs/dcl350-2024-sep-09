package com.example.hr.domain;

import java.util.List;
import java.util.Objects;

import com.example.ddd.Entity;

// Ubiquitous Language: Employee, TC Kimlik No, Full Name, Salary, Iban, Department, Birth Year,... 
// Ubiquitous Language: increaseSalary, Rate, promote
// Entity Class -> i. identity 
//                ii. persist 
//               iii. business method 
//                iv. mutable
@Entity(id = "identity") // i. documentation ii. sonarqube iii. low/no-code
public class Employee {
	private TcKimlikNo identity;
	private FullName fullname;
	private Salary salary;
	private Iban iban;
	private BirthYear birthYear;
	private List<Department> departments;
	private Photo photo;
	private JobStyle jobStyle;

	public Salary increaseSalary(Rate rate) {
		salary = this.salary.multiply(rate);
		return this.salary;
	}

	public List<Department> promote(Department department) {
		Objects.requireNonNull(department);
		this.departments.add(department);
		return this.departments;
	}
}
