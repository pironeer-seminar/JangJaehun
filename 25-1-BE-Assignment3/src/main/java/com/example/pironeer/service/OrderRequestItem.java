package com.example.pironeer.service;

public record OrderRequestItem(
        long productId,
        int amount
){}
