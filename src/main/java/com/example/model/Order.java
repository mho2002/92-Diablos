package com.example.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {
    private UUID id;
    private UUID userId;
    private double totalPrice;
    private List<Product> products=new ArrayList<>();

    public Order() {}

    public Order(UUID id, UUID userId, List<Product> products) {
        this.id = id;
        this.userId = userId;
        this.products = products;
        double tempTotalPrice = 0;
        for (Product p : products) {
            tempTotalPrice = tempTotalPrice + p.getPrice();
        }
        this.totalPrice = tempTotalPrice;
    }

    public Order(UUID userId, List<Product> products) {
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.products = products;
        double tempTotalPrice = 0;
        for (Product p : products) {
            tempTotalPrice = tempTotalPrice + p.getPrice();
        }
        this.totalPrice = tempTotalPrice;

    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}

