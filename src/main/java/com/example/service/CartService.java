package com.example.service;

import com.example.model.Cart;
import com.example.model.Product;
import com.example.model.User;
import com.example.repository.CartRepository;
import com.example.repository.MainRepository;
import com.example.repository.ProductRepository;
import com.example.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
@SuppressWarnings("rawtypes")
public class CartService extends MainService<Cart>{


    private final CartRepository cartRepository = (CartRepository) this.getMainRepository();
    private final UserRepository userRepository;
    private final ProductRepository productRepository;


    public CartService(MainRepository<Cart> mainRepository , UserRepository userRepository, Cart cart, ProductRepository productRepository) {
        super(mainRepository);
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public Cart addCart(Cart cart)
    {

        if (cart.getUserId()==null) {
            throw new IllegalArgumentException("this cart has no userId");
        }
        User user = userRepository.getUserById(cart.getUserId());
        if (user == null )
            throw new IllegalArgumentException("User not found");

        if (checkProductsInCartIfNotExisted(cart))
        {
            throw new IllegalArgumentException("this cart has products that do not exist");
        }
        return cartRepository.addCart(cart);
    }

    public ArrayList<Cart> getCarts()
    {
        return cartRepository.getCarts();
    }

    public Cart getCartById(UUID cartId)
    {
        Cart cart = cartRepository.getCartById(cartId);
        if (cart == null)
            throw new IllegalArgumentException("cart not found");
        return cart;
    }

    public Cart getCartByUserId(UUID userId)
    {
        User user = userRepository.getUserById(userId);
        if (user == null)
            throw new IllegalArgumentException("User not found");


        return cartRepository.getCartByUserId(userId);
    }

    public void addProductToCart(UUID cartId, Product product)
    {
        // check if the cartID is correct
        Cart cart = cartRepository.getCartById(cartId);
        if (cart == null)
            throw new IllegalArgumentException("Invalid cart id");
        if (checkProductsInCartIfNotExisted(cart))
            throw new IllegalArgumentException("this cart has products that do not exist");
        if (isProductInCartIsDuplicate(cart, product))
            throw new IllegalArgumentException("this cart has products that already exist");
        cartRepository.addProductToCart(cartId, product);
    }

    public void deleteProductFromCart(UUID cartId, Product product)
    {
        Cart cart = cartRepository.getCartById(cartId);
        if (cart == null)
            throw new IllegalArgumentException("Invalid cart id");
        cartRepository.deleteProductFromCart(cartId, product);
    }

    public void deleteCartById(UUID cartId)
    {
        Cart cart = cartRepository.getCartById(cartId);
        if (cart == null)
            throw new IllegalArgumentException("Invalid cart id");
        cartRepository.deleteCartById(cartId);
    }
    private boolean checkProductsInCartIfNotExisted(Cart cart)
    {
        ArrayList<Product> existedProducts = productRepository.getProducts();

        ArrayList<UUID> existedProductsIds = new ArrayList<>();
        ArrayList<UUID> cartProductsIds = new ArrayList<>();
        for (Product productTemp1 : cart.getProducts()) {
            cartProductsIds.add(productTemp1.getId());
        }
        for (Product existedProduct : existedProducts) {
            existedProductsIds.add(existedProduct.getId());
        }
        return !existedProductsIds.containsAll(cartProductsIds);
    }
    private boolean isProductInCartIsDuplicate(Cart cart , Product product)
    {
        for (Product Product : cart.getProducts()) {
            if (Product.getId().equals(product.getId())) {
                return true;
            }
        }
        return false;
    }
}