package com.example.demo1.common.exception;

import com.example.demo1.common.type.ErrorType;
import org.springframework.http.HttpStatus;

public class BaseException extends RuntimeException {

    private final ErrorType errorType;
    private final HttpStatus httpStatus;

    public BaseException(ErrorType errorType, HttpStatus httpStatus) {
        super(errorType.getMessage()); // 우리의 errorType이 가지고 있는 메시지를 RuntimeException에 넘겨줌
        this.errorType = errorType;
        this.httpStatus = httpStatus;
    }

    public ErrorType getErrorType() {
        return this.errorType;
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    public int getHttpStatusCode() { // http 상태 값을 가지고 오기 위한 Getter
        return this.httpStatus.value();
    }

}
