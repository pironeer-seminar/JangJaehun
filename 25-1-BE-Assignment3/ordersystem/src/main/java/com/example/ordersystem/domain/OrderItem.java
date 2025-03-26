package com.example.ordersystem.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ORDER_ITEM")
@Getter
@Setter
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // pk
    private Long quantity; // 수량
    private Double order_price; // 주문 금액

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")

    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")

    private Product product;

    // 기본 생성자
    protected OrderItem() {}
}
