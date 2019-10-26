package com.pk.ai.cloud.dao;

import java.util.List;
import java.util.Optional;

import com.pk.ai.cloud.domain.Customer;
import com.pk.ai.cloud.domain.CustomerAddress;

public interface CustomerDAO {

	List<Customer> listAllCustomer();
	
	Optional<Customer> getCustomerById(String uuid);

	Customer saveCustomer(Customer product);

    void deleteCustomer(String uuid);
    
    List<CustomerAddress> listAddressesByCustomerId(String customerId);
}
