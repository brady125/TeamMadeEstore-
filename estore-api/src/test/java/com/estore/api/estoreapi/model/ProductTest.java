package com.estore.api.estoreapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * The unit test suite for the Product class
 */
@Tag("Model-tier")
public class ProductTest {
    Product product;

    @BeforeEach
    public void setupProduct() {
        float price = 212.5F;
        product = new Product(5, "Charlie", "dog", "yellow", 7, price, "A friendly yellow lab.");
    }

    @Test
    public void testGetId() {
        assertEquals(5, product.getId());
    }
    @Test
    public void testGetName() {
        assertEquals("Charlie", product.getName());
    }
    @Test
    public void testGetSpecies() {
        assertEquals("dog", product.getSpecies());
    }
    @Test
    public void testGetColor() {
        assertEquals("yellow", product.getColor());
    }
    @Test
    public void testGetAge() {
        assertEquals(7, product.getAge());
    }
    @Test
    public void testGetPrice() {
        assertEquals(212.50, product.getPrice());
    }
    @Test
    public void testGetDescription() {
        assertEquals("A friendly yellow lab.", product.getDescription());
    }
}
