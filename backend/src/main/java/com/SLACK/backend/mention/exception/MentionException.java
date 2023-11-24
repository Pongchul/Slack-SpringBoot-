package com.SLACK.backend.mention.exception;

import com.SLACK.backend.auth.exception.CustomException;
import com.SLACK.backend.auth.exception.ErrorCode;

public class MentionException extends CustomException {
    public MentionException(ErrorCode code) {
        super(code);
    }
}
