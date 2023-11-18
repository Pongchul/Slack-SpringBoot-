package com.SLACK.backend.channel.domain;

import com.SLACK.backend.common.BaseTimeEntity;
import com.SLACK.backend.workspace.domain.Workspace;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "tb_channel")
public class Channel extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private ChannelName name;

    @Column(name = "private")
    private boolean isPrivate;  // 비공개 여부

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workspace_id")
    private Workspace workspaceId;

    public Channel(Long id, ChannelName name, boolean isPrivate, Workspace workspaceId) {
        this.id = id;
        this.name = name;
        this.isPrivate = isPrivate;
        this.workspaceId = workspaceId;
    }

    public Channel(Long id, String name, boolean isPrivate, Workspace workspaceId) {
        this.id = id;
        this.name = ChannelName.from(name);
        this.isPrivate = isPrivate;
        this.workspaceId = workspaceId;
    }

    public String getName() {
        return name.getValue();
    }

    public List<Channel> getChannels() {
        List<Channel> channels = new ArrayList<>();
        channels.stream()
                .map(Channel::getChannels)
                .collect(Collectors.toUnmodifiableList());

        return channels;
    }
}

