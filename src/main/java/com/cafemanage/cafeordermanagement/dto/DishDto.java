package com.cafemanage.cafeordermanagement.dto;

import lombok.Data;

@Data
public class DishDto {
  private Long id;
  private String name;
  private Double price;
  private String image;
  private String description;
  private Boolean available;
  private String category;
  private String originalCategory;
}