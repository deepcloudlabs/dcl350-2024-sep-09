package com.example.hr.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.hr.application.HrApplication;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.dto.request.HireEmployeeRequest;
import com.example.hr.dto.response.EmployeeQLResponse;
import com.example.hr.dto.response.EmployeeResponse;
import com.example.hr.dto.response.FireEmployeeResponse;
import com.example.hr.dto.response.HireEmployeeResponse;

@Service
public class HrService {
	private final HrApplication hrApplication;
	private final ModelMapper modelMapper;

	public HrService(HrApplication hrApplication, ModelMapper modelMapper) {
		this.hrApplication = hrApplication;
		this.modelMapper = modelMapper;
	}

	@Transactional
	public HireEmployeeResponse hireEmployee(HireEmployeeRequest request) {
		var employee = modelMapper.map(request, Employee.class);
		var hiredEmployee = hrApplication.hireEmployee(employee);
		return modelMapper.map(hiredEmployee,HireEmployeeResponse.class);
	}

	@Transactional
	public FireEmployeeResponse fireEmployee(String identity) {
		var firedEmployee = hrApplication.fireEmployee(TcKimlikNo.valueOf(identity))
				                         .orElseThrow(() -> new IllegalArgumentException("Employee [%s] does not exist to fire".formatted(identity)));
		return modelMapper.map(firedEmployee, FireEmployeeResponse.class);
	}

	public EmployeeResponse findEmployeeById(String identity) {
		var employee = hrApplication.findEmployee(TcKimlikNo.valueOf(identity))
				.orElseThrow(() -> new IllegalArgumentException("Employee[%s] does not exist.".formatted(identity)));
		// map Aggregate(Employee) to EmployeeResponse
		return modelMapper.map(employee, EmployeeResponse.class);
	}

	public EmployeeQLResponse findEmployeeByIdentity(String identity) {
		var employee = hrApplication.findEmployee(TcKimlikNo.valueOf(identity))
				                    .orElseThrow(() -> new IllegalArgumentException("Employee[%s] does not exist.".formatted(identity)));
		return modelMapper.map(employee, EmployeeQLResponse.class);
	}

}
