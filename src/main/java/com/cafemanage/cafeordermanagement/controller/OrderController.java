package com.cafemanage.cafeordermanagement.controller;

import com.cafemanage.cafeordermanagement.dto.OrderDto;
import com.cafemanage.cafeordermanagement.service.CustomerService;
import com.cafemanage.cafeordermanagement.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

  private final OrderService orderService;
  private final CustomerService customerService;

  public OrderController(OrderService orderService, CustomerService customerService) {
    this.orderService = orderService;
    this.customerService = customerService;
  }

  @GetMapping
  public ResponseEntity<List<OrderDto>> getAllOrders() {
    return ResponseEntity.ok(orderService.getAllOrders());
  }

  @GetMapping("{id}")
  public ResponseEntity<OrderDto> getOrderById(@PathVariable Long id) {
    return ResponseEntity.ok(orderService.getOrderById(id));
  }

  @GetMapping("customer/{id}")
  public ResponseEntity<List<OrderDto>> getOrdersByCustomer(@PathVariable Long id) {
    return ResponseEntity.ok(orderService.getOrdersByCustomer(id));
  }

  @PostMapping
  public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
    return ResponseEntity.ok(orderService.createOrder(orderDto));
  }
}
