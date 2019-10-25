package com.alliance.service;

import com.alliance.entity.SimpleCustomer;
import com.alliance.entity.UltraCustomer;
import com.alliance.type.SuperPower;

public interface CustomerService {

    SimpleCustomer saveCustomer(String firstname, String lastname);

    UltraCustomer saveUltraCustomer(UltraCustomer ultraCustomer);

}
