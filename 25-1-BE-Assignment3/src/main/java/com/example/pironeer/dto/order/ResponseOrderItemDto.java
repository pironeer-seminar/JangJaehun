package com.example.pironeer.dto.order;

import com.example.pironeer.domain.OrderItem;

// 단건 조회: 주문 ID로 조회 (주문에 포함된 상품 목록, 총금액 등 확인)
public class ResponseOrderItemDto {
    private String productName;
    private Long quantity;
    private Double price;

    public ResponseOrderItemDto(OrderItem orderItem) {
        this.productName = orderItem.getProduct().getName();
        this.quantity = orderItem.getQuantity();
        this.price = orderItem.getOrder_price();
    }

    public Double getPrice() {
        return price;
    }

    public Long getQuantity() {
        return quantity;
    }
}
