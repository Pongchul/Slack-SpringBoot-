package com.SLACK.backend.member.exception;

import com.SLACK.backend.global.exception.ErrorCode;

public enum MemberErrorCode implements ErrorCode {

    EMAIL_IS_WRONG_LENGTH(400, "MEMBER_001", "올바르지 않은 이메일 길이입니다."),
    EMAIL_IS_WRONG_FORMAT(400, "MEMBER_002", "올바르지 않은 이메일 형식입니다."),

    PASSWORD_IS_WRONG_LENGTH(400, "MEMBER_003", "패스워드는 8자 이상 15자 이하 여야 합니다."),
    PASSWORD_IS_NOT_COLLECT_PATTERN(400, "MEMBER_004", "패스워드는 영문자와 하나 이상의 숫자, 특수 문자를 갖고 있어야 합니다."),

    NICKNAME_IS_WRONG_LENGTH(400, "MEMBER_005", "이름은 1자 이상 20자 이하여야 합니다.")

    ;

    private final int statusCode;
    private final String errorCode;
    private final String message;

    MemberErrorCode(int statusCode, String errorCode, String message) {
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.message = message;
    }

    @Override
    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
