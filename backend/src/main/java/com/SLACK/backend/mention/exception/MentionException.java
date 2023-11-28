package com.SLACK.backend.mention.exception;

import com.SLACK.backend.common.exception.CustomException;
import com.SLACK.backend.common.exception.ErrorCode;

public class MentionException extends CustomException {
    public MentionException(ErrorCode code) {
        super(code);
    }
}
