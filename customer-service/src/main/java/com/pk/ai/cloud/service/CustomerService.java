package com.pk.ai.cloud.service;

import java.util.List;
import java.util.Optional;

import com.pk.ai.cloud.domain.Customer;
import com.pk.ai.cloud.domain.CustomerAddress;

public interface CustomerService {

	/** Methods related to customer */
	List<Customer> listAllCustomer();

	Optional<Customer> getCustomerById(String uuid);

	Customer saveCustomer(Customer customer);

	void deleteCustomer(String uuid);
	
	List<CustomerAddress> listAddressesByCustomerId(String customerId);

}
