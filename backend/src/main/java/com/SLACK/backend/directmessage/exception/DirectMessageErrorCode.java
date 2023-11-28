package com.SLACK.backend.directmessage.exception;

import com.SLACK.backend.common.exception.ErrorCode;

public enum DirectMessageErrorCode implements ErrorCode {

    DIRECT_MESSAGE_IS_OUT_OF_RANGE(400, "DM_001", "메시지는 255자 이하여야 합니다."),


    ;

    private final int statusCode;
    private final String errorCode;
    private final String message;


    DirectMessageErrorCode(int statusCode, String errorCode, String message) {
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
