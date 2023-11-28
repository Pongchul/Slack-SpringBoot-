package com.SLACK.backend.member.exception;

import com.SLACK.backend.common.exception.CustomException;
import com.SLACK.backend.common.exception.ErrorCode;

public class MemberException extends CustomException {
    public MemberException(ErrorCode code) {
        super(code);
    }
}
