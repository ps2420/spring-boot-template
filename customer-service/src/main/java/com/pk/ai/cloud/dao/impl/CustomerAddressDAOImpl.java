package com.pk.ai.cloud.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pk.ai.cloud.dao.CustomerAddressDAO;
import com.pk.ai.cloud.dao.repository.CustomerAddressRepository;
import com.pk.ai.cloud.dao.repository.CustomerRepository;
import com.pk.ai.cloud.domain.CustomerAddress;

@Repository
public class CustomerAddressDAOImpl implements CustomerAddressDAO {

	@Autowired
	private CustomerAddressRepository customerAddressRepository;
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public List<CustomerAddress> listAllCustomerAddress() {
		final List<CustomerAddress> customerList = new ArrayList<>();
		customerAddressRepository.findAll().forEach(customerList::add);
		return customerList;
	}

	@Override
	public Optional<CustomerAddress> getCustomerAddressByCustomerId(final String uuid) {
		return customerAddressRepository.findById(uuid);
	}

	@Override
	public List<CustomerAddress> saveCustomerAddress(List<CustomerAddress> customerAddressList) {
		final List<CustomerAddress> customerList = new ArrayList<>();
		customerAddressRepository.saveAll(customerAddressList).forEach(customerList::add);
		return customerList;
	}

	@Override
	public void deleteCustomerAddressByCustomerId(final String uuid) {
		customerAddressRepository.deleteById(uuid);
	}

}
