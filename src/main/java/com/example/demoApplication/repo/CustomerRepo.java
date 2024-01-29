package com.example.demoApplication.repo;

import com.example.demoApplication.model.Category;
import com.example.demoApplication.model.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Long> {

    @Transactional
    @Query(value = "Select * from Customers customer where customer.email  = :email", nativeQuery = true)
    Optional<Customer> findCustomerByEmail(String email);

}
