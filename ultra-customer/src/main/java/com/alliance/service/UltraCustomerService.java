package com.alliance.service;

import com.alliance.entity.UltraCustomer;

public interface UltraCustomerService {

    UltraCustomer saveUltraCustomer(UltraCustomer ultraCustomer);

    UltraCustomer saveUltraCustomer(String name, String lastName, String power);

}
