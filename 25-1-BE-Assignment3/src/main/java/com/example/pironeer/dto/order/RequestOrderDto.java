package com.example.pironeer.dto.order;

import com.example.pironeer.domain.Order;
import com.example.pironeer.domain.User;

public class RequestOrderDto {
    private User user;
    private String status;
    private int amount;

    public Order toOrder() {
        return new Order(user, status, amount);
    }
}
