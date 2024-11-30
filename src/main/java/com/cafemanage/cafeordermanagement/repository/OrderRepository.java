package com.cafemanage.cafeordermanagement.repository;

import com.cafemanage.cafeordermanagement.model.Customer;
import com.cafemanage.cafeordermanagement.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
  List<Order> findByCustomer(Customer customer);
  List<Order> findByStatus(Order.OrderStatus status);
  List<Order> findByOrderTimeBetween(LocalDateTime start, LocalDateTime end);
}