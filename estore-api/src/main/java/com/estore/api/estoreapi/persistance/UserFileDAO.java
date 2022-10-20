package com.estore.api.estoreapi.persistance;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.estore.api.estoreapi.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserFileDAO implements UserDAO {
    private static final Logger LOG = Logger.getLogger(UserFileDAO.class.getName());
    Map<String, User> users;
    private ObjectMapper objectMapper;

    private String filename; // The filename that contains the users

    public UserFileDAO(@Value("${users.file}") String filename, ObjectMapper objectMapper) throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;
        load();
    }

    private boolean load() throws IOException {
        users = new TreeMap<>();
        User[] userArray = objectMapper.readValue(new File(filename), User[].class);
        for (User user : userArray) {
            users.put(user.getUsername(), user);
        }
        return true;
    }

    private boolean save() throws IOException {
        User[] userArray = getUsersArray();
        objectMapper.writeValue(new File(filename), userArray);
        return true;
    }

    private User[] getUsersArray() {
        return getUsersArray(null);
    }

    private User[] getUsersArray(String containsText) {
        ArrayList<User> userArrayList = new ArrayList<>();
        for (User user : users.values()) {
            if (containsText == null || user.getUsername().contains(containsText)) {
                userArrayList.add(user);
            }
        }

        User[] userArray = new User[userArrayList.size()];
        userArrayList.toArray(userArray);
        return userArray;
    }

    @Override
    public User[] getUsers(String containsText) {
        synchronized (users) {
            return getUsersArray(containsText);
        }
    }

    @Override
    public User getUser(String username) {
        synchronized (users) {
            return users.getOrDefault(username, null);
        }
    }

    @Override
    public User createUser(User user) throws IOException {
        synchronized (users) {
            User newUser = new User(user.getUsername(), user.getPassword());
            users.put(newUser.getUsername(), newUser);
            save();
            return newUser;
        }
    }

    @Override
    public User updateUser(User user) throws IOException {
        synchronized (users) {
            if (!users.containsKey(user.getUsername())) {
                return null;
            } else {
                users.put(user.getUsername(), user);
                save();
                return user;
            }
        }
    }

    @Override
    public boolean deleteUser(String username) throws IOException {
        synchronized (users) {
            if (users.containsKey(username)) {
                users.remove(username);
                return save();
            } else {
                return false;
            }
        }
    }

    @Override
    public boolean userExists(User user) throws IOException {
        User[] usersArray = getUsersArray();
        boolean userDoesExist = false;
        for (User tempUser : usersArray) {
            if (tempUser.equals(user)) {
                userDoesExist = true;
                break;
            }
        }
        return userDoesExist;
    }

    /*
     * Checks that the given password is correct for the account with the given username.
     * If no account exists under that username or the password doesn't match, returns false.
     * If the password given matches the one stored, returns true.
     */
    @Override
    public User login(String username, String password) throws IOException {
        User user = this.getUser(username);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}
