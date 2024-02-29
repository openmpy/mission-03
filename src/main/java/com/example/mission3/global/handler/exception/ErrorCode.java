package com.example.mission3.global.handler.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    EMAIL_ALREADY_EXISTS("이미 가입된 이메일입니다."),
    CURRICULUM_MANAGER_PERMISSION_REQUIRED("커리큘럼, 개발 부서만 MANAGER 권한을 부여 받을 수 있습니다."),
    ADMIN_ACCOUNT_NOT_FOUND("찾을 수 없는 관리자 계정입니다."),
    TEACHER_ID_NOT_FOUND("찾을 수 없는 강사 번호입니다."),
    TEACHER_ALREADY_DELETED("이미 삭제된 강사입니다."),
    LECTURE_ID_NOT_FOUND("찾을 수 없는 강의 번호입니다."),
    LECTURE_ALREADY_DELETED("이미 삭제된 강의입니다."),
    ;

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }
}
