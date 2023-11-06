package com.SLACK.backend.directmessage.exception;

import com.SLACK.backend.global.exception.CustomException;
import com.SLACK.backend.global.exception.ErrorCode;

public class DirectMessageException extends CustomException {
    public DirectMessageException(ErrorCode code) {
        super(code);
    }
}
