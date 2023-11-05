package com.SLACK.backend.mention.exception;

import com.SLACK.backend.global.exception.ErrorCode;

public enum MentionErrorCode implements ErrorCode {

    CATEGORY_NOT_EXIST(400, "MENTION_001","카테고리가 존재하지 않습니다.");

    private final int statusCode;
    private final String errorCode;
    private final String message;

    MentionErrorCode(int statusCode, String errorCode, String message) {
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
