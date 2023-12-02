package com.SLACK.backend.auth.exception;

import com.SLACK.backend.common.exception.ErrorCode;

public enum AuthErrorCode implements ErrorCode {

    NOT_FOUND(400,"AUTH_001","토큰이 존재하지 않습니다."),
    NEED_TOKEN(404,"AUTH_002","토큰이 필요합니다."),
    INVALID_TOKEN(400, "AUTH_003", "잘못된 토큰입니다."),
    TOKEN_IS_EXPIRED(404, "AUTH_004","토큰이 만료됐습니다."),


    ;

    private final int statusCode;
    private final String errorCode;
    private final String message;

    AuthErrorCode(int statusCode, String errorCode, String message) {
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
