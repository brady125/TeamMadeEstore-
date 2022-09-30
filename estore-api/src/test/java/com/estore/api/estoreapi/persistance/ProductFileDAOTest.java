package com.estore.api.estoreapi.persistance;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doThrow;

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
    Product[] testProducts;
    ObjectMapper mockObjectMapper;

    /**
     * Before each test, we will create and inject a Mock Object Mapper to
     * isolate the tests from the underlying file
     * @throws IOException
     */
    @BeforeEach
    public void setupProductFileDAO() throws IOException {
        mockObjectMapper = mock(ObjectMapper.class);
        testProducts = new Product[3];
        testProducts[0] = new Product(0, "Orly", "turtle", "green", 7, 20, "A handsome green turtle.");
        testProducts[1] = new Product(1, "Sprinkle", "weasel", "red", 3, 16, "A friendly crimson weasel.");
        testProducts[2] = new Product(2, "George", "monkey", "brown", 5, 60, "A friendly and curious monkey.");

        // When the object mapper is supposed to read from the file
        // the mock object mapper will return the product array above
        when(mockObjectMapper
            .readValue(new File("doesnt_matter.txt"),Product[].class))
                .thenReturn(testProducts);
        productFileDAO = new ProductFileDAO("doesnt_matter.txt",mockObjectMapper);
    }

    @Test
    public void testGetInventory() {
        Product[] inventory = productFileDAO.getInventory();

        assertEquals(inventory.length, testProducts.length);
        for (int i=0; i<testProducts.length; i++){
            assertEquals(inventory[i], testProducts[i]);
        }
    }

    @Test
    public void testFindProductsName() {
        // Invoke
        Product[] products = productFileDAO.findProducts("or");

        // Analyze
        assertEquals(products.length,2);
        assertEquals(products[0],testProducts[0]);
        assertEquals(products[1],testProducts[2]);
    }

    @Test
    public void testFindProductsDescription() {
        // Invoke
        Product[] products = productFileDAO.findProducts("Friendly");

        // Analyze
        assertEquals(products.length,2);
        assertEquals(products[0],testProducts[1]);
        assertEquals(products[1],testProducts[2]);
    }

    @Test
    public void testFindProductsAge() {
        // Invoke
        Product[] products = productFileDAO.findProducts("5");

        // Analyze
        assertEquals(products.length,1);
        assertEquals(products[0],testProducts[2]);
    }

    @Test
    public void testFindProductsFieldSeperation() {
        /* Makes sure that the search isn't combining fields */
        // Invoke
        Product[] products = productFileDAO.findProducts("yt"); //"Orlyturtle"

        // Analyze
        assertEquals(products.length,0);
    }

    @Test
    public void testFindProductsNull() {
        // Invoke
        Product[] products = productFileDAO.findProducts(null);

        // Analyze
        assertEquals(products.length,3);
        assertEquals(products[0],testProducts[0]);
        assertEquals(products[1],testProducts[1]);
        assertEquals(products[2],testProducts[2]);
    }

    @Test
    public void testGetProduct() {
        //Invoke
        Product product = productFileDAO.getProduct(1);
        //Analyze
        assertEquals(testProducts[1], product);
    }

    @Test
    public void testDeleteProduct() {
        //Invoke
        boolean result = assertDoesNotThrow(() -> productFileDAO.deleteProduct(2),
                            "Unexpected exception thrown");
        //Analyze
        assertEquals(result, true);
        assertEquals(productFileDAO.inventory.size(), testProducts.length-1);
    }

    @Test
    public void testCreateProduct() {
        //Invoke
        Product product = new Product(77, "Humfrey", "Frog", "Green", 999, 0.05f, "Contains enough energy to power a small city for 20 years");

        Product results = assertDoesNotThrow(() -> productFileDAO.createProduct(product),
                                "Unexpected exception thrown");
        //Analyze
        assertNotNull(results);
            // the id should be changed from 77 to the next sequential id, 3, in actual and resusts by createProduct
        Product actual = productFileDAO.getProduct(results.getId());
        assertEquals(actual.getId(), 3);
        assertEquals(actual.getName(), product.getName());
    }

    @Test
    public void testSaveException() throws IOException {
        //Invoke
        doThrow(new IOException()).when(mockObjectMapper)
            .writeValue(any(File.class), any(Product[].class));
        
        Product product = new Product(99, "Sprinkle", "weasel", "red", 3, 16, "A friendly crimson weasel.");

        //Analyze
        assertThrows(IOException.class, () -> productFileDAO.createProduct(product),
            "IOException not thrown");
    }

    @Test
    public void testGetProductNotFound() {
        //Invoke
        Product product = productFileDAO.getProduct(54);
        //Analyze
        assertEquals(product, null);
    }

    @Test
    public void testDeleteProductNotFound() {
        //Invoke
        boolean result = assertDoesNotThrow(() -> productFileDAO.deleteProduct(33), "Unexpected exception thrown");
        //Analyze
        assertEquals(result, false);
        assertEquals(productFileDAO.inventory.size(), testProducts.length);
    }

    @Test
    public void testUpdateProduct() {
        //Invoke
        Product product = new Product(1, "Sprinkle", "weasel", "red", 3, 16, "Will steal your keys.");

        Product results = assertDoesNotThrow(() -> productFileDAO.updateProduct(product),
                "Unexpected exception thrown");
        //Analyze
        assertNotNull(results);
        Product actual = productFileDAO.getProduct(product.getId());
        assertEquals(product, actual);
    }

    @Test
    public void testUpdateProductNotFound() {
        //Invoke
        Product product = new Product(44, "Trebol", "Cat", "Tabby", 20, 2.00f, "Likes: politics, Dislikes: The Kardashians"); 

        Product result = assertDoesNotThrow(() -> productFileDAO.updateProduct(product), "Unexpected exception thrown");
        //Analyze
        assertNull(result);
    }

    @Test
    public void testConstructorException() throws IOException {
        ObjectMapper mockObjectMapper = mock(ObjectMapper.class);
        doThrow(new IOException()).when(mockObjectMapper)
            .readValue(new File("doesnt_matter.txt"), Product[].class);

        assertThrows(IOException.class, () -> new ProductFileDAO("doesnt_matter.txt", mockObjectMapper), "IOException not thrown");
    }
}
