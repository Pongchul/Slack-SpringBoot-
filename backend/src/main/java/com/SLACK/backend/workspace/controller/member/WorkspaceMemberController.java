package com.SLACK.backend.workspace.controller.member;

import com.SLACK.backend.workspace.service.WorkspaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/workspaces/{workspace")
public class WorkspaceMemberController {

    private final WorkspaceService workspaceService;
}
