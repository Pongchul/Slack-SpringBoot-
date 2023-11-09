package com.SLACK.backend.workspace.exception;

import com.SLACK.backend.global.exception.ErrorCode;

public enum WorkspaceErrorCode implements ErrorCode {

    WORKSPACE_NAME_IS_NOT_BLANK(400,"WORKSPACE_001","워크스페이스 이름은 공백이 될 수 없습니다."),
    WORKSPACE_NAME_CANNOT_BE_OUT_OF_RANGE(400, "WORKSPACE_002","워크스페이스 이름은 1자 이상 20자 이하 여야 합니다."),

    WORKSPACE_URL_IS_NOT_BLANK(400, "WORKSPACE_003","워크스페이스 URL은 공백이 될 수 없습니다."),
    WORKSPACE_URL_CANNOT_BE_OUT_OF_RANGE(400, "WORKSPACE_004","워크스페이스 URL은 1자 이상 10자 이하 여야 합니다."),

    WORKSPACE_IS_NOT_EXIST(404, "WORKSPACE_005", "존재하지 않는 워크스페이스 입니다."),


    ;




    private final int statusCode;
    private final String errorCode;
    private final String message;

    WorkspaceErrorCode(int statusCode, String errorCode, String message) {
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.message = message;
    }

    @Override
    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
