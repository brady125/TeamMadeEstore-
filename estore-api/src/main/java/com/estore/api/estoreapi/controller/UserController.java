package com.estore.api.estoreapi.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.estore.api.estoreapi.model.User;
import com.estore.api.estoreapi.persistance.UserDAO;

@RestController
@RequestMapping("users")
public class UserController {
    private static final Logger LOG = Logger.getLogger(UserController.class.getName());
    private UserDAO userDAO;

    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username) {
        LOG.info("GET /users/" + username);
        try {
            User user = userDAO.getUser(username);
            if (user != null)
                return new ResponseEntity<>(user, HttpStatus.OK);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("")
    public ResponseEntity<User[]> getUsers(String containsText) {
        LOG.info("GET /users/");
        try {
            User[] users = userDAO.getUsers(containsText);
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    public ResponseEntity<User[]> searchUsers(@RequestParam String username) {
        LOG.info("GET /users/?username=" + username);
        try {
            User[] users = userDAO.getUsers(username);
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/check")
    public ResponseEntity<User> loginUser(@RequestBody User user) {
        LOG.info("POST /users/check");
        try {
            User match = userDAO.login(user.getUsername(), user.getPassword());
            if (match != null) {
                return new ResponseEntity<User>(match, HttpStatus.OK);
            } else {
                LOG.log(Level.SEVERE, "conflict");
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        LOG.info("POST /users/" + user);
        try {
            if(user != null && !userDAO.userExists(user)) {
                User newUser = userDAO.createUser(user);
                return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
            } else {
                LOG.log(Level.SEVERE, "conflict");
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        LOG.info("PUT /users/" + user);
        try {
            user = userDAO.updateUser(user);
            if (user != null)
                return new ResponseEntity<>(user, HttpStatus.OK);

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<User> deleteUser(@PathVariable String username) {
        LOG.info("DELETE /users/" + username);
        try {
            boolean deleted = userDAO.deleteUser(username);
            if (deleted)
                return new ResponseEntity<>(HttpStatus.OK);

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }   
}

