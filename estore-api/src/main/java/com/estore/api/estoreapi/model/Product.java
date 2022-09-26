package com.estore.api.estoreapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {
    @JsonProperty("id") private int id;
    @JsonProperty("name") private String name;
    @JsonProperty("species") private String species;
    @JsonProperty("color") private String color;
    @JsonProperty("age") private int age;
    @JsonProperty("price") private float price;
    @JsonProperty("description") private String description;

    public Product(@JsonProperty("id") int id, @JsonProperty("name") String name, @JsonProperty("species") String species, @JsonProperty("color") String color, @JsonProperty("age") int age, @JsonProperty("price") float price, @JsonProperty("description") String description){
        this.id = id;
        this.name = name;
        this.species = species;
        this.color = color;
        this.age = age;
        this.price = price;
        this.description = description;
    }

    public String getName() {return name;}

    public int getId() {return id;}

    public String getSpecies() {return species;}

    public String getColor() {return color;}

    public int getAge() {return age;}

    public float getPrice() {return price;}

    public String getDescription() {return description;}
}
