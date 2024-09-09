package com.example.hr.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DepartmentTest {

	@Test
	void useDepartments() {
		assertEquals(0,Department.IT.ordinal());
		assertEquals(1,Department.SALES.ordinal());
		assertEquals(2,Department.HR.ordinal());
		assertEquals(3,Department.FINANCE.ordinal());
		assertEquals(4,Department.ACCOUNTING.ordinal());
		assertEquals("IT",Department.IT.name());
		assertEquals("SALES",Department.SALES.name());
		assertEquals("HR",Department.HR.name());
		assertEquals("FINANCE",Department.FINANCE.name());
		assertEquals("ACCOUNTING",Department.ACCOUNTING.name());
		assertEquals(1,Department.IT.getDepartmentNo());
		assertEquals(5,Department.SALES.getDepartmentNo());
		assertEquals(7,Department.HR.getDepartmentNo());
		assertEquals(10,Department.FINANCE.getDepartmentNo());
		assertEquals(24,Department.ACCOUNTING.getDepartmentNo());
		assertEquals(Department.valueOf("IT"),Department.IT);
		assertEquals(Department.valueOf("SALES"),Department.SALES);
		assertEquals(Department.valueOf("HR"),Department.HR);
		assertEquals(Department.valueOf("FINANCE"),Department.FINANCE);
		assertEquals(Department.valueOf("ACCOUNTING"),Department.ACCOUNTING);
		
	}

}
