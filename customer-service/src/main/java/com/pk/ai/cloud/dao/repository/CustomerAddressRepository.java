package com.pk.ai.cloud.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.pk.ai.cloud.domain.CustomerAddress;

public interface CustomerAddressRepository extends CrudRepository<CustomerAddress, String> {

	@Query("FROM CustomerAddress g where g.customer.id = :customerId")
	List<CustomerAddress> findAllAddressesByCustomerId(@Param("customerId") String customerId);
}
