package com.SLACK.backend.workspace.exception;

import com.SLACK.backend.global.exception.CustomException;
import com.SLACK.backend.global.exception.ErrorCode;

public class WorkspaceException extends CustomException {
    public WorkspaceException(ErrorCode code) {
        super(code);
    }
}
