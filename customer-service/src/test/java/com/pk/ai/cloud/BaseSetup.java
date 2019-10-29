package com.pk.ai.cloud;

import java.util.HashSet;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pk.ai.cloud.domain.Customer;
import com.pk.ai.cloud.domain.CustomerAddress;
import com.pk.ai.cloud.util.CustomerServiceUtil;

public class BaseSetup {

	private static final Logger LOGGER = LoggerFactory.getLogger(BaseSetup.class);

	public static String RANDOM = "XXX";
	public static String AMARIS_USER = "amaris";
	public static String RANDOM_DOCUMENT = "XXX" + ".pdf";

	public final static String BOOTSTRAP_SERVERS = "localhost:9092";
	public final static String TOPIC = "document_upload_event";

	@BeforeClass
	public static void setup() {
		System.setProperty("EUREKA_CLIENT_ENABLED", "false");
		System.setProperty("LOG_DIR", System.getProperty("java.io.tmpdir"));
		System.setProperty("APP_NAME", java.util.UUID.randomUUID().toString());
		System.setProperty("spring.cloud.discovery.enabled", "false");
		System.setProperty("spring.cloud.refresh.refreshable", "none");
	}

	@AfterClass
	public static void destroy() throws Exception {
	}

	public static void consolelog(final Object payload) {
		CustomerServiceUtil.writeJsonData(payload);
	}

	protected Customer prepareCustomerData() {
		final Customer customer = new Customer();
		customer.setFirstName("FIRST_NAME_" + java.util.UUID.randomUUID().toString());
		customer.setLastName("LAST_NAME_" + java.util.UUID.randomUUID().toString());
		readData(CustomerServiceUtil.writeJsonData(customer), Customer.class);

		final CustomerAddress address = new CustomerAddress();
		address.setAddressLine1(customer.getFirstName());
		address.setAddressLine2(customer.getLastName());
		address.setAddressLine3("addressLine3");
		address.setCustomer(customer);
		readData(CustomerServiceUtil.writeJsonData(address), CustomerAddress.class);

		final Set<CustomerAddress> set = new HashSet<>();
		set.add(address);
		customer.setAddresses(set);
		return customer;
	}

	protected <T> T readData(final String jsondata, final Class<T> clazz) {
		try {
			return CustomerServiceUtil.objectMapper().readValue(jsondata.getBytes(), clazz);
		} catch (final Exception ex) {
			LOGGER.error("Error in converting to class:[{}], json-data:[{}]" + ex, clazz, jsondata, ex);
		}
		return null;
	}

}
