package com.example.mission3.global.handler.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    NOT_FOUND_ADMIN_ID("찾을 수 없는 관리자 번호입니다."),
    ;

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }
}
