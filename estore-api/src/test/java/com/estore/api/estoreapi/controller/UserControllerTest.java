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

    /* ********************* GET USER ************************** */

    @Test
    public void testGetUser() throws IOException {  // getUser may throw IOException
        // Setup
        User user = new User("PassiveInspector", "193alamoIDK");
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
    public void testGetUserNotFound() throws Exception { // createUser may throw IOException
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
    public void testGetUserHandleException() throws Exception { // createUser may throw IOException
        // Setup
        String username = "Gonzo";
        // When getUser is called on the Mock User DAO, throw an IOException
        doThrow(new IOException()).when(userMockDAO).getUser(username);

        // Invoke
        ResponseEntity<User> response = uc.getUser(username);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    /* ********************* CREATE USER ************************** */

    @Test
    public void testCreateUser() throws IOException { // createUser may throw IOException
        // Setup
        User user = new User("Goosemaster", "H0nkH0nk");
        // when createUser is called, return true simulating successful
        // creation and save
        when(userMockDAO.createUser(user)).thenReturn(user);

        // Invoke
        ResponseEntity<User> response = uc.createUser(user);

        // Analyze
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    public void testCreateUserFailed() throws IOException { // createUser may throw IOException
        // Setup
        User user = new User("Zerma", "589");
        // when createUser is called, retur simulating failed
        // creation and save
        when(userMockDAO.userExists(user)).thenReturn(true);

        // Invoke
        ResponseEntity<User> response = uc.createUser(user);

        // Analyze
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void testCreateUserHandleException() throws IOException { // createUser may throw IOException
        // Setup
        User user = new User("LiterallyTheJoker", "WowCrazy");

        // When createUser is called on the Mock User DAO, throw an IOException
        doThrow(new IOException()).when(userMockDAO).createUser(user);

        // Invoke
        ResponseEntity<User> response = uc.createUser(user);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    /* ********************* GET USERS ************************** */

    @Test
    public void testGetUsers() throws IOException { // createUser may throw IOException
        // Setup
        User[] users = new User[3];
        users[0] = new User("Goosemaster", "H0nkH0nk");
        users[1] = new User("Zerma", "589");
        users[2] = new User("LiterallyTheJoker", "WowCrazy");

        // When getUsers is called return the Users created above
        when(userMockDAO.getUsers(null)).thenReturn(users);

        // Invoke
        ResponseEntity<User[]> response = uc.getUsers(null);

        // Analyze
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(users, response.getBody());
    }

    @Test
    public void testGetUsersHandleException() throws IOException { // getUsers may throw IOException
        // Setup
        // When getUsers is called on the Mock User DAO, throw an IOException
        doThrow(new IOException()).when(userMockDAO).getUsers(null);

        // Invoke
        ResponseEntity<User[]> response = uc.getUsers(null);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    /* ********************* SEARCH USERS ************************** */

    @Test
    public void testSearchUsers() throws IOException {
        // Setup
        String searchString = "ly";
        User[] users = new User[2];
        users[0] = new User("shortly", "goop");
        users[1] = new User("sheetly", "glop");
        // When getUsers is called with the search string, return the two
        /// users above
        when(userMockDAO.getUsers(searchString)).thenReturn(users);

        // Invoke
        ResponseEntity<User[]> response = uc.searchUsers(searchString);

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(users,response.getBody());
    }

    @Test
    public void testSearchUsersNoUsers() throws IOException {
        // Setup
        String searchString = "ly";
        User[] users = new User[2];
        // When findUsers is called with the search string, return the two
        /// users above
        when(userMockDAO.getUsers(searchString)).thenReturn(users);

        // Invoke
        ResponseEntity<User[]> response = uc.searchUsers(searchString);

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(users,response.getBody());
    }

    @Test
    public void testSearchUsersHandleException() throws IOException { // findUsers may throw IOException
        // Setup
        String searchString = "an";
        // When findUsers is called on the Mock User DAO, throw an IOException
        doThrow(new IOException()).when(userMockDAO).getUsers(searchString);

        // Invoke
        ResponseEntity<User[]> response = uc.searchUsers(searchString);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    /* ********************* DELETE USER ************************** */

    @Test
    public void testDeleteUser() throws IOException {
        // Setup
        String username = "MegaLad";
        when(userMockDAO.deleteUser(username)).thenReturn(true);

        // Invoke
        ResponseEntity<User> response = uc.deleteUser(username);

        // Analyze
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testDeleteUserNotFound() throws IOException {
        // Setup

        String username = "GlupSitto";
        when(userMockDAO.deleteUser(username)).thenReturn(false);

        // Invoke
        ResponseEntity<User> response = uc.deleteUser(username);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testDeleteUserHandleException() throws IOException {

        // Setup
        String username = "GlupSitto";
        doThrow(new IOException()).when(userMockDAO).deleteUser(username);

        // Invoke
        ResponseEntity<User> response = uc.deleteUser(username);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    /* ********************* UPDATE USER ************************** */

    @Test
    public void testUpdateUser() throws IOException { // updateUser may throw IOException
        // Setup
        User user = new User("PantsAndSocks", "DresserDrawer");
        // when updateUser is called, return true simulating successful
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
    public void testUpdateUserFailed() throws IOException { // updateUser may throw IOException
        // Setup
        User user = new User("noname", "IgotNoName");
        // when updateUser is called, return true simulating successful
        // update and save
        when(userMockDAO.updateUser(user)).thenReturn(null);

        // Invoke
        ResponseEntity<User> response = uc.updateUser(user);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void testUpdateUserHandleException() throws IOException { // updateUser may throw IOException
        // Setup
        User user = new User("noname", "IgotNoName");
        // When updateUser is called on the userMockDAO, throw an IOException
        doThrow(new IOException()).when(userMockDAO).updateUser(user);

        // Invoke
        ResponseEntity<User> response = uc.updateUser(user);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }
}

