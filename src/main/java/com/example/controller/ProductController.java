package com.example.controller;

import com.example.model.Product;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {
//The Dependency Injection Variables
//The Constructor with the requried variables mapping the Dependency Injection.

    @PostMapping("/")
    public Product addProduct(@RequestBody Product product)
    {

    }

    @GetMapping("/")
    public ArrayList<Product> getProducts()
    {

    }

    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable UUID productId)
    {

    }

    @PutMapping("/update/{productId}")
    public Product updateProduct(@PathVariable UUID productId, @RequestBody Map<String,Object> body)
    {

    }

    @PutMapping("/applyDiscount")
    public String applyDiscount(@RequestParam double discount,@RequestBody ArrayList<UUID> productIds)
    {

    }

    @DeleteMapping("/delete/{productId}")
    public String deleteProductById(@PathVariable UUID productId)
    {

    }


}

