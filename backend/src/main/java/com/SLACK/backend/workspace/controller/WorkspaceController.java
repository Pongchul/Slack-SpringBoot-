package com.SLACK.backend.workspace.controller;

import com.SLACK.backend.member.domain.Email;
import com.SLACK.backend.member.dto.response.LoginResponse;
import com.SLACK.backend.workspace.dto.request.WorkspaceRequest;
import com.SLACK.backend.workspace.dto.response.WorkspaceIdResponse;
import com.SLACK.backend.workspace.dto.response.WorkspaceResponse;
import com.SLACK.backend.workspace.service.WorkspaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Void> create(Long ownerId, WorkspaceRequest request) {
        workspaceService.create(ownerId, request);

        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @GetMapping
    public ResponseEntity<List<WorkspaceResponse>> findWorkspaces(@PathVariable Long workspaceId) {
        List<WorkspaceResponse> responses = workspaceService.findWorkspaces(workspaceId);

        return ResponseEntity.ok().body(responses);

    }
}
