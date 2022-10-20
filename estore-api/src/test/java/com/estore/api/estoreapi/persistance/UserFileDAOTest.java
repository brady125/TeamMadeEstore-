package com.estore.api.estoreapi.persistance;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doThrow;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.estore.api.estoreapi.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserFileDAOTest {
    UserFileDAO userFileDAO;
    User[] testUsers;
    ObjectMapper mockObjectMapper;

    /**
     * Before each test, we will create and inject a Mock Object Mapper to
     * isolate the tests from the underlying file
     * 
     * @throws IOException
     */
    @BeforeEach
    public void setupUserFileDAO() throws IOException {
        mockObjectMapper = mock(ObjectMapper.class);
        testUsers = new User[3];
        testUsers[0] = new User("SupJathan32", "wonkywoo23");
        testUsers[1] = new User("MadDoggy", "bon333s");
        testUsers[2] = new User("SuperPro123", "password123");

        // When the object mapper is supposed to read from the file
        // the mock object mapper will return the User array above
        when(mockObjectMapper
                .readValue(new File("dont_matter.txt"), User[].class))
                .thenReturn(testUsers);
        userFileDAO = new UserFileDAO("dont_matter.txt", mockObjectMapper);
    }

    @Test
    public void testGetUsers() {
        User[] users = userFileDAO.getUsers(null);

        assertEquals(users.length, testUsers.length);
        // all users seem to be there just out of order, so comparing each user is way
        // more of a pain than in userfiledao
    }

    @Test
    public void testFindUsersName() {
        // Invoke
        User[] Users = userFileDAO.getUsers("Su");

        // Analyze
        assertEquals(Users.length, 2);
        assertEquals(Users[0], testUsers[0]);
        assertEquals(Users[1], testUsers[2]);
    }

    @Test
    public void testGetUser() {
        // Invoke
        User User = userFileDAO.getUser("SuperPro123");
        // Analyze
        assertEquals(testUsers[2], User);
    }

    @Test
    public void testDeleteUser() {
        // Invoke
        boolean result = assertDoesNotThrow(() -> userFileDAO.deleteUser("MadDoggy"),
                "Unexpected exception thrown");
        // Analyze
        assertTrue(result);
        assertEquals(userFileDAO.users.size(), testUsers.length - 1);
    }

    @Test
    public void testCreateUser() {
        // Invoke
        User User = new User("Teensy", "V3rySm4ll");

        User results = assertDoesNotThrow(() -> userFileDAO.createUser(User),
                "Unexpected exception thrown");
        // Analyze
        assertNotNull(results);
        // the id should be changed from 77 to the next sequential id, 3, in actual and
        // resusts by createUser
        User actual = userFileDAO.getUser(results.getUsername());
        assertEquals(actual.getUsername(), "Teensy");
        assertEquals(actual.getPassword(), User.getPassword());
    }

    @Test
    public void testSaveException() throws IOException {
        //Invoke
        doThrow(new IOException()).when(mockObjectMapper)
            .writeValue(any(File.class), any(User[].class));
        
        User User = new User("SuperPro123", "password123");

        //Analyze
        assertThrows(IOException.class, () -> userFileDAO.createUser(User),
            "IOException not thrown");
    }

    @Test
    public void testGetUserNotFound() {
        // Invoke
        User User = userFileDAO.getUser("M_R_Bones");
        // Analyze
        assertNull(User);
    }

    @Test
    public void testDeleteUserNotFound() {
        // Invoke
        boolean result = assertDoesNotThrow(() -> userFileDAO.deleteUser("M_R_S_Bones"), "Unexpected exception thrown");
        // Analyze
        assertFalse(result);
        assertEquals(userFileDAO.users.size(), testUsers.length);
    }

    @Test
    public void testUpdateUser() {
        User User = new User("SupJathan32", "wonkywoo23");
        
        User results = assertDoesNotThrow(() -> userFileDAO.updateUser(User),
                "Unexpected exception thrown");
        // Analyze
        assertNotNull(results);
        User actual = userFileDAO.getUser(User.getUsername());
        assertEquals(User, actual);
    }

    @Test
    public void testUpdateUserNotFound() {
    
        // Invoke
        User User = new User("M_R_Bones", "Sk3llington");

        User result = assertDoesNotThrow(() -> userFileDAO.updateUser(User), "Unexpected exception thrown");
        // Analyze
        assertNull(result);
    }

    @Test
    public void testConstructorException() throws IOException {
        ObjectMapper mockObjectMapper = mock(ObjectMapper.class);
        doThrow(new IOException()).when(mockObjectMapper)
            .readValue(new File("doesnt_matter.txt"), User[].class);

        assertThrows(IOException.class, () -> new UserFileDAO("doesnt_matter.txt", mockObjectMapper), "IOException not thrown");
    }

    @Test
    public void testLoginCorrect() throws IOException {
        User user = testUsers[0];
        User result = userFileDAO.login(user.getUsername(), user.getPassword());
        assertEquals(user, result);
    }

    @Test
    public void testLoginWrongPassword() throws IOException {
        User user = testUsers[0];
        User result = userFileDAO.login(user.getUsername(), "wrongpassord");
        assertEquals(null, result);
    }

    @Test
    public void testLoginWrongUsername() throws IOException {
        User result = userFileDAO.login("notanexistinguser", "wrongpassord");
        assertEquals(null, result);
    }
}
