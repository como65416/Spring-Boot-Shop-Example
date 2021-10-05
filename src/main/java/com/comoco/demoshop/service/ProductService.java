package com.comoco.demoshop.service;

import com.comoco.demoshop.model.Product;
import com.comoco.demoshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<Product> searchProduct(String name) {
        return this.productRepository.searchByName(name);
    }
}
