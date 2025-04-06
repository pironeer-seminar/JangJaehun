package com.example.demo1.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass // 객체 입장에서 공통 매핑 정보가 필요할 때
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {

    @CreatedDate // createdAt 필드를 자동으로 채워줌
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt; // 생성 시각이 저장될 필드 선언

}