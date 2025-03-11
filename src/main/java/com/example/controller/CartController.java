package com.example.controller;

import com.example.model.Cart;
import com.example.model.Product;
import com.example.repository.CartRepository;
import com.example.service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequestMapping("/cart")
public class CartController {
    //The Dependency Injection Variables
//The Constructor with the requried variables mapping the Dependency Injection.
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }
    //tested
    @PostMapping("/")
    public Cart addCart(@RequestBody Cart cart)
    {
        if (cart.getId() == null)
            cart.setId(UUID.randomUUID());
        return cartService.addCart(cart);
    }
    //tested
    @GetMapping("/")
    public ArrayList<Cart> getCarts()
    {
        return cartService.getCarts();
    }
    //tested
    @GetMapping("/{cartId}")
    public Cart getCartById(@PathVariable UUID cartId)
    {
        return cartService.getCartById(cartId);
    }
    //tested
    @PutMapping("/addProduct/{cartId}")
    public String addProductToCart(@PathVariable UUID cartId, @RequestBody Product product)
    {
        try {
            cartService.addProductToCart(cartId, product);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Added product to Cart";

    }
    //tested
    @DeleteMapping("/delete/{cartId}")
    public String deleteCartById(@PathVariable UUID cartId)
    {
        try {
            cartService.deleteCartById(cartId);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Cart deleted successfully";
    }


}