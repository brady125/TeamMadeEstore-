package com.estore.api.estoreapi;

import java.io.IOException;
import com.estore.api.estoreapi.model.User;

/**
 * Defines the interface for Hero object persistence
 * 
 * @author SWEN Faculty
 */
public interface UserDAO {
    User[] getUsers() throws IOException;

    User[] findUsers(String containsText) throws IOException; // idk if we need this

    User getUser(String username) throws IOException;

    User createUser(User user) throws IOException;

    User updateUser(User user) throws IOException;

    boolean deleteUser(String username) throws IOException;

    boolean userExists(User user) throws IOException;
}
