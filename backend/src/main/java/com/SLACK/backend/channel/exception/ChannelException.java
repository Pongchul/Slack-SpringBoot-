package com.SLACK.backend.channel.exception;

import com.SLACK.backend.global.exception.CustomException;
import com.SLACK.backend.global.exception.ErrorCode;

public class ChannelException extends CustomException {
    public ChannelException(ErrorCode code) {
        super(code);
    }
}
