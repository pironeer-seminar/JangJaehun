package com.example.pironeer.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.AUTO) // 해당 필드가 기본 키임을 명시
    private Long id;

    private String username;
    private String email;

    // 양방향 연관관계 (User ↔ Order)
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

    // 기본 생성자
    protected User(){}

    // DTO에서 사용할 생성자
    public User(String username, String email){
        this.username = username;
        this.email = email;
    }

    // User -> Order 양방향 연결(양방향 관계를 한번에 유지 가능)
    public void addOrder(Order order) {
        this.orders.add(order);
        order.setUser(this);
    }

    // 생성자
    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
    public String getName(){
        return username;
    }

    public List<Order> getOrders(){
        return orders;
    }
}
