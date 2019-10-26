package com.pk.ai.cloud.dao;

import java.util.List;
import java.util.Optional;

import com.pk.ai.cloud.domain.CustomerAddress;

public interface CustomerAddressDAO {

	List<CustomerAddress> listAllCustomerAddress();

	Optional<CustomerAddress> getCustomerAddressByCustomerId(String uuid);

	List<CustomerAddress> saveCustomerAddress(List<CustomerAddress> customerAddressList);

	void deleteCustomerAddressByCustomerId(String uuid);

}
