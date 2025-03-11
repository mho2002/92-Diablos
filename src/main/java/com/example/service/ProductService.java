package com.example.service;

import com.example.model.Product;
import com.example.repository.ProductRepository;
import com.example.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
@SuppressWarnings("rawtypes")
public class ProductService extends MainService<Product> {


    public ProductService(ProductRepository productRepository) {
    super(productRepository);
    }

    private final ProductRepository productRepository= (ProductRepository) this.getMainRepository();

    public Product addProduct(Product product)
    {
        return productRepository.addProduct(product);
    }

    public ArrayList<Product> getProducts()
    {
        return productRepository.getProducts();
    }

    public Product getProductById(UUID productId)
    {
        return productRepository.getProductById(productId);
    }

    public Product updateProduct(UUID productId, String newName, double newPrice)
    {
        return productRepository.updateProduct(productId, newName, newPrice);
    }

    public void applyDiscount(double discount, ArrayList<UUID> productIds)
    {
        productRepository.applyDiscount(discount, productIds);
    }

    public void deleteProductById(UUID productId)
    {
        productRepository.deleteProductById(productId);
    }





}