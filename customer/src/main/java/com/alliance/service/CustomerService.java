package com.alliance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alliance.entity.SimpleCustomer;
import com.alliance.repository.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
//    @Autowired
//    private FrontierService frontierService;


    public SimpleCustomer saveCustomer(String firstname, String lastname){
        return customerRepository.save(new SimpleCustomer(firstname, lastname));
    }

//    public UltraCustomer saveUltraCustomer(String firstname, String lastname) {
//        return frontierService.createUltraCustomer(firstname, lastname);
//    }

}
