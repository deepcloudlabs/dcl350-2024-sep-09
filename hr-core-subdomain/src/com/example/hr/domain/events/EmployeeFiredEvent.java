package com.example.hr.domain.events;

import com.example.hr.domain.Employee;

public class EmployeeFiredEvent extends HrEvent {

	private final Employee firedEmployee;

	public EmployeeFiredEvent(Employee firedEmployee) {
		super(HrEventType.EMPLOYEE_FIRED_EVENT, firedEmployee.getIdentity());
		this.firedEmployee = firedEmployee;
	}

	public Employee getFiredEmployee() {
		return firedEmployee;
	}

}
