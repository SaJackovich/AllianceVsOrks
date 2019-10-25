package com.alliance.one;

import static org.junit.Assert.assertNotNull;

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
import com.alliance.repository.AnimalRepository;

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
    private AnimalRepository repository;

    @Test
    public void shouldProvideUUID() {
        Animal animal = new Animal();
        animal.setName("Cat");
        Animal saved = repository.save(animal);
        assertNotNull(saved);
    }

}
