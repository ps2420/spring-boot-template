package com.pk.ai.cloud.service;

import java.util.List;
import java.util.Optional;

import com.pk.ai.cloud.domain.Customer;


public interface CustomerService {

	List<Customer> listAllCustomer();
	
	Optional<Customer> getCustomerById(String uuid);

	Customer saveCustomer(Customer customer);

    void deleteCustomer(String uuid);
    
}
