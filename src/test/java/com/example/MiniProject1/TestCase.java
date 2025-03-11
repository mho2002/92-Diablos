package com.example.MiniProject1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.model.Cart;
import com.example.model.Order;
import com.example.model.Product;
import com.example.model.User;
import com.example.repository.CartRepository;
import com.example.repository.OrderRepository;
import com.example.repository.ProductRepository;
import com.example.repository.UserRepository;
import com.example.service.CartService;
import com.example.service.OrderService;
import com.example.service.ProductService;
import com.example.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
@ComponentScan(basePackages = "com.example.*")
@WebMvcTest
class TestCase {

    @Value("${spring.application.userDataPath}")
    private String userDataPath;

    @Value("${spring.application.productDataPath}")
    private String productDataPath;

    @Value("${spring.application.orderDataPath}")
    private String orderDataPath;

    @Value("${spring.application.cartDataPath}")
    private String cartDataPath;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Autowired
    private UserService userService;


    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    public void overRideAll() {
        try {
            objectMapper.writeValue(new File(userDataPath), new ArrayList<User>());
            objectMapper.writeValue(new File(productDataPath), new ArrayList<Product>());
            objectMapper.writeValue(new File(orderDataPath), new ArrayList<Order>());
            objectMapper.writeValue(new File(cartDataPath), new ArrayList<Cart>());
        } catch (IOException e) {
            throw new RuntimeException("Failed to write to JSON file", e);
        }
    }

    public Object find(String typeString, Object toFind) {
        switch (typeString) {
            case "User":
                ArrayList<User> users = getUsers();

                for (User user : users) {
                    if (user.getId().equals(((User) toFind).getId())) {
                        return user;
                    }
                }
                break;
            case "Product":
                ArrayList<Product> products = getProducts();
                for (Product product : products) {
                    if (product.getId().equals(((Product) toFind).getId())) {
                        return product;
                    }
                }
                break;
            case "Order":
                ArrayList<Order> orders = getOrders();
                for (Order order : orders) {
                    if (order.getId().equals(((Order) toFind).getId())) {
                        return order;
                    }
                }
                break;
            case "Cart":
                ArrayList<Cart> carts = getCarts();
                for (Cart cart : carts) {
                    if (cart.getId().equals(((Cart) toFind).getId())) {
                        return cart;
                    }
                }
                break;
        }
        return null;
    }

    public Product addProduct(Product product) {
        try {
            File file = new File(productDataPath);
            ArrayList<Product> products;
            if (!file.exists()) {
                products = new ArrayList<>();
            } else {
                products = new ArrayList<>(Arrays.asList(objectMapper.readValue(file, Product[].class)));
            }
            products.add(product);
            objectMapper.writeValue(file, products);
            return product;
        } catch (IOException e) {
            throw new RuntimeException("Failed to write to JSON file", e);
        }
    }

    public ArrayList<Product> getProducts() {
        try {
            File file = new File(productDataPath);
            if (!file.exists()) {
                return new ArrayList<>();
            }
            return new ArrayList<Product>(Arrays.asList(objectMapper.readValue(file, Product[].class)));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read from JSON file", e);
        }
    }

    public User addUser(User user) {
        try {
            File file = new File(userDataPath);
            ArrayList<User> users;
            if (!file.exists()) {
                users = new ArrayList<>();
            } else {
                users = new ArrayList<>(Arrays.asList(objectMapper.readValue(file, User[].class)));
            }
            users.add(user);
            objectMapper.writeValue(file, users);
            return user;
        } catch (IOException e) {
            throw new RuntimeException("Failed to write to JSON file", e);
        }
    }

    public ArrayList<User> getUsers() {
        try {
            File file = new File(userDataPath);
            if (!file.exists()) {
                return new ArrayList<>();
            }
            return new ArrayList<User>(Arrays.asList(objectMapper.readValue(file, User[].class)));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read from JSON file", e);
        }
    }

    public Cart addCart(Cart cart) {
        try {
            File file = new File(cartDataPath);
            ArrayList<Cart> carts;
            if (!file.exists()) {
                carts = new ArrayList<>();
            } else {
                carts = new ArrayList<>(Arrays.asList(objectMapper.readValue(file, Cart[].class)));
            }
            carts.add(cart);
            objectMapper.writeValue(file, carts);
            return cart;
        } catch (IOException e) {
            throw new RuntimeException("Failed to write to JSON file", e);
        }
    }

    public ArrayList<Cart> getCarts() {
        try {
            File file = new File(cartDataPath);
            if (!file.exists()) {
                return new ArrayList<>();
            }
            return new ArrayList<Cart>(Arrays.asList(objectMapper.readValue(file, Cart[].class)));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read from JSON file", e);
        }
    }

    public Order addOrder(Order order) {
        try {
            File file = new File(orderDataPath);
            ArrayList<Order> orders;
            if (!file.exists()) {
                orders = new ArrayList<>();
            } else {
                orders = new ArrayList<>(Arrays.asList(objectMapper.readValue(file, Order[].class)));
            }
            orders.add(order);
            objectMapper.writeValue(file, orders);
            return order;
        } catch (IOException e) {
            throw new RuntimeException("Failed to write to JSON file", e);
        }
    }

    public ArrayList<Order> getOrders() {
        try {
            File file = new File(orderDataPath);
            if (!file.exists()) {
                return new ArrayList<>();
            }
            return new ArrayList<Order>(Arrays.asList(objectMapper.readValue(file, Order[].class)));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read from JSON file", e);
        }
    }


    private UUID userId;
    private User testUser;

    @BeforeEach
    void setUp() {
        userId = UUID.randomUUID();
        testUser = new User();
        testUser.setId(userId);
        testUser.setName("Test User");
        addUser(testUser);
        overRideAll();
    }

// ------------------------ User Tests -------------------------

    //test add user

    @Test
    void testAddUser_Success() throws Exception {
        // Arrange
        User testUser = new User();
        testUser.setId(UUID.randomUUID());
        testUser.setName("Valid User");

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/user/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testUser)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Valid User"));
    }

    @Test
    void testAddUser_NullName_ShouldReturnBadRequest() throws Exception {
        // Arrange
        User testUser = new User();
        testUser.setId(UUID.randomUUID());
        testUser.setName(null); // Null name

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/user/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testUser)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void testAddUser_EmptyName_ShouldReturnBadRequest() throws Exception {
        // Arrange
        User testUser = new User();
        testUser.setId(UUID.randomUUID());
        testUser.setName(""); // Empty name

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/user/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testUser)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void testGetUsersReturnsList() throws Exception {
        // Add test users to the actual service
        userService.add(new User("User1" , new ArrayList<Order>()));
        userService.add(new User("User2",new ArrayList<Order>()));

        mockMvc.perform(MockMvcRequestBuilders.get("/user/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
    }

    @Test
    void testGetUsersReturnsEmptyList() throws Exception {
        // No users added in setup, so the list should be empty

        mockMvc.perform(MockMvcRequestBuilders.get("/user/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(0));
    }

    @Test
    void testGetUsersHandlesMultipleUsers() throws Exception {
        userService.add(new User("User1" , new ArrayList<Order>()));
        userService.add(new User("User2" , new ArrayList<Order>()));
        userService.add(new User("User3" , new ArrayList<Order>()));

        mockMvc.perform(MockMvcRequestBuilders.get("/user/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("User1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("User2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].name").value("User3"));
    }

    @Test
    void testGetUserByIdReturnsUser() throws Exception {
        User testUser8=new User();
        testUser8.setId(UUID.randomUUID());
        testUser8.setName("Test User8");
        addUser(testUser8);

        mockMvc.perform(MockMvcRequestBuilders.get("/user/{userId}", testUser8.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(testUser8)));
    }

    @Test
    void testGetUserByIdNotFound() throws Exception {
        UUID nonExistentUserId = UUID.randomUUID();

        mockMvc.perform(MockMvcRequestBuilders.get("/user/" + nonExistentUserId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testGetUserByIdInvalidUUID() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/invalid-uuid")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void testGetOrdersByUserIdReturnsOrders() throws Exception {
        // Add orders to this user
        userService.add(testUser);
        Order order1 = new Order(UUID.randomUUID(), userId, 100.0, new ArrayList<>());
        Order order2 = new Order(UUID.randomUUID(), userId, 50.0, new ArrayList<>());
        orderService.addOrder(order1);
        orderService.addOrder(order2);
        mockMvc.perform(MockMvcRequestBuilders.get("/user/" + userId + "/orders")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2)); // Expecting 2 orders
    }

    @Test
    void testGetOrdersByUserIdNoOrders() throws Exception {
        UUID newUserId = UUID.randomUUID();
        userService.add(new User(newUserId, "New User",new ArrayList<Order>()));

        mockMvc.perform(MockMvcRequestBuilders.get("/user/" + newUserId + "/orders")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(0)); // Expecting an empty list
    }

    @Test
    void testGetOrdersByNonExistentUser() throws Exception {
        UUID nonExistentUserId = UUID.randomUUID();

        mockMvc.perform(MockMvcRequestBuilders.get("/user/" + nonExistentUserId + "/orders")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testAddOrderToUser_Success() throws Exception {
        userService.add(testUser);
        Cart cart = new Cart(testUser.getId() , new ArrayList<Product>());
        Product product = new Product("TestProduct" , 100.0);
        productService.addProduct(product);
        cart.getProducts().add(product);
        cartService.addCart(cart);
        mockMvc.perform(MockMvcRequestBuilders.post("/user/" + userId + "/checkout")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Order added successfully"));
    }

    @Test
    void testAddOrderToUser_EmptyCart() throws Exception {
       User newUser = new User();
       UUID newUserId = UUID.randomUUID();
       newUser.setId(newUserId);
       newUser.setName("Test User3");
       userService.add(newUser);
       Cart cart = new Cart(newUserId,new ArrayList<Product>());
       cartService.addCart(cart);


        mockMvc.perform(MockMvcRequestBuilders.post("/user/" + newUserId + "/checkout")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Cart is empty")); // Assuming this is the exception message
    }

    @Test
    void testAddOrderToUser_NonExistentUser() throws Exception {
        UUID nonExistentUserId = UUID.randomUUID();

        mockMvc.perform(MockMvcRequestBuilders.post("/user/" + nonExistentUserId + "/checkout")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("User not found")); // Assuming this is the exception message
    }

    @Test
    void testRemoveOrderFromUser_Success() throws Exception {
        UUID existingUserId = userId;
        addUser(testUser);
        UUID existingOrderId = UUID.randomUUID();
        Product product = new Product("TestProduct" , 100.0);
        addProduct(product);
        Order order1 = new Order(existingOrderId, existingUserId, 100.0, new ArrayList<>());
        order1.getProducts().add(product);
        addOrder(order1);


        mockMvc.perform(MockMvcRequestBuilders.post("/user/{userId}/removeOrder", existingUserId).param("orderId", existingOrderId.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Order removed successfully"));
    }

    @Test
    void testRemoveOrderFromUser_OrderNotFound() throws Exception {
        UUID nonExistentOrderId = UUID.randomUUID();
        UUID existingUserId = userId;
        addUser(testUser);
        UUID existingOrderId = UUID.randomUUID();

        mockMvc.perform(MockMvcRequestBuilders.post("/user/{userId}/removeOrder", existingUserId).param("orderId", existingOrderId.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Order not found"));
    }

    @Test
    void testRemoveOrderFromUser_UserNotFound() throws Exception {
        UUID nonExistentUserId = UUID.randomUUID();
        UUID existingOrderId = UUID.randomUUID();
        addOrder(new Order(existingOrderId, UUID.randomUUID(), 100.0, new ArrayList<>()));

        mockMvc.perform(MockMvcRequestBuilders.post("/user/{userId}/removeOrder", nonExistentUserId).param("orderId", existingOrderId.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("User not found"));
    }

    @Test
    void testEmptyCart_Success() throws Exception {
        User testUser13=new User();
        testUser13.setId(UUID.randomUUID());
        testUser13.setName("Test User13");
        Product product = new Product(UUID.randomUUID(), "Test Product", 100.0);
        Cart cart = new Cart(UUID.randomUUID(), testUser13.getId(), new ArrayList<>(List.of(product)));
        addUser(testUser13);
        addCart(cart);

        mockMvc.perform(MockMvcRequestBuilders.delete("/user/{userId}/emptyCart", testUser13.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Cart emptied successfully"));
    }

    @Test
    void testEmptyCart_UserNotFound() throws Exception {
        UUID nonExistentUserId = UUID.randomUUID();

        mockMvc.perform(MockMvcRequestBuilders.delete("/user/" + nonExistentUserId + "/emptyCart")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()) // Adjust if exceptions are mapped to HTTP error codes
                .andExpect(MockMvcResultMatchers.content().string("User not found"));
    }

    @Test
    void testEmptyAlreadyEmptyCart() throws Exception {
        User testUser13=new User();
        testUser13.setId(UUID.randomUUID());
        testUser13.setName("Test User13");

        Cart cart = new Cart(UUID.randomUUID(), testUser13.getId(), new ArrayList<>());
        addUser(testUser13);
        addCart(cart);

        // Try emptying again
        mockMvc.perform(MockMvcRequestBuilders.delete("/user/" + testUser13.getId() + "/emptyCart")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Cart emptied successfully"));
    }




}
