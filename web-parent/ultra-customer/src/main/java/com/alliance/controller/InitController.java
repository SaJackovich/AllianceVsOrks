package com.alliance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.alliance.service.UltraCustomerService;

@Controller
public class InitController {

    @Autowired
    private UltraCustomerService service;

    @GetMapping("/")
    public void saveUltraCustomer() {
        String name = "Vlad";
        String lastName = "Nero";
        String power = "3";
        service.saveUltraCustomer(name, lastName, power);
    }

}
