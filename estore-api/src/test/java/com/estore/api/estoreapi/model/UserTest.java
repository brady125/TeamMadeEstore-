package com.estore.api.estoreapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * The unit test suite for the Product class
 */
@Tag("Model-tier")
public class UserTest {
    User user;
    ShoppingCart cart;

    @BeforeEach
    public void setupProduct() {
        Product[] products = new Product[3];
        products[0] = new Product(0, "Orly", "turtle", "green", 7, 20, "A handsome green turtle.");
        products[1] = new Product(1, "Sprinkle", "weasel", "red", 3, 16, "A friendly crimson weasel.");
        products[2] = new Product(2, "George", "monkey", "brown", 5, 60, "A friendly and curious monkey.");

        cart = new ShoppingCart(products);
        user = new User("Book_x_Bro", "BuffBrain", false, cart);
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
        assert(!user.isAdmin());
    }
    @Test
    public void testGetCart() {
        assertEquals(cart, user.getCart());
    }
}