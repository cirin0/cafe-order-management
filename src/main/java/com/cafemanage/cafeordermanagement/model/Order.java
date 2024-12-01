package com.cafemanage.cafeordermanagement.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "customer_id")
  private Customer customer;

  @ManyToMany
  @JoinTable(
      name = "order_dishes",
      joinColumns = @JoinColumn(name = "order_id"),
      inverseJoinColumns = @JoinColumn(name = "dish_id")
  )
  private List<Dish> dishes = new ArrayList<>();

  @Column(nullable = false)
  private LocalDateTime orderTime;

  @Enumerated(EnumType.STRING)
  private OrderStatus status;

  @Column(nullable = false)
  private Double totalPrice = 0.0;

  public enum OrderStatus {
    PENDING,
    IN_PROGRESS,
    READY,
    COMPLETED,
    CANCELED
  }
}
