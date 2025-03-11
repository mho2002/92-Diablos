package com.example.service;

import com.example.model.Cart;
import com.example.model.Order;
import com.example.model.User;
import com.example.repository.CartRepository;
import com.example.repository.OrderRepository;
import com.example.repository.ProductRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@SuppressWarnings("rawtypes")
public class UserService extends MainService<User> {

    private final UserRepository userRepository = (UserRepository) this.getMainRepository();
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final OrderService orderService;

    public UserService(UserRepository userRepository , CartRepository cartRepository , OrderRepository orderRepository, CartService cartService, OrderService orderService) {
        super(userRepository);
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
        this.cartService = cartService;
        this.orderService = orderService;
    }


    public User add(User user) {
        return userRepository.addUser(user);
    }
    public ArrayList<User> getAll() {
        return userRepository.getUsers();
    }

    public User getById(UUID userId) {
        return userRepository.getUserById(userId);
    }

    public void deleteById(UUID userId) {
        userRepository.deleteUserById(userId);
    }

    public List<Order> getOrdersByUserId(UUID userId) {
        return userRepository.getOrdersByUserId(userId);
    }

    public void addOrderToUser(UUID userId) {
        User user = userRepository.getUserById(userId);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        Cart cart = cartRepository.getCartByUserId(userId);
        if (cart.getProducts().isEmpty())
            throw new IllegalArgumentException("Cart is empty");
        Order newOrder = new Order(userId,  cart.calcPrice() , cart.getProducts());
        cartRepository.deleteCartById(cart.getId());
        userRepository.addOrderToUser(userId,newOrder);
        orderRepository.addOrder(newOrder);
    }

    public void emptyCart(UUID userId) {
        User user = userRepository.getUserById(userId);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        Cart cart = cartRepository.getCartByUserId(userId);
        if (cart != null) {
            cartService.deleteCartById(cart.getId());
        }else
        {
            throw new IllegalArgumentException("there is no cart for this user ");
        }
    }
    public void removeOrderFromUser(UUID userId, UUID orderId) {
        Order order = orderRepository.getOrderById(orderId);
        if (order == null)
            throw new IllegalArgumentException("Order not found");

        userRepository.removeOrderFromUser(userId, orderId);


    }
}

