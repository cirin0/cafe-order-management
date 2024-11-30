package com.cafemanage.cafeordermanagement.service;

import com.cafemanage.cafeordermanagement.dto.DishDto;
import com.cafemanage.cafeordermanagement.mapper.DishMapper;
import com.cafemanage.cafeordermanagement.model.Dish;
import com.cafemanage.cafeordermanagement.repository.DishRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DishService {

  private final DishRepository dishRepository;
  private final DishMapper dishMapper;

  public DishService(DishRepository dishRepository, DishMapper dishMapper) {
    this.dishRepository = dishRepository;
    this.dishMapper = dishMapper;
  }

  public List<DishDto> getAllDishes() {
    List<Dish> dishes = dishRepository.findAll();
    return dishes.stream()
        .map(dishMapper::toDto)
        .collect(Collectors.toList());
  }

  public DishDto getDishById(Long id) {
    Dish dish = dishRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Dish not found"));
    return dishMapper.toDto(dish);
  }

  public List<DishDto> getDishesByCategory(Dish.Category category) {
    List<Dish> dishes = dishRepository.findByCategory(category);
    return dishes.stream()
        .map(dishMapper::toDto)
        .collect(Collectors.toList());
  }

  public DishDto createDish(DishDto dishDto) {
    Dish dish = dishMapper.toEntity(dishDto);
    Dish savedDish = dishRepository.save(dish);
    return dishMapper.toDto(savedDish);
  }

  public DishDto updateDish(Long id, DishDto dishDto) {
    Dish dish = dishRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Dish not found"));
    Dish updatedDish = dishMapper.partialUpdate(dishDto, dish);
    return dishMapper.toDto(dishRepository.save(updatedDish));
  }

  public void deleteDish(Long id) {
    dishRepository.deleteById(id);
  }
}
