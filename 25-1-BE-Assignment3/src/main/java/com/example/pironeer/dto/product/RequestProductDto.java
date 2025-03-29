package com.example.pironeer.dto.product;

import com.example.pironeer.domain.Product;

public class RequestProductDto {
    private String name;
    private int price;
    private Integer stockQuantity;

    // 새로운 product 등록
    public Product toProduct() {
        return new Product(name, price, stockQuantity);
    }
}
