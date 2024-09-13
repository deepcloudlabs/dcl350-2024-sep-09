package com.example.hr.configuration;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.hr.document.EmployeeDocument;
import com.example.hr.domain.Department;
import com.example.hr.domain.Employee;
import com.example.hr.dto.request.HireEmployeeRequest;
import com.example.hr.dto.response.EmployeeQLResponse;
import com.example.hr.dto.response.EmployeeResponse;
import com.example.hr.dto.response.FireEmployeeResponse;
import com.example.hr.dto.response.HireEmployeeResponse;
import com.example.hr.entity.EmployeeEntity;

@Configuration
public class ModelMapperConfiguration {

	private static final Converter<Employee, EmployeeResponse> EMPLOYEE_TO_EMPLOYEE_RESPONSE_CONVERTER = context -> {
		var employee = context.getSource();
		return new EmployeeResponse(
				employee.getIdentity().getValue(), 
				employee.getFullname().firstName(), 
				employee.getFullname().lastName(), 
			    employee.getIban().getValue(), 
			    employee.getSalary().value(), 
			    employee.getSalary().currency(), 
			    employee.getDepartments(), 
			    employee.getJobStyle(), 
			    employee.getBirthYear().value(), 
			    employee.getPhoto().toBase64()
			    );
	};
	private static final Converter<Employee, HireEmployeeResponse> EMPLOYEE_TO_HIRE_EMPLOYEE_RESPONSE_CONVERTER = context -> {
		var employee = context.getSource();
		return new HireEmployeeResponse(
				employee.getIdentity().getValue(), 
				employee.getFullname().firstName(), 
				employee.getFullname().lastName(), 
				employee.getIban().getValue(), 
				employee.getSalary().value(), 
				employee.getSalary().currency(), 
				employee.getDepartments(), 
				employee.getJobStyle(), 
				employee.getBirthYear().value(), 
				employee.getPhoto().toBase64()
				);
	};

	private static final Converter<Employee, EmployeeQLResponse> EMPLOYEE_TO_EMPLOYEE_QLRESPONSE_CONVERTER = context -> {
		var employee = context.getSource();
		return new EmployeeQLResponse(
				employee.getIdentity().getValue(), 
				employee.getFullname().firstName(), 
				employee.getFullname().lastName(), 
				employee.getIban().getValue(), 
				employee.getSalary().value(), 
				employee.getSalary().currency(), 
				employee.getDepartments(), 
				employee.getJobStyle(), 
				employee.getBirthYear().value(), 
				employee.getPhoto().toBase64()
				);
	};
	private static final Converter<Employee, FireEmployeeResponse> EMPLOYEE_TO_FIRE_EMPLOYEE_RESPONSE_CONVERTER = context -> {
		var employee = context.getSource();
		return new FireEmployeeResponse(
				employee.getIdentity().getValue(), 
				employee.getFullname().firstName(), 
				employee.getFullname().lastName(), 
				employee.getIban().getValue(), 
				employee.getSalary().value(), 
				employee.getSalary().currency(), 
				employee.getDepartments(), 
				employee.getJobStyle(), 
				employee.getBirthYear().value(), 
				employee.getPhoto().toBase64()
				);
	};
	private static final Converter<Employee, EmployeeEntity> EMPLOYEE_TO_EMPLOYEE_ENTITY_CONVERTER = context -> {
		var employee = context.getSource();
		var entity = new EmployeeEntity();
		entity.setIdentity(employee.getIdentity().getValue()); 
		entity.setFirstName(employee.getFullname().firstName()); 
		entity.setLastName(employee.getFullname().lastName());
		entity.setIban(employee.getIban().getValue());
		entity.setSalary(employee.getSalary().value());
		entity.setCurrency(employee.getSalary().currency()); 
		entity.setDepartments(employee.getDepartments());
		entity.setJobStyle(employee.getJobStyle());
		entity.setBirthYear(employee.getBirthYear().value()); 
		entity.setPhoto(employee.getPhoto().values());
		return entity;		
	};
	private static final Converter<HireEmployeeRequest, Employee> HIRE_EMPLOYEE_REQUEST_TO_EMPLOYEE_CONVERTER = context -> {
		var request = context.getSource();
		return new Employee.Builder()
				     .identity(request.identity()) 
				     .fullname(request.firstName(),request.lastName()) 
				     .salary(request.salary(),request.currency()) 
				     .iban(request.iban()) 
				     .departments(request.departments().toArray(new Department[0])) 
				     .jobStyle(request.jobStyle()) 
				     .birthYear(request.birthYear()) 
				     .photo(request.photo())
				     .build();
	};
	private static final Converter<EmployeeEntity, Employee> EMPLOYEE_ENTITY_TO_EMPLOYEE_CONVERTER = context -> {
		var entity = context.getSource();
		return new Employee.Builder()
				.identity(entity.getIdentity()) 
				.fullname(entity.getFirstName(),entity.getLastName()) 
				.salary(entity.getSalary(),entity.getCurrency()) 
				.iban(entity.getIban()) 
				.departments(entity.getDepartments().toArray(new Department[0])) 
				.jobStyle(entity.getJobStyle()) 
				.birthYear(entity.getBirthYear()) 
				.photo(entity.getPhoto())
				.build();
	};
	private static final Converter<EmployeeDocument, Employee> EMPLOYEE_DOCUMENT_TO_EMPLOYEE_CONVERTER = context -> {
		var document = context.getSource();
		return new Employee.Builder()
				.identity(document.getIdentity()) 
				.fullname(document.getFirstName(),document.getLastName()) 
				.salary(document.getSalary(),document.getCurrency()) 
				.iban(document.getIban()) 
				.departments(document.getDepartments().toArray(new Department[0])) 
				.jobStyle(document.getJobStyle()) 
				.birthYear(document.getBirthYear()) 
				.photo(document.getPhoto())
				.build();
	};
	private static final Converter<Employee, EmployeeDocument> EMPLOYEE_TO_EMPLOYEE_DOCUMENT_CONVERTER = context -> {
		var employee = context.getSource();
		var document = new EmployeeDocument();
		document.setIdentity(employee.getIdentity().getValue()); 
		document.setFirstName(employee.getFullname().firstName()); 
		document.setLastName(employee.getFullname().lastName());
		document.setIban(employee.getIban().getValue());
		document.setSalary(employee.getSalary().value());
		document.setCurrency(employee.getSalary().currency()); 
		document.setDepartments(employee.getDepartments());
		document.setJobStyle(employee.getJobStyle());
		document.setBirthYear(employee.getBirthYear().value()); 
		document.setPhoto(employee.getPhoto().toBase64());
		return document;		
	};
	@Bean
	ModelMapper createModelMapper() {
		var modelMapper = new ModelMapper();
		modelMapper.addConverter(EMPLOYEE_TO_EMPLOYEE_RESPONSE_CONVERTER,Employee.class, EmployeeResponse.class);
		modelMapper.addConverter(HIRE_EMPLOYEE_REQUEST_TO_EMPLOYEE_CONVERTER,HireEmployeeRequest.class, Employee.class);
		modelMapper.addConverter(EMPLOYEE_TO_HIRE_EMPLOYEE_RESPONSE_CONVERTER,Employee.class, HireEmployeeResponse.class);
		modelMapper.addConverter(EMPLOYEE_TO_FIRE_EMPLOYEE_RESPONSE_CONVERTER,Employee.class, FireEmployeeResponse.class);
		modelMapper.addConverter(EMPLOYEE_ENTITY_TO_EMPLOYEE_CONVERTER,EmployeeEntity.class, Employee.class);
		modelMapper.addConverter(EMPLOYEE_TO_EMPLOYEE_ENTITY_CONVERTER,Employee.class, EmployeeEntity.class);
		modelMapper.addConverter(EMPLOYEE_TO_EMPLOYEE_QLRESPONSE_CONVERTER,Employee.class, EmployeeQLResponse.class);
		modelMapper.addConverter(EMPLOYEE_TO_EMPLOYEE_DOCUMENT_CONVERTER,Employee.class, EmployeeDocument.class);
		modelMapper.addConverter(EMPLOYEE_DOCUMENT_TO_EMPLOYEE_CONVERTER,EmployeeDocument.class, Employee.class);
		return modelMapper;
	}
}
