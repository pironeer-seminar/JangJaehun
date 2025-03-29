package com.example.pironeer.service;

import com.example.pironeer.domain.Order;
import com.example.pironeer.domain.OrderItem;
import com.example.pironeer.domain.Product;
import com.example.pironeer.domain.User;
import com.example.pironeer.dto.order.RequestOrderDto;
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

    @Transactional
    public void createOrder(Long userId, OrderRequestItem orderRequestItem) {
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
        Order order = new Order(user, "ORDERD", orderRequestItem.amount());

        order.addOrderItem(orderItem);

        orderRepository.save(order);
    }

    public List<Order> getOrdersByUserId(Long userId) {
        Optional<User> findUserId = userRepository.findById(userId);
        return findUserId.get().getOrders();
    }
}
