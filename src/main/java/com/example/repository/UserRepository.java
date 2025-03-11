package com.example.repository;

import com.example.model.Order;
import com.example.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@SuppressWarnings("rawtypes")
public class UserRepository extends MainRepository<User>{

    @Value("${spring.application.userDataPath}")
    private String userDataPath;

    @Override
    protected String getDataPath() {

        return userDataPath;
    }

    @Override
    protected Class<User[]> getArrayType() {
        return User[].class;
    }
    public UserRepository() {
    }

    public ArrayList<User> getUsers() {
        try {
            return findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving users", e);
        }
    }
    public User getUserById(UUID userId) {
        try {
            return findAll().stream()
                    .filter(user -> user.getId().equals(userId))
                    .findFirst()
                    .orElse(null);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400) , e.getMessage());
        }
    }
    public User addUser(User user) {
        try {
            ArrayList<User> users = getUsers();
            if (users.stream().anyMatch(
                    u -> u.getId().equals(user.getId())
            ))
            {
                throw new IllegalArgumentException("User with this ID already exists");
            }
            save(user);
            return user;
        } catch (Exception e) {
            throw new RuntimeException("Error adding user", e);
        }
    }
    public List<Order> getOrdersByUserId(UUID userId) {
        User user = getUserById(userId);
        if (user == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return user.getOrders();
    }
    public void addOrderToUser(UUID userId, Order order) {
        try {
            ArrayList<User> users = findAll();
            for (User user : users) {
                if (user.getId().equals(userId)) {
                    user.getOrders().add(order);
                    saveAll(users);
                    return;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error adding order to user", e);
        }
    }

    public void removeOrderFromUser(UUID userId, UUID orderId) {
        User user1 = getUserById(userId);
        if (user1 == null)
            throw new IllegalArgumentException("User not found");
        try {
            ArrayList<User> users = findAll();
            for (User user : users) {
                if (user.getId().equals(userId)) {
                    user.setOrders(user.getOrders().stream()
                            .filter(order -> !order.getId().equals(orderId))
                            .collect(Collectors.toList()));
                    saveAll(users);
                    return;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error removing order from user", e);
        }
    }

    public void deleteUserById(UUID userId) {
        ArrayList<User> users = findAll();
        for (User user : users) {
            if (user.getId().equals(userId)) {
                users.remove(user);
                saveAll(users);
                return;
            }
        }
        throw new RuntimeException("User not found");
}


}

