package com.SLACK.backend.workspace.service;

import com.SLACK.backend.member.service.MemberService;
import com.SLACK.backend.workspace.domain.Workspace;
import com.SLACK.backend.workspace.domain.WorkspaceName;
import com.SLACK.backend.workspace.domain.WorkspaceRepository;
import com.SLACK.backend.workspace.domain.WorkspaceUrl;
import com.SLACK.backend.workspace.dto.request.WorkspaceRequest;
import com.SLACK.backend.workspace.dto.response.WorkspaceResponse;
import com.SLACK.backend.workspace.exception.WorkspaceErrorCode;
import com.SLACK.backend.workspace.exception.WorkspaceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class WorkspaceService {

    private final WorkspaceRepository workspaceRepository;
    private final MemberService memberService;


    @Transactional
    public void create(Long memberId, WorkspaceRequest request) {
        WorkspaceUrl url = WorkspaceUrl.from(request.getUrl());
        WorkspaceName name = WorkspaceName.from(request.getName());

        Workspace workspace = new Workspace(memberId, name, url, false);
        workspaceRepository.save(workspace);

    }


    public Workspace findWorkspacesById(Long id) {
        return workspaceRepository.findWorkspacesById(id)
                .orElseThrow(() -> new WorkspaceException(WorkspaceErrorCode.WORKSPACE_IS_NOT_EXIST));
    }

    public List<WorkspaceResponse> findWorkspaces(Long workspaceId) {
        Workspace workspace = this.findWorkspacesById(workspaceId);
        List<Workspace> workspaces = workspace.getWorkspaces();

        return WorkspaceResponse.workspaceResponses(workspaces);
    }



}
