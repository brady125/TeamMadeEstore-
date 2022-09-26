package com.estore.api.estoreapi.persistance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.estore.api.estoreapi.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

@Tag("Persistance-tier")
public class ProductFileDAOTest {
    ProductFileDAO productFileDAO;
    Product[] testInventory;
    ObjectMapper fakeObjectMapper;

    @BeforeEach
    public void setupFileDAO() throws IOException {
        fakeObjectMapper = mock(ObjectMapper.class);
        Product[] testProducts = new Product[3];
        testProducts[0] = new Product(30, "Del Taco", "Dog", "Spotted Brown", 4, 99.99f, "He's the most dog ever!!!");
        testProducts[1] = new Product(1, "Trebol", "Cat", "Tabby", 20, 2.00f, "Likes: politics, Dislikes: The Kardashians");
        testProducts[2] = new Product(999, "Henry", "Frog", "Green", 9999, 0.01f, "Contains enough power to run a small city for 30 years");
    
        when(fakeObjectMapper.readValue(new File("whatever.txt"), Product[].class)).thenReturn(testProducts);
        productFileDAO = new ProductFileDAO("whatever.txt", fakeObjectMapper);
    }

    @Test
    public void testGetInventory() {
        Product[] inventory = productFileDAO.getInventory();

        assertEquals(inventory.length, testInventory.length);
        for (int i=0; i<testInventory.length; i++){
            assertEquals(inventory[i], testInventory[i]);
        }
    }

    @Test
    public void testFindProduct() {
        Product[] products = productFileDAO.findProducts("T");

        assertEquals(products.length, 2);
        assertEquals(products[0], testInventory[0]);
        assertEquals(products[1], testInventory[1]);
    }

    @Test
    public void testGetProduct() {
        Product product = productFileDAO.getProduct(30);

        assertEquals(product, testInventory[0]);
    }
}
