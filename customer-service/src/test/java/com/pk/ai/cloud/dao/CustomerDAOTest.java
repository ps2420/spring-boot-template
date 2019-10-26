package com.pk.ai.cloud.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ITTestSetup.class }, webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles({ "local" })
@Transactional
public class CustomerDAOTest extends BaseSetup {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerDAOTest.class);
 
	@Autowired
	private CustomerDAO customerDAO;
	
	@Test
	@Transactional
	public void saveCustomer() throws Exception {
		final Customer customer = new Customer();
		customer.setFirstName("FIRST_NAME_" + java.util.UUID.randomUUID().toString());
		customer.setLastName("LAST_NAME_" + java.util.UUID.randomUUID().toString());
		customerDAO.saveCustomer(customer);
		BaseSetup.consolelog(customer);
		assertNotNull(customer.getUuid());
	}
	
	@Test
	@Transactional
	public void saveAndSearchCustomer() throws Exception {
		final Customer customer = new Customer();
		customer.setFirstName("FIRST_NAME_" + java.util.UUID.randomUUID().toString());
		customer.setLastName("LAST_NAME_" + java.util.UUID.randomUUID().toString());
		customerDAO.saveCustomer(customer);
		BaseSetup.consolelog(customer);
		final Optional<Customer> customerOpt = customerDAO.getCustomerById(customer.getUuid());
		LOGGER.info("inside insertcustomer retrieved successfully customer");
		assertTrue(customerOpt.get().getUuid().equalsIgnoreCase(customer.getUuid())); 
	}
	
	@Test
	@Transactional
	public void deleteCustomerById() throws Exception {
		final Customer customer = new Customer();
		customer.setFirstName("FIRST_NAME_" + java.util.UUID.randomUUID().toString());
		customer.setLastName("LAST_NAME_" + java.util.UUID.randomUUID().toString());
		customerDAO.saveCustomer(customer);
		BaseSetup.consolelog(customer);
		customerDAO.deleteCustomer(customer.getUuid());
		final Optional<Customer> customerOpt = customerDAO.getCustomerById(customer.getUuid());
		LOGGER.info("inside insertcustomer retrieved successfully customer");
		assertTrue(!customerOpt.isPresent()); 
	}

}
