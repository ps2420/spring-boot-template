package com.pk.ai.cloud.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pk.ai.cloud.dao.CustomerAddressDAO;
import com.pk.ai.cloud.dao.CustomerDAO;
import com.pk.ai.cloud.domain.Customer;
import com.pk.ai.cloud.domain.CustomerAddress;
import com.pk.ai.cloud.service.CustomerService;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDAO customerDAO;

	@Autowired
	private CustomerAddressDAO customerAddressDAO;

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

	@Override
	public List<CustomerAddress> listAllCustomerAddress(final String customerId) {
		return customerAddressDAO.listAllCustomerAddress();
	}

	@Override
	public Optional<CustomerAddress> getCustomerAddressByCustomerId(final String customerId) {
		return customerAddressDAO.getCustomerAddressByCustomerId(customerId);
	}

	@Override
	public List<CustomerAddress> saveCustomerAddress(final List<CustomerAddress> customerAddressList) {
		return customerAddressDAO.saveCustomerAddress(customerAddressList);
	}

	@Override
	public void deleteCustomerAddressByCustomerId(final String customerId) {
		customerAddressDAO.deleteCustomerAddressByCustomerId(customerId);
	}

}
