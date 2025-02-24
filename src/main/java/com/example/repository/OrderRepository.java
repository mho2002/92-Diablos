package com.example.repository;

import com.example.model.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
@SuppressWarnings("rawtypes")
public class OrderRepository extends MainRepository<Order> {
    public static List<Order> orders = new ArrayList<>();

    @Override
    protected String getDataPath() {
        return "";
    }

    @Override
    protected Class<Order[]> getArrayType() {
        return null;
    }

    public OrderRepository() {
    }

    public void addOrder(Order order);

    public ArrayList<Order> getOrders();

    public Order getOrderById(UUID orderId);

    public void deleteOrderById(UUID orderId);


}

