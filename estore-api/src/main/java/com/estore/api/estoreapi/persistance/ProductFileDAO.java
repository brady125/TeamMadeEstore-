package com.estore.api.estoreapi.persistance;

import com.estore.api.estoreapi.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class ProductFileDAO implements ProductDAO{
    private static final Logger LOG = Logger.getLogger(ProductFileDAO.class.getName());
    Map<Integer, Product> inventory;
    
    private ObjectMapper objectMapper;
    private static int nextId;
    private String filename;

    
    public ProductFileDAO(@Value("${inventory.file}") String filename, ObjectMapper objectMapper) throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;
        load();
    }
    
    private synchronized static int nextId() {
        int id = nextId;
        nextId++;
        return id;
    }

    private Product[] getInventoryArray(String containsText){
        ArrayList<Product> inventoryList = new ArrayList<>();
        for(Product product : inventory.values()) {
            // gets all the fields but price and puts them into a string called fields
            // that can be checked for the search text all at once
            String[] fieldsArray = {
                product.getName().toLowerCase(),
                product.getSpecies().toLowerCase(),
                product.getColor().toLowerCase(),
                String.valueOf(product.getAge()),
                product.getDescription().toLowerCase()
            };
            String fields = String.join(" ", fieldsArray);
            // checks the fields of the product for the search text
            if (containsText == null || fields.contains(containsText.toLowerCase())) {
                inventoryList.add(product);
            }
        }
        Product[] inventoryArray = new Product[inventoryList.size()];
        inventoryList.toArray(inventoryArray);
        System.out.println(inventoryList.size() + " ||| " + inventoryArray + " ||| " + inventoryList);
        return inventoryArray;
    }

    private boolean save() throws IOException {
        Product[] inventoryArray = getInventoryArray(null);

        objectMapper.writeValue(new File(filename), inventoryArray);
        return true;
    }
    
    private boolean load() throws IOException{
        inventory = new TreeMap<>();
        nextId = 0;

        Product[] inventoryArray = objectMapper.readValue(new File(filename), Product[].class);

        for(Product product : inventoryArray) {
            inventory.put(product.getId(), product);
            if(product.getId() > nextId){
                nextId = product.getId();
            }
        }
        ++nextId;
        return true;
    }

    @Override
    public Product[] getInventory() {
        synchronized(inventory){
            return getInventoryArray(null);
        }
    }

    @Override
    public Product[] findProducts(String containsText) {
        synchronized(inventory) {
            return getInventoryArray(containsText);
        }
    }

    @Override
    public Product getProduct(int id){
        synchronized(inventory){
            return inventory.getOrDefault(id, null);
        }
    }

    @Override
    public Product createProduct(Product product) throws IOException {
        synchronized(inventory) {
            Product newProduct = new Product(nextId(), product.getName(), product.getSpecies(), product.getColor(),
                    product.getAge(), product.getPrice(), product.getDescription());
            inventory.put(newProduct.getId(), newProduct);
            save();
            return newProduct;
        }
    }

    @Override
    public Product updateProduct(Product product) throws IOException {
        synchronized(inventory) {
            if(inventory.containsKey(product.getId()) == false)
                return null;
            
            inventory.put(product.getId(), product);
            save();
            return product;
        }
    }

    @Override
    public Boolean deleteProduct(int id) throws IOException {
        synchronized(inventory) {
            if(inventory.containsKey(id)) {
                inventory.remove(id);
                return save();
            }
            else
                return false;
        }
    }
}

