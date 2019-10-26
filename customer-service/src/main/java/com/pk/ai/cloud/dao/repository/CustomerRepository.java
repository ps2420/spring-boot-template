package com.pk.ai.cloud.dao.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pk.ai.cloud.domain.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, String> {}


