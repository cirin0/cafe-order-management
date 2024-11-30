package com.cafemanage.cafeordermanagement.web;

import com.cafemanage.cafeordermanagement.dto.OrderDto;
import com.cafemanage.cafeordermanagement.service.CustomerService;
import com.cafemanage.cafeordermanagement.service.DishService;
import com.cafemanage.cafeordermanagement.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderWebController {

  private final OrderService orderService;
  private final DishService dishService;
  private final CustomerService customerService;

  @GetMapping
  public String listOrders(Model model){
    model.addAttribute("orders", orderService.getAllOrders());
    return "orders/list";
  }

  @GetMapping("/customer/{customerId}")
  public String listOrdersByCustomer(@PathVariable Long customerId, Model model){
    model.addAttribute("customer", customerService.getCustomerById(customerId));
    model.addAttribute("orders", orderService.getOrdersByCustomer(customerId));
    return "customers/list-orders";
  }

  @GetMapping("/create")
  public String showCreateOrderForm(Model model) {
    model.addAttribute("order", new OrderDto());
    model.addAttribute("dishes", dishService.getAllDishes());
    model.addAttribute("customers", customerService.getAllCustomers());
    return "orders/create";
  }

  @PostMapping("/create")
  public String createOrder(@ModelAttribute OrderDto orderDto) {
    orderService.createOrder(orderDto);
    return "redirect:/orders";
  }
}
