package com.alliance.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alliance.entity.SimpleCustomer;

@Repository
public interface CustomerRepository extends CrudRepository<SimpleCustomer, Long> {
	List<SimpleCustomer> findByLastName(String lastName);
}
