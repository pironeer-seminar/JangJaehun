package com.example.pironeer.controller;

import com.example.pironeer.dto.SentenceResponseDto;
import com.example.pironeer.service.SentenceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("analyze") // Controller의 기본 URL 경로
public class SentenceController {
    private final SentenceService sentenceService; // 생성한 Service를 참조해옴

    public SentenceController(SentenceService sentenceService){ // DI를 이용해 의존성 주입
        this.sentenceService = sentenceService;
    }

    // 모든 문장을 가지고 오는 로직
    @GetMapping
    public List<SentenceResponseDto> getAllSentences() {
        List<SentenceResponseDto> sentences = new ArrayList<>();
        return sentences;
    }

    // 새로운 문장을 검사하는 로직(검사해서 유효한지 검사 필요)
}
