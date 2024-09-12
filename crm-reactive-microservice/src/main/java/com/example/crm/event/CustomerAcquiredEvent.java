package com.example.crm.event;

import com.example.crm.document.Customer;

public class CustomerAcquiredEvent extends CrmEvent {

	private final Customer customer;

	public CustomerAcquiredEvent(Customer customer) {
		super(CrmEventType.CUSTOMER_ACQUIRED_EVENT, customer.getIdentity());
		this.customer = customer;
	}

	public Customer getCustomer() {
		return customer;
	}

}
