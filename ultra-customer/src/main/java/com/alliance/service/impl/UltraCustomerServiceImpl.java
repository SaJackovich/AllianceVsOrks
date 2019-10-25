package com.alliance.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alliance.entity.UltraCustomer;
import com.alliance.repository.UltraCustomerRepository;
import com.alliance.service.UltraCustomerService;
import com.alliance.type.SuperPower;

@Service
public class UltraCustomerServiceImpl implements UltraCustomerService {

    @Autowired
    private UltraCustomerRepository repository;

    public UltraCustomer saveUltraCustomer(UltraCustomer ultraCustomer) {
        return repository.save(ultraCustomer);
    }

    public UltraCustomer saveUltraCustomer(String name, String lastName, String power) {
        UltraCustomer ultraCustomer = new UltraCustomer();
        ultraCustomer.setFirstName(name);
        ultraCustomer.setLastName(lastName);
        ultraCustomer.setSuperPower(SuperPower.valueOf(power));
        return repository.save(ultraCustomer);
    }

}
