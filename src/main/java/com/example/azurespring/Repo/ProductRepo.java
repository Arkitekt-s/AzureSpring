package com.example.azurespring.Repo;

import com.example.azurespring.Modle.Product;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {
    //find product by name
    //%before and after ?1 can bring all search based on first alphabet
    @Query(value= "SELECT p From Product p WHERE p.name Like %?1%")
    List<Product> findByName(String keyword);

}
