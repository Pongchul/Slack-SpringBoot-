package com.SLACK.backend.workspace.controller;

import com.SLACK.backend.member.domain.Email;
import com.SLACK.backend.member.dto.response.LoginResponse;
import com.SLACK.backend.workspace.dto.request.WorkspaceRequest;
import com.SLACK.backend.workspace.dto.response.WorkspaceResponse;
import com.SLACK.backend.workspace.service.WorkspaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/workspaces")
public class WorkspaceController {

    private final WorkspaceService workspaceService;

    @PostMapping
    public ResponseEntity<Long> create(Long ownerId, WorkspaceRequest request) {
        Long response = workspaceService.create(ownerId, request);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<WorkspaceResponse> findWorkspaces(Long id) {
        WorkspaceResponse response = WorkspaceResponse.toResponse(workspaceService.findWorkspacesById(id));

        return ResponseEntity.ok().body(response);

    }
}
