package com.alliance.repository;

import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alliance.entity.UltraCustomer;

@Repository
public interface UltraCustomerRepository extends CrudRepository<UltraCustomer, BigInteger> {

}
