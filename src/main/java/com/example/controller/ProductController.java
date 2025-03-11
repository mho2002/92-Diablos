package com.example.controller;

import com.example.model.Product;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/product")
//allTested
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {this.productService = productService;}
    //tested
    @PostMapping("/")
    public Product addProduct(@RequestBody Product product)
    {
        if (product.getId() == null)
            product.setId(UUID.randomUUID());
        if (product.getName() == null || product.getName().isEmpty())
            throw new IllegalArgumentException("Product name cannot be empty or null");
        else if (product.getPrice() <= 0) {
            throw new IllegalArgumentException("Product price cannot be less than or equal 0");
        }

        return productService.addProduct(product);
    }
    //tested
    @GetMapping("/")
    public ArrayList<Product> getProducts()
    {
        return productService.getProducts();
    }
    //tested
    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable UUID productId)
    {
        return productService.getProductById(productId);
    }
    //tested
    @PutMapping("/update/{productId}")
    public Product updateProduct(@PathVariable UUID productId, @RequestBody Map<String,Object> body)
    {
        return productService.updateProduct( productId, (String) body.get("newName"),
                Double.parseDouble(body.get("newPrice").toString()));
    }
    //tested
    @PutMapping("/applyDiscount")
    public String applyDiscount(@RequestParam double discount,@RequestBody ArrayList<UUID> productIds)
    {
        try {
            productService.applyDiscount(discount / 100, productIds);
            return "Discount applied successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    //tested
    @DeleteMapping("/delete/{productId}")
    public String deleteProductById(@PathVariable UUID productId)
    {
        try {
            productService.deleteProductById(productId);
            return "success delete product ";
        } catch (Exception e) {
            return e.getMessage();
        }
    }


}