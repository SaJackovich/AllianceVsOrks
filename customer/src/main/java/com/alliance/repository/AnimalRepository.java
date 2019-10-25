package com.alliance.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.alliance.entity.Animal;

public interface AnimalRepository extends CrudRepository<Animal, UUID> {
}
