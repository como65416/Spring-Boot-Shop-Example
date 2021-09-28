package com.xenby.demo.controller;

import com.xenby.demo.dto.response.SearchProductResponse;
import com.xenby.demo.service.ProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping(value="/products")
    @ApiOperation("搜尋商品")
    public SearchProductResponse searchProduct(@RequestParam(value="", required=false) String name) {
        List<SearchProductResponse.Product> resDatas = productService.searchProduct(name)
                .stream()
                .map(p -> SearchProductResponse.Product.builder()
                        .name(p.getName())
                        .price(p.getPrice())
                        .id(p.getId())
                        .build())
                .collect(Collectors.toList());

        return SearchProductResponse.builder()
                .products(resDatas)
                .build();
    }
}
