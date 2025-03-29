package com.example.pironeer.dto.order;

import com.example.pironeer.domain.Order;
import java.util.List;

// 단건 조회: 주문 ID로 조회 (주문에 포함된 상품 목록, 총금액 등 확인)
public class ResponseOrderDetailDto {
    private Long orderId;
    private String status;
    private List<ResponseOrderItemDto> orderItems;
    private Double totalPrice;

    public ResponseOrderDetailDto(Order order) {
        this.orderId = order.getId();
        this.status = order.getStatus();
        this.orderItems = order.getOrderItems().stream()
                .map(ResponseOrderItemDto::new)
                .toList();
        this.totalPrice = orderItems.stream()
                .mapToDouble(orderItem -> orderItem.getPrice() * orderItem.getQuantity())
                .sum(); // 각 아이템의 가격과 갯수를 곱하고 최종적으로 더해 값을 구함.
    }
}
