package com.pk.ai.cloud.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.pk.ai.cloud.BaseSetup;
import com.pk.ai.cloud.ITTestSetup;
import com.pk.ai.cloud.domain.Customer;
import com.pk.ai.cloud.domain.CustomerAddress;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ITTestSetup.class }, webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles({ "local" })
@Transactional
public class CustomerServiceTest extends BaseSetup {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceTest.class);

	@Autowired
	private CustomerService customerService;

	@Test
	public void insertCustomer() throws Exception {
		final Customer customer = prepareCustomerData();
		customerService.saveCustomer(customer);
		BaseSetup.consolelog(customer);
		final Optional<Customer> customerOpt = customerService.getCustomerById(customer.getUuid());
		LOGGER.info("inside insertcustomer retrieved successfully customer");
		assertTrue(customerOpt.get().getUuid().equalsIgnoreCase(customer.getUuid()));
		BaseSetup.consolelog(customerOpt.get());
	}

	@Test
	public void insertAndDeleteCutomer() throws Exception {
		final Customer customer = prepareCustomerData();
		customerService.saveCustomer(customer);
		BaseSetup.consolelog(customer);
		final Optional<Customer> customerOpt = customerService.getCustomerById(customer.getUuid());
		LOGGER.info("inside insertcustomer retrieved successfully customer");
		assertTrue(customerOpt.get().getUuid().equalsIgnoreCase(customer.getUuid()));

		customerService.deleteCustomer(customer.getUuid());
		final Optional<Customer> searchCustomerOpt = customerService.getCustomerById(customer.getUuid());
		assertFalse(searchCustomerOpt.isPresent());
	}

	@Test
	public void searchAllCustomers() throws Exception {
		final Customer customer = new Customer();
		customer.setFirstName("FIRST_NAME_" + java.util.UUID.randomUUID().toString());
		customer.setLastName("LAST_NAME_" + java.util.UUID.randomUUID().toString());
		customerService.saveCustomer(customer);
		BaseSetup.consolelog(customer);
		final List<Customer> customerList = customerService.listAllCustomer();
		assertTrue(customerList.size() > 0);
	}

	@Test
	public void searchCustomerAddressByCustomerId() throws Exception {
		final Customer customer = prepareCustomerData();
		customerService.saveCustomer(customer);

		final List<CustomerAddress> customerAddressList = customerService.listAddressesByCustomerId(customer.getUuid());
		assertTrue(customerAddressList.size() > 0);
		assertTrue(customerAddressList.get(0).getCustomer().getUuid().equalsIgnoreCase(customer.getUuid()));
	}

}
