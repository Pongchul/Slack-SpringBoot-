package com.SLACK.backend.member.exception;

import com.SLACK.backend.global.exception.CustomException;
import com.SLACK.backend.global.exception.ErrorCode;

public class MemberException extends CustomException {
    public MemberException(ErrorCode code) {
        super(code);
    }
}
