package com.example.repository;

import com.example.model.Cart;
import com.example.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
@SuppressWarnings("rawtypes")
public class CartRepository extends MainRepository<Cart> {

    @Override
    protected String getDataPath() {
        return "src/main/java/com/example/data/carts.json";
    }

    @Override
    protected Class<Cart[]> getArrayType() {
        return Cart[].class;
    }

    public CartRepository(){
    }

    public Cart addCart(Cart cart){
        save(cart);
        return cart;
    }

    public ArrayList<Cart> getCarts()
    {
        return findAll();
    }

    public Cart getCartById(UUID cartId)
    {
        return findAll().stream().filter(cart -> cart.getId().equals(cartId)).findFirst().orElse(null);
    }

    public Cart getCartByUserId(UUID userId)
    {
        return findAll().stream().filter(cart -> cart.getUserId().equals(userId)).findFirst().orElse(null);
    }

    public void addProductToCart(UUID cartId, Product product)
    {
        ArrayList<Cart> carts = getCarts();
        for(Cart cart : carts)
        {
            if (cart.getId().equals(cartId))
            {
                cart.getProducts().add(product);
            }
        }
        saveAll(carts);
    }

    public void deleteProductFromCart(UUID cartId, Product product)
    {
        ArrayList<Cart> carts = getCarts();
        for (Cart cart : carts)
        {
            cart.getProducts().removeIf(p -> p.getId().equals(product.getId()));
        }
        saveAll(carts);
    }

    public void deleteCartById(UUID cartId)
    {
        ArrayList<Cart> carts = getCarts();
        carts.removeIf(cart -> cart.getId().equals(cartId));
        saveAll(carts);
    }
}