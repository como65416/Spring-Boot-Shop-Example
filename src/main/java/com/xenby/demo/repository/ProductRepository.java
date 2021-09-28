package com.xenby.demo.repository;

import com.xenby.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {
    @Query("SELECT p FROM Product p WHERE p.name LIKE %?1%")
    public List<Product> searchByName(String name);
}
