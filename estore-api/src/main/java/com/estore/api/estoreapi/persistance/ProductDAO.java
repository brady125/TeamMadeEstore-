package com.estore.api.estoreapi.persistance;
import java.io.IOException;

import com.estore.api.estoreapi.model.Product;

/*
 * Defines the interface for product object persistance
 */
public interface ProductDAO {
    
    /*
     * Adds the new product to the persistant store and returns it.
     */
    Product[] getInventory() throws IOException;

    Product[] findProducts(String containsText) throws IOException;

    Product getProduct(int id) throws IOException;

    Product createProduct(Product product) throws IOException;

    Product updateProduct(Product product) throws IOException;

    Boolean deleteProduct(int id) throws IOException;
}
