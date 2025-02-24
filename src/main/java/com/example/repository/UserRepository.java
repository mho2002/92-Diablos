package com.example.repository;

import com.example.model.Order;
import com.example.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
@SuppressWarnings("rawtypes")
public class UserRepository extends MainRepository<User>{
    public static List<User> users = new ArrayList<>();

    @Override
    protected String getDataPath() {
        return "";
    }

    @Override
    protected Class<User[]> getArrayType() {
        return null;
    }

    public UserRepository() {
    }
    public ArrayList<User> getUsers();
    public User getUserById(UUID userId);
    public User addUser(User user);
    public List<Order> getOrdersByUserId(UUID userId);
    public void addOrderToUser(UUID userId, Order order);
    public void removeOrderFromUser(UUID userId, UUID orderId);
    public void deleteUserById(UUID userId);


}

