package com.pk.ai.cloud.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pk.ai.cloud.dao.CustomerDAO;
import com.pk.ai.cloud.domain.Customer;
import com.pk.ai.cloud.service.CustomerService;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDAO customerDAO;
	
	public List<Customer> listAllCustomer() {
		return customerDAO.listAllCustomer();
	}

	@Override
	@Transactional
	public Optional<Customer> getCustomerById(final String uuid) {
		return customerDAO.getCustomerById(uuid);
	}

	@Override
	@Transactional
	public Customer saveCustomer(final Customer customer) {
		return customerDAO.saveCustomer(customer);
	}

	@Override
	@Transactional
	public void deleteCustomer(final String uuid) {
		customerDAO.deleteCustomer(uuid);
	}

}
