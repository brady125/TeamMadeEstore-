package com.estore.api.estoreapi.persistance;

import com.estore.api.estoreapi.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
/**
 * Implements the functionality for JSON file-based persistance for Products
 * @author
 */
@Component
public class ProductFileDAO implements ProductDAO{
    private static final Logger LOG = Logger.getLogger(ProductFileDAO.class.getName());
    Map<Integer, Product> inventory;
    
    private ObjectMapper objectMapper;
    private static int nextId;
    private String filename;

    /**
     * Creates a product File Data Acess Object
     * 
     * @param filename to read and write to
     * @param objectMapper that controls JSON serialization and deserialization
     * @throws IOException when file cannot be read
     */
    public ProductFileDAO(@Value("${inventory.file}") String filename, ObjectMapper objectMapper) throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;
        load();
    }
    
    /**
     * Generates the next id for new products
     *
     * @return next id
     */
    private synchronized static int nextId() {
        int id = nextId;
        nextId++;
        return id;
    }

    /**
     * Generates an array of {@link Product products} from the inventory tree map.
     * Only includes products with names containing the given string. If containsText is
     * left empty, the function will generate an array with every product from the inventory map
     * 
     * @param containsText the text to compare (not case sensitive)
     * 
     * @return an array of {@link Product products} with names containing the given string
     */
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
        return inventoryArray;
    }

    /**
     * Saves all products in the inventory map to a JSON file at the destination set in filename
     * 
     * @return true on succesful save
     * @throws IOException
     */
    private boolean save() throws IOException {
        Product[] inventoryArray = getInventoryArray(null);

        objectMapper.writeValue(new File(filename), inventoryArray);
        return true;
    }
    
    /**
     * Loads all products from a JSON file into the inventory map from the destination set in filename
     * 
     * @return true on successful load
     * @throws IOException
     */
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

    /**
     ** {@inheritDoc} 
     */
    @Override
    public Product[] getInventory() {
        synchronized(inventory){
            return getInventoryArray(null);
        }
    }

    /**
     ** {@inheritDoc} 
     */
    @Override
    public Product[] findProducts(String containsText) {
        synchronized(inventory) {
            return getInventoryArray(containsText);
        }
    }

    /**
     ** {@inheritDoc} 
     */
    @Override
    public Product getProduct(int id){
        synchronized(inventory){
            return inventory.getOrDefault(id, null);
        }
    }

    /**
     ** {@inheritDoc} 
     */
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

    /**
     ** {@inheritDoc} 
     */
    @Override
    public Product updateProduct(Product product) throws IOException {
        synchronized(inventory) {
            if(!inventory.containsKey(product.getId()))
                return null;
            
            inventory.put(product.getId(), product);
            save(); // may throw an IOException
            return product;
        }
    }

    /**
     ** {@inheritDoc} 
     */
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

