package com.example.crm.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.crm.document.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerReactiveRepository extends ReactiveMongoRepository<Customer, String>{
	Mono<Customer> findOneByEmail(String email);
	Flux<Customer> findAllByAddressesCountry(PageRequest page,String country);
	Flux<Customer> findAllByAddressesCity(PageRequest page,String city);
	Flux<Customer> findAllByFullNameLastName(PageRequest page,String city);
    @Query("{'email': ?}")
	Mono<Customer> getir(String email);
    
    @Query("{}")
	Flux<Customer> findAll(PageRequest page);
}
