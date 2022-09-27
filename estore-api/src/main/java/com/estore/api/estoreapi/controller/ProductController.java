package com.estore.api.estoreapi.controller;

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

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.estore.api.estoreapi.persistance.ProductDAO;
import com.estore.api.estoreapi.model.Product;

/**
 * Handles the REST API requests for the Product resource
 * @author
 */

@RestController
@RequestMapping("products")
public class ProductController {
    private static final Logger LOG = Logger.getLogger(ProductController.class.getName());
    private ProductDAO productDAO;

    /**
     * Creates a REST API controller to respond to requests
     * 
     * @param productDAO the {@link ProductDAO Product Data Access Object} to perform CRUD operations
     */
    public ProductController(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }


    /**
     * Responds to the GET request for a {@linkplain Product product} with the given id
     * 
     * @param id associated with the given {@linkplain Product product}
     * @return ResponseEntity with {@linkplain Product product} object and HTTP status of OK<br>
     * Returns NOT_FOUND if {@linkplain Product product} doesn't exists<br>
     * Returns INTERNAL_SERVER_ERROR otherwise
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id) {
        LOG.info("GET /products/" + id);
        
        try {
            Product gotProduct = productDAO.getProduct(id);
            if (gotProduct != null) {
                return new ResponseEntity<Product>(gotProduct, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * Responds to the GET request for all {@linkplain Product product}
     * 
     * @return ResponseEntity with an array of {@linkplain Product product} objects (may be empty) and HTTP status of OK<br>
     * Returns INTERNAL_SERVER_ERROR otherwise
     */
    @GetMapping("")
    public ResponseEntity<Product[]> getInventory() {
        LOG.info("GET /product");
        try {
            Product[] inventory = productDAO.getInventory();
            return new ResponseEntity<Product[]>(inventory, HttpStatus.OK);
            
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * Responds to the GET request for all {@linkplain Product products} whose name contains
     * the text in name
     * 
     * @param name The name parameter which contains the text used to find the {@link Product products}
     * 
     * @return ResponseEntity with array of {@link Product products} objects (may be empty) and
     * HTTP status of OK<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @GetMapping("/")
    public ResponseEntity<Product[]> searchProducts(@RequestParam String name) {
        LOG.info("GET /products/?name="+name);
        try {
            Product[] products = productDAO.findProducts(name);
            return new ResponseEntity<Product[]>(products, HttpStatus.OK);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
        /**
         * Creates a {@linkplain Product product} with the provided product object
         * 
         * @param product that will be created
         * @return ResponseEntity with created {@linkplain Product product} and HTTP status of CREATED<br>
         * Returns CONFLICT if {@linkplain Product product} already exists<br>
         * Returns INTERNAL_SERVER_ERROR otherwise
         */
        @PostMapping("")
        public ResponseEntity<Product> createProduct(@RequestBody Product product) {
            LOG.info("POST /products " + product);
            try {
                Product newProduct = productDAO.createProduct(product);
                if(newProduct != null)
                    return new ResponseEntity<Product>(newProduct, HttpStatus.CREATED);
                else
                    return new ResponseEntity<>(HttpStatus.CONFLICT);
    
            } catch (IOException e) {
                LOG.log(Level.SEVERE, e.getLocalizedMessage());
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    
    /**
     * Deletes a {@linkplain Product product} with the given id
     * 
     * @param id of the {@linkplain Product product} in question 
     * @return ResponseEntity HTTP status of OK if deleted<br>
     * Returns NOT_FOUND if not found<br>
     * Returns INTERNAL_SERVER_ERROR otherwise
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable int id){
        LOG.info("DELETE /products/" + id);

        try{
            boolean productDeleted = productDAO.deleteProduct(id);
            if (productDeleted){
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Updates the {@linkplain Product product} with the provided {@linkplain Product product} object, if it exists
     * 
     * @param product that will be updated
     * 
     * @return ResponseEntity with updated {@linkplain Product product} and HTTP status of OK if updated<br>
     * Returns NOT_FOUND if not found<br>
     * Returns INTERNAL_SERVER_ERROR otherwise
     */
    @PutMapping("")
    public ResponseEntity<Product[]> updateProduct(@RequestParam Product product) {
        LOG.info("PUT /products" + product);
        Product returnProduct;
        try {
            returnProduct = productDAO.updateProduct(product);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(returnProduct != null){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
