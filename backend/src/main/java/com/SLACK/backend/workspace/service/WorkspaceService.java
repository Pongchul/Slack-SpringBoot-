package com.SLACK.backend.workspace.service;

import com.SLACK.backend.member.domain.Member;
import com.SLACK.backend.member.service.MemberService;
import com.SLACK.backend.workspace.domain.Workspace;
import com.SLACK.backend.workspace.domain.WorkspaceRepository;
import com.SLACK.backend.workspace.dto.request.WorkspaceRequest;
import com.SLACK.backend.workspace.dto.response.WorkspaceIdResponse;
import com.SLACK.backend.workspace.exception.WorkspaceErrorCode;
import com.SLACK.backend.workspace.exception.WorkspaceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class WorkspaceService {

    private final WorkspaceRepository workspaceRepository;
    private final MemberService memberService;

    @Transactional
    public Long create(Long ownerId, WorkspaceRequest request) {
        Member owner = memberService.findMember(ownerId);
        Workspace workspace = new Workspace(request.getOwnerId(), request.getName(), request.getUrl(), request.isDeleted());
        Workspace saveWorkspace = workspaceRepository.save(workspace);

        return saveWorkspace.getId();
    }


    public Workspace findWorkspace(Long id) {
        return workspaceRepository.findById(id)
                .orElseThrow(() -> new WorkspaceException(WorkspaceErrorCode.WORKSPACE_IS_NOT_EXIST));
    }



}
