package com.example.pironeer.dto;

import com.example.pironeer.model.Sentence;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// User에게 입력을 받을 때 SentenceRequestDto 사용
public class SentenceRequestDto {
    private String sentence;

    public Sentence toEntity() { // toEntity 메서드를 이용해 Sentence 객체 생성
        return new Sentence(sentence);
    }
}
