package com.example.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class Cart {


    private UUID id;
    private UUID userId;
    private List<Product> products=new ArrayList<>();

    public Cart() {

    }
    public Cart(UUID id, UUID userId, List<Product> products) {
        this.id = id;
        this.userId = userId;
        this.products = products;
    }

    public Cart(UUID userId, List<Product> products) {
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.products = products;

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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public double calcPrice() {
        double price=0;
        for(Product product:products) {
            price += product.getPrice();
        }
        return price;
    }
}