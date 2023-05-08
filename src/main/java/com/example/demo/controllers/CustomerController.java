package com.example.demo.controllers;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.models.CustomerModel;

import com.example.demo.services.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/customers")
    public Iterable<CustomerModel> getCustomers() {

        return this.customerService.getCustomers();
    }

    @GetMapping("/customers/{id}")
    public Optional<CustomerModel> getCustomer(@PathVariable("id") Long id) {

        return this.customerService.getCustomer(id);
    }

    @PostMapping("/addcustomer")
    public CustomerModel createCustomer(@RequestBody CustomerModel customer) {

        return this.customerService.createCustomer(customer);
    }

    @PutMapping("/updatecustomer/{id}")
    public Optional<CustomerModel> updateCustomer(@PathVariable Long id, @RequestBody CustomerModel customer) {

        return this.customerService.updateCustomer(id, customer);
    }

    @DeleteMapping("/deletecustomer/{id}")
    public void deleteCustomer(@PathVariable("id") Long id) {

        this.customerService.deleteCustomer(id);
    }
}