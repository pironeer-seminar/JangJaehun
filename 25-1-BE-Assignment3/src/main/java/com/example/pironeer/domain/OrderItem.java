package com.example.pironeer.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "order_item")
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

    public OrderItem(Product product, Long quantity, Double order_price) {
        this.product = product;
        this.quantity = quantity;
        this.order_price = order_price;
    }

    // Getter
    public Long getId() { return id; }
    public Long getQuantity() { return quantity; }
    public Double getOrder_price() { return order_price; }
    public Product getProduct() { return product; }
    public Order getOrder() { return order; }

    // order 엔티티에서 사용
    public void setOrder(Order order) {
        this.order = order;
    }

    // product 엔티티에서 사용
    public void setProduct(Product product) {
        this.product = product;
    }
}
