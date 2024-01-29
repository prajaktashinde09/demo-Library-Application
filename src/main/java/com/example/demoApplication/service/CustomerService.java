package com.example.demoApplication.service;



import com.example.demoApplication.model.Customer;
import com.example.demoApplication.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepo customerRepo;

    @Autowired
    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    public ResponseEntity<List<Customer>> getAllCustomers() {
        try{
            List<Customer> customerList = customerRepo.findAll();
            if(customerList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(customerList, HttpStatus.OK);
        }catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Customer> getCustomerById(String email) {
        Optional<Customer> customerData = customerRepo.findCustomerByEmail(email);
        if(customerData.isPresent())
        {
            return new ResponseEntity<>(customerData.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Customer> addCustomer(Customer customer)  {

        //check valid email address
        if (!isValidEmail(customer.getEmail())) {
            // Handle invalid email
            throw new IllegalArgumentException("Invalid email address");
        }
        // check if email address already exists in db or not
        Optional<Customer> oldCustomer =  customerRepo.findCustomerByEmail(customer.getEmail());
        if(oldCustomer.isPresent()) {
            return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
        }

        // encrypt the password and store it in database
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword =  bCryptPasswordEncoder.encode(customer.getPassword());

        // set details of the customer to be read to store in db
        Customer newCustomer = new Customer();
        newCustomer.setEmail(customer.getEmail());
        newCustomer.setName(customer.getName());

        newCustomer.setPassword(encryptedPassword);//store encryptedpassword in the db
        Customer customerObje = customerRepo.save(newCustomer);

        return new ResponseEntity<>( customerObje, HttpStatus.CREATED);
    }

    public ResponseEntity<String> authenticationLoginCredentialsCustomer(Customer customer)
    {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        Optional<Customer> customerdb =  customerRepo.findCustomerByEmail(customer.getEmail());

        if(customerdb.isPresent()) {
            if( bCryptPasswordEncoder.matches(customer.getPassword(),customerdb.get().getPassword()))
                return  new ResponseEntity<>("Customer Logged in sucessfully",HttpStatus.OK) ;
            else
                return new ResponseEntity<>("Email address or password incorrect",HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>("Customer not found email address",HttpStatus.NOT_FOUND);
    }

    private boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,6}$");
    }

    public ResponseEntity<Customer> updateCustomerByEmail(String email, Customer newCustomer) {

        Optional<Customer> oldCustomerData = customerRepo.findCustomerByEmail(email);
        // if Customer not found then return Book NOT FOUND
        if(oldCustomerData.isPresent())
        {
            // if Customer  exists then update custmer Data
            Customer updateCustomerData = oldCustomerData.get();
            updateCustomerData.setName(newCustomer.getName());
            updateCustomerData.setEmail(newCustomer.getEmail());
            updateCustomerData.setPassword(newCustomer.getPassword());

            Customer customerObj =customerRepo.save(updateCustomerData);
            return new ResponseEntity<Customer>(customerObj, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<HttpStatus> deleteCustomerByEmail(String email) {
        Optional<Customer> customerToBeDeleted = customerRepo.findCustomerByEmail(email);
        if(customerToBeDeleted.isPresent())
        {
            customerRepo.delete(customerToBeDeleted.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

