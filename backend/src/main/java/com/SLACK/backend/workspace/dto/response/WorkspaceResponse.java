package com.SLACK.backend.workspace.dto.response;

import com.SLACK.backend.workspace.domain.Workspace;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class WorkspaceResponse {

    private String url;
    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime updatedAt;

    private Long ownerId;
    private boolean deleted;

    public WorkspaceResponse(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public static WorkspaceResponse toResponse(Workspace workspace) {
        return new WorkspaceResponse(workspace.getUrl(), workspace.getName());
    }
}
