package com.example.ordersystem.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity // JPA 사용
@Table(name = "USER") // 테이블 명 지정
// @NoArgsConstructor // JPA에서 엔티티는 기본 생성자 필요
@Getter
@Setter
public class User {
    @Id // 해당 필드가 기본 키임을 명시
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // pk
    private String name; // username
    private String email; // user email

    // DTO에서 사용할 생성자
    public User(String name, String email) { // 생성자를 이용한 의존성 주입
        this.name = name;
        this.email = email;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

    // 기본 생성자
    protected User() {}

    // User -> Order 양방향 연결(양방향 관계를 한번에 유지 가능)
    public void addOrder(Order order) {
        this.orders.add(order);
        order.setUser(this);
    }
}
