package com.example.service;

import com.example.model.Order;
import com.example.model.Product;
import com.example.model.User;
import com.example.repository.MainRepository;
import com.example.repository.OrderRepository;
import com.example.repository.ProductRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

@Service
@SuppressWarnings("rawtypes")
public class OrderService extends MainService<Order> {

    private final OrderRepository orderRepository = (OrderRepository) this.getMainRepository();
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    public OrderService(MainRepository<Order> mainRepository, UserRepository userRepository, ProductRepository productRepository) {
        super(mainRepository);
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }
//The Dependency Injection Variables
//The Constructor with the required variables mapping the Dependency Injection.

    public void addOrder(Order order)  {
        if (order.getId() == null) {
            order.setId(UUID.randomUUID());
        }
        if (order.getTotalPrice()==0.0)
        {
            order.calculateTotalPrice();
        }
        User user = userRepository.getUserById(order.getUserId());
        if (user==null) {
            user = new User();
            user.setId(order.getUserId());
            user.setName("noName");
            user.getOrders().add(order);
        }
        if (!isProductsExistedInOrder(order))
            throw new IllegalArgumentException("Product list does not contain excited product/s");
        if (isOrderHasDuplicateProducts(order))
            throw new IllegalArgumentException("Order has duplicate product/s");

        userRepository.addOrderToUser(user.getId(), order);

        orderRepository.addOrder(order);
    }

    public ArrayList<Order> getOrders()
    {
        return orderRepository.getOrders();
    }

    public Order getOrderById(UUID orderId)
    {
        return orderRepository.getOrderById(orderId);
    }

    public void deleteOrderById(UUID orderId) throws IllegalArgumentException
    {
        Order order = orderRepository.getOrderById(orderId);
        if (order == null)
        {
            throw new IllegalArgumentException("Order id doesn't exist");
        }
        orderRepository.deleteOrderById(orderId);

    }

    private boolean isProductsExistedInOrder (Order order)
    {
        ArrayList<Product> products = new ArrayList<>(order.getProducts());
        ArrayList<Product> existedProducts = productRepository.getProducts();
        ArrayList<UUID> productIds = new ArrayList<>();
        for (Product product : products)
        {
            productIds.add(product.getId());
        }
        ArrayList<UUID> existedProductIds = new ArrayList<>();
        for (Product product : existedProducts)
            existedProductIds.add(product.getId());

        return existedProductIds.containsAll(productIds);
    }

    private boolean isOrderHasDuplicateProducts (Order order)
    {
        ArrayList<UUID> productsIds = new ArrayList<>();
        for (Product product : order.getProducts() )
        {
            productsIds.add(product.getId());
        }
        Collections.sort(productsIds);
        for (int i = 1; i < productsIds.size(); i++) {
            int index = Collections.binarySearch(productsIds.subList(0, i), productsIds.get(i));
            if (index >= 0) {
                return true; // Duplicate found
            }
        }
        return false;
}


}