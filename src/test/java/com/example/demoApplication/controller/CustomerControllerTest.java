package com.example.demoApplication.controller;

import com.example.demoApplication.model.Customer;
import com.example.demoApplication.service.CustomerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void test_authenticationLoginCredentialsCustomer() {
        //given
        Customer customer = new Customer();
        ResponseEntity<String> expectedResponse = new ResponseEntity<>("someResult", HttpStatus.OK);

        //  Calling the controller method
        when(customerService.authenticationLoginCredentialsCustomer(Mockito.any(Customer.class)))
                .thenReturn(expectedResponse);
        ResponseEntity<String> actualResponse = customerController.authenticationLoginCredentialsCustomer(customer);

        // Assertions
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void test_addCustomer() {
        //given
        Customer customer = new Customer();
        ResponseEntity<Customer> expectedResponse = new ResponseEntity<>(new Customer(), HttpStatus.CREATED);

        //  Calling the controller method
        when(customerService.addCustomer(Mockito.any(Customer.class)))
                .thenReturn(expectedResponse);
        ResponseEntity<Customer> actualResponse = customerController.addCustomer(customer);

        // Assertions
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void test_getAllCustomers() {
        //given
        List<Customer> customers = new ArrayList<>();
        ResponseEntity<List<Customer>> expectedResponse = new ResponseEntity<>(customers, HttpStatus.OK);

        //  Calling the controller method
        when(customerService.getAllCustomers())
                .thenReturn(expectedResponse);
        ResponseEntity<List<Customer>> actualResponse = customerController.getAllCustomers();

        // Assertions
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void test_getCustomerById() {
        //given
        String email = "test@example.com";
        ResponseEntity<Customer> expectedResponse = new ResponseEntity<>(new Customer(), HttpStatus.OK);

        //  Calling the controller method
        when(customerService.getCustomerById(email))
                .thenReturn(expectedResponse);
        ResponseEntity<Customer> actualResponse = customerController.getCustomerById(email);

        // Assertions
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void test_updateCustomerByEmail() {
        //given
        String email = "test@example.com";
        Customer customer = new Customer();
        ResponseEntity<Customer> expectedResponse = new ResponseEntity<>(new Customer(), HttpStatus.OK);

        // Calling the controller method
        when(customerService.updateCustomerByEmail(Mockito.anyString(), Mockito.any(Customer.class)))
                .thenReturn(expectedResponse);
        ResponseEntity<Customer> actualResponse = customerController.updateCustomerByEmail(email, customer);

        // Assertions
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void test_deleteCustomerByEmail() {
        //given
        String email = "test@example.com";
        ResponseEntity<HttpStatus> expectedResponse = new ResponseEntity<>(HttpStatus.NO_CONTENT);

        // Calling the controller method
        when(customerService.deleteCustomerByEmail(email))
                .thenReturn(expectedResponse);
        ResponseEntity<HttpStatus> actualResponse = customerController.deleteCustomerByEmail(email);

        //Assertions
        assertEquals(expectedResponse, actualResponse);
    }
}