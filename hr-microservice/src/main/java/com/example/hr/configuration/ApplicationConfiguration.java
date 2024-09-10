package com.example.hr.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.hr.application.HrApplication;
import com.example.hr.application.business.StandardHrApplication;
import com.example.hr.domain.events.HrEvent;
import com.example.hr.infrastructure.EventPublisher;
import com.example.hr.repository.EmployeeRepository;

@Configuration
public class ApplicationConfiguration {

	@Bean
	HrApplication createHrApplication(EmployeeRepository employeeRepository, EventPublisher<HrEvent> eventPublisher) {
		return new StandardHrApplication(employeeRepository, eventPublisher);
	}
}
