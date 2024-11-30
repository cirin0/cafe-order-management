package com.cafemanage.cafeordermanagement.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

  @ElementCollection
  @CollectionTable(name = "order_dish_prices", joinColumns = @JoinColumn(name = "order_id"))
  @MapKeyJoinColumn(name = "dish_id")
  @Column(name = "price_at_order_time")
  private Map<Dish, Double> dishPricesAtOrderTime = new HashMap<>();

  @Column(nullable = false)
  private LocalDateTime orderTime;

  //private Integer quantity;

  @Enumerated(EnumType.STRING)
  private OrderStatus status;

  @Column(nullable = false)
  private Double totalPrice = 0.0;

  public enum OrderStatus {
    PENDING,
    IN_PROGRESS,
    READY,
    COMPLETED,
    CANCELLED
  }
}
