package com.cafemanage.cafeordermanagement.mapper;

import com.cafemanage.cafeordermanagement.dto.CustomerDto;
import com.cafemanage.cafeordermanagement.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
  public CustomerDto toDto(Customer customer) {
    CustomerDto customerDto = new CustomerDto();
    customerDto.setId(customer.getId());
    customerDto.setName(customer.getName());
    customerDto.setPhoneNumber(customer.getPhoneNumber());
    customerDto.setEmail(customer.getEmail());
    return customerDto;
  }

  public Customer toEntity(CustomerDto customerDto) {
    Customer customer = new Customer();
    customer.setId(customerDto.getId());
    customer.setName(customerDto.getName());
    customer.setPhoneNumber(customerDto.getPhoneNumber());
    customer.setEmail(customerDto.getEmail());
    return customer;
  }

  public Customer partialUpdate(CustomerDto customerDto, Customer customer) {
    if (customerDto.getName() != null) {
      customer.setName(customerDto.getName());
    }
    if (customerDto.getPhoneNumber() != null) {
      customer.setPhoneNumber(customerDto.getPhoneNumber());
    }
    if (customerDto.getEmail() != null) {
      customer.setEmail(customerDto.getEmail());
    }
    return customer;
  }
}
