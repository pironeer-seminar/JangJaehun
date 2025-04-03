package com.example.pironeer.service;

import com.example.pironeer.domain.Order;
import com.example.pironeer.domain.OrderItem;
import com.example.pironeer.domain.Product;
import com.example.pironeer.domain.User;
import com.example.pironeer.repository.OrderRepository;
import com.example.pironeer.repository.ProductRepository;
import com.example.pironeer.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    public final OrderRepository orderRepository;
    public final UserRepository userRepository;
    public final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    // 주문 생성
    @Transactional
    public Long createOrder(Long userId, OrderRequestItem orderRequestItem) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        Product product = productRepository.findById(orderRequestItem.productId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));

        // 제품의 개수를 주문 넣은 만큼 감소
        product.removeAmount(orderRequestItem.amount());

        Double totalPrice = (double) (product.getPrice() * orderRequestItem.amount());

        // OrderItem 생성
        OrderItem orderItem = new OrderItem(product, (long) orderRequestItem.amount(), totalPrice);

        // 주문 생성
        Order order = new Order(user, "ORDERED", orderRequestItem.amount());

        order.addOrderItem(orderItem);

        orderRepository.save(order);

        return order.getId();
    }

    // userId로 주문 목록 전체 검색
    public List<Order> getOrdersByUserId(Long userId) {
        Optional<User> findUserId = userRepository.findById(userId);
        return findUserId.get().getOrders();
    }

    // 주문 삭제
    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalStateException("일치하는 주문이 없습니다"));

        if ("CANCELED".equals(order.getStatus())) {
            throw new IllegalStateException("취소된 주문입니다.");
        }

        order.getOrderItems()
                .forEach(orderItem -> orderItem.getProduct().addAmount(orderItem.getQuantity().intValue()));

        order.setStatus("CANCELED");

    }
}
