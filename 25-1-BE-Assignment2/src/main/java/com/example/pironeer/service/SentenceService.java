package com.example.pironeer.service;

import com.example.pironeer.dto.SentenceAnalysisDto;
import com.example.pironeer.dto.SentenceRequestDto;
import com.example.pironeer.dto.SentenceResponseDto;
import com.example.pironeer.model.Sentence;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service // Spring Boot에게 Service 로직이라는 것을 알리기 위한 어노테이션
public class SentenceService {
    private final List<Sentence> sentences = new ArrayList<>(); // User가 입력한 값을 저장할 ArrayList(서버가 실행되는 동안 유지)

    // 모든 문장 반환
    public List<SentenceResponseDto> getAllSentences() { // DTO를 사용해 Sentence Entity의 정보들을 가지고 옴
        return sentences.stream() // 리스트를 Stream으로 변환
                .map(SentenceResponseDto::new) // 각 문장을 DTO로 변환
                // == map.(sentence -> new SentenceResponseDto(sentence))
                .collect(Collectors.toList()); // 변환된 DTO들을 List로 수집
    }

    // 새로운 문장 추가
    public void addSentence(SentenceRequestDto sentenceRequestDto) {
        Sentence sentence = sentenceRequestDto.toEntity();
        sentences.add(sentence);
    }

//    // 가장 최근의 문장을 가지고 오는 로직
//    public Sentence getLatestSentence () {
//        if (sentences.isEmpty()) return null;
//        else return sentences.get(sentences.size() - 1); // size로 배열 전체 크기를 가지고 온 후 마지막 index 접근
//    }

    // 문장 분석 API(문장 저장은 x)
    public SentenceAnalysisDto analyzeSentence (String sentence) {
        return new SentenceAnalysisDto(
                sentence.length(), // 문장 길이
                sentence.split("\\s+").length, // 단어 개수
                sentence.toLowerCase().contains("spring") // spring 포함 여부
        );
    }
}
