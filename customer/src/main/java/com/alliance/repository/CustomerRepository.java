package com.alliance.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alliance.entity.SimpleCustomer;

@Repository
public interface CustomerRepository extends CrudRepository<SimpleCustomer, Long> {

	List<SimpleCustomer> findByLastName(String lastName);

	@Query(value = "SELECT Additional_Info " +
			"FROM simple_customer " +
			"WHERE Id = :id", nativeQuery = true)
	String findAdditionalInfoById(@Param("id") BigInteger id);

}
