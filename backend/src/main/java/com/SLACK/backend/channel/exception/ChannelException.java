package com.SLACK.backend.channel.exception;

import com.SLACK.backend.common.exception.CustomException;
import com.SLACK.backend.common.exception.ErrorCode;

public class ChannelException extends CustomException {
    public ChannelException(ErrorCode code) {
        super(code);
    }
}
