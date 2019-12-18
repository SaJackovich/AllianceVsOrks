package com.alliance.one;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.alliance.config.ClassesScanConfig;
import com.alliance.config.MariaDBTestConfig;
import com.alliance.entity.Animal;
import com.alliance.entity.Zoo;
import com.alliance.repository.AnimalRepository;
import com.alliance.repository.ZooRepository;
import com.alliance.type.AnimalCondition;

@RunWith(SpringRunner.class)
@Transactional
@Rollback
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestPropertySource(locations="classpath:application.properties")
@ContextHierarchy({
        @ContextConfiguration(classes = {
                ClassesScanConfig.class,
                MariaDBTestConfig.class
        })
})
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class AnimalTestIT {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private ZooRepository zooRepository;

    @Test
    public void shouldProvideUUID() {
        Animal saved = animalRepository.save(getAnimal("Cat", AnimalCondition.FREE));
        assertNotNull(saved);
    }

    @Test
    public void shouldProvideOnlyInCellAnimals() {
        List<Animal> animals = new ArrayList<>();
        animals.add(getAnimal("Cat", AnimalCondition.FREE));
        animals.add(getAnimal("Dog", AnimalCondition.IN_CELL));
        Zoo zoo = new Zoo();
        zoo.setAnimals(animals);
        zoo.setName("Reatch");
        Zoo saved = zooRepository.save(zoo);

        Optional<Zoo> found = zooRepository.findById(saved.getId());

        assertTrue(found.isPresent());
        Zoo zoo1 = found.get();
        assertEquals(2, zoo1.getAnimals().size());
    }

    private Animal getAnimal(String name, AnimalCondition condition) {
        Animal animal = new Animal();
        animal.setName(name);
        animal.setAnimalCondition(condition);
        return animal;
    }

}
