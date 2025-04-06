package com.example.demo1.common.exception;

import com.example.demo1.common.dto.ApiRes;
import com.example.demo1.common.type.CommonErrorType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j // 로깅을 하기 위한 어노테이션 -> 로그가 더 보기 좋게 나옴
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiRes<?>> handerIllegalArgumentException(
            final IllegalArgumentException ex) {
        log.error(ex.getMessage()); // 디버깅을 위한 로그 찍기
        return new ResponseEntity<>(
            ApiRes.fail(CommonErrorType.INTERNAL_SERVER, HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()),
                // INTERNAL_SERVER 라고 반드시 선언해야하는 것이 아닌 팀에서 결정하는대로 작성해주면 된다!
            HttpStatus.INTERNAL_SERVER_ERROR // code의 status 값
        );
    }

    // NotFound를 처리할 수 있는 handler
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ApiRes<?>> handerBaseException(BaseException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ApiRes.fail(ex), ex.getHttpStatus());
    }

    // ValidException을 처리하는 handler
    @ExceptionHandler(MethodArgumentNotValidException.class) // 유효성 검사에 실패한 에러 처리
    public ResponseEntity<ApiRes<?>> handerMethodArgumentNotValidException(
            final MethodArgumentNotValidException ex
    ) {
        log.error(ex.getMessage()); // 디버깅을 위한 로그 찍기
        return new ResponseEntity<>(
                ApiRes.fail(
                        CommonErrorType.VALIDATION_ERROR,
                        HttpStatus.BAD_REQUEST,
                        ex.getMessage()
                ),
                HttpStatus.BAD_REQUEST
        );
    }
}
