package com.cafemanage.cafeordermanagement.dto;

import lombok.*;

@Data
public class CustomerDto {
  private Long id;
  private String name;
  private String phoneNumber;
  private String email;
}