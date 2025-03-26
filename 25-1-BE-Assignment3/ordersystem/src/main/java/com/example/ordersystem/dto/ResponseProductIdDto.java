package com.example.ordersystem.dto;

import com.example.ordersystem.domain.Product;
import lombok.Getter;

@Getter
public class ResponseProductIdDto {
    private String name;
    private double price;
    private int stockQuantity;

    public ResponseProductIdDto(Product product){
        this.name = product.getName();
        this.price = product.getPrice();
        this.stockQuantity = product.getStockQuantity();
    }
}
