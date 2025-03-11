package com.example.controller;

import com.example.model.Order;
import com.example.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderController {
    //The Dependency Injection Variables
    //The Constructor with the requried variables mapping the Dependency Injection.
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    //tested
    @PostMapping("/")
    public void addOrder(@RequestBody Order order)
    {
        orderService.addOrder(order);
    }
    //tested
    @GetMapping("/{orderId}")
    public Order getOrderById(@PathVariable UUID orderId) throws IllegalAccessException {
        Order order = orderService.getOrderById(orderId);
        if (order == null)
            throw new IllegalAccessException("there is no order with this id");
        return order;
    }
    //tested
    @GetMapping("/")
    public ArrayList<Order> getOrders()
    {
        return orderService.getOrders();
    }
    //tested
    @DeleteMapping("/delete/{orderId}")
    public String deleteOrderById(@PathVariable UUID orderId)
    {
        try {
            orderService.deleteOrderById(orderId);
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
        return "Order deleted";
}

}