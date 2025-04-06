package com.example.demo1.common.type;

public enum PostErrorType implements ErrorType{
    NOT_FOUND("POST_1", "조회된 게시글이 없습니다.");

    private final String code;
    private final String message;

    PostErrorType(String code, String message) {
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