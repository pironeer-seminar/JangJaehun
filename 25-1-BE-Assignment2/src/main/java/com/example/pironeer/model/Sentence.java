package com.example.pironeer.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor // JPA에서 기본 생성자가 필요
public class Sentence {
    private String sentence;

    // 생성자(DTO에서 사용)
    public Sentence(String sentence) { // 생성자를 이용한 의존성 주입
        this.sentence = sentence;
    }
}
