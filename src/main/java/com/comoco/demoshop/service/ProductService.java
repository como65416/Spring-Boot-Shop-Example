package com.comoco.demoshop.service;

import com.comoco.demoshop.enums.ProductType;
import com.comoco.demoshop.model.Product;
import com.comoco.demoshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<Product> searchProduct(String name, ProductType type, Integer page) {
        Pageable pageable = PageRequest.of(page, 10);

        return this.productRepository.searchByName(name, type, pageable);
    }
}
