package com.estore.api.estoreapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.logging.Logger;

public class User {
    private static final Logger LOG = Logger.getLogger(User.class.getName());
    static final String STRING_FORMAT = "User [username=%s, password=%s, isAdmin%s]";
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;
    @JsonProperty("isAdmin")
    private boolean isAdmin;
    @JsonProperty("shoppingcart")
    private ShoppingCart shoppingCart;

    public User(@JsonProperty("username") String username, @JsonProperty("password") String password,
            @JsonProperty("isAdmin") boolean isAdmin, @JsonProperty("shoppingcart") ShoppingCart shoppingCart) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.shoppingCart = shoppingCart;
    }

    public User(@JsonProperty("username") String username, @JsonProperty("password") String password,
            @JsonProperty("isAdmin") boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.shoppingCart = new ShoppingCart();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setOwner(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public ShoppingCart getCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart cart) {
        this.shoppingCart = cart;
    }

    public String toString() {
        return String.format(STRING_FORMAT, username, password, isAdmin);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            User compareUser = (User) obj;
            return compareUser.getUsername().equals(this.username);
        }
        return false;
    }
}
