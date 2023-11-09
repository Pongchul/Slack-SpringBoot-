package com.SLACK.backend.workspace.domain;

import com.SLACK.backend.workspace.exception.WorkspaceErrorCode;
import com.SLACK.backend.workspace.exception.WorkspaceException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor
public class WorkspaceUrl {

    private static final int MIN_LENGTH = 1;
    private static final int MAX_LENGTH = 10;

    @Column(name = "url", nullable = false, length = 10)
    private String value;

    public WorkspaceUrl(String value) {
        this.value = value;
    }

    public static WorkspaceUrl from(String value) {
        validateIsNotBlank(value);
        validateLengthInRange(value);

        return new WorkspaceUrl(value);
    }

    public WorkspaceUrl update(String value) {
        return WorkspaceUrl.from(value);
    }

    private static void validateIsNotBlank(String value) {
        if (value.isBlank()) {
            throw new WorkspaceException(WorkspaceErrorCode.WORKSPACE_URL_IS_NOT_BLANK);
        }
    }

    private static void validateLengthInRange(String value) {
        int length = value.length();
        if (length > MAX_LENGTH || length < MIN_LENGTH) {
            throw new WorkspaceException(WorkspaceErrorCode.WORKSPACE_URL_CANNOT_BE_OUT_OF_RANGE);
        }
    }
}
