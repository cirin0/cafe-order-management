package com.cafemanage.cafeordermanagement.controller;

import com.cafemanage.cafeordermanagement.dto.CustomerDto;
import com.cafemanage.cafeordermanagement.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/customers")
public class CustomerController {
  private final CustomerService customerService;

  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @GetMapping
  public ResponseEntity<List<CustomerDto>> getAllCustomers() {
    return ResponseEntity.ok(customerService.getAllCustomers());
  }

  @GetMapping("{name}")
  public ResponseEntity<CustomerDto> getCustomerByName(@PathVariable String name) {
    return ResponseEntity.ok(customerService.getCustomerByName(name));
  }

  @PostMapping
  public ResponseEntity<CustomerDto> addCustomer(@RequestBody CustomerDto customerDto) {
    return ResponseEntity.ok(customerService.addCustomer(customerDto));
  }

  @PutMapping("{name}")
  public ResponseEntity<CustomerDto> updateCustomer(@PathVariable String name, @RequestBody CustomerDto customerDto) {
    return ResponseEntity.ok(customerService.updateCustomer(name, customerDto));
  }
}
