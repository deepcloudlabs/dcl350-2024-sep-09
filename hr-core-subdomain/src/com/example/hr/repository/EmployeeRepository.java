package com.example.hr.repository;

import java.util.Optional;

import com.example.hexagonal.Port;
import com.example.hexagonal.PortType;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;

@Port(PortType.DRIVEN) // SPI
public interface EmployeeRepository {

	Optional<Employee> findById(TcKimlikNo identity);

	boolean exists(TcKimlikNo identity);

	Employee persist(Employee employee);

	void remove(Employee employee);

}
