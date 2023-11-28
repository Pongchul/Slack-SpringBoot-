package com.SLACK.backend.workspace.domain;

import com.SLACK.backend.common.BaseTimeEntity;
import com.SLACK.backend.member.domain.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "tb_workspace")
public class Workspace extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Member owner;

    @Embedded
    private WorkspaceName name;

    @Embedded
    private WorkspaceUrl url;

    @Column(nullable = false)
    private boolean deleted;

    public Workspace(Long owner, WorkspaceName name, WorkspaceUrl url, boolean deleted) {
        this.owner = new Member(owner);
        this.name = name;
        this.url = url;
        this.deleted = deleted;
    }

    public Workspace(Long memberId, String name, String url) {
        this.owner = new Member(memberId);
        this.name = WorkspaceName.from(name);
        this.url = WorkspaceUrl.from(url);
        this.deleted = false;
    }

    public Member getOwnerId() {
        return getOwnerId();
    }

    public String getUrl() {
        return Optional.ofNullable(url)
                .map(WorkspaceUrl::getValue)
                .orElse("");
    }

    public String getName() {
        return Optional.ofNullable(name)
                .map(WorkspaceName::getValue)
                .orElse("");
    }

    public List<Workspace> getWorkspaces() {
        List<Workspace> workspaces = new ArrayList<>();
        workspaces.stream()
                .map(Workspace::getWorkspaces)
                .collect(Collectors.toUnmodifiableList());

        return workspaces;

    }
}
