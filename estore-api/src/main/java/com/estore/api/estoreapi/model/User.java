package com.estore.api.estoreapi.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    static final String STRING_FORMAT = "User [username=%s, password=%s, isAdmin%s]";
    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    @JsonProperty("isAdmin")
    private boolean isAdmin;
    
    @JsonProperty("shoppingcart")
    private List<Integer> shoppingCart;

    public User(@JsonProperty("username") String username, @JsonProperty("password") String password) {
        this.username = username;
        this.password = password;
        this.shoppingCart = new ArrayList<>();
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

    public List<Integer> getCart() {
        return this.shoppingCart;
    }

    public void addItemToCart(int productID) {
        this.shoppingCart.add(productID);
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
