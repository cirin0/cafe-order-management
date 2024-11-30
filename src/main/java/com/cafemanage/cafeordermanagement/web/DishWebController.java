package com.cafemanage.cafeordermanagement.web;

import com.cafemanage.cafeordermanagement.dto.DishDto;
import com.cafemanage.cafeordermanagement.model.Dish;
import com.cafemanage.cafeordermanagement.service.DishService;
import com.cafemanage.cafeordermanagement.utils.EnumTranslator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/dishes")
public class DishWebController {

  private final DishService dishService;

  public DishWebController(DishService dishService) {
    this.dishService = dishService;
  }

  private void categoriesTranslate(Model model) {
    List<String> categories = Arrays.stream(Dish.Category.values())
        .map(category -> EnumTranslator.translate("DISH_CATEGORY", category.name()))
        .toList();
    model.addAttribute("categories", categories);

    List<String> originalCategories = Arrays.stream(Dish.Category.values())
        .map(Dish.Category::name)
        .toList();
    model.addAttribute("originalCategories", originalCategories);
  }

  @GetMapping
  public String listDishes(Model model){
    model.addAttribute("dishes", dishService.getAllDishes());
    return "dishes/list";
  }

  @GetMapping("/add")
  public String showAddDishForm(Model model) {
    model.addAttribute("dish", new DishDto());
    categoriesTranslate(model);
    return "dishes/add";
  }

  @PostMapping("/add")
  public String addDish(@ModelAttribute("dish") DishDto dishDto) {
    dishDto.setOriginalCategory(
        Arrays.stream(Dish.Category.values())
            .filter(c -> EnumTranslator.translate("DISH_CATEGORY", c.name()).equals(dishDto.getCategory()))
            .findFirst()
            .map(Dish.Category::name)
            .orElse(null)
    );
    dishService.createDish(dishDto);
    return "redirect:/dishes";
  }

  @GetMapping("/{id}/edit")
  public String showEditDishForm(@PathVariable Long id, Model model) {
    DishDto dish = dishService.getDishById(id);
    model.addAttribute("dish", dish);
    categoriesTranslate(model);
    return "dishes/edit";
  }

  @PostMapping("/{id}")
  public String editDish(@PathVariable Long id, @ModelAttribute ("dish") DishDto dishDto) {
    dishDto.setOriginalCategory(
        Arrays.stream(Dish.Category.values())
            .filter(c -> EnumTranslator.translate("DISH_CATEGORY", c.name()).equals(dishDto.getCategory()))
            .findFirst()
            .map(Dish.Category::name)
            .orElse(null)
    );
    dishService.updateDish(id, dishDto);
    return "redirect:/dishes";
  }

  @GetMapping("{id}/delete")
  public String deleteDish(@PathVariable Long id) {
    dishService.deleteDish(id);
    return "redirect:/dishes";
  }
}
