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

    User getHero(int id) throws IOException;

    User createHero(User user) throws IOException;

    User updateHero(User user) throws IOException;

    boolean deleteUser(int id) throws IOException;
}
