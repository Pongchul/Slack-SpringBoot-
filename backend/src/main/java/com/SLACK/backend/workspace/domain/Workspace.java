package com.SLACK.backend.workspace.domain;

import com.SLACK.backend.common.BaseTimeEntity;
import com.SLACK.backend.member.domain.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Workspace extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Member host;

    @Embedded
    private WorkspaceName name;

    @Embedded
    private WorkspaceUrl url;






}
