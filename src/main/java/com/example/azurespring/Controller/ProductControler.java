package com.example.azurespring.Controller;

import com.example.azurespring.Modle.Product;
import com.example.azurespring.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:3000")
    @RestController
    @RequestMapping("/products")
    @RequiredArgsConstructor

    public class ProductControler {
        private final ProductService productService;

        @GetMapping
        public ResponseEntity<List<Product>> getAll() {
            return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
        }

        //get by id
        @GetMapping("{id}")
        public ResponseEntity<Product> getById(@PathVariable Long id) {
            if(id==null || id <=0) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
        }
        //creat
        @PostMapping
        public ResponseEntity<Product> save(@RequestBody Product product) {
            return new ResponseEntity<>(productService.createProduct(product), HttpStatus.CREATED);
        }
        //edit
        @PutMapping("{id}")
        public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product product) {
            if(id==null || id <=0) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(productService.updateProduct(id, product), HttpStatus.OK);
        }
        //delete
        @DeleteMapping("{id}")
        public ResponseEntity<?> del(@PathVariable Long id) {
            if(id==null || id <=0) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            productService.deleteProduct(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        //find by keyword
        @GetMapping(params = "keyword")
        public ResponseEntity<List<Product>> findByName(@RequestParam String keyword) {
            return new ResponseEntity<>(productService.findByName(keyword), HttpStatus.OK);
        }
    }


