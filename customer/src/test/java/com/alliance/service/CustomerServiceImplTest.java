package com.alliance.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.alliance.entity.AdditionalInfo;
import com.alliance.entity.SimpleCustomer;
import com.alliance.repository.CustomerRepository;
import com.alliance.service.impl.CustomerServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplTest {

    private static final String NAME = "Vlad";
    private static final String LAST_NAME = "Nero";
    private static final List<Integer> anotherCustomers = Arrays.asList(1, 2, 4, 5);

    @Mock
    private CustomerRepository repository;

    @InjectMocks
    private CustomerServiceImpl service;

    @Test
    public void saveCustomer() {
        SimpleCustomer expected = new SimpleCustomer();
        expected.setFirstName(NAME);
        expected.setLastName(LAST_NAME);

        when(repository.save(any())).thenReturn(expected);

        SimpleCustomer actual = service.saveCustomer(NAME, LAST_NAME);

        assertEquals(expected, actual);
    }

    @Test
    public void saveCustomerWithAdditionalInfo() {
        SimpleCustomer expected = new SimpleCustomer();
        expected.setFirstName(NAME);
        expected.setLastName(LAST_NAME);
        AdditionalInfo additionalInfo = new AdditionalInfo(anotherCustomers);
        expected.setAdditionalInfo(additionalInfo);

        when(repository.save(any())).thenReturn(expected);

        SimpleCustomer actual = service.saveCustomer(NAME, LAST_NAME);

        assertEquals(expected, actual);
    }

}
