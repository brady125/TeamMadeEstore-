package com.estore.api.estoreapi.persistance;
import com.estore.api.estoreapi.model.Product;

/*
 * Defines the interface for product object persistance
 */
public interface ProductDAO {
    
    /*
     * Adds the new product to the persistant store and returns it.
     */
    Product createProduct(Product product);
    
}
