package com.cafemanage.cafeordermanagement.repository;

import com.cafemanage.cafeordermanagement.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DishRepository extends JpaRepository<Dish, Long> {
  List<Dish> findByCategory(Dish.Category category);
}