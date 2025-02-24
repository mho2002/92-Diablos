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
    public static List<Cart> carts = new ArrayList<>();

    @Override
    protected String getDataPath() {
        return "";
    }

    @Override
    protected Class<Cart[]> getArrayType() {
        return null;
    }

    public CartRepository(){
    }

    public Cart addCart(Cart cart);

    public ArrayList<Cart> getCarts();

    public Cart getCartById(UUID cartId);

    public Cart getCartByUserId(UUID userId);

    public void addProductToCart(UUID cartId, Product product);

    public void deleteProductFromCart(UUID cartId, Product product);

    public void deleteCartById(UUID cartId);
}
