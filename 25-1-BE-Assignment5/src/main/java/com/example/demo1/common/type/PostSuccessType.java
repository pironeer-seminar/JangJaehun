package com.example.demo1.common.type;

public enum PostSuccessType implements SuccessType{
    CREATE("POST_1", "게시글 생성에 성공하였습니다."),
    GET_ALL("POST_2", "게시글 목록 조회에 성공하였습니다."),
    GET_DETAIL("POST_3", "게시글 상세 목록 조회에 성공하였습니다."),
    UPDATE("POST_4", "게시글 수정에 성공하였습니다."),
    DELETE("POST_5", "게시글 삭제에 성공하였습니다.");

    private final String code;
    private final String message;

    PostSuccessType(String code, String message) { // final이기에 생성자로 값을 집어넣음
        this.code = code;
        this.message = message;
    }

    @Override // SuccessType의 getCode 메서드를 상속받아 작성
    public String getCode() {
        return this.code;
    }

    @Override // SuccessType의 getMessage 메서드를 상속받아 작성
    public String getMessage() {
        return this.message;
    }
}
