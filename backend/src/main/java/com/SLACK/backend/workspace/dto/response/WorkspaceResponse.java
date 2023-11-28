package com.SLACK.backend.workspace.dto.response;

import com.SLACK.backend.member.domain.Member;
import com.SLACK.backend.workspace.domain.Workspace;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class WorkspaceResponse {

    private Long id;
    private String name;
    private String url;
    private Member ownerId;


    public static WorkspaceResponse toResponse(Workspace workspace) {
        return new WorkspaceResponse(workspace.getId(), workspace.getUrl(), workspace.getName(), workspace.getOwnerId());
    }

    public static List<WorkspaceResponse> workspaceResponses(List<Workspace> workspaces) {
        return workspaces.stream()
                .map(WorkspaceResponse::toResponse)
                .collect(Collectors.toList());
    }
}
