package com.cafemanage.cafeordermanagement.service;

import com.cafemanage.cafeordermanagement.model.Order;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class OrderScheduler {

  private final OrderService orderService;

  public OrderScheduler(OrderService orderService) {
    this.orderService = orderService;
  }

  public void statusUpdate(Long orderId) {
    ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    scheduler.schedule(() -> orderService.updateOrderStatus(orderId, Order.OrderStatus.IN_PROGRESS), 10, TimeUnit.SECONDS);
  }

  public void deleteOrder(Long orderId) {
    ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    scheduler.schedule(() -> orderService.deleteOrder(orderId), 20, TimeUnit.SECONDS);
  }
}