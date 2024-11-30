package com.cafemanage.cafeordermanagement.utils;

import java.util.HashMap;
import java.util.Map;

public class EnumTranslator {
  private static final Map<String, Map<String, String>> TRANSLATIONS = new HashMap<>();

  static {
    Map<String, String> dishCategories = new HashMap<>();
    dishCategories.put("APPETIZER", "Закуска");
    dishCategories.put("MAIN_COURSE", "Основна страва");
    dishCategories.put("DESSERT", "Десерт");
    dishCategories.put("DRINK", "Напій");
    TRANSLATIONS.put("DISH_CATEGORY", dishCategories);

    Map<String, String> orderStatuses = new HashMap<>();
    orderStatuses.put("PENDING", "Очікується");
    orderStatuses.put("IN_PROGRESS", "Готується");
    orderStatuses.put("READY", "Готово");
    orderStatuses.put("COMPLETED", "Виконано");
    orderStatuses.put("CANCELED", "Скасовано");
    TRANSLATIONS.put("ORDER_STATUS", orderStatuses);
  }

  public static String translate(String type, String key) {
    return TRANSLATIONS.getOrDefault(type, new HashMap<>())
        .getOrDefault(key, key);
  }
}
