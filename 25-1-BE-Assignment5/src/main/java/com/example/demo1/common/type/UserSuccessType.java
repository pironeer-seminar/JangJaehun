package com.example.demo1.common.type;


public enum UserSuccessType implements SuccessType {
    CREATE("USER_1", "유저가 성공적으로 생성되었습니다.");

    private final String code;
    private final String message;

    UserSuccessType(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
