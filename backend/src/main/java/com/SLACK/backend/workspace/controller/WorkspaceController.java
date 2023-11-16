package com.SLACK.backend.workspace.controller;

import com.SLACK.backend.member.domain.Email;
import com.SLACK.backend.member.dto.response.LoginResponse;
import com.SLACK.backend.workspace.dto.request.WorkspaceRequest;
import com.SLACK.backend.workspace.dto.response.WorkspaceIdResponse;
import com.SLACK.backend.workspace.dto.response.WorkspaceResponse;
import com.SLACK.backend.workspace.service.WorkspaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/workspaces")
public class WorkspaceController {

    private final WorkspaceService workspaceService;

    @PostMapping
    public ResponseEntity<WorkspaceIdResponse> create(Long ownerId, WorkspaceRequest request) {
        WorkspaceIdResponse response = workspaceService.create(ownerId, request);
        URI uri = URI.create("/api/workspaces/" + response.getWorkspaceId());

        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    public ResponseEntity<List<WorkspaceResponse>> findWorkspaces(@PathVariable Long workspaceId) {
        List<WorkspaceResponse> responses = workspaceService.findWorkspaces(workspaceId);

        return ResponseEntity.ok().body(responses);

    }
}
