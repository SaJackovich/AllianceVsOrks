package com.alliance.repository;

import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;

import com.alliance.entity.Zoo;

public interface ZooRepository extends CrudRepository<Zoo, BigInteger> {
}
