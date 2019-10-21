package com.alliance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alliance.service.UltraCustomerService;

@Controller
@RequestMapping("/ultra-customer")
public class UltraCustomerController {

    @Autowired
    private UltraCustomerService service;

    @PutMapping("/add")
    public void add(@RequestParam String name, @RequestParam String lastName, @RequestParam String power) {
        service.saveUltraCustomer(name, lastName, power);
    }

}
