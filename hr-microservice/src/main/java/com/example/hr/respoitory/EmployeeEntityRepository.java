package com.example.hr.respoitory;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.hr.domain.Department;
import com.example.hr.entity.EmployeeEntity;

public interface EmployeeEntityRepository extends JpaRepository<EmployeeEntity, String>{
	Collection<EmployeeEntity> findFirst10ByLastName(String lastname);
	@Query(value = "select e from EmployeeEntity e where e.lastName=:lastName")
	Collection<EmployeeEntity> getir(PageRequest page,String lastName);
	
	EmployeeEntity findTopByOrderByBirthYearDesc();
	EmployeeEntity findTopByOrderByBirthYearAsc();
	EmployeeEntity findTopByOrderBySalaryDesc();
	
	List<EmployeeEntity> findByDepartmentsIn(List<Department> departments);
}
