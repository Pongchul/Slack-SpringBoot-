package com.SLACK.backend.auth.exception;

import com.SLACK.backend.common.exception.CustomException;
import com.SLACK.backend.common.exception.ErrorCode;

public class AuthException extends CustomException {
    public AuthException(ErrorCode code) {
        super(code);
    }
}
