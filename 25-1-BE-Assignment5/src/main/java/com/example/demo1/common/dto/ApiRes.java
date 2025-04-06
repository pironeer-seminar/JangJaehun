package com.example.demo1.common.dto;

import com.example.demo1.common.exception.BaseException;
import com.example.demo1.common.type.SuccessType;
import com.example.demo1.common.type.ErrorType;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

public record ApiRes<T>( // record : 데이터를 변경하지 않고 실어 나르는 역할만 수행하는 순수 데이터 클래스
        Integer status, String code, String message, @JsonInclude(JsonInclude.Include.NON_NULL) T data // T type을 가진 data, data는 있을 수도 있고 없을 수도 있음 -> @JasonInclude 어노테이션을 사용
) { // ApiRes는 원하는 이름으로 작성하면 됨(주로 프로젝트 이름으로 작성)

    // 응답 데이터가 없는 version
    // class.메서드를 사용하기 위해선 static이어야 함!
    public static ApiRes<?> success(SuccessType successType) { // ?는 데이터가 없다라는 의미로 작성, <?>는 제네릭이 아니기에 앞에 <?>를 붙이지 않아도 됨!
        return new ApiRes<>(HttpStatus.OK.value(), successType.getCode(), successType.getMessage(), null);
    }


    // 응답 데이터가 있는 version
    // static 뒤에 <T>가 있어야 하는 이유? <T>는 제네릭이기에 문법 상 작성되어야 함
    public static <T> ApiRes<T> success(SuccessType successType, T data) { // 메서드 오버라이딩
        return new ApiRes<>(HttpStatus.OK.value(), successType.getCode(), successType.getMessage(), data);

    }

    // 실패한 응답에 대한 return
    // 응답 데이터가 없는 version
    public static ApiRes<?> fail(ErrorType errorType, HttpStatus status) { // ?는 데이터가 없다라는 의미로 작성
        return new ApiRes<>(
                status.value(), errorType.getCode(), errorType.getMessage(), null
        );
    }


    // 응답 데이터가 있는 version
    public static <T> ApiRes<T> fail(ErrorType errorType, HttpStatus status, T data) { // 메서드 오버라이딩
        return new ApiRes<>(
                status.value(), errorType.getCode(), errorType.getMessage(), data
        );

    }

    // 위의 함수에선 errorType과 status를 따로 넘겨줬지만 이를 BaseException으로 묶어서 매개변수로 받아 처리 가능
    public static <T> ApiRes<T> fail(BaseException baseException) {
        ErrorType errorType = baseException.getErrorType();
        return new ApiRes<>(
                baseException.getHttpStatusCode(),
                errorType.getCode(),
                errorType.getMessage(),
                null
        );
    }

}
