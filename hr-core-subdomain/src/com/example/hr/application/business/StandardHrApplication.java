package com.example.hr.application.business;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

import com.example.hr.application.HrApplication;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.domain.events.EmployeeFiredEvent;
import com.example.hr.domain.events.EmployeeHiredEvent;
import com.example.hr.domain.events.HrEvent;
import com.example.hr.infrastructure.EventPublisher;
import com.example.hr.repository.EmployeeRepository;

public class StandardHrApplication implements HrApplication {
	private final EmployeeRepository employeeRepository;
	private final EventPublisher<HrEvent> eventPublisher;

	public StandardHrApplication(EmployeeRepository employeeRepository, EventPublisher<HrEvent> eventPublisher) {
		this.employeeRepository = employeeRepository;
		this.eventPublisher = eventPublisher;
	}

	@Override
	public Employee hireEmployee(Employee employee) {
		Objects.requireNonNull(employee);
		var identity = employee.getIdentity();
		if (employeeRepository.exists(identity))
			throw new IllegalStateException("Employe with identity [%s] already exists.".formatted(identity));
		Employee persistedEmployee = employeeRepository.persist(employee);
		var event = new EmployeeHiredEvent(persistedEmployee);
		eventPublisher.publish(event);
		return persistedEmployee;
	}

	@Override
	public Optional<Employee> fireEmployee(TcKimlikNo identity) {
		var employee = employeeRepository.findById(identity);
		Consumer<Employee> removeAction = employeeRepository::remove;
		Consumer<Employee> eventPublishActionAction = firedEmployee -> {
			eventPublisher.publish(new EmployeeFiredEvent(firedEmployee));
		};
		employee.ifPresent(removeAction.andThen(eventPublishActionAction));
		return employee;
	}

	@Override
	public Optional<Employee> findEmployee(TcKimlikNo identity) {
		return employeeRepository.findById(identity);
	}

}
