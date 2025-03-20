package com.example.pironeer.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor // 기본 생성자
// 문장 분석 결과를 담을 DTO(여러 결과를 JSON 형식으로 반환하기 위해 필요)
public class SentenceAnalysisDto {
    @NotBlank(message = "문장은 필수 입력 값입니다.")
    private int length;
    private int wordCnt;
    private boolean containSpring;
}