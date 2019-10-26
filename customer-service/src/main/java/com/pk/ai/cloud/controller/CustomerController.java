package com.pk.ai.cloud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pk.ai.cloud.domain.Customer;
import com.pk.ai.cloud.service.CustomerService;
import com.pk.ai.cloud.util.CustomerServiceUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/customer")
@Api(value = "customer-maanagement", description = "Operations pertaining to customer management")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@ApiOperation(value = "List all customers", response = Customer.class)
	@RequestMapping(value = "/listAllCustomer", method = RequestMethod.GET, produces = "application/json")
	public List<Customer> listAllCustomer() {
		return customerService.listAllCustomer();
	}

	@ApiOperation(value = "Search a customer with an uuid", response = Customer.class)
	@RequestMapping(value = "/customerById/{uuid}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> getCustomerById(@PathVariable String uuid) {
		final Optional<Customer> customerOpt = customerService.getCustomerById(uuid);
		if (customerOpt.isPresent()) {
			return new ResponseEntity<String>(CustomerServiceUtil.writeJsonData(customerOpt), HttpStatus.OK);
		}
		return new ResponseEntity<String>("Customer record not found", HttpStatus.OK);
	}

	@ApiOperation(value = "Save customer")
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<String> saveCustomer(@RequestBody Customer customer) {
		customerService.saveCustomer(customer);
		return new ResponseEntity<String>("Customer saved successfully", HttpStatus.OK);
	}

	@ApiOperation(value = "Delete customer")
	@RequestMapping(value = "/delete/{uuid}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<String> delete(final @PathVariable String uuid) {
		customerService.deleteCustomer(uuid);
		return new ResponseEntity<String>("Customer deleted successfully", HttpStatus.OK);

	}

}
