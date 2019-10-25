package com.alliance.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alliance.entity.UltraCustomer;

@Repository
public interface UltraCustomerRepository extends CrudRepository<UltraCustomer, BigInteger> {

    @Query("SELECT uc.superPower " +
            "FROM #{#entityName} uc " +
            "WHERE uc.id = :id")
    Integer findSuperPowerById(@Param("id") BigInteger id);

}
