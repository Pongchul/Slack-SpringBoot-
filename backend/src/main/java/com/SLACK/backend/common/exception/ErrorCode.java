package com.SLACK.backend.common.exception;

public interface ErrorCode {

    int getStatusCode();

    String getErrorCode();

    String getMessage();


}
