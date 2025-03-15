package com.example.pironeer.dto;

import com.example.pironeer.model.Sentence;
import lombok.Getter;

@Getter
public class SentenceResponseDto {
    private String sentence;

    public SentenceResponseDto(Sentence sentence) {
        this.sentence = sentence.getSentence(); // Sentence Entity의 get 메소드를 이용해 DTO 구현
    }
}
