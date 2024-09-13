package com.example.hr.respoitory;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.hr.document.EmployeeDocument;
import com.example.hr.domain.Department;

public interface EmployeeDocumentRepository extends MongoRepository<EmployeeDocument, String>{
	Collection<EmployeeDocument> findFirst10ByLastName(String lastname);
	@Query(value = "{'lname': ?}")
	Collection<EmployeeDocument> getir(PageRequest page,String lastName);
	
	EmployeeDocument findTopByOrderByBirthYearDesc();
	EmployeeDocument findTopByOrderByBirthYearAsc();
	EmployeeDocument findTopByOrderBySalaryDesc();
	
	List<EmployeeDocument> findByDepartmentsIn(List<Department> departments);
}
