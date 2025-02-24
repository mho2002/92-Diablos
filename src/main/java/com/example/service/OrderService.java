package com.example.service;

import com.example.model.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
@SuppressWarnings("rawtypes")
public class OrderService extends MainService<Order> {
//The Dependency Injection Variables
//The Constructor with the requried variables mapping the Dependency Injection.

    public void addOrder(Order order);

    public ArrayList<Order> getOrders();

    public Order getOrderById(UUID orderId);

    public void deleteOrderById(UUID orderId) throws IllegalArgumentException;


}
