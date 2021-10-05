package com.comoco.demoshop.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SearchProductResponse {
    private List<Product> products;

    @Data
    @Builder
    static public class Product {
        Long id;
        String name;
        Float price;
    }
}
