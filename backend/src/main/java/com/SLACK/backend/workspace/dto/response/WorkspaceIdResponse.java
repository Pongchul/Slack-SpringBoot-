package com.SLACK.backend.workspace.dto.response;

import com.SLACK.backend.workspace.domain.Workspace;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WorkspaceIdResponse {

    private Long workspaceId;

    public static WorkspaceIdResponse toResponse(Workspace workspace) {
        return new WorkspaceIdResponse(workspace.getId());
    }
}
