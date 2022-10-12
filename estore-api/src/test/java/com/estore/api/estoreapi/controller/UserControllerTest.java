package com.estore.api.estoreapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import com.estore.api.estoreapi.model.User;
import com.estore.api.estoreapi.persistance.UserDAO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Tag("Controller-tier")
public class UserControllerTest {
    private UserController uc;

    private UserDAO userMockDAO;

    @BeforeEach
    public void setupUserController() {
        userMockDAO = mock(UserDAO.class);
        uc = new UserController(userMockDAO);
    }

    /*****************************************************************
     * TESTS FOR ALL CONTROLLER METHODS
     ****************************************************************/

    /* ********************* GET PRODUCT ************************** */

    @Test
    public void testGetUser() throws IOException {  // getUser may throw IOException
        // Setup
        User user = new User("PassiveInspector", "193alamoIDK", false);
        // When the same id is passed in, our mock User DAO will return the User
        // object
        when(userMockDAO.getUser(user.getUsername())).thenReturn(user);

        // Invoke
        ResponseEntity<User> response = uc.getUser(user.getUsername());

        // Analyze
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    public void testGetUserNotFound() throws Exception { // createProduct may throw IOException
        // Setup
        String username = "Gonzo";
        // When the same id is passed in, our mock User DAO will return null, simulating
        // no User found
        when(userMockDAO.getUser(username)).thenReturn(null);

        // Invoke
        ResponseEntity<User> response = uc.getUser(username);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetUserHandleException() throws Exception { // createProduct may throw IOException
        // Setup
        String username = "Gonzo";
        // When getUser is called on the Mock User DAO, throw an IOException
        doThrow(new IOException()).when(userMockDAO).getUser(username);

        // Invoke
        ResponseEntity<User> response = uc.getUser(username);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    /* ********************* CREATE PRODUCT ************************** */

    @Test
    public void testCreateProduct() throws IOException { // createProduct may throw IOException
        // Setup
        User user = new User("Goosemaster", "H0nkH0nk", false);
        // when createProduct is called, return true simulating successful
        // creation and save
        when(userMockDAO.createUser(user)).thenReturn(user);

        // Invoke
        ResponseEntity<User> response = uc.createUser(user);

        // Analyze
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    public void testCreateProductFailed() throws IOException { // createProduct may throw IOException
        // Setup
        User user = new User("Zerma", "589", false);
        // when createProduct is called, return false simulating failed
        // creation and save
        when(userMockDAO.userExists(user)).thenReturn(true);

        // Invoke
        ResponseEntity<User> response = uc.createUser(user);

        // Analyze
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void testCreateProductHandleException() throws IOException { // createProduct may throw IOException
        // Setup
        User user = new User("LiterallyTheJoker", "WowCrazy", false);

        // When createProduct is called on the Mock User DAO, throw an IOException
        doThrow(new IOException()).when(userMockDAO).createUser(user);

        // Invoke
        ResponseEntity<User> response = uc.createUser(user);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    /* ********************* GET PRODUCT INVENTORY ************************** */

    @Test
    public void testGetInventory() throws IOException { // createProduct may throw IOException
        // Setup
        User[] users = new User[3];
        users[0] = new User("Goosemaster", "H0nkH0nk", false);
        users[1] = new User("Zerma", "589", false);
        users[2] = new User("LiterallyTheJoker", "WowCrazy", false);

        // When getInventory is called return the Products created above
        when(userMockDAO.getUsers()).thenReturn(users);

        // Invoke
        ResponseEntity<User[]> response = uc.getUsers();

        // Analyze
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(users, response.getBody());
    }

    @Test
    public void testGetInventoryHandleException() throws IOException { // getUsers may throw IOException
        // Setup
        // When getInventory is called on the Mock User DAO, throw an IOException
        doThrow(new IOException()).when(userMockDAO).getUsers();

        // Invoke
        ResponseEntity<User[]> response = uc.getUsers();

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    /* ********************* SEARCH PRODUCTS ************************** */

    @Test
    public void testSearchUsers() throws IOException {
        // Setup
        String searchString = "ly";
        User[] users = new User[2];
        users[0] = new User("shortly", "goop", false);
        users[1] = new User("sheetly", "glop", false);
        // When findProducts is called with the search string, return the two
        /// products above
        when(userMockDAO.findUsers(searchString)).thenReturn(users);

        // Invoke
        ResponseEntity<User[]> response = uc.searchUsers(searchString);

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(users,response.getBody());
    }

    @Test
    public void testSearchProductsNoProducts() throws IOException {
        // Setup
        String searchString = "ly";
        User[] users = new User[2];
        // When findProducts is called with the search string, return the two
        /// products above
        when(userMockDAO.findUsers(searchString)).thenReturn(users);

        // Invoke
        ResponseEntity<User[]> response = uc.searchUsers(searchString);

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(users,response.getBody());
    }

    @Test
    public void testSearchProductsHandleException() throws IOException { // findProducts may throw IOException
        // Setup
        String searchString = "an";
        // When findProducts is called on the Mock User DAO, throw an IOException
        doThrow(new IOException()).when(userMockDAO).findUsers(searchString);

        // Invoke
        ResponseEntity<User[]> response = uc.searchUsers(searchString);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    /* ********************* DELETE PRODUCT ************************** */

    @Test
    public void testDeleteProduct() throws IOException {
        // Setup
        String username = "MegaLad";
        when(userMockDAO.deleteUser(username)).thenReturn(true);

        // Invoke
        ResponseEntity<User> response = uc.deleteUser(username);

        // Analyze
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testDeleteProductNotFound() throws IOException {
        // Setup

        String username = "GlupSitto";
        when(userMockDAO.deleteUser(username)).thenReturn(false);

        // Invoke
        ResponseEntity<User> response = uc.deleteUser(username);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testDeleteProductHandleException() throws IOException {

        // Setup
        String username = "GlupSitto";
        doThrow(new IOException()).when(userMockDAO).deleteUser(username);

        // Invoke
        ResponseEntity<User> response = uc.deleteUser(username);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    /* ********************* UPDATE PRODUCT ************************** */

    @Test
    public void testUpdateProduct() throws IOException { // updateProduct may throw IOException
        // Setup
        User user = new User("PantsAndSocks", "DresserDrawer", false);
        // when updateProduct is called, return true simulating successful
        // update and save
        when(userMockDAO.updateUser(user)).thenReturn(user);

        // Invoke
        user.setUsername("Molly");
        ResponseEntity<User> response = uc.updateUser(user);

        // Analyze
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    public void testUpdateProductFailed() throws IOException { // updateProduct may throw IOException
        // Setup
        User user = new User("noname", "IgotNoName", false);
        // when updateProduct is called, return true simulating successful
        // update and save
        when(userMockDAO.updateUser(user)).thenReturn(null);

        // Invoke
        ResponseEntity<User> response = uc.updateUser(user);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void testUpdateProductHandleException() throws IOException { // updateProduct may throw IOException
        // Setup
        User user = new User("noname", "IgotNoName", false);
        // When updateProduct is called on the userMockDAO, throw an IOException
        doThrow(new IOException()).when(userMockDAO).updateUser(user);

        // Invoke
        ResponseEntity<User> response = uc.updateUser(user);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

}

