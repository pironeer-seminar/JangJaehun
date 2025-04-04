package com.example.pironeer.repository;

import com.example.pironeer.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

// DB 로직을 Repository 로직과 분리하기 위해 작성
// Data jpa를 사용하기 위해 interface로 생성
public interface UserRepository extends JpaRepository<User, Long> {
}
