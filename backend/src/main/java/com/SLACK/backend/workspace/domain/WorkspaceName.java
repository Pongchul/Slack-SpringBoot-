package com.SLACK.backend.workspace.domain;

import com.SLACK.backend.workspace.exception.WorkspaceErrorCode;
import com.SLACK.backend.workspace.exception.WorkspaceException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class WorkspaceName {

    private static final int MIN_LENGTH = 1;
    private static final int MAX_LENGTH = 20;

    @Column(name = "name", nullable = true, length = 20)
    private String value;

    public WorkspaceName(String value) {
        this.value = value;
    }

    public static WorkspaceName from(String value) {
        validateIsNotBlank(value);
        validateLengthInRange(value);
        return new WorkspaceName(value);
    }

    public WorkspaceName update(String value) {
        return WorkspaceName.from(value);
    }

    private static void validateIsNotBlank(String value) {
        if (value.isBlank()) {
            throw new WorkspaceException(WorkspaceErrorCode.WORKSPACE_NAME_IS_NOT_BLANK);
        }
    }

    private static void validateLengthInRange(String value) {
        int length = value.length();
        if (length > MAX_LENGTH || length < MIN_LENGTH) {
            throw new WorkspaceException(WorkspaceErrorCode.WORKSPACE_NAME_CANNOT_BE_OUT_OF_RANGE);
        }
    }
}
