package com.estore.api.estoreapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;

import com.estore.api.estoreapi.model.Product;
import com.estore.api.estoreapi.persistance.ProductDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Tag("Controller-tier")
public class ProductControllerTest {
    private ProductController pc;

    private ProductDAO productMockDAO;

    @BeforeEach
    public void setupHeroController() {
        productMockDAO = mock(ProductDAO.class);
        pc = new ProductController(productMockDAO);
    }

    /*****************************************************************
     * TESTS FOR ALL CONTROLLER METHODS
     ****************************************************************/

    /* ********************* GET PRODUCT ************************** */

    @Test
    public void testGetProduct() throws IOException { // getHero may throw IOException
        // Setup
        Product product = new Product(5, "snek", "snake", "orange", 69, 55,
                "issa snake");
        // When the same id is passed in, our mock Product DAO will return the Product
        // object
        when(productMockDAO.getProduct(product.getId())).thenReturn(product);

        // Invoke
        ResponseEntity<Product> response = pc.getProduct(product.getId());

        // Analyze
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
    }

    @Test
    public void testGetProductNotFound() throws Exception { // createHero may throw IOException
        // Setup
        int productId = 5;
        // When the same id is passed in, our mock Hero DAO will return null, simulating
        // no Product found
        when(productMockDAO.getProduct(productId)).thenReturn(null);

        // Invoke
        ResponseEntity<Product> response = pc.getProduct(productId);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetProductHandleException() throws Exception { // createHero may throw IOException
        // Setup
        int productId = 5;
        // When getProduct is called on the Mock Hero DAO, throw an IOException
        doThrow(new IOException()).when(productMockDAO).getProduct(productId);

        // Invoke
        ResponseEntity<Product> response = pc.getProduct(productId);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    /* ********************* CREATE PRODUCT ************************** */

    @Test
    public void testCreateProduct() throws IOException { // createHero may throw IOException
        // Setup
        Product product = new Product(5, "birb", "bird", "blue", 70, 55,
                "issa bird");
        // when createProduct is called, return true simulating successful
        // creation and save
        when(productMockDAO.createProduct(product)).thenReturn(product);

        // Invoke
        ResponseEntity<Product> response = pc.createProduct(product);

        // Analyze
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(product, response.getBody());
    }

    @Test
    public void testCreateProductFailed() throws IOException { // createHero may throw IOException
        // Setup
        Product product = new Product(5, "snoop", "dog", "maroon", 80, 23,
                "issa snoop dog");
        // when createProduct is called, return false simulating failed
        // creation and save
        when(productMockDAO.createProduct(product)).thenReturn(null);

        // Invoke
        ResponseEntity<Product> response = pc.createProduct(product);

        // Analyze
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void testCreateProductHandleException() throws IOException { // createHero may throw IOException
        // Setup
        Product product = new Product(5, "mitch", "cat", "red", 45, 234, "issa cat");

        // When createProduct is called on the Mock Hero DAO, throw an IOException
        doThrow(new IOException()).when(productMockDAO).createProduct(product);

        // Invoke
        ResponseEntity<Product> response = pc.createProduct(product);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testGetInventory() throws IOException { // createHero may throw IOException
        // Setup
        Product[] inventory;
        Product product1 = new Product(5, "mitch", "cat", "red", 45, 234, "issa cat");
        Product product2 = new Product(4, "brad", "cat", "blone", 20, 300, "issa cat");
        Product product3 = new Product(2, "chad", "cat", "brunette", 10, 400, "issa cat");
        int length = 3;

        // Invoke
        pc.createProduct(product1);
        pc.createProduct(product2);
        pc.createProduct(product3);
        inventory = pc.getInventory().getBody();
        ResponseEntity<Product[]> response = pc.getInventory();
        // Analyze
        // assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(length, inventory.length);
    }

}
