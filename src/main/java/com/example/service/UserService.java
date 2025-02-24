package com.example.service;

import com.example.model.Order;
import com.example.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
@SuppressWarnings("rawtypes")
public class UserService extends MainService<User>{
//The Dependency Injection Variables
//The Constructor with the requried variables mapping the Dependency Injection.

    public User addUser(User user);

    public ArrayList<User> getUsers();

    public User getUserById(UUID userId);

    public List<Order> getOrdersByUserId(UUID userId);

    public void addOrderToUser(UUID userId, Order order);

    public void emptyCart(UUID userId);

    public void removeOrderFromUser(UUID userId, UUID orderId);

    public void deleteUserById(UUID userId);






}

