package com.SLACK.backend.workspace.exception;

import com.SLACK.backend.common.exception.CustomException;
import com.SLACK.backend.common.exception.ErrorCode;

public class WorkspaceException extends CustomException {
    public WorkspaceException(ErrorCode code) {
        super(code);
    }
}
