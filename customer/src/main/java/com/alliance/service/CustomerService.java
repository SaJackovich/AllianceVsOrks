package com.alliance.service;

import com.alliance.entity.SimpleCustomer;
import com.alliance.entity.UltraCustomer;
import com.alliance.entity.type.SuperPower;

public interface CustomerService {

    SimpleCustomer saveCustomer(String firstname, String lastname);

    UltraCustomer saveUltraCustomer(String firstname, String lastname, SuperPower power);

}
