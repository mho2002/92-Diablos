package com.example.controller;

import com.example.model.Order;
import com.example.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
//The Dependency Injection Variables
//The Constructor with the requried variables mapping the Dependency Injection.

    @PostMapping("/")
    public User addUser(@RequestBody User user){

    }

    @GetMapping("/")
    public ArrayList<User> getUsers(){

    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable UUID userId)
    {

    }

    @GetMapping("/{userId}/orders")
    public List<Order> getOrdersByUserId(@PathVariable UUID userId)
    {

    }

    @PostMapping("/{userId}/checkout")
    public String addOrderToUser(@PathVariable UUID userId)
    {

    }

    @PostMapping("/{userId}/removeOrder")
    public String removeOrderFromUser(@PathVariable UUID userId, @RequestParam UUID orderId)
    {

    }

    @DeleteMapping("/{userId}/emptyCart")
    public String emptyCart(@PathVariable UUID userId)
    {

    }

    @PutMapping("/addProductToCart")
    public String addProductToCart(@RequestParam UUID userId, @RequestParam UUID productId)
    {

    }

    @PutMapping("/deleteProductFromCart")
    public String deleteProductFromCart(@RequestParam UUID userId, @RequestParam UUID productId)
    {

    }

    @DeleteMapping("/delete/{userId}")
    public String deleteUserById(@PathVariable UUID userId)
    {

    }


}



