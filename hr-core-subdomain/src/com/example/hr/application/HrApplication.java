package com.example.hr.application;

import java.util.Optional;

import com.example.hexagonal.Port;
import com.example.hexagonal.PortType;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;

// Ubiquitous Language: hire
@Port(PortType.DRIVING) // API
public interface HrApplication {
	Employee hireEmployee(Employee employee);

	Optional<Employee> fireEmployee(TcKimlikNo identity);

	Optional<Employee> findEmployee(TcKimlikNo identity);

}
