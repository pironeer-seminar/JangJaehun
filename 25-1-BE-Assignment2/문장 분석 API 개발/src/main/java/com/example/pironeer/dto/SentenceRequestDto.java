package com.example.pironeer.dto;

import com.example.pironeer.model.Sentence;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// User에게 입력을 받을 때 SentenceRequestDto 사용
public class SentenceRequestDto {
    @NotBlank(message = "문장은 필수 입력 값입니다.")
    private String sentence;

    public Sentence toEntity() { // toEntity 메서드를 이용해 Sentence 객체 생성
        return new Sentence(sentence);
    }
}
