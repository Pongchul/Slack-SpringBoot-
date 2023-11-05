package com.SLACK.backend.mention.domain;

import com.SLACK.backend.common.BaseTimeEntity;
import com.SLACK.backend.member.domain.Member;
import com.SLACK.backend.workspace.domain.Workspace;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "tb_mention")
public class Mention extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Category category;


    @ManyToOne
    @JoinColumn(name = "workspace_id")
    private Workspace workspace;

    @OneToOne
    @JoinColumn(name = "sender_id")
    private Member sender;

    @OneToOne
    @JoinColumn(name = "receiver_id")
    private Member receiver;


}
