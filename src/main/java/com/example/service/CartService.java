package com.example.service;

import com.example.model.Cart;
import com.example.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
@SuppressWarnings("rawtypes")
public class CartService extends MainService<Cart>{
//The Dependency Injection Variables
//The Constructor with the requried variables mapping the Dependency Injection.

    public Cart addCart(Cart cart);

    public ArrayList<Cart> getCarts();

    public Cart getCartById(UUID cartId);

    public Cart getCartByUserId(UUID userId);

    public void addProductToCart(UUID cartId, Product product);

    public void deleteProductFromCart(UUID cartId, Product product);

    public void deleteCartById(UUID cartId);




}

