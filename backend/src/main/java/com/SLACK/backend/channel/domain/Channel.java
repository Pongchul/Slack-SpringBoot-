package com.SLACK.backend.channel.domain;

import com.SLACK.backend.common.BaseTimeEntity;
import com.SLACK.backend.member.domain.Member;
import com.SLACK.backend.workspace.domain.Workspace;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Channel extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Member host;

    @Embedded
    private ChannelName name;

    @Column(name = "private")
    private int privateNm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Workspace_id")
    private Workspace workspace;
}

