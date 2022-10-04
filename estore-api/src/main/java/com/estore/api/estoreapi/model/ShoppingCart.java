package com.estore.api.estoreapi.model;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ShoppingCart {
    private static final Logger LOG = Logger.getLogger(ShoppingCart.class.getName());
    static final String STRING_FORMAT = "ShoppingCart [product count=%d]";
    @JsonProperty("products")
    private Product[] products;

    public ShoppingCart(@JsonProperty("products") Product[] products) {
        this.products = products;
    }

    public Product[] getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        ArrayList<Product> productList = new ArrayList<>();
        for (Product prod : products) {
            productList.add(prod);
        }
        productList.add(product);
        Product[] productArray = new Product[productList.size()];
        productList.toArray(productArray);
        products = productArray;
    }

    public void removeProduct(Product product) {
        ArrayList<Product> productList = new ArrayList<>();
        for (Product prod : products) {
            productList.add(prod);
        }
        productList.remove(product);
        Product[] productArray = new Product[productList.size()];
        productList.toArray(productArray);
        products = productArray;
    }

    @Override
    public String toString() {
        return String.format(STRING_FORMAT, products.length);
    }
}
