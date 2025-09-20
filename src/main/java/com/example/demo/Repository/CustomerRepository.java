package com.example.demo.Repository;

import com.example.demo.Models.Customer;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    @Query("""
            SELECT ID, MSISDN, SEGMENTID
            FROM CUSTOMER
            WHERE MSISDN = :msisdn
         
            """)

    Optional<Customer> getCustomerByMsisdn(Long msisdn);
}

