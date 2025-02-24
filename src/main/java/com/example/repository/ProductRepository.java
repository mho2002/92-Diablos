package com.example.repository;

import com.example.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
@SuppressWarnings("rawtypes")
public class ProductRepository extends MainRepository<Product> {
    public static List<Product> products = new ArrayList<>();

    @Override
    protected String getDataPath() {
        return "";
    }

    @Override
    protected Class<Product[]> getArrayType() {
        return null;
    }

    public ProductRepository() {
    }

    public Product addProduct(Product product);

    public ArrayList<Product> getProducts();

    public Product getProductById(UUID productId);

    public Product updateProduct(UUID productId, String newName, double newPrice);

    public void applyDiscount(double discount, ArrayList<UUID> productIds);

    public void deleteProductById(UUID productId);


}

