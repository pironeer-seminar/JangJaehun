package com.example.ordersystem.domain;

import com.example.ordersystem.handler.NotEnoughStockException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PRODUCT")
@NoArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // pk
    private String name; // 제품명
    private Double price; // 가격
    private Integer stockQuantity; // 재고

    // DTO에서 사용할 생성자
    public Product(String name, double price, Integer stockQuantity) { // 생성자를 이용한 의존성 주입
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    // order_item 입장에서 "product" 필드가 외래 키를 가진 주인
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    public void addOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
        orderItem.setProduct(this);
    }

    public void minusStockQuantity(int quantity) {
        if (this.stockQuantity < quantity) {
            throw new NotEnoughStockException("재고가 부족합니다.");
        }
        this.stockQuantity -= quantity;
    }

    public void plusStockQuantity(int quantity) {
        this.stockQuantity += quantity;
    }
}
