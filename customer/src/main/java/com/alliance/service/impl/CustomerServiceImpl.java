package com.alliance.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alliance.entity.SimpleCustomer;
import com.alliance.entity.UltraCustomer;
import com.alliance.repository.CustomerRepository;
import com.alliance.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private UltraCustomerServiceImpl ultraCustomerServiceImpl;

    public SimpleCustomer saveCustomer(String firstname, String lastname){
        SimpleCustomer simpleCustomer = new SimpleCustomer();
        simpleCustomer.setFirstName(firstname);
        simpleCustomer.setLastName(lastname);
        return customerRepository.save(simpleCustomer);
    }

    public UltraCustomer saveUltraCustomer(UltraCustomer ultraCustomer) {
        return ultraCustomerServiceImpl.saveUltraCustomer(ultraCustomer);
    }

}
