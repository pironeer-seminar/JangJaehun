package com.example.pironeer.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // User:Order = 1:N // 연관관계의 주인 (User와 다대일 관계)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") // 외래 키 명 직접 지정
    private User user;
    private String status;
    private int amount; // 총 금액

    // Order:OrderItem = 1:N | product와 order는 N:M 관계이므로, 1:N, N:1의 관계로 쪼개어 관리
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL) //
    private List<OrderItem> orderItems = new ArrayList<>();

    protected Order(){}

    public Order(User user, String status, int amount){
        this.user = user;
        this.status = status;
        this.amount = amount;
    }

    public void addOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public Long getId() { return id; }
    public String getStatus() {
        return status;
    }
    public User getUser() { return user; }
    public List<OrderItem> getOrderItems() { return orderItems; }

    public void setUser(User user) {
        this.user = user;
    }
}
