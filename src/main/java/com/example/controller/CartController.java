package com.example.controller;

import com.example.model.Cart;
import com.example.model.Product;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequestMapping("/cart")
public class CartController {
//The Dependency Injection Variables
//The Constructor with the requried variables mapping the Dependency Injection.

    @PostMapping("/")
    public Cart addCart(@RequestBody Cart cart)
    {

    }

    @GetMapping("/")
    public ArrayList<Cart> getCarts()
    {

    }

    @GetMapping("/{cartId}")
    public Cart getCartById(@PathVariable UUID cartId)
    {

    }

    @PutMapping("/addProduct/{cartId}")
    public String addProductToCart(@PathVariable UUID cartId, @RequestBody Product product)
    {

    }

    @DeleteMapping("/delete/{cartId}")
    public String deleteCartById(@PathVariable UUID cartId)
    {

    }


}
