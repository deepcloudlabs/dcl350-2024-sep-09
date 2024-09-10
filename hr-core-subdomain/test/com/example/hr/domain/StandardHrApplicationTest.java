package com.example.hr.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import com.example.hr.application.business.StandardHrApplication;
import com.example.hr.domain.events.HrEvent;
import com.example.hr.infrastructure.EventPublisher;
import com.example.hr.repository.EmployeeRepository;

class StandardHrApplicationTest {

	@ParameterizedTest
	@CsvFileSource(resources = "valid-employees.csv")
	void hireEmployee(String identity,String firstName,String lastName,String iban,double salary,String currency,String departments,int birthYear,String jobStyle,String photo) {
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
		// test doubles -> employeeRepository + eventPublisher
		var employeeRepository = new InMemoryEmployeeRepository();
		var eventPublisher = new DummyEventPublisher();
		var application = new StandardHrApplication(employeeRepository, eventPublisher);
		application.hireEmployee(employee);
		var hired_employee = application.findEmployee(TcKimlikNo.valueOf(identity));
		assertTrue(hired_employee.isPresent());
		assertEquals(identity,hired_employee.get().getIdentity().getValue());
	}

}

class DummyEventPublisher implements EventPublisher<HrEvent> {

	@Override
	public void publish(HrEvent event) {
		System.out.println("Publishing event [%s]: %s".formatted(event.getId(), event.getType().name()));
	}

}

class InMemoryEmployeeRepository implements EmployeeRepository {
	private Map<String, Employee> employees = new ConcurrentHashMap<>();

	@Override
	public Optional<Employee> findById(TcKimlikNo identity) {
		return Optional.ofNullable(employees.get(identity.getValue()));
	}

	@Override
	public boolean exists(TcKimlikNo identity) {
		return employees.containsKey(identity.getValue());
	}

	@Override
	public Employee persist(Employee employee) {
		var identity = employee.getIdentity().getValue();
		employees.put(identity , employee);
		return employee;
	}

	@Override
	public void remove(Employee employee) {
		employees.remove(employee.getIdentity().getValue());	
	}
}
