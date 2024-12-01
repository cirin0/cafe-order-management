package com.cafemanage.cafeordermanagement.repository;

import com.cafemanage.cafeordermanagement.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
  Customer findByName(String name);
}