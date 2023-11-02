package com.SLACK.backend.channel.exception;

import com.SLACK.backend.global.exception.ErrorCode;

public enum ChannelErrorCode implements ErrorCode {

    CHANNEL_NAME_IS_NOT_BLANK(400,"CHANNEL_001","워크스페이스 이름은 공백이 될 수 없습니다."),
    CHANNEL_NAME_CANNOT_BE_OUT_OF_RANGE(400, "CHANNEL_002","워크스페이스 이름은 1자 이상 20자 이하 여야 합니다."),
    CHANNEL_CHAT_IS_OUT_OF_RANGE(400, "CHANNEL_003", "메시지는 255자 이하여야 합니다."),


    ;

    private final int statusCode;
    private final String errorCode;
    private final String message;

    ChannelErrorCode(int statusCode, String errorCode, String message) {
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
