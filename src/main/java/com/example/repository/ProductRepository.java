package com.example.repository;

import com.example.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
@SuppressWarnings("rawtypes")
public class ProductRepository extends MainRepository<Product> {
    public static List<Product> products = new ArrayList<>();
    private final Product product;

    public ProductRepository(Product product) {
        this.product = product;
    }

    @Override
    protected String getDataPath() {
        return "src/main/java/com/example/data/products.json";
    }

    @Override
    protected Class<Product[]> getArrayType() {
        return Product[].class;
    }



    public Product addProduct(Product product)
    {
        if(product.getId()!=null)
        {
            UUID id = product.getId();
        }

        if(product.getName()!=null)
        {
            String name = product.getName();
        }
        if (product.getPrice()!= 0.0)
        {
            double price = product.getPrice();
        }

        //check if the product already exist
        ArrayList<Product> productsExisted = this.findAll();
        Product foundProduct = productsExisted.stream().filter(item -> item.getId().equals(product.getId())).findFirst().orElse(null);
        if (foundProduct != null) {
            throw new IllegalArgumentException("Product with this ID already exists");
        }


        this.save(product);
        return product;

    }

    public ArrayList<Product> getProducts()
    {
        return this.findAll();
    }

    public Product getProductById(UUID productId)
    {
        return this.findAll().stream().filter(
                item -> item.getId().equals(productId)).findFirst().orElse(null);
    }

    public Product updateProduct(UUID productId, String newName, double newPrice)
    {
        if (newName== null || newName.isEmpty() || newPrice == 0.0)
            throw new IllegalArgumentException("New name or price cannot be empty");
        ArrayList<Product> productsExisted = this.findAll();
        Product productToUpdate = productsExisted.stream().filter(
                item -> item.getId().equals(productId)).findFirst().orElse(null);
        if (productToUpdate == null)
        {
            throw new IllegalArgumentException("Product with this ID does not exist");
        }

        productToUpdate.setName(newName);
        productToUpdate.setPrice(newPrice);
        this.overrideData(productsExisted);
        return productToUpdate;

    }

    public void applyDiscount(double discount, ArrayList<UUID> productIds)
    {
        if (discount <= 0.0 || discount > 1.0 || productIds.isEmpty())
            throw new IllegalArgumentException("Invalid discounts or productIds");
        ArrayList<Product> productsExisted = this.findAll();
        //check if all the product ids are correct
        ArrayList<UUID>existedProductsIds = new ArrayList<>();
        for(Product product : productsExisted)
        {
            existedProductsIds.add(product.getId());
        }

        if (!existedProductsIds.containsAll(productIds))
            throw new IllegalArgumentException("one or more of the productIds does not exist ");

        for (Product product : productsExisted) {
            if (productIds.contains(product.getId())) {
                double actualPrice = product.getPrice();
                product.setPrice(actualPrice - (actualPrice * discount));
                productIds.remove(product.getId());
            }
        }

        this.overrideData(productsExisted);
    }

    public void deleteProductById(UUID productId)
    {
        ArrayList<Product> productsExisted = this.findAll();
        Product productToDelete = productsExisted.stream().filter(
                item -> item.getId().equals(productId)).findFirst().orElse(null);
        if (productToDelete == null)
            throw new IllegalArgumentException("Product with this ID does not exist");
        else
        {
            productsExisted.remove(productToDelete);
            this.overrideData(productsExisted);
        }



    }


}