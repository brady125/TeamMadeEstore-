package com.estore.api.estoreapi.persistance;
import java.io.IOException;

import com.estore.api.estoreapi.model.Product;

/*
 * Defines the interface for product object persistance
 */
public interface ProductDAO {
    
    Product[] getInventory() throws IOException;

    /**
     * Returns an array of Products that contains the given text in one of its fields (not case sensitive)
     * @param
     * @return an array of {@link Product products} that contains the given text
     * @throws IOException
     */
    Product[] findProducts(String containsText) throws IOException;

    /**
     * Gets a {@link Product product} object from the persistent using the {@link Product products} unique id 
     * @param 
     * @return the {@link Product product} object with the given id
     * @throws IOException
     */
    Product getProduct(int id) throws IOException;

    /**
     * Adds the new product to the persistent store and returns it.
     * @return the added {@link Product product}
     * @param
     * @throws IOException
     */
    Product createProduct(Product product) throws IOException;

    /**
     * Updates an existing product in the persistent store and returns it. Returns null if the product doesn't already exist
     * @param product
     * @return {@link Product product}
     * @throws IOException
     */
    Product updateProduct(Product product) throws IOException;

    /**
     * Deletes a product from the persistent store, returns true on a successful deletion.
     * @param id
     * @return boolean
     * @throws IOException
     */
    Boolean deleteProduct(int id) throws IOException;
}
