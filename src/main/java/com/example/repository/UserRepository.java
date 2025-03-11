package com.example.repository;

import com.example.model.Order;
import com.example.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@SuppressWarnings("rawtypes")
public class UserRepository extends MainRepository<User>{

    @Override
    protected String getDataPath() {

        return "src/main/java/com/example/data/users.json";
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
            throw new RuntimeException("Error retrieving user by ID", e);
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
        try {
            User user = getUserById(userId);
            return (user != null) ? user.getOrders() : new ArrayList<>();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving orders for user", e);
        }
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

