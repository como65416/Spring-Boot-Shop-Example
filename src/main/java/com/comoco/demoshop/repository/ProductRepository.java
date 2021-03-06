package com.comoco.demoshop.repository;

import com.comoco.demoshop.enums.ProductType;
import com.comoco.demoshop.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {
    @Query("SELECT p FROM Product p WHERE p.name LIKE %?1% AND p.type = ?2")
    public List<Product> searchByName(String name, ProductType type, Pageable pageable);
}
