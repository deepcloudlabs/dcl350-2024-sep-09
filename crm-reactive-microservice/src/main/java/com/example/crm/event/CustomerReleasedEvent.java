package com.example.crm.event;

import com.example.crm.document.Customer;

public class CustomerReleasedEvent extends CrmEvent {

	private final Customer customer;

	public CustomerReleasedEvent(Customer customer) {
		super(CrmEventType.CUSTOMER_RELEASED_EVENT, customer.getIdentity());
		this.customer = customer;
	}

	public Customer getCustomer() {
		return customer;
	}

}
