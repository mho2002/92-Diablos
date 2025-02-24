package com.example.service;

import com.example.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
@SuppressWarnings("rawtypes")
public class ProductService extends MainService<Product> {
//The Dependency Injection Variables
//The Constructor with the requried variables mapping the Dependency Injection.
    public Product addProduct(Product product);

    public ArrayList<Product> getProducts();

    public Product getProductById(UUID productId);

    public Product updateProduct(UUID productId, String newName, double newPrice);

    public void applyDiscount(double discount, ArrayList<UUID> productIds);

    public void deleteProductById(UUID productId);





}
