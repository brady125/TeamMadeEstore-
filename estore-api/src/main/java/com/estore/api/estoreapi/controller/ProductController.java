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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

// import com.estore.api.estoreapi.persistence.ProductDAO;
import com.estore.api.estoreapi.persistance.ProductDAO;
import com.estore.api.estoreapi.model.Product;

public class ProductController {
    private static final Logger LOG = Logger.getLogger(ProductController.class.getName());
    private ProductDAO productDAO;

    public ProductController(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @PostMapping("")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        LOG.info("POST /products " + product);

        if (product != null) {
            try {
                Product newProduct = productDAO.createProduct(product);
                return new ResponseEntity<Product>(newProduct, HttpStatus.CREATED);
            } catch (IOException e) {
                LOG.log(Level.SEVERE, e.getLocalizedMessage());
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

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

    @GetMapping("")
    public ResponseEntity<Product[]> getInventory() {
        LOG.info("GET /product/");
        try {
            Product[] inventory = productDAO.getInventory();
            return new ResponseEntity<Product[]>(inventory, HttpStatus.OK);

        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("")
    public ResponseEntity<Product> deleteProduct(@PathVariable int id){

        LOG.info("DELETE /products/" + id);

        try{
            Product product = productDAO.getProduct(id);
            if (product != null){
                deleteProduct(id);
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

}
