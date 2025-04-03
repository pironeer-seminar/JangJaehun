package com.example.pironeer.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Table(name = "users")
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 다른 생성자를 생성했을 때, 기본 생성자도 함께 생성하기 위해 작성
@AllArgsConstructor(access = AccessLevel.PRIVATE) // @Builder를 사용하기 위해 모든 필드를 인자로 받는 생성자가 필요
// access를 사용하지 않으면 외부에서 Builder를 사용할 수 있고 생성자도 마음대로 쓸 수 있기에 코드가 꼬일 수 있음!
// -> 접근 제어자를 최소화하기 위해 AccessLevel로 접근을 제어
// NoArgsConsturctor(기본 생성자)는 protected로 선언한 이유: JPA가 내부적으로 User라는 객체를 관리하려면 protected로 선언되어야 관리 가능
public class User {
    @Id // pk 값을 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // id 값을 직접 생성하는게 아닌 애플리케이션에서 알아서 생성하도록
    // GeneratedValue의 IDENTITY 전략(1부터 순서대로 값이 자동으로 올라감) 사용
    private Long id;

    @Column(nullable = false, length = 50) // null 값일 수 없고 글자수를 50자로 제한
    private String name;

    // 비즈니스 로직(Service)에서 객체를 생성하는 생성 로직이 들어가는 것은 코드가 지저분해질 수 있기에 정적 팩터리 메서드를 생성
    public static User create(String name) {
        return User.builder()
                .name(name)
                .build();
    }
}
