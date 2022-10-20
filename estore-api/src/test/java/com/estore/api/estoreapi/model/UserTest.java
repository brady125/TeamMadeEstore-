package com.estore.api.estoreapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * The unit test suite for the Product class
 */
@Tag("Model-tier")
public class UserTest {
    User user;

    @BeforeEach
    public void setupProduct() {
<<<<<<< HEAD
        Product[] products = new Product[3];
        products[0] = new Product(0, "Orly", "turtle", "green", 7, 20, "A handsome green turtle.");
        products[1] = new Product(1, "Sprinkle", "weasel", "red", 3, 16, "A friendly crimson weasel.");
        products[2] = new Product(2, "George", "monkey", "brown", 5, 60, "A friendly and curious monkey.");

        cart = new ShoppingCart(products);
        user = new User("Book_x_Bro", "BuffBrain");
        user.setShoppingCart(cart);
=======
        user = new User("Book_x_Bro", "BuffBrain", false);
>>>>>>> 399c6452cb57174b6307ca04a6f356471cbe54d9
    }

    @Test
    public void testGetUsername() {
        assertEquals("Book_x_Bro", user.getUsername());
    }

    @Test
    public void testGetPassword() {
        assertEquals("BuffBrain", user.getPassword());
    }

    @Test
    public void testIsAdmin() {
        assert (!user.isAdmin());
    }

<<<<<<< HEAD
=======
    @Test
    public void testSetPassword() {
        user.setPassword("BruhFame9");
        assertEquals("BruhFame9", user.getPassword());
    }

    @Test
    public void testSetOwner() {
        user.setOwner(true);
        assertTrue(user.isAdmin());
    }

>>>>>>> 399c6452cb57174b6307ca04a6f356471cbe54d9
    @Test
    public void testGetCart() {
        List<Integer> checkCart = new ArrayList<>();
        assertEquals(checkCart, user.getCart());
    }

    @Test
    public void testAddToCart() {
        user.addItemToCart(23);
        user.addItemToCart(5);

        List<Integer> checkCart = new ArrayList<>();
        checkCart.add(23);
        checkCart.add(5);

        assertEquals(checkCart, user.getCart());
    }
}
