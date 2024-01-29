package com.example.demoApplication.controller;


import com.example.demoApplication.model.Customer;
import com.example.demoApplication.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> authenticationLoginCredentialsCustomer(@RequestBody Customer customer){
        return customerService.authenticationLoginCredentialsCustomer(customer);
    }

    @PostMapping("/registration")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){
        return customerService.addCustomer(customer);
    }

    @GetMapping("/getAllCustomers")
    public ResponseEntity<List<Customer>> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @GetMapping("/getCustomerById/{email}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable String email){
        return customerService.getCustomerById(email);
    }

    @PostMapping("/updateCustomerByEmail/{email}")
    public ResponseEntity<Customer>  updateCustomerByEmail(@PathVariable String email, @RequestBody Customer customer){
        return customerService.updateCustomerByEmail(email, customer);
    }

    @DeleteMapping("/deleteCustomerByEmail/{email}")
    public ResponseEntity<HttpStatus> deleteCustomerByEmail(@PathVariable String email){
        return customerService.deleteCustomerByEmail(email);
    }


}
