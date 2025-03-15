package com.example.pironeer.service;

import com.example.pironeer.dto.SentenceResponseDto;
import com.example.pironeer.model.Sentence;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service // Spring Boot에게 Service 로직이라는 것을 알리기 위한 어노테이션
public class SentenceService {
    private final List<Sentence> sentences = new ArrayList<>(); // User가 입력한 값을 저장할 ArrayList

    // 모든 문장 반환
    public List<SentenceResponseDto> getAllSentences() { // DTO를 사용해 Sentence Entity의 정보들을 가지고 옴
        return sentences.stream() // 리스트를 Stream으로 변환
                .map(SentenceResponseDto::new) // 각 문장을 DTO로 변환
                // == map.(sentence -> new SentenceResponseDto(sentence))
                .collect(Collectors.toList()); // 변환된 DTO들을 List로 수집
    }
}
