package com.example.hr.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hexagonal.Adapter;
import com.example.hr.application.HrApplication;
import com.example.hr.dto.request.HireEmployeeRequest;
import com.example.hr.dto.response.EmployeeResponse;
import com.example.hr.dto.response.FireEmployeeResponse;
import com.example.hr.dto.response.HireEmployeeResponse;
import com.example.hr.service.HrService;
import com.example.validation.TcKimlikNo;

@RestController
@RequestMapping("/employees")
@Validated
@CrossOrigin
@Adapter(port = HrApplication.class)
public class HrRestController {
	private final HrService hrService;

	public HrRestController(HrService hrService) {
		System.err.println(hrService.getClass().getName());
		this.hrService = hrService;
	}
	//             application.properties  
	// POST http://localhost:9100/hr/api/v1/employees
	@PostMapping
	public HireEmployeeResponse hireEmployee(
			@Validated @RequestBody HireEmployeeRequest request) {
		return hrService.hireEmployee(request);
	}

	// DELETE http://localhost:9100/hr/api/v1/employees/11111111110
	@DeleteMapping("{identity}")
	public FireEmployeeResponse fireEmployee(
			@TcKimlikNo @PathVariable String identity) {
		return hrService.fireEmployee(identity);
	}

	// GET http://localhost:9100/hr/api/v1/employees/11111111110
	@GetMapping("{identity}")
	public EmployeeResponse findEmployeeById(
			@TcKimlikNo @PathVariable String identity) {
		return hrService.findEmployeeById(identity);
	}
}
