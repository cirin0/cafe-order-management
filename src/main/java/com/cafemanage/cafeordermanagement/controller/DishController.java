package com.cafemanage.cafeordermanagement.controller;

import com.cafemanage.cafeordermanagement.dto.DishDto;
import com.cafemanage.cafeordermanagement.model.Dish;
import com.cafemanage.cafeordermanagement.service.DishService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/dishes")
public class DishController {
  private final DishService dishService;

  public DishController(DishService dishService) {
    this.dishService = dishService;
  }

  @GetMapping
  public ResponseEntity<List<DishDto>> getAllDishes() {
    return ResponseEntity.ok(dishService.getAllDishes());
  }

  @GetMapping("{id}")
  public ResponseEntity<DishDto> getDishById(@PathVariable Long id) {
    return ResponseEntity.ok(dishService.getDishById(id));
  }

  @GetMapping("category/{category}")
  public ResponseEntity<List<DishDto>> getDishesByCategory(@PathVariable Dish.Category category) {
    return ResponseEntity.ok(dishService.getDishesByCategory(category));
  }

  @PostMapping
  public ResponseEntity<DishDto> createDish(@RequestBody DishDto dishDto) {
    DishDto createdDish = dishService.createDish(dishDto);
    return ResponseEntity.ok(createdDish);
  }

  @PutMapping("{id}")
  public ResponseEntity<DishDto> updateDish(@PathVariable Long id,
                                            @RequestBody DishDto dishDto) {
    return ResponseEntity.ok(dishService.updateDish(id, dishDto));
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> deleteDish(@PathVariable Long id) {
    dishService.deleteDish(id);
    return ResponseEntity.noContent().build();
  }
}
