package com.estore.api.estoreapi.persistance;
import java.io.IOException;

import com.estore.api.estoreapi.model.Product;

/*
 * Defines the interface for product object persistance
 */
public interface ProductDAO {
    
    Product[] getInventory() throws IOException;

    /*
     * Returns an array of Products that contains the given text in one of its fields (not case sensitive)
     */
    Product[] findProducts(String containsText) throws IOException;

    Product getProduct(int id) throws IOException;

    /*
     * Adds the new product to the persistant store and returns it.
     */
    Product createProduct(Product product) throws IOException;

    Product updateProduct(Product product) throws IOException;

    Boolean deleteProduct(int id) throws IOException;
}
