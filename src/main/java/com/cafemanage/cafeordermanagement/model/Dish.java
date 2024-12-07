package com.cafemanage.cafeordermanagement.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "dishes")
public class Dish {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private Double price;

  private String image;

  private String description;

  private Boolean available;

  @Enumerated(EnumType.STRING)
  private Category category;

  public enum Category {
    APPETIZER,
    MAIN_COURSE,
    DESSERT,
    DRINK
  }
}
