package com.alliance.customer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

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
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.alliance.config.ClassesScanConfig;
import com.alliance.config.MariaDBTestConfig;
import com.alliance.entity.SimpleCustomer;
import com.alliance.entity.UltraCustomer;
import com.alliance.entity.type.SuperPower;
import com.alliance.repository.CustomerRepository;
import com.alliance.repository.UltraCustomerRepository;
import com.alliance.service.CustomerService;

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
public class MySecondUnitTest {

    private static final String JOHN = "John";

    @Autowired
    private CustomerRepository repository;
    @Autowired
    private UltraCustomerRepository ultraCustomerRepository;
    @Autowired
    private CustomerService customerService;

    @Test
    public void should_find_no_customers_if_repository_is_empty() throws InterruptedException {

        Iterable<SimpleCustomer> customers = repository.findAll();
        assertThat(customers).isEmpty();
    }

    @Test
    public void should_store_a_customer() throws InterruptedException {

        SimpleCustomer customer = repository.save(new SimpleCustomer("Jack", "Smith"));

        assertThat(customer).hasFieldOrPropertyWithValue("firstName", "Jack");
        assertThat(customer).hasFieldOrPropertyWithValue("lastName", "Smith");
    }

    @Test
    public void should_save_customer_by_service() throws InterruptedException {

        customerService.saveCustomer("John", "Smith");
        Iterator<SimpleCustomer> customers = repository.findAll().iterator();
        assertTrue(customers.hasNext());
        assertEquals(customers.next().getFirstName(),"John");
    }

    @Test
    public void should_save_ultra_customer() throws InterruptedException {

        customerService.saveUltraCustomer(JOHN, "Smith", SuperPower.SPEED);
        Iterator<UltraCustomer> ultraCustomers
                = ultraCustomerRepository.findAll().iterator();
        assertTrue(ultraCustomers.hasNext());
        assertEquals(ultraCustomers.next().getFirstName(), JOHN);
    }

    @Test
    @Sql(scripts = {
            "classpath:clean_create_tables.sql",
            "classpath:add_actual_data.sql"})
    public void test_with_sql_script() throws InterruptedException {

        Iterator<UltraCustomer> ultraCustomers
                = ultraCustomerRepository.findAll().iterator();
    }

}

