package com.example.pironeer.dto.product;

import com.example.pironeer.domain.Product;

public class ResponseProductIdDto {
    private String name;
    private int price;
    private int stockQuantity;

    public ResponseProductIdDto(Product product){
        this.name = product.getName();
        this.price = product.getPrice();
        this.stockQuantity = product.getStockQuantity();
    }
}
