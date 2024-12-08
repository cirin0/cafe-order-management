package com.cafemanage.cafeordermanagement.controller;

import com.cafemanage.cafeordermanagement.dto.OrderDto;
import com.cafemanage.cafeordermanagement.model.Order;
import com.cafemanage.cafeordermanagement.service.OrderScheduler;
import com.cafemanage.cafeordermanagement.service.OrderService;
import com.cafemanage.cafeordermanagement.utils.EnumTranslator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

  private final OrderService orderService;
  private final OrderScheduler orderScheduler;

  public OrderController(OrderService orderService, OrderScheduler orderScheduler) {
    this.orderService = orderService;
    this.orderScheduler = orderScheduler;
  }

  @GetMapping
  public ResponseEntity<List<OrderDto>> getAllOrders() {
    return ResponseEntity.ok(orderService.getAllOrders());
  }

  @GetMapping("{id}")
  public ResponseEntity<OrderDto> getOrderById(@PathVariable Long id) {
    return ResponseEntity.ok(orderService.getOrderById(id));
  }

  @GetMapping("customer/{id}")
  public ResponseEntity<List<OrderDto>> getOrdersByCustomer(@PathVariable Long id) {
    return ResponseEntity.ok(orderService.getOrdersByCustomer(id));
  }

  @PostMapping
  public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
    OrderDto createdOrder = orderService.createOrder(orderDto);
    orderScheduler.statusUpdate(createdOrder.getId());
    return ResponseEntity.ok(createdOrder);
  }

  @PutMapping("{id}/status")
  public ResponseEntity<Void> updateOrderStatus(@PathVariable Long id, @RequestParam("status") String status) {
    String originalStatus = Arrays.stream(Order.OrderStatus.values())
        .filter(s -> EnumTranslator.translate("ORDER_STATUS", s.name()).equals(status))
        .findFirst()
        .map(Order.OrderStatus::name)
        .orElseThrow(() -> new IllegalArgumentException("Invalid status: " + status));

    Order.OrderStatus orderStatus = Order.OrderStatus.valueOf(originalStatus);
    orderService.updateOrderStatus(id, orderStatus);
    if (originalStatus.equals(Order.OrderStatus.CANCELED.name())) {
      orderScheduler.deleteOrder(id);
    }
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
    orderService.deleteOrder(id);
    return ResponseEntity.noContent().build();
  }
}
