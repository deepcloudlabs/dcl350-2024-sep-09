package com.example.hr.domain;

@ValueObject
public enum Department {
	IT(1), SALES(5), HR(7), FINANCE(10), ACCOUNTING(24);
	private final int departmentNo;

	private Department(int departmentNo) {
		this.departmentNo = departmentNo;
	}

	public int getDepartmentNo() {
		return departmentNo;
	}
	
}
