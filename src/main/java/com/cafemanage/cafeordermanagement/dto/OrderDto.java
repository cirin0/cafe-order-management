package com.cafemanage.cafeordermanagement.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDto {
  private Long id;
  private Long customerId;
  private String customerName;
  private List<Long> dishIds;
  private List<DishDto> dish;
  private LocalDateTime orderTime;
  private String status;
  private String originalStatus;
  private Double totalPrice;
}