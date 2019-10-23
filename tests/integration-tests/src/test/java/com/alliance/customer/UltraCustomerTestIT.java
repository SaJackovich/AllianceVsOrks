package com.alliance.customer;

import static java.lang.Thread.sleep;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
import com.alliance.entity.AdditionalInfo;
import com.alliance.entity.SimpleCustomer;
import com.alliance.entity.UltraCustomer;
import com.alliance.entity.type.SuperPower;
import com.alliance.repository.CustomerRepository;
import com.alliance.repository.UltraCustomerRepository;
import com.alliance.service.CustomerService;

@RunWith(SpringRunner.class)
//@Transactional
//@Rollback
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
public class UltraCustomerTestIT {

	private static final String NAME = "John";
	private static final String LAST_NAME = "Smith";
	private static final List<Integer> anotherCustomers = Arrays.asList(1, 2, 4, 5);

	@Autowired
	private CustomerRepository repository;
	@Autowired
	private UltraCustomerRepository ultraCustomerRepository;
	@Autowired
	private CustomerService customerService;

	@PersistenceContext
	private EntityManager entityManager;

	@Test
	public void shouldFindNoCustomersIfRepositoryIsEmpty() {
		Iterable<SimpleCustomer> customers = repository.findAll();
		assertThat(customers).isEmpty();
	}

	@Test
	public void shouldStoreCustomer() {
		SimpleCustomer simpleCustomer = new SimpleCustomer();
		simpleCustomer.setFirstName(NAME);
		simpleCustomer.setLastName(LAST_NAME);

		SimpleCustomer customer = repository.save(simpleCustomer);

		assertThat(customer).hasFieldOrPropertyWithValue("firstName", NAME);
		assertThat(customer).hasFieldOrPropertyWithValue("lastName", LAST_NAME);
	}

	@Test
	public void shouldSaveCustomerByService() {
		customerService.saveCustomer(NAME, LAST_NAME);
		Iterator<SimpleCustomer> customers = repository.findAll().iterator();
		assertTrue(customers.hasNext());
		assertEquals(customers.next().getFirstName(),NAME);
	}

	@Test
	public void shouldSaveUltraCustomer() {

		customerService.saveUltraCustomer(NAME, LAST_NAME, SuperPower.SPEED);
		Iterator<UltraCustomer> ultraCustomers
				= ultraCustomerRepository.findAll().iterator();
		assertTrue(ultraCustomers.hasNext());
		assertEquals(ultraCustomers.next().getFirstName(), NAME);
	}

	@Test
	@Sql(scripts = {
			"classpath:clean_create_tables.sql",
			"classpath:add_actual_data.sql"})
	public void testWithSqlScrips() {

		Iterator<UltraCustomer> ultraCustomers
				= ultraCustomerRepository.findAll().iterator();
	}

	@Test
	public void entityManagerUltraCustomer() {
		customerService.saveUltraCustomer(NAME, LAST_NAME, SuperPower.FIRE_MAGIC);

		UltraCustomer ultraCustomer = entityManager.find(UltraCustomer.class, BigInteger.valueOf(1));
		System.out.println(ultraCustomer.getId());
		System.out.println(ultraCustomer.getFirstName());
		System.out.println(ultraCustomer.getLastName());
		System.out.println(ultraCustomer.getSuperPower().getPowerNumber());

	}

	@Test
	public void saveAdditionalInfo() throws InterruptedException {
		SimpleCustomer simpleCustomer = new SimpleCustomer();
		simpleCustomer.setLastName(LAST_NAME);
		simpleCustomer.setFirstName(NAME);
		simpleCustomer.setAdditionalInfo(new AdditionalInfo(anotherCustomers));

		SimpleCustomer savedCustomer = repository.save(simpleCustomer);

		System.out.println(savedCustomer.getAdditionalInfo());

	}

	@Test
	public void saveLocalDatetime() {
		SimpleCustomer customer = new SimpleCustomer();
		customer.setFirstName(NAME);
		customer.setLastName(LAST_NAME);
		customer.setAdditionalInfo(new AdditionalInfo(anotherCustomers));
		customer.setSysCreatedDatetime(LocalDateTime.now());
		customer.setSysUpdatedDatetime(LocalDateTime.now());

		SimpleCustomer savedCustomer = repository.save(customer);

		System.out.println(savedCustomer.getSysCreatedDatetime());
		System.out.println(savedCustomer.getSysUpdatedDatetime());
	}

}
