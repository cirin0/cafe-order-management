package com.cafemanage.cafeordermanagement.service;

import com.cafemanage.cafeordermanagement.dto.CustomerDto;
import com.cafemanage.cafeordermanagement.mapper.CustomerMapper;
import com.cafemanage.cafeordermanagement.model.Customer;
import com.cafemanage.cafeordermanagement.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
  
  private final CustomerRepository customerRepository;
  private final CustomerMapper customerMapper;

  public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
    this.customerRepository = customerRepository;
    this.customerMapper = customerMapper;
  }

  public CustomerDto addCustomer(CustomerDto customerDto) {
    Customer customer = customerMapper.toEntity(customerDto);
    Customer savedCustomer = customerRepository.save(customer);
    return customerMapper.toDto(savedCustomer);
  }

  public List<CustomerDto> getAllCustomers() {
    List<Customer> customers = customerRepository.findAll();
    return customers.stream()
        .map(customerMapper::toDto)
        .collect(Collectors.toList());
  }

  public CustomerDto getCustomerByName(String name) {
    Customer customer = customerRepository.findByName(name);
    if (customer == null) {
      throw new RuntimeException("Customer not found");
    }
    return customerMapper.toDto(customer);
  }

  public void deleteCustomer(Long id) {
    Customer customer = customerRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Customer not found"));
    customerRepository.delete(customer);
  }

  public CustomerDto updateCustomer(String name, CustomerDto customerDto) {
    Customer customer = customerRepository.findByName(name);
    if (customer == null) {
      throw new RuntimeException("Customer not found");
    }
    Customer updatedCustomer = customerMapper.partialUpdate(customerDto, customer);
    return customerMapper.toDto(customerRepository.save(updatedCustomer));
  }

  public CustomerDto getCustomerById(Long id) {
    Customer customer = customerRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Customer not found"));
    return customerMapper.toDto(customer);
  }
}
