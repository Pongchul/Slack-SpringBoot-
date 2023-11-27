package com.SLACK.backend.channel.dto.request;

import com.SLACK.backend.channel.domain.Channel;
import com.SLACK.backend.channel.domain.ChannelName;
import com.SLACK.backend.workspace.domain.Workspace;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ChannelRequest {

    private final Long id;
    private final String name;
    private final boolean isPrivate;
    private final Long workspaceId;

    public Long getChannelId() {
        return new Channel().getId();
    }

    public ChannelName getName() {
        return new ChannelName(name);
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public Long getWorkspaceId() {
        return new Workspace().getId();
    }

}
