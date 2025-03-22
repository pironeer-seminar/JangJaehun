package com.example.pironeer.controller;

import com.example.pironeer.dto.SentenceAnalysisDto;
import com.example.pironeer.dto.SentenceRequestDto;
import com.example.pironeer.dto.SentenceResponseDto;
import com.example.pironeer.service.SentenceService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SentenceController {
    private final SentenceService sentenceService; // 생성한 Service를 참조해옴

    public SentenceController(SentenceService sentenceService){ // DI를 이용해 의존성 주입
        this.sentenceService = sentenceService;
    }

    // 모든 문장을 가지고 오는 로직
    @GetMapping("/sentence")
    public List<SentenceResponseDto> getAllSentences() {
        return sentenceService.getAllSentences(); // SentenceService 클래스의 getAllSentences 메소드를 활용해 모든 문장을 가지고 옴
    }

    @PostMapping("/sentence")
    public String createSentence(@Valid @RequestBody SentenceRequestDto sentenceRequestDto) {
        sentenceService.addSentence(sentenceRequestDto);
        return "문장 저장 성공!";
    }

    // 문장을 검사하는 로직(검사해서 유효한지 검사 필요)
    @PostMapping("/analyze")
//    public SentenceAnalysisDto analyzeSentence(@Valid @RequestBody SentenceRequestDto sentenceRequestDto) {
//        sentenceService.addSentence(sentenceRequestDto);
//        return sentenceService.analyzeSentence(); // 입력된 문자 저장
//    }
    public ResponseEntity<SentenceAnalysisDto> analyzeSentence(@Valid @RequestBody SentenceRequestDto sentenceRequestDto) {
        return ResponseEntity.ok(sentenceService.analyzeSentence(sentenceRequestDto.getSentence()));
    }

}
