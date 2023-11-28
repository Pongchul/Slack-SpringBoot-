package com.SLACK.backend.workspace.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WorkspaceRequest {

    private Long ownerId;
    private String name;
    private String url;
    private boolean deleted;
}
