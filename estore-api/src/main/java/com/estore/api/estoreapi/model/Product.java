package com.estore.api.estoreapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a product in the webstore
 * 
 * @author
 */
public class Product {
    @JsonProperty("id") private int id;
    @JsonProperty("name") private String name;
    @JsonProperty("species") private String species;
    @JsonProperty("color") private String color;
    @JsonProperty("age") private int age;
    @JsonProperty("price") private float price;
    @JsonProperty("description") private String description;

    /**
     * Create a new product object with the given values
     * @param id the position the product will be stored at
     * @param name of the animal
     * @param species of animal
     * @param color of the animal
     * @param age of the animal
     * @param price as a float value
     * @param description of the animal
     */
    public Product(@JsonProperty("id") int id, @JsonProperty("name") String name, @JsonProperty("species")
    String species, @JsonProperty("color") String color, @JsonProperty("age") int age, @JsonProperty("price")
    float price, @JsonProperty("description") String description){
        this.id = id;
        this.name = name;
        this.species = species;
        this.color = color;
        this.age = age;
        this.price = price;
        this.description = description;
    }

    /**
     * Gets the name of the product
     * @return name as a string
     */
    public String getName() {return name;}

    /**
     * Gets the id of the product
     * @return id as an int
     */
    public int getId() {return id;}

    /**
     * Gets the species of the product
     * @return species as a string
     */
    public String getSpecies() {return species;}

    /**
     * Gets the color of the product
     * @return color as a string
     */
    public String getColor() {return color;}

    /**
     * Gets the age of the product
     * @return age as an int
     */
    public int getAge() {return age;}

    /**
     * Gets the price of the product
     * @return price as a float
     */
    public float getPrice() {return price;}

    /**
     * Gets the description of the product
     * @return description as a string
     */
    public String getDescription() {return description;}

    /**
     * Sets a new name specified in the parameter string
     * @param newName The new name to change to
     */
    public void setName(String newName){ this.name = newName; }

    /**
     * Sets a new species specified in the parameter string
     * @param newSpec The new species to change to
     */
    public void setSpecies(String newSpec){ this.species = newSpec; }

    /**
     * Sets a new color specified in the parameter string
     * @param newColor The new color to change to
     */
    public void setColor(String newColor){ this.color = newColor; }

    /**
     * Sets a new age specified in the parameter string
     * @param newAge The new age to change to
     */
    public void setAge(int newAge){ this.age = newAge; }

    /**
     * Sets a new price specified in the parameter string
     * @param newPrice The new price to change to
     */
    public void setPrice(float newPrice){ this.price = newPrice; }

    /**
     * Sets a new description specified in the parameter string
     * @param newDesc The new description to change to
     */
    public void setDescription(String newDesc){ this.description = newDesc; }

}
