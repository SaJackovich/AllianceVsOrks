package com.war.alliance.repository;

import com.war.alliance.entity.military.pedestrian.Crusader;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CrusaderRepository extends CrudRepository<Crusader, UUID> {
}
