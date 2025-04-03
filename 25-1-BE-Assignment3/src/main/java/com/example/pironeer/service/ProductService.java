package com.example.pironeer.service;

import com.example.pironeer.domain.Product;
import com.example.pironeer.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Long createProduct(Product product) {
        Product savedProduct = productRepository.save(product);
        return savedProduct.getId();
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // 재고가 부족할 시 error 발생 여기서 에러 처리
    @Transactional
    public void decreaseStock(Long productId, int amount) {
        productRepository.findById(productId).ifPresent(product -> {
            product.removeAmount(amount);
        });
    }
}
