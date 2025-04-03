package com.example.pironeer.domain;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // pk

    private String name;
    private int price;

    private int amount;

    protected Product(){} // 기본 생성자

    // DTO에서 사용할 생성자
    public Product(String name, int price, int amount){
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    // order_item 입장에서 "product" 필드가 외래 키를 가진 주인
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    public void addOrderItem(OrderItem orderItem){
        this.orderItems.add(orderItem);
        orderItem.setProduct(this);
    }

    public Long getId() { return id; }
    public String getName() {
         return name;
    }

    public int getPrice(){
        return price;
    }
    public int getStockQuantity(){
        return amount;
    }

    public void removeAmount(int amount){
        if (this.amount < amount){
            throw new IllegalStateException();
        }
        this.amount -= amount;
    }

    public void addAmount(int amount){
        this.amount += amount;
    }
}
