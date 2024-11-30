package com.cafemanage.cafeordermanagement.service;

import com.cafemanage.cafeordermanagement.dto.OrderDto;
import com.cafemanage.cafeordermanagement.mapper.OrderMapper;
import com.cafemanage.cafeordermanagement.model.Customer;
import com.cafemanage.cafeordermanagement.model.Dish;
import com.cafemanage.cafeordermanagement.model.Order;
import com.cafemanage.cafeordermanagement.repository.CustomerRepository;
import com.cafemanage.cafeordermanagement.repository.DishRepository;
import com.cafemanage.cafeordermanagement.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

  private final OrderRepository orderRepository;
  private final OrderMapper orderMapper;
  private final CustomerRepository customerRepository;
  private final DishRepository dishRepository;


  public OrderService(OrderRepository orderRepository, OrderMapper orderMapper, CustomerRepository customerRepository, DishRepository dishRepository) {
    this.orderRepository = orderRepository;
    this.orderMapper = orderMapper;
    this.customerRepository = customerRepository;
    this.dishRepository = dishRepository;
  }

  public OrderDto createOrder(OrderDto orderDto) {
    Customer customer = customerRepository.findById(orderDto.getCustomerId())
        .orElseThrow(() -> new RuntimeException("Customer not found"));

    List<Dish> dishes = orderDto.getDish().stream()
        .map(dishDto -> dishRepository.findById(dishDto.getId())
            .orElseThrow(() -> new RuntimeException("Dish not found")))
        .collect(Collectors.toList());

    Double totalPrice = dishes.stream()
        .map(Dish::getPrice)
        .reduce(0.0, Double::sum);

    orderDto.setTotalPrice(totalPrice);
    orderDto.setOrderTime(LocalDateTime.now());
    orderDto.setStatus(Order.OrderStatus.valueOf(Order.OrderStatus.PENDING.name()));

    Order order = orderMapper.toEntity(orderDto, customer, dishes);
    Order savedOrder = orderRepository.save(order);
    return orderMapper.toDto(savedOrder);
  }

  public List<OrderDto> getAllOrders() {
    List<Order> orders = orderRepository.findAll();
    return orders.stream()
        .map(orderMapper::toDto)
        .collect(Collectors.toList());
  }

  public OrderDto getOrderById(Long id) {
    Order order = orderRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Order not found"));
    return orderMapper.toDto(order);
  }

  public List<OrderDto> getOrdersByCustomer(Long customerId){
    Customer customer = customerRepository.findById(customerId)
        .orElseThrow(() -> new RuntimeException("Customer not found"));

    return orderRepository.findByCustomer(customer).stream()
        .map(orderMapper::toDto)
        .collect(Collectors.toList());
  }
}
