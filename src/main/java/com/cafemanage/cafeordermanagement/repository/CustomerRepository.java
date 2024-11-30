package com.cafemanage.cafeordermanagement.repository;

import com.cafemanage.cafeordermanagement.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
  Customer findByName(String name);
  Customer findByPhoneNumber(String phoneNumber);
}