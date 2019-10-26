package com.pk.ai.cloud.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pk.ai.cloud.dao.CustomerDAO;
import com.pk.ai.cloud.dao.repository.CustomerAddressRepository;
import com.pk.ai.cloud.dao.repository.CustomerRepository;
import com.pk.ai.cloud.domain.Customer;
import com.pk.ai.cloud.domain.CustomerAddress;

@Repository
@Transactional
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CustomerAddressRepository customerAddressRepository;

	public List<Customer> listAllCustomer() {
		final List<Customer> customerList = new ArrayList<>();
		customerRepository.findAll().forEach(customerList::add);
		return customerList;
	}

	@Override
	@Transactional
	public Optional<Customer> getCustomerById(final String uuid) {
		return customerRepository.findById(uuid);
	}

	@Override
	@Transactional
	public Customer saveCustomer(final Customer customer) {
		customer.getAddresses().parallelStream().forEach(customerAddress -> customerAddress.setCustomer(customer));
		customerRepository.save(customer); 
		return customer;
	}

	@Override
	@Transactional
	public void deleteCustomer(final String uuid) {
		customerRepository.deleteById(uuid);
	}

	@Override
	public List<CustomerAddress> listAddressesByCustomerId(String customerId) {
		return customerAddressRepository.findAllAddressesByCustomerId(customerId);
	}

}
