package com.pk.ai.cloud.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.pk.ai.cloud.BaseSetup;
import com.pk.ai.cloud.ITTestSetup;
import com.pk.ai.cloud.domain.Customer;
import com.pk.ai.cloud.domain.CustomerAddress;
import com.pk.ai.cloud.service.CustomerServiceTest;
import com.pk.ai.cloud.util.CustomerServiceUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ITTestSetup.class }, webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles({ "local" })
public class CustomerControllerTest extends BaseSetup {
 
	@Autowired
	private CustomerController customerController;

	@Test
	public void listAllCustomer() throws Exception {
		final Customer customer = prepareCustomerData();
		customerController.saveCustomer(customer);
		assertNotNull(customer.getUuid());
		final List<Customer> customerList = customerController.listAllCustomer();
		assertTrue(customerList.size() > 0);
		final List<CustomerAddress> customerAddressList = customerController
				.listAddressesByCustomerId(customer.getUuid());
		assertNotNull(customerAddressList);
		assertTrue(customerAddressList.size() > 0);
	}

	@Test
	public void getCustomerById() throws Exception {
		final Customer customer = prepareCustomerData();
		customerController.saveCustomer(customer);
		assertNotNull(customer.getUuid());

		final ResponseEntity<String> responseEntity = customerController.getCustomerById(customer.getUuid());
		CustomerServiceUtil.writeJsonData(responseEntity);
		final Customer searchedData = CustomerServiceUtil.readData(responseEntity.getBody(), Customer.class);
		assertNotNull(searchedData);
		CustomerServiceUtil.writeJsonData(customer);
		CustomerServiceUtil.writeJsonData(searchedData);
		assertTrue(searchedData.getUuid().equalsIgnoreCase(customer.getUuid()));
	}

	@Test
	public void deleteAllCustomers() throws Exception {
		final Customer customer = prepareCustomerData();
		customerController.saveCustomer(customer);
		assertNotNull(customer.getUuid());
		customerController.delete(customer.getUuid());
		final ResponseEntity<String> responseEntity = customerController.getCustomerById(customer.getUuid());
		assertTrue(responseEntity.getBody().equalsIgnoreCase("Customer record not found"));
	}

}
