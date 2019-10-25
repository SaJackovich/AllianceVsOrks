package com.alliance.customer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
import com.alliance.repository.CustomerRepository;
import com.alliance.repository.UltraCustomerRepository;
import com.alliance.service.CustomerService;
import com.alliance.type.SuperPower;

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
public class UltraCustomerTestIT {

	private static final Integer FIRST_ID = 1;
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
		SimpleCustomer customer = repository.save(getSimpleCustomer());

		assertThat(customer).hasFieldOrPropertyWithValue("firstName", NAME);
		assertThat(customer).hasFieldOrPropertyWithValue("lastName", LAST_NAME);
	}

	@Test
	public void shouldSaveCustomerByService() {
		customerService.saveCustomer(NAME, LAST_NAME);
		Iterator<SimpleCustomer> customers = repository.findAll().iterator();
		assertTrue(customers.hasNext());
		assertEquals(NAME, customers.next().getFirstName());
	}

	@Test
	public void shouldSaveUltraCustomer() {

		customerService.saveUltraCustomer(getUltraCustomerByPower(SuperPower.SPEED));
		Iterator<UltraCustomer> ultraCustomers
				= ultraCustomerRepository.findAll().iterator();
		assertTrue(ultraCustomers.hasNext());
		assertEquals(NAME, ultraCustomers.next().getFirstName());
	}

	@Test
	@Sql(scripts = {
			"classpath:clean_create_tables.sql",
			"classpath:add_actual_data.sql"})
	public void testWithSqlScrips() {

	}

	@Test
	public void entityManagerUltraCustomer() {
		customerService.saveUltraCustomer(getUltraCustomerByPower(SuperPower.FIRE_MAGIC));

		UltraCustomer ultraCustomer = entityManager.find(UltraCustomer.class, BigInteger.valueOf(FIRST_ID));

		assertSame(SuperPower.FIRE_MAGIC, ultraCustomer.getSuperPower());
	}

	@Test
	public void saveAdditionalInfo() {
		String expectedInfo = "01020405";

		SimpleCustomer savedCustomer = repository.save(getSimpleCustomer());
		String additionalInfo = repository.findAdditionalInfoById(savedCustomer.getId());

		assertEquals(expectedInfo, additionalInfo);
	}

	@Test
	public void saveLocalDatetime() {
		SimpleCustomer savedCustomer = repository.save(getSimpleCustomer());

		assertNotNull(savedCustomer.getSysCreatedDatetime());
		assertNotNull(savedCustomer.getSysUpdatedDatetime());
	}

	@Test
	public void shouldBeIntegerInDatabase() {
		UltraCustomer saved = ultraCustomerRepository.save(getUltraCustomerByPower(SuperPower.FIRE_MAGIC));

		Integer powerValue = ultraCustomerRepository.findSuperPowerById(saved.getId());
		assertSame(SuperPower.FIRE_MAGIC.getValue(), powerValue);
	}

	private SimpleCustomer getSimpleCustomer() {
		SimpleCustomer simpleCustomer = new SimpleCustomer();
		simpleCustomer.setFirstName(NAME);
		simpleCustomer.setLastName(LAST_NAME);

		AdditionalInfo additionalInfo = new AdditionalInfo(anotherCustomers);
		simpleCustomer.setAdditionalInfo(additionalInfo);
		simpleCustomer.setSysUpdatedDatetime(LocalDateTime.now());
		simpleCustomer.setSysCreatedDatetime(LocalDateTime.now());
		return simpleCustomer;
	}

	private UltraCustomer getUltraCustomerByPower(SuperPower power) {
		UltraCustomer ultraCustomer = new UltraCustomer();
		ultraCustomer.setFirstName(NAME);
		ultraCustomer.setLastName(LAST_NAME);
		ultraCustomer.setSuperPower(power);
		return ultraCustomer;
	}

}
