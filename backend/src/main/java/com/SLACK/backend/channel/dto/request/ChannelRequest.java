package com.SLACK.backend.channel.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class ChannelRequest {

    private final Long id;
    private final String name;
    private final boolean isPrivate;
    private final Long workspaceId;

}
