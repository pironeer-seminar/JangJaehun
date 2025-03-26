package com.example.ordersystem.dto;

import com.example.ordersystem.domain.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestProductDto {
    private String name;
    private Double price;
    private Integer stockQuantity;

    // 새로운 product 등록
    public Product toProduct() {
        return new Product(name, price, stockQuantity);
    }
}
