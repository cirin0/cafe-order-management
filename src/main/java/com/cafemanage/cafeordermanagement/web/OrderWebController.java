package com.cafemanage.cafeordermanagement.web;

import com.cafemanage.cafeordermanagement.dto.OrderDto;
import com.cafemanage.cafeordermanagement.model.Order;
import com.cafemanage.cafeordermanagement.service.CustomerService;
import com.cafemanage.cafeordermanagement.service.DishService;
import com.cafemanage.cafeordermanagement.service.OrderService;
import com.cafemanage.cafeordermanagement.service.OrderScheduler;
import com.cafemanage.cafeordermanagement.utils.EnumTranslator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderWebController {

  private final OrderService orderService;
  private final DishService dishService;
  private final CustomerService customerService;
  private final OrderScheduler orderScheduler;


  @GetMapping
  public String listOrders(Model model) {
    model.addAttribute("orders", orderService.getAllOrders());
    List<String> statuses = Arrays.stream(Order.OrderStatus.values())
        .map(status -> EnumTranslator.translate("ORDER_STATUS", status.name()))
        .toList();

    model.addAttribute("statuses", statuses);
    return "orders/list";
  }

  @GetMapping("/customer/{customerId}")
  public String listOrdersByCustomer(@PathVariable Long customerId, Model model) {
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
    OrderDto createdOrder = orderService.createOrder(orderDto);
    orderScheduler.statusUpdate(createdOrder.getId());
    return "redirect:/orders";
  }

  @PostMapping("/{id}/status")
  public String updateOrderStatus(@PathVariable Long id, @RequestParam("status") String status, @ModelAttribute OrderDto orderDto) {
    orderDto.setOriginalStatus(
        Arrays.stream(Order.OrderStatus.values())
            .filter(s -> EnumTranslator.translate("ORDER_STATUS", s.name()).equals(status))
            .findFirst()
            .map(Order.OrderStatus::name)
            .orElse(null)
    );
    orderService.updateOrderStatus(id, Order.OrderStatus.valueOf(orderDto.getOriginalStatus()));
    if (orderDto.getOriginalStatus().equals(Order.OrderStatus.CANCELED.name())) {
      orderScheduler.deleteOrder(id);
    }
    return "redirect:/orders#" + id;
  }

  @GetMapping("/{id}/delete")
  public String deleteOrder(@PathVariable Long id) {
    orderService.deleteOrder(id);
    return "redirect:/orders";
  }
}
