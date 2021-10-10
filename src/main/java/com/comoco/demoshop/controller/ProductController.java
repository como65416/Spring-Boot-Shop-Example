package com.comoco.demoshop.controller;

import com.comoco.demoshop.dto.response.SearchProductResponse;
import com.comoco.demoshop.enums.ProductType;
import com.comoco.demoshop.service.ProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping(value="/products")
    @ApiOperation("搜尋商品")
    public SearchProductResponse searchProduct(
        @RequestParam(defaultValue="", required=false) String name,
        @RequestParam(defaultValue="0", required=false) Integer page
    ) {
        List<SearchProductResponse.Product> products = productService.searchProduct(name, ProductType.Normal, page)
            .stream()
            .map(p -> SearchProductResponse.Product.builder()
                .name(p.getName())
                .price(p.getPrice())
                .id(p.getId())
                .build())
            .collect(Collectors.toList());

        return SearchProductResponse.builder()
            .products(products)
            .build();
    }

    @GetMapping(value="/vip-products")
    @ApiOperation("搜尋 VIP 專屬商品")
    @PreAuthorize("hasRole('ROLE_VIPMember')")
    public SearchProductResponse searchVIPProduct(
        @RequestParam(defaultValue="", required=false) String name,
        @RequestParam(defaultValue="0", required=false) Integer page
    ) {
        List<SearchProductResponse.Product> products = productService.searchProduct(name, ProductType.VIPOnly, page)
            .stream()
            .map(p -> SearchProductResponse.Product.builder()
                .name(p.getName())
                .price(p.getPrice())
                .id(p.getId())
                .build())
            .collect(Collectors.toList());

        return SearchProductResponse.builder()
            .products(products)
            .build();
    }
}
