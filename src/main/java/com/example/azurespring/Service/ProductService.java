package com.example.azurespring.Service;



import com.example.azurespring.Modle.Product;
import com.example.azurespring.Repo.ProductRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepo productRepo;

    //getALL
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    //getByID
    public Product getProductById(Long id) {
        return productRepo.findById(id).orElseThrow(() -> new IllegalStateException("Product with id " + id + " does not exist"));
    }
    //delete
    public void deleteProduct(Long id) {
        productRepo.deleteById(id);
    }
    //create
    public Product createProduct(Product product) {
        return productRepo.save(product);
    }
    //update
    public Product updateProduct(Long id, Product product) {
        productRepo.findById(id).orElseThrow(() -> new IllegalStateException("Product with id " + id + " does not exist"));
        return productRepo.save(product);
    }
    //find by keyword
    public List<Product> findByName(String keyword) {
        return productRepo.findByName(keyword);
    }

}
