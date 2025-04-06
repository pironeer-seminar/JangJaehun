package com.example.demo1.common.type;

public interface SuccessType { // interface로 만드는 이유: 여러 enum을 공통 인터페이스인 SuccessType으로 다루기 위함

    String getCode();
    String getMessage();
}
