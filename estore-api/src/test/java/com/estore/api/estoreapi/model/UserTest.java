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
        user = new User("Book_x_Bro", "BuffBrain", false);
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
