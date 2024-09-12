package com.example.crm.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.crm.document.Customer;
import com.example.crm.repository.CustomerReactiveRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CrmReactiveService {

	private final CustomerReactiveRepository reactiveRepository;
	
	public CrmReactiveService(CustomerReactiveRepository reactiveRepository) {
		this.reactiveRepository = reactiveRepository;
	}

	public Mono<Customer> findCustomerByIdentity(String identity) {
		return reactiveRepository.findById(identity);
	}

	public Flux<Customer> findCustomers(int pageNo, int pageSize) {
		return reactiveRepository.findAll(PageRequest.of(pageNo, pageSize));
	}

	public Mono<Customer> releaseCustomerByIdentity(String identity) {
		return reactiveRepository.findById(identity)
		                         .doOnSuccess(customer -> reactiveRepository.deleteById(identity));
	}

	public Mono<Customer> acquireCustomer(Customer customer) {
		return reactiveRepository.insert(customer);
	}

}
