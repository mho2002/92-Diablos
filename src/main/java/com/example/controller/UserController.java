package com.example.controller;

import com.example.model.Cart;
import com.example.model.Order;
import com.example.model.Product;
import com.example.model.User;
import com.example.service.CartService;
import com.example.service.ProductService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final CartService cartService;
    private final ProductService productService;

    @Autowired
    public UserController(UserService userService, CartService cartService, ProductService productService) {
        this.userService = userService;
        this.cartService = cartService;
        this.productService = productService;
    }
    //tested
    @PostMapping("/")
    public User addUser(@RequestBody User user) {
        if (user.getId()==null) {
            user.setId(UUID.randomUUID());
        }
        if (user.getName()==null || user.getName().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        return userService.add(user);
    }
    //tested
    @GetMapping("/")
    public ArrayList<User> getUsers() {
        return userService.getAll();
    }
    //tested
    @GetMapping("/{userId}")
    public User getUserById(@PathVariable UUID userId) {
        return userService.getById(userId);
    }
    //tested
    @GetMapping("/{userId}/orders")
    public List<Order> getOrdersByUserId(@PathVariable UUID userId) {
        return userService.getOrdersByUserId(userId);
    }
    //tested
    @PostMapping("/{userId}/checkout")
    public String addOrderToUser(@PathVariable UUID userId) {
        try {
            userService.addOrderToUser(userId);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Order added successfully";
    }
    //tested
    @PostMapping("/{userId}/removeOrder")
    public String removeOrderFromUser(@PathVariable UUID userId, @RequestParam UUID orderId) {
        try {
            userService.removeOrderFromUser(userId, orderId);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Order removed successfully";
    }
    //tested
    @DeleteMapping("/{userId}/emptyCart")
    public String emptyCart(@PathVariable UUID userId) {
        try {
            userService.emptyCart(userId);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Cart emptied successfully";
    }
    //tested
    @DeleteMapping("/delete/{userId}")
    public String deleteUserById(@PathVariable UUID userId) {
        try {
            userService.deleteById(userId);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "User deleted successfully";
    }

    @PutMapping("/addProductToCart")
    public String addProductToCart(@RequestParam UUID userId, @RequestParam UUID productId)
    {
        try {
            Cart cart = cartService.getCartByUserId(userId);
            Product product = productService.getProductById(productId);
            if (cart == null) {
                cart = new Cart();
                cart.setUserId(userId);
                cart.setId(UUID.randomUUID());
                cartService.addCart(cart);
            }

            cartService.addProductToCart(cart.getId(), product);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Product added to cart";
    }
    @PutMapping("/deleteProductFromCart")
    public String deleteProductFromCart(@RequestParam UUID userId, @RequestParam UUID productId)
    {
        try {
            Cart cart = cartService.getCartByUserId(userId);
            if (cart == null) {
                return "Cart is empty";
            }
            Product product = productService.getProductById(productId);
            cartService.deleteProductFromCart(cart.getId(), product);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Product deleted from cart";
}


}

