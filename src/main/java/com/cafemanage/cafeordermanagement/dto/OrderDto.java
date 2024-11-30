package com.cafemanage.cafeordermanagement.dto;

import com.cafemanage.cafeordermanagement.model.Order;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDto{
  private Long id;
  private Long customerId;
  private String customerName;
  private List<DishDto> dish;
  private LocalDateTime orderTime;
  private Order.OrderStatus status;
  private Double totalPrice;
}