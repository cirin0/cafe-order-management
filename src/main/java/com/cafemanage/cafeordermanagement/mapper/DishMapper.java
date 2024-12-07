package com.cafemanage.cafeordermanagement.mapper;

import com.cafemanage.cafeordermanagement.dto.DishDto;
import com.cafemanage.cafeordermanagement.model.Dish;
import com.cafemanage.cafeordermanagement.utils.EnumTranslator;
import org.springframework.stereotype.Component;

@Component
public class DishMapper {
  public DishDto toDto(Dish dish) {
    DishDto dishDto = new DishDto();
    dishDto.setId(dish.getId());
    dishDto.setName(dish.getName());
    dishDto.setPrice(dish.getPrice());
    dishDto.setImage(dish.getImage());
    dishDto.setDescription(dish.getDescription());
    dishDto.setAvailable(dish.getAvailable());
    dishDto.setCategory(EnumTranslator.translate("DISH_CATEGORY", dish.getCategory().name()));

    dishDto.setOriginalCategory(dish.getCategory().name());
    return dishDto;
  }

  public Dish toEntity(DishDto dishDto) {

    Dish.Category category = dishDto.getOriginalCategory() != null
        ? Dish.Category.valueOf(dishDto.getOriginalCategory())
        : Dish.Category.valueOf(dishDto.getCategory());

    Dish dish = new Dish();
    dish.setId(dishDto.getId());
    dish.setName(dishDto.getName());
    dish.setPrice(dishDto.getPrice());
    dish.setImage(dishDto.getImage());
    dish.setDescription(dishDto.getDescription());
    dish.setAvailable(dishDto.getAvailable());
    dish.setCategory(category);
    return dish;
  }

  public Dish partialUpdate(DishDto dishDto, Dish dish) {
    if (dishDto.getName() != null) {
      dish.setName(dishDto.getName());
    }
    if (dishDto.getPrice() != null) {
      dish.setPrice(dishDto.getPrice());
    }
    if (dishDto.getImage() != null) {
      dish.setImage(dishDto.getImage());
    }
    if (dishDto.getDescription() != null) {
      dish.setDescription(dishDto.getDescription());
    }
    if (dishDto.getAvailable() != null) {
      dish.setAvailable(dishDto.getAvailable());
    }
    if (dishDto.getOriginalCategory() != null) {
      dish.setCategory(Dish.Category.valueOf(dishDto.getOriginalCategory()));
    }
    return dish;
  }
}
