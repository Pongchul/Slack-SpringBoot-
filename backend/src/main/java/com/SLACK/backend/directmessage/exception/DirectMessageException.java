package com.SLACK.backend.directmessage.exception;

import com.SLACK.backend.common.exception.CustomException;
import com.SLACK.backend.common.exception.ErrorCode;

public class DirectMessageException extends CustomException {
    public DirectMessageException(ErrorCode code) {
        super(code);
    }
}
