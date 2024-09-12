package com.example.crm.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.crm.document.Customer;
import com.example.crm.service.CrmReactiveService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customers")
@CrossOrigin
public class CrmReactiveRestController {
	
	private final CrmReactiveService crmReactiveService;
	
	public CrmReactiveRestController(CrmReactiveService crmReactiveService) {
		this.crmReactiveService = crmReactiveService;
	}
	
	@GetMapping("{identity}")
	public Mono<Customer> findById(@PathVariable String identity) {
		return crmReactiveService.findCustomerByIdentity(identity);
	}
	
	@GetMapping(params= {"pageno", "pagesize"})
	public Flux<Customer> findAllByPage(
			@RequestParam(name = "pageno") int pageNo,
			@RequestParam(name = "pagesize") int pageSize
			) {
		return crmReactiveService.findCustomers(pageNo,pageSize);
	}
	
	@PostMapping
	public Mono<Customer> addCustomer(@RequestBody Customer customer) {
		return crmReactiveService.acquireCustomer(customer);
	}
	
	@DeleteMapping("{identity}")
	public Mono<Customer> removeById(@PathVariable String identity) {
		return crmReactiveService.releaseCustomerByIdentity(identity);
	}
}
