package com.example.hr.domain.events;

import com.example.hr.domain.Employee;

public class EmployeeHiredEvent extends HrEvent {
	private final Employee employee;

	public EmployeeHiredEvent(Employee employee) {
		super(HrEventType.EMPLOYEE_HIRED_EVENT, employee.getIdentity());
		this.employee = employee;
	}

	public Employee getEmployee() {
		return employee;
	}

}
