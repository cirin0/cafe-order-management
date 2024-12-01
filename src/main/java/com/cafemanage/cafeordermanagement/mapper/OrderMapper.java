package com.cafemanage.cafeordermanagement.mapper;

import com.cafemanage.cafeordermanagement.dto.OrderDto;
import com.cafemanage.cafeordermanagement.model.Customer;
import com.cafemanage.cafeordermanagement.model.Dish;
import com.cafemanage.cafeordermanagement.model.Order;
import com.cafemanage.cafeordermanagement.utils.EnumTranslator;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {
  public OrderDto toDto(Order order) {
    OrderDto orderDto = new OrderDto();
    orderDto.setId(order.getId());
    orderDto.setCustomerId(order.getCustomer().getId());
    orderDto.setCustomerName(order.getCustomer().getName());
    orderDto.setDish(order.getDishes().stream()
        .map(dish -> new DishMapper().toDto(dish))
        .collect(Collectors.toList()));
    orderDto.setOrderTime(order.getOrderTime());
    orderDto.setStatus(EnumTranslator.translate("ORDER_STATUS", order.getStatus().name()));
    orderDto.setTotalPrice(order.getTotalPrice());
    return orderDto;
  }

  public Order toEntity(OrderDto orderDto, Customer customer, List<Dish> dishes) {

    Order.OrderStatus status = orderDto.getOriginalStatus() != null
        ? Order.OrderStatus.valueOf(orderDto.getOriginalStatus())
        : Order.OrderStatus.valueOf(orderDto.getStatus());

    Order order = new Order();
    order.setId(orderDto.getId());
    order.setCustomer(customer);
    order.setDishes(dishes);
    order.setOrderTime(orderDto.getOrderTime());
    order.setStatus(status);
    order.setTotalPrice(orderDto.getTotalPrice());
    return order;
  }
}
